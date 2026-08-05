package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentMapper studentMapper;

    // 查询所有
    public List<Student> findAll() {
        log.info("Service层：执行查询所有学生");
        List<Student> list = studentMapper.selectList(null);
        log.info("Service层：查询到 {} 条数据", list.size());
        return list;
    }

    // 条件查询
    public List<Student> findStudents(String name, Integer minAge, Integer maxAge) {
        log.info("Service层：执行条件查询 - name={}, minAge={}, maxAge={}", name, minAge, maxAge);
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like("name", name);
        }
        if (minAge != null) {
            wrapper.ge("age", minAge);
        }
        if (maxAge != null) {
            wrapper.le("age", maxAge);
        }
        List<Student> list = studentMapper.selectList(wrapper);
        log.info("Service层：条件查询完成，返回 {} 条数据", list.size());
        return list;
    }

    // 分页查询（带缓存）
    @Cacheable(value = "students", key = "'page:' + #pageNum + ':' + #pageSize")
    public Page<Student> findPage(int pageNum, int pageSize) {
        log.info("Service层：执行分页查询 - pageNum={}, pageSize={}", pageNum, pageSize);
        Page<Student> page = new Page<>(pageNum, pageSize);
        Page<Student> result = studentMapper.selectPage(page, null);
        log.info("Service层：分页查询完成 - 总记录数={}, 总页数={}", result.getTotal(), result.getPages());
        return result;
    }

    // 根据 ID 查询
    public Student findById(int id) {
        log.info("Service层：根据ID查询 - id={}", id);
        Student student = studentMapper.selectById(id);
        if (student == null) {
            log.warn("Service层：未找到学生 - id={}", id);
        } else {
            log.info("Service层：查询到学生 - id={}, name={}", id, student.getName());
        }
        return student;
    }

    // 新增
    @CacheEvict(value = "students", allEntries = true)
    public void insert(Student student) {
        log.info("Service层：新增学生 - {}", student);
        studentMapper.insert(student);
        log.info("Service层：新增成功 - id={}", student.getId());
    }

    // 修改
    @CacheEvict(value = "students", allEntries = true)
    public void update(Student student) {
        log.info("Service层：修改学生 - id={}, {}", student.getId(), student);
        studentMapper.updateById(student);
        log.info("Service层：修改成功 - id={}", student.getId());
    }

    // 删除
    @CacheEvict(value = "students", allEntries = true)
    public void delete(int id) {
        log.info("Service层：删除学生 - id={}", id);
        studentMapper.deleteById(id);
        log.info("Service层：删除成功 - id={}", id);
    }
}