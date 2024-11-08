import com.lzw.mapper.StudentMapper;
import com.lzw.pojo.Student;
import com.lzw.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/11/7 23:27
 * @Version: 1.0
 */


public class TestDemo {
    SqlSessionFactory factory;

    @Test
    public void test1(){
        new SqlSessionFactoryUtils();
        this.factory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = this.factory.openSession();
        StudentMapper mapper = (StudentMapper)sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectAll();
        sqlSession.close();
        System.out.println(students);
    }

}
