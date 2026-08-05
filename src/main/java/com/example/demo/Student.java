package com.example.demo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.io.Serializable;
// 其他 import 不变

@TableName("students")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    // 其余内容不变

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 1, message = "年龄必须大于0")
    private Integer age;

    @NotNull(message = "成绩不能为空")
    @Min(value = 0, message = "成绩不能小于0")
    private Double score;

    private String gender;

    // 无参构造
    public Student() {}

    // 带 id 构造
    public Student(Integer id, String name, Integer age, Double score, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
        this.gender = gender;
    }

    // 不带 id 构造
    public Student(String name, Integer age, Double score, String gender) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.gender = gender;
    }

    // Getter & Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}