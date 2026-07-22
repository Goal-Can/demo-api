package com.example.demo;

public class Result<T> {
    private Integer code;    // 状态码：200成功，400失败
    private String message;  // 提示信息
    private T data;          // 实际数据（可以是 List 或单个对象）

    // 构造方法私有，通过静态方法创建
    private Result() {}

    // 成功（带数据）
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.code = 200;
        r.message = "操作成功";
        r.data = data;
        return r;
    }

    // 成功（无数据，用于删除、更新）
    public static <T> Result<T> success() {
        Result<T> r = new Result<>();
        r.code = 200;
        r.message = "操作成功";
        return r;
    }

    // 失败
    public static <T> Result<T> error(String message) {
        Result<T> r = new Result<>();
        r.code = 400;
        r.message = message;
        return r;
    }

    // Getter & Setter（必须要有，否则 Jackson 无法序列化）
    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}