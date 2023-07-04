package cn.liyu.common.core.global;

public enum SysStubInfo implements RetStub {
    SUCCESS(200, "success"),
    FAIL(500, "service is not available"),
    PARAMETER_INVALID(400, "parameter error"),
    UNAUTHORIZED(401, "user unauthorized"),
    FORBIDDEN(403, "not allowed"),
    NOT_FOUND(404, "source not exist"),
    METHOD_INVALID(405, "unsupported method"),
    CONTENT_TYPE_INVALID(405, "Content-Type invalid"),
    REQUEST_TIMEOUT(408, "request timeout"),
    ;

    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误信息
     */
    private final String msg;

    SysStubInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
