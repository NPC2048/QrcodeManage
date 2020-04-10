# 基础镜像
FROM maven:3.5.4-jdk-8
# 维护者信息
MAINTAINER liangyuelong "npc2048@qq.com"

RUN echo "-------------------- 环境配置 -------------------"

# 暴露端口
EXPOSE 8091
# 环境编码
ENV LANG C.UTF-8

# 运行项目
#ENTRYPOINT ["mvn", "clean", "spring-boot:run", "-Dspring-boot.run.profiles=prod", "-Dmaven.test.skip=true"]