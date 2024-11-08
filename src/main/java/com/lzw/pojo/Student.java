package com.lzw.pojo;

public class Student {
    private Integer id;
    private String name;
    private String Phone;
    private String email;
    private Integer age;
    private Integer status;

    public Student(Integer id, String name, String phone, String email, Integer age, Integer status) {
        this.id = id;
        this.name = name;
        this.Phone = phone;
        this.email = email;
        this.age = age;
        this.status = status;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String toString() {
        return "Student{id=" + this.id + ", name='" + this.name + "', age=" + this.age + ", Phone='" + this.Phone + "', email='" + this.email + "', status=" + this.status + "}";
    }

    public String getStatusStr() {
        if (this.status == null) {
            return "未知";
        } else {
            return this.status == 0 ? "禁用" : "启用";
        }
    }
}

