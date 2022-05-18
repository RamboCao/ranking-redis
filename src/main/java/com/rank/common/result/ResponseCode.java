package com.rank.common.result;

public enum ResponseCode {

    /**
     * 请求成功
     */
    SUCCESS("SUCCESS", "SUCCESS-200"),
    /**
     * 付款方式名称重复
     */
    SERVER_ERROR("SERVER_ERROR", "SERVER_ERROR-10001");

    private String message;

    private String code;

    ResponseCode(String message, String code) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
