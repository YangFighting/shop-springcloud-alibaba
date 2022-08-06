package io.yang.shop.io.yang.shop.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author zhangyang
 * @date 2022/08/06 22:08
 * @description 网关配置类
 **/
@Configuration
public class GatewayConfig {
    private final List<ViewResolver> viewResolvers;

    private final ServerCodecConfigurer serverCodecConfigurer;

    @Value("${spring.cloud.gateway.discovery.locator.route-id-prefix}")
    private String routeIdPrefix;

    public GatewayConfig(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    private String getResource(String targetServiceName) {
        if (routeIdPrefix == null) {
            routeIdPrefix = "";
        }
        return routeIdPrefix.concat(targetServiceName);
    }

    private GatewayFlowRule getGatewayFlowRule(String resource){
        //传入资源名称生成GatewayFlowRule
        GatewayFlowRule gatewayFlowRule = new GatewayFlowRule(resource);
        //限流阈值
        gatewayFlowRule.setCount(1);
        //统计的时间窗口，单位为
        gatewayFlowRule.setIntervalSec(1);
        return gatewayFlowRule;
    }

    /**
     * 配置初始化的限流参数
     */
    private void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        //用户微服务网关
        rules.add(this.getGatewayFlowRule(getResource("server-user")));
        //商品微服务网关
        rules.add(this.getGatewayFlowRule(getResource("server-product")));
        //订单微服务网关
        rules.add(this.getGatewayFlowRule(getResource("server-order")));
        //加载规则
        GatewayRuleManager.loadRules(rules);

    }

    @PostConstruct
    public void init() {
        this.initGatewayRules();
        this.initBlockHandlers();
    }

    /**
     * 自定义限流异常页面
     */
    private void initBlockHandlers() {
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                HashMap<Object, Object> map = new HashMap<>();
                map.put("code", 1001);
                map.put("codeMsg", "接口限流");
                return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);

    }

    /**
     * @return 配置限流的异常处理器
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }
}
