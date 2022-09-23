package com.sp.testproc;

import java.util.Map;

/**
 * 响应返回工具类
 * Created by alexlu on 2017/1/9.
 */
public class ResponseUtil {
    /**
     * 返回成功响应对象
     * @return
     */
    public static <T> ResponseVO<T> getSuccess(){
        return new ResponseVO(SystemErrorCodeEnum.SUCCESS.getCode(),"Succeed!");
    }

    /**
     * 返回失败响应对象
     * @return
     */
    public static <T> ResponseVO<T> getFailure(){
        return new ResponseVO(SystemErrorCodeEnum.SYSTEM_ERROR.getCode(),"Failed!");
    }

    /**
     * 返回成功响应含有数据的对象
     * @param data
     * @return
     */
    public static <T> ResponseVO<T> getFromData(T data){
        ResponseVO responseVO = getSuccess();
        responseVO.setData(data);
        return responseVO;
    }

    /**
     * 返回指定失败错误文本的响应对象
     * @param msg
     * @return
     */
    public static <T> ResponseVO<T> getFailure(String msg){
        return new ResponseVO(SystemErrorCodeEnum.SYSTEM_ERROR.getCode(), msg);
    }

    /**
     * 返回指定失败错误码和错误文本的响应对象
     * @param errorCode
     * @param msg
     * @return
     */
    public static <T> ResponseVO<T> getFailure(Integer errorCode, String msg){
        return new ResponseVO(errorCode, msg);
    }

    /**
     * 返回指定失败错误码和错误文本参数的响应对象
     * @param errorCode
     * @param errMsgMap
     * @return
     */
    public static <T> ResponseVO<T> getFailureWithMap(Integer errorCode, Map<String, String> errMsgMap){
        StringBuilder stb = new StringBuilder();
        for (String errMsg : errMsgMap.values()){
            stb.append(errMsg);
        }

        return new ResponseVO(errorCode, stb.toString());
    }

    /**
     * 获取成功响应数据对象
     * @param data
     * @return
     */
    public static <T> ResponseVO<T> getResponse(T data){
        ResponseVO responseVO =  getSuccess();
        responseVO.setData(data);
        return responseVO;
    }
}
