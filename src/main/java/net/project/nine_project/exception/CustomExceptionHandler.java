package net.project.nine_project.exception;

import net.project.nine_project.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//异常处理类声明
@ControllerAdvice
public class CustomExceptionHandler {

    //记录异常日志
    private final static Logger logger= LoggerFactory.getLogger(CustomExceptionHandler.class);

    //ExceptionHandler规定处理哪类异常，这里我们处理所有异常
    @ExceptionHandler(value = Exception.class)
    //@ResponseBody的作用是将java对象转为json格式的数据,发送给用户。
    @ResponseBody
    public JsonData handle(Exception e){

        logger.error("[ 系统异常 ]{}",e);

        if(e instanceof XMException){
            XMException xmException=(XMException) e;
            return JsonData.buildError(xmException.getCode(),xmException.getMsg());
        }else {
            return JsonData.buildError("全局异常，未知错误");
        }
    }
}
