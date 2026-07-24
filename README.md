# 学生管理系统 API

基于 Spring Boot + JdbcTemplate + MySQL 实现的 RESTful API 项目。

## 技术栈
- Spring Boot 3.2.5
- JdbcTemplate
- MySQL 8.0
- Maven
- Git

## 功能列表
- 查询所有学生：GET /students
- 查询单个学生：GET /students/{id}
- 新增学生：POST /students
- 修改学生：PUT /students/{id}
- 删除学生：DELETE /students/{id}

## 统一返回格式
所有接口返回统一的 Result 结构：
{
"code": 200,
"message": "操作成功",
"data": {}
}

## 本地运行
1. 修改 application.properties 中的数据库密码
2. 运行 DemoApplication.java
3. 访问 http://localhost:8080/students