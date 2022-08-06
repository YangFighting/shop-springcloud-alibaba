# shop-springcloud-alibaba

## 数据库初始化

### 新建数据库表

```sql
CREATE DATABASE shop;

CREATE TABLE IF NOT EXISTS `t_user`(
   `id` BIGINT UNSIGNED,
   `t_username` VARCHAR(50),
   `t_password` VARCHAR(64),
   `t_phone` VARCHAR(20),
   `t_address` VARCHAR(255),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_product`(
   `id` BIGINT UNSIGNED,
   `t_pro_name` VARCHAR(50),
   `t_pro_price` DECIMAL(10,2),
   `t_pro_stock` INT(11),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `t_order`(
   `id` BIGINT UNSIGNED,
   `t_user_id` BIGINT(20),
   `t_user_name` VARCHAR(50),
   `t_phone` VARCHAR(20),
   `t_address` VARCHAR(255),
   `t_total_price` DECIMAL(10,2),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `t_order_item`(
   `id` BIGINT UNSIGNED,
   `t_order_id` BIGINT(20),
   `t_pro_id` BIGINT(20),
   `t_pro_name` VARCHAR(50),
   `t_pro_price` DECIMAL(10,2),
   `t_number` INT(11),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


```

### 添加测试数据

```sql
INSERT INTO `shop`.`t_user`(`id`, `t_username`, `t_password`, `t_phone`, `t_address`) VALUES (1001, 'binghe', 'c26be8aaf53b15054896983b43eb6a65', '13212345678', '北京');


INSERT INTO `shop`.`t_product`(`id`, `t_pro_name`, `t_pro_price`, `t_pro_stock`) VALUES (1001, '华为', 2399.00, 100);
INSERT INTO `shop`.`t_product`(`id`, `t_pro_name`, `t_pro_price`, `t_pro_stock`) VALUES (1002, '小米', 1999.00, 100);
INSERT INTO `shop`.`t_product`(`id`, `t_pro_name`, `t_pro_price`, `t_pro_stock`) VALUES (1003, 'iphone', 4999.00, 100);
```



订单微服务测试

http://localhost:8080/order/submit_order?productId=1001&userId=1001&count=1001

## 服务治理

### nacas下载安装

[nacos-server-1.4.3软件安装包](https://heyangyi.com/archives/nacos-server-143-xia-zai-fen-xiang)

1. 解压压缩包

   unzip -o nacos-server-1.4.3.zip

2. 建立软连接

   ln -s  /root/nacos /usr/local/nacos

3. 启动 

    bash /root/nacos/bin/startup.sh -m standalone

4. 访问 http://47.97.124.83:8848/nacos/index.html#/login

5. 输入默认账号密码：nacos/nacos 

[nacos2.1.0安装包](https://blog.csdn.net/huaquangui/article/details/124767123)

- 解压压缩包

```shell
tar -zxf nacos-server-2.1.0.tar.gz 
```

- 启动`nacos`

```shell
bash /root/nacos-2.1.0/bin/startup.sh -m standalone
```

参考：

https://blog.csdn.net/muriyue6/article/details/119845925

#### 使用2.1.0 遇到的问题

启动时报错 Client not connected,current status:STARTING 

需要开放 9846和 9849 两个端口

https://zhuanlan.zhihu.com/p/482216338



## 整合Sentinel实现限流与容错 

### Sentinel 安装与使用

链接 https://github.com/alibaba/Sentinel/releases  

#### Liunx 下载命令

```shell
wget https://github.com/alibaba/Sentinel/releases/download/1.8.4/sentinel-dashboard-1.8.4.jar
```



#### 后台启动Sentinel控制台  

```shell
nohup java -Dserver.port=8888 -Dcsp.sentinel.dashboard.server=localhost:8888 -Dproject.name=sentinel-dashboard -jar /root/sentinel-dashboard-1.8.4.jar > /var/log/sentinel.log /dev/null &
```

启动后在浏览器中输入 http://47.97.124.83:8888 访问Sentinel控制台  

#### window 启动

```powershell
java -Dserver.port=8888 -Dcsp.sentinel.dashboard.server=localhost:8888 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.4.jar  
```

启动后在浏览器中输入 http://localhost:8888 访问Sentinel控制台  



输入默认账号密码：  sentinel/sentinel

在浏览器中输入 http://localhost:8080/order/test_sentinel 访问在订单微服务中新增的接口



## 遇到的问题

### 项目共享依赖时，无法引用工具模块的类

删掉父项目中的src文件

https://blog.csdn.net/qq_23095607/article/details/115266818



### idea 创建mybatis的xml文件

https://blog.csdn.net/q15102780705/article/details/114687269



### java:无效的目标发行版: 11

https://zhuanlan.zhihu.com/p/348660719



### jMeter 下载与使用

官网下载：

https://jmeter.apache.org/download_jmeter.cgi

https://www.jianshu.com/p/0e4daecc8122



### 项目集成Sentinel 

Failed to fetch metric from

https://blog.csdn.net/weixin_44953395/article/details/122791379



### 初步整合SpringCloud Gateway  

1. **reactor.netty.http.server.HttpServer     : [id: 0xd768d665, L:/0:0:0:0:0:0:0:1:10001 - R:/0:0:0:0:0:0:0:1:14535]** 

https://www.cnblogs.com/wypzpz/p/12940833.html

版本引用

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>

```

```xml
<parent>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.2.6.RELEASE</version>
</parent>
```

spring-cloud-starter-gateway 版本来自 spring-cloud.version

2. **compatible version of reactor.netty.resources.ConnectionProvider$Builder**

   目前Spring Boot的版本是 2.2.6.RELEASE，和  Spring Cloud 的版本（ Hoxton.SR12 ，spring-cloud-starter-gateway 2.2.9.RELEASE）有冲突，需要提高 Spring Boot 的版本，通过 dependencyManagement 的方式来更改 Spring Boot  的版本就可以解决。另外，还需要先启动shop-user等模块

   **为什么 dependencyManagement 申明 Spring Boot 后，shop-gateway能正常启动，但是shop-gateway 的依赖还是 2.2.6？？** 
   
   

####  版本选择

Spring Cloud Alibaba

Spring Boot 

Spring Cloud 

Nacos

https://blog.csdn.net/qq_38637558/article/details/114448690

https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

