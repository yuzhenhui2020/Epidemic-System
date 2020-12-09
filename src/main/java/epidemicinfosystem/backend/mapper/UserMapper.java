package epidemicinfosystem.backend.mapper;

import epidemicinfosystem.backend.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface UserMapper {


    @Select(value = "select usr_name,password from user where usr_name=#{username}")
    @Results
            ({@Result(property = "usr_name",column = "usr_name"),
                    @Result(property = "password",column = "password")})
    User findUserByName(@Param("username") String username);

    @Insert("insert into user values(null,#{usr_name},#{password},#{type},#{secure_question},#{secure_answer},0)")
    void regist(User user);

    @Select("select usr_id from user where usr_name = #{usr_name} and password = #{password}")
    Integer login(User user);

    @Select("select secure_question from user where usr_name=#{usr_name}")
    String getSecureQuestion(User user);


}


