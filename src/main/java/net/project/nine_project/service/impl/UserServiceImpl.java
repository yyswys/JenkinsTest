package net.project.nine_project.service.impl;

import net.project.nine_project.domain.LoginRequest;
import net.project.nine_project.domain.User;
import net.project.nine_project.mapper.UserMapper;
import net.project.nine_project.service.UserService;
import net.project.nine_project.utils.CommonUtils;
import net.project.nine_project.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public int save(Map<String,String> userInfo) {
        User user=parseToUser(userInfo);
        if(user==null){
            //信息不全
            return -1;
        }
        if(userMapper.findByAccount(user.getAccount())!=null){
            //帐号重复
            return -2;
        }
        //成功则返回1，生效行数
        return userMapper.save(user);
    }
    //前端传来的数据转换成user
    private User parseToUser(Map<String, String> userInfo) {
        if(userInfo.containsKey("account")&&userInfo.containsKey("password")&&userInfo.containsKey("name")){
            User user=new User();
            user.setName(userInfo.get("name"));
            user.setHeadImg(getRandomImg());
            user.setAccount(userInfo.get("account"));
            user.setCreateTime(new Date());///////////////////////////////////////////////////
            String password=userInfo.get("password");
            //MD5加密
            user.setPassword(CommonUtils.MD5(password));
            user.setMoney(0);
            user.setOrderNumber(0);
            return user;
        }else {
            return null;
        }
    }

    /**
     * 放在CDN上的随机头像
     */
    private static final String [] headImg = {
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/12.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/11.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/13.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/14.jpeg",
            "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/default/head_img/15.jpeg"
    };

    private String getRandomImg(){
        int size =  headImg.length;
        Random random = new Random();
        int index = random.nextInt(size);
        return headImg[index];
    }
    @Override
    public String findByAccountAndPwd(String account, String pwd) {
        User user=userMapper.findByAccountAndPwd(account,CommonUtils.MD5(pwd));
        //User user=userMapper.findByAccountAndPwd(account,pwd);
        if(user==null){
            return null;
        }else {
            //user.setLastLoTime(new Date());
            String token = JWTUtils.geneJsonWebToken(user);
            return token;
        }
    }

    @Override
    public User loginByAccountAndPwd(String account, String pwd) {
        User user=userMapper.findByAccountAndPwd(account,CommonUtils.MD5(pwd));

        //User user=userMapper.loginByAccountAndPwd(account,pwd);
        //user.setLastLoTime(new Date());
        return user;
    }

    public int updateByLogin(User user)
    {
        if(user!=null)
        {
            user.setLastLoTime(new Date());
            userMapper.updateByLogin(user);
            return 0;
        }
        //return userMapper.updateByLogin(user);
        return -1;
    }

    @Override
    public User findByUserId(Integer userId) {
        User user=userMapper.findByUserId(userId);
        return user;
    }

    @Override
    public int completeInfo(User user)
    {
        return userMapper.changeMoney(user);
    }
}