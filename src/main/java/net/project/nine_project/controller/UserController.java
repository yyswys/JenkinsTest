package net.project.nine_project.controller;

import net.project.nine_project.domain.*;
import net.project.nine_project.service.StrategyService;
import net.project.nine_project.service.UserService;
import net.project.nine_project.utils.CommonUtils;
import net.project.nine_project.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    StrategyService strategyService;

    @Override
    public String toString() {
        return "UserController{" +
                "userService=" + userService +
                '}';
    }

    @RequestMapping("register")   //此处用GetMapping而不能用PostMapping
    public JsonData register(@RequestBody Map<String,String> userInfo){
        int rows=userService.save(userInfo);
        if(rows==-1){
            return JsonData.buildError(-1,"注册失败，信息缺失");
        }
        if(rows==-2){
            return JsonData.buildError(-1,"注册失败，帐号已存在");
        }
        return JsonData.buildSuccess();
    }


    @RequestMapping("login")   //此处用GetMapping而不能用PostMapping
//这边接收参数的形式对照注册的map，两种方式都可以
    public JsonData login(@RequestBody LoginRequest loginRequest,HttpServletResponse response){

        response.setHeader("Access-Control-Allow-Origin","*");
        String account=loginRequest.getAccount();
        String password=loginRequest.getPassword();
        String token=userService.findByAccountAndPwd(account,password);
        System.out.println(token);
        User user=userService.loginByAccountAndPwd(account,password);//////////
        if(userService.updateByLogin(user)==-1)
        {

            return JsonData.buildError("登录失败，账号密码错误");
        }
        return token == null?JsonData.buildError("登录失败，账号密码错误"):JsonData.buildSuccess(token);
    }


    @GetMapping("/find_by_token")
    public JsonData findUserInfoByToken(HttpServletRequest request){
        //这里是拦截器放行后给过来的user_id
        Integer userId=(Integer) request.getAttribute("user_id");
        System.out.println(userId);
        if(userId==null){
            return JsonData.buildError("查询失败");
        }
        User user=userService.findByUserId(userId);
        return JsonData.buildSuccess(user);
    }

    @RequestMapping("complete_info")
//这边接收参数的形式对照注册的map，两种方式都可以
    public JsonData completeInfo(@RequestBody UserInfoRequest userInfoRequest,HttpServletRequest request)
    {
        System.out.println(userInfoRequest.getAge());
        Integer userId=(Integer) request.getAttribute("user_id");
        if(userId==null)
        {
            return JsonData.buildError("查询失败");
        }
        User user=userService.findByUserId(userId);
        if(userInfoRequest.getSex()!=null) {
            user.setSex(userInfoRequest.getSex());
        }
        if(userInfoRequest.getAge()!=0) {
            user.setAge(userInfoRequest.getAge());
        }
        if(userInfoRequest.getJob()!=null) {
            user.setJob(userInfoRequest.getJob());
        }
        if(userInfoRequest.getName()!=null) {
            user.setName(userInfoRequest.getName());
        }
        userService.completeInfo(user);
        String password=CommonUtils.MD5(userInfoRequest.getPasswordOld());
        String password1=CommonUtils.MD5(userInfoRequest.getPasswordNew());
        if(password==null||password1==null)
        {
            return JsonData.buildSuccess(user);
        }
        if(!password.equals(user.getPassword()))
        {
            return JsonData.buildError("原密码输入错误！");
        }
        if(CommonUtils.MD5(userInfoRequest.getPasswordNew()).equals(user.getPassword()))
        {
            return JsonData.buildError("新密码不可与原密码相同！");
        }
        user.setPassword(CommonUtils.MD5(userInfoRequest.getPasswordNew()));
        userService.completeInfo(user);
        return JsonData.buildSuccess(user);
    }

    @GetMapping("change_password")   //此处用GetMapping而不能用PostMapping
//这边接收参数的形式对照注册的map，两种方式都可以
    public JsonData changePassword(@RequestBody PasswordRequest passwordRequest, HttpServletRequest request)
    {
        Integer userId=(Integer) request.getAttribute("user_id");
        if(userId==null)
        {
            return JsonData.buildError("查询失败");
        }
        User user=userService.findByUserId(userId);
        String password=CommonUtils.MD5(passwordRequest.getPasswordOld());
        if(!password.equals(user.getPassword()))
        {
            return JsonData.buildError("原密码输入错误！");
        }
        if(CommonUtils.MD5(passwordRequest.getPasswordNew()).equals(user.getPassword()))
        {
            return JsonData.buildError("新密码不可与原密码相同！");
        }
        user.setPassword(CommonUtils.MD5(passwordRequest.getPasswordNew()));
        userService.completeInfo(user);
        return JsonData.buildSuccess();
    }

    @GetMapping("save_strategy")   //此处用GetMapping而不能用PostMapping
    public JsonData saveStrategy(@RequestBody StrategyRequest strategyRequest,HttpServletRequest request){
        Integer userId=(Integer) request.getAttribute("user_id");
        if(userId==null)
        {
            return JsonData.buildError("查询失败");
        }
        User user=userService.findByUserId(userId);
        if(user==null)
        {
            return JsonData.buildError("查询失败");
        }

        int rows=strategyService.saveStrategy(userId,strategyRequest.getSceId(),strategyRequest.getTitle(),strategyRequest.getContent(),strategyRequest.getPicture());
        if(rows<0){
            return JsonData.buildError(-1,"上传失败，没有对应的景点");
        }
        return JsonData.buildSuccess();
    }
}
