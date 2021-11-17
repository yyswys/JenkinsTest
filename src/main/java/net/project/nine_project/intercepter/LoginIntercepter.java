package net.project.nine_project.intercepter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import net.project.nine_project.utils.JWTUtils;
import net.project.nine_project.utils.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//进入controller之前的方法，需要注册
public class LoginIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getAttributeNames());
        String accesTonken = request.getHeader("token");
        System.out.println(accesTonken);
        if (accesTonken == null) {
            accesTonken = request.getParameter("token");
        }
        if (StringUtils.isNotBlank(accesTonken)) {
            Claims claims = JWTUtils.checkJWT(accesTonken);
            if (claims == null) {
                //不放行，则需要给前端数据，这里封装一个方法传送错误信息
                sendJsonMessage(response, JsonData.buildError("登录过期重新登录"));
                return false;
            }
            Integer id = (Integer) claims.get("id");
            String name = (String) claims.get("name");
            request.setAttribute("user_id", id);
            request.setAttribute("name", name);
            //放行，则由后续的controller给前端数据
            return true;
        }
        sendJsonMessage(response, JsonData.buildError("登录过期重新登录"));
        return false;
    }

    public static void sendJsonMessage(HttpServletResponse response, Object object) {
        try {
            //把对象序列化成json字符串的类
            ObjectMapper objectMapper = new ObjectMapper();
            //设置响应类型
            response.setContentType("application/json;charset=utf-8");
            PrintWriter writer = response.getWriter();
            //把内容当成字符串，以json格式写出去
            writer.print(objectMapper.writeValueAsString(object));
            writer.close();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}