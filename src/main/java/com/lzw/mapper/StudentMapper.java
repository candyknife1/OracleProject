package com.lzw.mapper;

import com.lzw.pojo.Student;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    @Select({"select * from stu"})
    @ResultMap({"studentResultMap"})
    List<Student> selectAll();

    @Insert({"insert into stu values(null,#{name},#{Phone},#{email},#{age},#{status})"})
    @ResultMap({"studentResultMap"})
    void add(Student var1);

    void deleteByIds(@Param("ids") int[] var1);

//    @Select({"select  * from stu limit #{begin},#{size}"})
//    @ResultMap({"studentResultMap"})
//    List<Student> selectByPage(@Param("begin") int var1, @Param("size") int var2);

    @Select({
            "SELECT * FROM (",
            "   SELECT a.*, ROWNUM rnum FROM (",
            "       SELECT * FROM stu",
            "   ) a WHERE ROWNUM <= #{begin} + #{size}",
            ") WHERE rnum > #{begin}"
    })
    @ResultMap("studentResultMap")
    List<Student> selectByPage(@Param("begin") int begin, @Param("size") int size);


    @Select({"select count(*) from stu"})
    int selectTotalCount();

    List<Student> selectByPageAndCondition(@Param("begin") int var1, @Param("size") int var2, @Param("stu") Student var3);

    int selectTotalCountByCondition(Student var1);

    @Select({"delete from stu where id = #{id}"})
    void deleteById(int var1);

    void update(Student var1);
}

