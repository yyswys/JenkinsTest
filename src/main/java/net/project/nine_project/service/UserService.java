package net.project.nine_project.service;

import net.project.nine_project.domain.LoginRequest;
import net.project.nine_project.domain.User;
//import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {
    int save(Map<String,String> userInfo);

    String findByAccountAndPwd(String account, String password);

    User findByUserId(Integer userId);

    User loginByAccountAndPwd(String account, String password);

    int updateByLogin(User user);

    int completeInfo(User user);
}