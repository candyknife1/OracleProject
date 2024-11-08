
package com.lzw.service.impl;

import com.lzw.mapper.UserMapper;
import com.lzw.pojo.User;
import com.lzw.service.UserService;
import com.lzw.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserServiceImpl implements UserService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public UserServiceImpl() {
    }

    public User login(String username, String password) {
        SqlSession sqlSession = this.factory.openSession();
        UserMapper mapper = (UserMapper)sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(username, password);
        sqlSession.close();
        return user;
    }

    public boolean register(User user) {
        SqlSession sqlSession = this.factory.openSession();
        UserMapper mapper = (UserMapper)sqlSession.getMapper(UserMapper.class);
        User u = mapper.selectByUsername(user.getUsername());
        if (u == null) {
            mapper.add(user);
            sqlSession.commit();
        }

        sqlSession.close();
        return u == null;
    }

    public boolean selectByName(String name) {
        SqlSession sqlSession = this.factory.openSession();
        UserMapper mapper = (UserMapper)sqlSession.getMapper(UserMapper.class);
        User u = mapper.selectByUsername(name);
        sqlSession.close();
        return u == null;
    }

    public void updatePassword(String username, String password) {
        SqlSession sqlSession = this.factory.openSession();
        UserMapper mapper = (UserMapper)sqlSession.getMapper(UserMapper.class);
        mapper.updatePassword(username, password);
        sqlSession.commit();
        sqlSession.close();
    }

    public void updateName(String username, String updateName) {
        SqlSession sqlSession = this.factory.openSession();
        UserMapper mapper = (UserMapper)sqlSession.getMapper(UserMapper.class);
        mapper.updateName(username, updateName);
        sqlSession.commit();
        sqlSession.close();
    }
}
