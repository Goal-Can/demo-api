# 📚 学生管理系统 API

基于 Spring Boot 3.2.5 + JdbcTemplate + MySQL 8.0 构建的学生信息管理 RESTful API 项目，支持完整的增删改查功能，并已部署至阿里云服务器，可公开访问。

---

## 🚀 在线访问

- **前端管理页面**：[http://8.156.74.250:8081/index2.html](http://8.156.74.250:8081/index2.html)
- **后端接口文档**：[http://8.156.74.250:8081/doc.html](http://8.156.74.250:8081/doc.html)
- **接口测试地址**：[http://8.156.74.250:8081/students](http://8.156.74.250:8081/students)

---

## 📦 技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Spring Boot | 3.2.5 | 后端框架 |
| JdbcTemplate | - | 数据库操作 |
| MySQL | 8.0 | 数据存储 |
| Knife4j | 4.3.0 | API 接口文档 |
| Maven | 3.9.x | 项目构建 |
| Git | 2.40.x | 版本控制 |
| 阿里云 ECS | - | 服务器部署 |

---

## 🎯 功能列表

| 功能 | 请求方式 | 接口地址 | 说明 |
|------|----------|----------|------|
| 查询所有学生 | GET | `/students` | 返回所有学生信息列表 |
| 查询单个学生 | GET | `/students/{id}` | 根据 ID 查询学生信息 |
| 新增学生 | POST | `/students` | 添加一名学生 |
| 修改学生 | PUT | `/students/{id}` | 根据 ID 修改学生信息 |
| 删除学生 | DELETE | `/students/{id}` | 根据 ID 删除学生 |

---

## 📁 项目结构
src/main/java/com/example/demo/
├── DemoApplication.java        # 项目启动类
├── Student.java                 # 学生实体类
├── StudentDao.java              # 数据访问层（JdbcTemplate）
├── StudentService.java          # 业务逻辑层
├── StudentController.java       # 控制器层（REST API）
├── Result.java                  # 统一返回格式
├── GlobalExceptionHandler.java  # 全局异常处理
└── resources/static/
└── index2.html              # 前端管理页面


---

## ✅ 统一返回格式

所有接口返回统一 JSON 结构：

```json
{
  "code": 200,
  "message": "操作成功",
  "data": { ... }
}


code 含义
200 请求成功
400 参数错误或业务异常
500 服务器内部错误

## 📝 作者

- **姓名**：蒋灿
- **邮箱**：jcan@stu.xhu.edu.cn（填你自己的实际邮箱）
- **GitHub**：[Goal-Can](https://github.com/Goal-Can)
- **项目时间**：2026年7月

## 📄 许可证

本项目采用 [MIT License](https://opensource.org/licenses/MIT) 开源协议，仅供学习交流使用。