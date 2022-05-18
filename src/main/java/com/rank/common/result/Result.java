package com.rank.common.result;

/**
 * @author Caolp
 */
public class Result {

    private Boolean status = true;
    private String code = "200";
    private String message;
    private Object dataList;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Object getDataList() {
        return dataList;
    }

    public void setDataList(Object dataList) {
        this.dataList = dataList;
    }

    /**
     * 无参构造器
     */
    public Result() {
    }

    /**
     * 有参构造器
     *
     * @param status
     * @param code
     * @param message
     * @param dataList
     */
    public Result(Boolean status, String code, String message, Object dataList) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.dataList = dataList;
    }

    public static Result ofSuccess() {
        Result result = new Result();
        result.setStatus(true);
        return result;
    }

    public static Result ofSuccess(Object dataList) {
        Result result = new Result();
        result.setStatus(true);
        result.setDataList(dataList);
        return result;
    }

    public static Result ofFail(String code, String msg) {
        Result result = new Result();
        result.setStatus(false);
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static Result ofFail(ResponseCode responseCode) {
        Result result = new Result();
        result.setStatus(false);
        result.setCode(responseCode.getCode());
        result.setMessage(responseCode.getMessage());
        return result;
    }
}
