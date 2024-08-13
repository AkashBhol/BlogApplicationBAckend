package com.example.BlogApplicationBAckend.BasicConfiguration;

import com.example.BlogApplicationBAckend.DTO.Result;

import java.util.Objects;

public class BsicUtil {
    public Result prepareResponseObject(String code, String message, Object responseData) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
//        if (responseData) {
//            result.setData(null);
//        } else {
        result.setData(responseData);
//        }
        return result;
    }

    public static boolean isNullOrEmpty(Object value) {
        return (Objects.isNull(value) || value.toString().isBlank()) ? Boolean.TRUE : Boolean.FALSE;
    }
}
