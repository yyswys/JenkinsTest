package net.project.nine_project.mapper;


import net.project.nine_project.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    //@Param用作参数的对应，多参数使用，这里可加可不加
    User findByAccount(@Param("account") String account);

    int save(User user);

    User findByAccountAndPwd(@Param("account") String account, @Param("password") String password);

    User findByUserId(Integer userId);

    int updateByLogin(User user);

    int changeMoney(User user);
}
