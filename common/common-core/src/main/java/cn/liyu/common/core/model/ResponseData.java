package cn.liyu.common.core.model;

import cn.liyu.common.core.global.RetStub;
import lombok.Data;

import java.io.Serializable;

import static cn.liyu.common.core.global.SysStubInfo.FAIL;
import static cn.liyu.common.core.global.SysStubInfo.SUCCESS;


/**
 * 通用 响应信息主体
 */
@Data
public class ResponseData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应码
     *
     * @mock 200
     */
    private int code;
    /**
     * 响应信息
     *
     * @mock success
     */
    private String msg;
    /**
     * 响应数据
     *
     * @mock json
     */
    private T data;

    public ResponseData() {
    }

    public ResponseData(int code, String msg) {
        this(code, msg, null);
    }

    public ResponseData(int code, String msg, T result) {
        super();
        this.code(code).message(msg).result(result);
    }

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(SUCCESS.getCode());
        responseData.setMsg(SUCCESS.getMsg());
        responseData.setData(data);
        return responseData;
    }

    public static <T> ResponseData<T> error(String msg) {
        return error(FAIL.getCode(), msg);
    }

    public static <T> ResponseData<T> error(int code, String msg) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(code);
        responseData.setMsg(msg);
        return responseData;
    }

    public ResponseData(RetStub sysStubInfo, T result) {
        this.code = sysStubInfo.getCode();
        this.msg = sysStubInfo.getMsg();
        this.data = result;
    }

    public ResponseData(RetStub sysStubInfo) {
        this.code = sysStubInfo.getCode();
        this.msg = sysStubInfo.getMsg();
    }


    private ResponseData<T> message(String message) {
        this.setMsg(message);
        return this;
    }

    private ResponseData<T> code(int code) {
        this.setCode(code);
        return this;
    }

    private ResponseData<T> result(T result) {
        this.setData(result);
        return this;
    }

    public boolean assertSuccess() {
        return this.code == SUCCESS.getCode();
    }
}
