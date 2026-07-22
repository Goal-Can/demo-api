package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

@Autowired
    private StudentService studentService;

    @GetMapping
    public Result<List<Student>> findAll() {
        System.out.println("1.Controller接收到请求");
        return Result.success(studentService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Student> findById(@PathVariable int id) {
        Student s = studentService.findById(id);
        if (s == null) {
            return Result.error("学生不存在");
        }
        return Result.success(s);
    }

    @PostMapping
    public Result<String> insert(@Valid @RequestBody Student student) {
        studentService.insert(student);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable int id, @Valid @RequestBody Student student) {
        student.setId(id);
        studentService.update(student);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable int id) {
        studentService.delete(id);
        return Result.success();
    }
}
