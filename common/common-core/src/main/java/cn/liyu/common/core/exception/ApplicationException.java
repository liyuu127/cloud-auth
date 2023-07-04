package cn.liyu.common.core.exception;


import cn.liyu.common.core.global.RetStub;

/**
 * 全局自定义异常,所有可知异常有此异常统一抛出
 */
public class ApplicationException extends RuntimeException {


    public ApplicationException(int code, String message, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ApplicationException(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public ApplicationException() {
    }


    private int code;
    private String message;

    private Object data;

    public ApplicationException(RetStub retStub) {
        this(retStub.getCode(), retStub.getMsg());
    }

    public ApplicationException(RetStub retStub, Object data) {
        this(retStub.getCode(), retStub.getMsg(), data);

    }


    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public String getRelMessage() {
        return this.message;
    }

    @Override
    public String getMessage() {
        if (data == null) {
            return this.code + ":" + this.message;
        } else {
            String s = this.data.toString();
            return this.code + ":" + this.message + "\n" + s;
        }
    }

}