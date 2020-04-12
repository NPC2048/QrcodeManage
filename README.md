## QrcodeManage 二维码管理

根据 xxx, 写了一个 Java 版的

框架 `Spring Boot` + `Spring Security` + `Mybatis-Plus` + `MySql`

***
###### 功能：

1. 登录注册等
2. 二维码管理

***
###### 开发环境

1. JDK 1.8
2. IDEA 2019.3
3. MYySQL 8.0.19

***
###### 部署

1. 拉取项目
2. 在项目根目录运行命令:
> mvn spring-boot:run -Dspring-boot.run.profiles=prod

spring-boot.run.profiles 如果不指定则默认为 dev, 默认值可以在 application.yml 中修改

***
###### Docker 部署
建议打包后把 jar 包与 Dockerfile、docker-compose.yml 上传到服务器构建容器运行
1. 
 
***
###### 演示地址: [http://www.liangyuelong.com:8888](http://www.liangyuelong.com:8888)

###### GitHub地址：[https://github.com/npc2048/qrcodemanage](https://github.com/npc2048/qrcodemanage)
