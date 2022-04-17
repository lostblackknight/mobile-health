package io.github.lostblackknight.member.handler;

import io.github.lostblackknight.model.vo.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author chensixiang (chensixiang1234@gmail.com)
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public CommonResult<?> handle() {
        return CommonResult.fail("操作失败");
    }
}
