package net.project.nine_project.exception;

//这里可以根据业务定制更多的异常
public class XMException extends RuntimeException {
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public XMException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
