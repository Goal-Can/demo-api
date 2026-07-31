package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "学生管理", description = "学生信息的增删改查接口")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Operation(summary = "查询所有学生")
    @GetMapping
    public Result<List<Student>> findAll() {
        return Result.success(studentService.findAll());
    }

    @Operation(summary = "根据ID查询学生")
    @GetMapping("/{id}")
    public Result<Student> findById(@PathVariable int id) {
        Student s = studentService.findById(id);
        if (s == null) {
            return Result.error("学生不存在");
        }
        return Result.success(s);
    }

    @Operation(summary = "新增学生")
    @PostMapping
    public Result<String> insert(@Valid @RequestBody Student student) {
        studentService.insert(student);
        return Result.success();
    }

    @Operation(summary = "修改学生")
    @PutMapping("/{id}")
    public Result<String> update(@PathVariable int id, @Valid @RequestBody Student student) {
        student.setId(id);
        studentService.update(student);
        return Result.success();
    }

    @Operation(summary = "删除学生")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable int id) {
        studentService.delete(id);
        return Result.success();
    }
}