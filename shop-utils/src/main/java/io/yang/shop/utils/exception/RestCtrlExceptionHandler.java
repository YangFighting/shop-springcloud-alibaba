package io.yang.shop.utils.exception;


import io.yang.shop.utils.constants.HttpCode;
import io.yang.shop.utils.resp.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhangyang
 * @date 2022/07/20 23:17
 * @description 全局异常捕获器
 **/
@RestControllerAdvice
public class RestCtrlExceptionHandler {
    private final Logger logger =  LoggerFactory.getLogger(RestCtrlExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e){
        logger.error("服务器异常：{0}", e);
        return new Result<String>(HttpCode.FAILURE, "执行失败", e.getMessage());
    }

}
