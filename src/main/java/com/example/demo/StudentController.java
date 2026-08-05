package com.example.demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    // 查询所有 + 条件查询（姓名模糊、年龄范围）
    @GetMapping
    public Result<List<Student>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {
        log.info("接收到查询请求: name={}, minAge={}, maxAge={}", name, minAge, maxAge);
        List<Student> list;
        if ((name == null || name.isEmpty()) && minAge == null && maxAge == null) {
            list = studentService.findAll();
        } else {
            list = studentService.findStudents(name, minAge, maxAge);
        }
        log.info("查询完成，返回 {} 条数据", list.size());
        return Result.success(list);
    }

    // 分页查询
    @GetMapping("/page")
    public Result<Page<Student>> page(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        log.info("接收到分页请求: page={}, size={}", page, size);
        Page<Student> pageData = studentService.findPage(page, size);
        log.info("分页查询完成，总记录数: {}", pageData.getTotal());
        return Result.success(pageData);
    }

    // 根据 ID 查询
    @GetMapping("/{id}")
    public Result<Student> findById(@PathVariable int id) {
        log.info("接收到根据ID查询请求: id={}", id);
        Student s = studentService.findById(id);
        if (s == null) {
            log.warn("学生不存在: id={}", id);
            return Result.error("学生不存在");
        }
        return Result.success(s);
    }

    // 新增
    @PostMapping
    public Result<String> insert(@Valid @RequestBody Student student) {
        log.info("接收到新增请求: {}", student);
        studentService.insert(student);
        log.info("新增成功: {}", student);
        return Result.success();
    }

    // 修改
    @PutMapping("/{id}")
    public Result<String> update(@PathVariable int id, @Valid @RequestBody Student student) {
        log.info("接收到修改请求: id={}, student={}", id, student);
        student.setId(id);
        studentService.update(student);
        log.info("修改成功: id={}", id);
        return Result.success();
    }

    // 删除
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable int id) {
        log.info("接收到删除请求: id={}", id);
        studentService.delete(id);
        log.info("删除成功: id={}", id);
        return Result.success();
    }
}