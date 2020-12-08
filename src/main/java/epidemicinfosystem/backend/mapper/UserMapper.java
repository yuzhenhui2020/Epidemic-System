package epidemicinfosystem.backend.mapper;

import epidemicinfosystem.backend.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * mapper的具体表达式
 */
@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface UserMapper {

    /**
     * 查询用户名是否存在，若存在，不允许注册
     * 注解@Param(value) 若value与可变参数相同，注解可省略
     * 注解@Results  列名和字段名相同，注解可省略
     * @param username
     * @return
     */
    @Select(value = "select usr_name,password from user where usr_name=#{username}")
    @Results
            ({@Result(property = "usr_name",column = "usr_name"),
                    @Result(property = "password",column = "password")})
    User findUserByName(@Param("username") String username);

    /**
     * 注册  插入一条user记录
     * @param user
     * @return
     */
    @Insert("insert into user(usr_name,password) values(#{usr_name},#{password})")
    void regist(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    @Select("select usr_id from user where usr_name = #{usr_name} and password = #{password}")
    Integer login(User user);
}


