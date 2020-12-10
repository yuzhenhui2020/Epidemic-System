package epidemicinfosystem.backend.mapper;

import epidemicinfosystem.backend.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface UserMapper {


    @Select(value = "select userName,password,type,permission from user where userName=#{userName}")
    @Results
            ({@Result(property = "userName",column = "userName"),
                    @Result(property = "password",column = "password"),
                    @Result(property = "type",column = "type"),
                    @Result(property = "permission",column = "permission")
            })
    User findUserByName(@Param("userName") String userName);

    @Insert("insert into user values(#{userName},#{password},#{type},#{secureQuestion},#{secureAnswer},0)")
    void regist(User user);

    @Select("select * from user where userName = #{userName} and password = #{password}")
    User login(User user);

    @Select("select secureQuestion from user where userName=#{userName}")
    String getSecureQuestion(User user);

    @Select("select secureAnswer from user where userName=#{userName}")
    String getSecureAnswer(String userName);

    @Update("update user set password=#{password} where userName=#{userName}")
    void changePassword(String userName,String password);

    @Update("update user set permission=1 where userName=#{userName}")
    void getPermission(String userName);
}


