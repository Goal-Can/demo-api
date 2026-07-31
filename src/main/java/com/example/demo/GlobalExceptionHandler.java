package com.example.demo;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理参数校验失败异常（@Valid 校验不通过时触发）
     * 返回具体字段的错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Map<String, String>> handleValidation(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return Result.error("参数校验失败：" + errors);
    }

    /**
     * 处理查询数据不存在异常（如 GET /students/999）
     * 返回友好的提示信息
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public Result<String> handleEmptyResult(EmptyResultDataAccessException e) {
        return Result.error("数据不存在，请检查查询条件");
    }

    /**
     * 处理数据库操作异常（如连接失败、SQL 错误）
     * 不暴露具体 SQL 细节，只返回通用提示
     */
    @ExceptionHandler(DataAccessException.class)
    public Result<String> handleDataAccess(DataAccessException e) {
        // 开发阶段可以打印日志，方便排查
        e.printStackTrace();
        return Result.error("数据库操作失败，请检查网络或重试");
    }

    /**
     * 处理其他所有未预料到的异常（兜底）
     * 返回通用错误信息，不暴露技术细节
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        // 开发阶段打印日志，方便定位
        e.printStackTrace();
        return Result.error("系统内部错误，请稍后重试");
    }
}