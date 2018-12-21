package com.dc.sb.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局同一的异常处理
 *
 * @author DUCHONG
 * @since 2018-12-03 15:45
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    private Map<String,Object> handerException(Exception e){


        Map<String,Object> result=new HashMap<String,Object>();
        result.put("code",500);
        result.put("msg",e.toString());

        return result;
    }
}
