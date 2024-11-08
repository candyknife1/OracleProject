
package com.lzw.service;

import com.lzw.pojo.PageBean;
import com.lzw.pojo.Student;
import java.util.List;

public interface StudentService {
    List<Student> selectAll();

    void add(Student var1);

    void deleteByIds(int[] var1);

    PageBean<Student> selectByPage(int var1, int var2);

    PageBean<Student> selectByPageAndCondition(int var1, int var2, Student var3);

    void deleteById(int var1);

    void update(Student var1);
}
