package io.github.lostblackknight.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static org.springframework.http.HttpStatus.*;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@Data
@JsonInclude(NON_NULL)
public class CommonResult<T> {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp;
    private Integer statusCode;
    private HttpStatus status;
    private String message;
    private T data;
    // 业务代码
    private Integer code;

    public static <T> CommonResult<T> success() {
        final CommonResult<T> result = new CommonResult<>();
        result.setCode(1);
        result.setStatusCode(OK.value());
        result.setStatus(OK);
        result.setTimestamp(new Date());
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        final CommonResult<T> result = new CommonResult<>();
        result.setCode(1);
        result.setStatusCode(OK.value());
        result.setStatus(OK);
        result.setData(data);
        result.setTimestamp(new Date());
        return result;
    }

    public static <T> CommonResult<T> success(String message) {
        final CommonResult<T> result = new CommonResult<>();
        result.setCode(1);
        result.setStatusCode(OK.value());
        result.setStatus(OK);
        result.setMessage(message);
        result.setTimestamp(new Date());
        return result;
    }

    public static <T> CommonResult<T> success(T data, Integer code, String message) {
        final CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setStatusCode(OK.value());
        result.setStatus(OK);
        result.setData(data);
        result.setMessage(message);
        result.setTimestamp(new Date());
        return result;
    }

    public static <T> CommonResult<T> success(T data, Integer code) {
        final CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setStatusCode(OK.value());
        result.setStatus(OK);
        result.setData(data);
        result.setTimestamp(new Date());
        return result;
    }

    public static <T> CommonResult<T> success(T data, String message) {
        final CommonResult<T> result = new CommonResult<>();
        result.setCode(1);
        result.setStatusCode(OK.value());
        result.setStatus(OK);
        result.setMessage(message);
        result.setData(data);
        result.setTimestamp(new Date());
        return result;
    }

    public static <T> CommonResult<T> fail() {
        final CommonResult<T> result = new CommonResult<>();
        result.setCode(0);
        result.setStatusCode(BAD_REQUEST.value());
        result.setStatus(BAD_REQUEST);
        result.setTimestamp(new Date());
        return result;
    }

    public static <T> CommonResult<T> fail(String message) {
        final CommonResult<T> result = new CommonResult<>();
        result.setCode(0);
        result.setStatusCode(BAD_REQUEST.value());
        result.setStatus(BAD_REQUEST);
        result.setMessage(message);
        result.setTimestamp(new Date());
        return result;
    }

    public static <T> CommonResult<T> unauthorized(String message) {
        final CommonResult<T> result = new CommonResult<>();
        result.setCode(0);
        result.setStatusCode(UNAUTHORIZED.value());
        result.setStatus(UNAUTHORIZED);
        result.setMessage(message);
        result.setTimestamp(new Date());
        return result;
    }

    public static <T> CommonResult<T> unauthorized(String message, Integer code) {
        final CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setStatusCode(UNAUTHORIZED.value());
        result.setStatus(UNAUTHORIZED);
        result.setMessage(message);
        result.setTimestamp(new Date());
        return result;
    }
}
