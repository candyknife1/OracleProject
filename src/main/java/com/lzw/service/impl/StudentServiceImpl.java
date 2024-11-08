

package com.lzw.service.impl;

import com.lzw.mapper.StudentMapper;
import com.lzw.pojo.PageBean;
import com.lzw.pojo.Student;
import com.lzw.service.StudentService;
import com.lzw.util.SqlSessionFactoryUtils;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class StudentServiceImpl implements StudentService {
    SqlSessionFactory factory;

    public StudentServiceImpl() {
        new SqlSessionFactoryUtils();
        this.factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    }

    public List<Student> selectAll() {
        SqlSession sqlSession = this.factory.openSession();
        StudentMapper mapper = (StudentMapper)sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectAll();
        sqlSession.close();
        return students;
    }

    public void add(Student student) {
        SqlSession sqlSession = this.factory.openSession();
        StudentMapper mapper = (StudentMapper)sqlSession.getMapper(StudentMapper.class);
        mapper.add(student);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = this.factory.openSession();
        StudentMapper mapper = (StudentMapper)sqlSession.getMapper(StudentMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    public PageBean<Student> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = this.factory.openSession();
        StudentMapper mapper = (StudentMapper)sqlSession.getMapper(StudentMapper.class);
        int begin = (currentPage - 1) * pageSize;
        List<Student> rows = mapper.selectByPage(begin, pageSize);
        int totalCount = mapper.selectTotalCount();
        PageBean<Student> pageBean = new PageBean();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    public PageBean<Student> selectByPageAndCondition(int currentPage, int pageSize, Student student) {
        SqlSession sqlSession = this.factory.openSession();
        StudentMapper mapper = (StudentMapper)sqlSession.getMapper(StudentMapper.class);
        int begin = (currentPage - 1) * pageSize;
        String name = student.getName();
        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
            student.setName(name);
        }

        List<Student> rows = mapper.selectByPageAndCondition(begin, pageSize, student);
        int totalCount = mapper.selectTotalCountByCondition(student);
        PageBean<Student> pageBean = new PageBean();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    public void deleteById(int id) {
        SqlSession sqlSession = this.factory.openSession();
        StudentMapper mapper = (StudentMapper)sqlSession.getMapper(StudentMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    public void update(Student student) {
        SqlSession sqlSession = this.factory.openSession();
        StudentMapper mapper = (StudentMapper)sqlSession.getMapper(StudentMapper.class);
        mapper.update(student);
        sqlSession.commit();
        sqlSession.close();
    }
}
