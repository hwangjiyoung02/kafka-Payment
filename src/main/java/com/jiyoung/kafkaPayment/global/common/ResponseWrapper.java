package com.jiyoung.kafkaPayment.global.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class ResponseWrapper implements ResponseBodyAdvice<Object> { // object로 리턴값을 설정
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return false;
    }

    // 여기서 body는 controller에서 반환해준 response body
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ErrorResponse){
            return new ApiResponse<>("ERROR",body);
        }
        return new ApiResponse<>("SUCCESS",body);
    }
}
