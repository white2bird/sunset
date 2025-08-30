package com.sunset.utils;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalException extends  RuntimeException{

    // token 失效
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseBody
    public  ReturnJson tokenException(TokenExpiredException exception){
        exception.printStackTrace();
        doLog(exception);
        log.info(exception.toString());
        log.info("Token异常："+exception.getMessage());
        return ReturnJson.fail(401, "令牌过期");
    }
    // 全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnJson<Object> otherException(Exception exception){
        exception.printStackTrace();
        doLog(exception);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取请求相关信息
        String requestUrl = request.getRequestURL().toString();
        String method = request.getMethod();
        String clientIp = request.getRemoteAddr();

        log.info("全局异常 - 请求URL: {}, 方法: {}, 客户端IP: {}", requestUrl, method, clientIp);
        log.info("异常信息: {}", exception.getMessage());

        exception.printStackTrace();
        return ReturnJson.fail(500,exception.getMessage());
    }

    private void doLog(Exception exception) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.error("无法获取请求属性");
            return;
        }
        HttpServletRequest request = attributes.getRequest();

        // 获取请求相关信息
        String requestUrl = request.getRequestURL().toString();
        String method = request.getMethod();
        String clientIp = request.getRemoteAddr();

        // 获取并格式化请求参数（Query参数）
        Map<String, String[]> paramMap = request.getParameterMap();
        StringBuilder queryParams = new StringBuilder();
        if (paramMap != null && !paramMap.isEmpty()) {
            queryParams.append("{ ");
            for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
                queryParams.append(entry.getKey()).append(": ");
                queryParams.append(Arrays.toString(entry.getValue())).append(", ");
            }
            if (queryParams.length() > 2) {
                queryParams.setLength(queryParams.length() - 2);
            }
            queryParams.append(" }");
        } else {
            queryParams.append("无查询参数");
        }

        // 获取并格式化请求体参数
        String bodyParams = "无法获取请求体";
        try {
            // 仅对POST、PUT等可能包含Body的请求尝试读取
            if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) ||
                    "PATCH".equalsIgnoreCase(method)) {

                // 包装请求以支持重复读取
                ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(request);
                byte[] body = wrapper.getContentAsByteArray();
                if (body != null && body.length > 0) {
                    // 尝试按UTF-8编码转换
                    bodyParams = new String(body, StandardCharsets.UTF_8);
                    // 限制长度，避免大文件导致日志过大
                    if (bodyParams.length() > 1024) {
                        bodyParams = bodyParams.substring(0, 1024) + "...(内容过长已截断)";
                    }
                } else {
                    bodyParams = "请求体为空";
                }
            } else {
                bodyParams = "非Body类型请求";
            }
        } catch (Exception e) {
            log.error("读取请求体失败", e);
        }

        log.info("全局异常 - 请求URL: {}, 方法: {}, 客户端IP: {}", requestUrl, method, clientIp);
        log.info("查询参数: {}", queryParams.toString());
        log.info("请求体参数: {}", bodyParams);
        log.info("异常信息: {}", exception.getMessage());
        log.error("异常堆栈:", exception); // 建议用error级别记录堆栈信息
    }

}
