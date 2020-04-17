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
###### 运行项目

1. 拉取项目
2. 导入项目到 idea
>  File -> Open -> 选择该项目 -> OK -> 等待项目导入完成
3. 找到 QrcodeApplication, 运行
> 路径: src/main/java/com.liangyuelong.qrcode.QrcodeApplication

###### 打包

生产环境打包前需要在 src/main/resources/ 下新建 application-prod.yml, 对生产环境的数据源等进行配置, 配置后再打包

* 开发环境
> mvn clean package
* 生产环境
> mvn clean package -P prod

***
###### 部署

maven 编译部署

1. 拉取项目
2. 在项目根目录运行命令:
> mvn spring-boot:run -Dspring-boot.run.profiles=prod

spring-boot.run.profiles 如果不指定则默认为 dev, 默认值可以在 application.yml 中修改
需要服务器安装 java、maven 环境

打包后运行 jar 包
1.打包, 然后把 target 下的 qrcode.jar 包传到服务器
2.在服务器的 qrcode.jar 包目录下, 运行
> java -jar qrcode.jar

***
###### Docker 部署

建议这种方式, 打包后把 jar 包与 Dockerfile、docker-compose.yml 上传到服务器构建容器运行

或者在服务器拉取源代码, 打包后运行也行
1. 打包
2. 运行项目
* 构建容器
> docker-compose build
* 运行容器, -d 表示后台运行
> docker-compose up -d 
* 查看日志, qrcode-manage 为 docker-compose.yml 中定义的容器名称, 可自己更改
> docker logs -f qrcode-manage
 
需要服务器安装 docker、docker-compose 环境, 如果在服务器打包的话需要安装 java、maven 环境 
***
###### 演示地址: [http://www.liangyuelong.com:8091](http://www.liangyuelong.com:8091)

###### GitHub地址：[https://github.com/npc2048/qrcodemanage](https://github.com/npc2048/qrcodemanage)
