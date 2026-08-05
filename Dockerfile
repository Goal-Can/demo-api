# 1. 指定基础镜像（包含 JDK 17 的轻量级 Linux）
FROM openjdk:17-jdk-slim

# 2. 设置容器内的工作目录（相当于 cd /app）
WORKDIR /app

# 3. 把本地打包好的 jar 包复制到容器内的 /app 目录，并重命名为 app.jar
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# 4. 暴露容器内的 8081 端口（只是声明，实际映射在 docker run 时指定）
EXPOSE 8081

# 5. 容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8081"]