package com.zhulong.framework.common.spring.mvc;

import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * 统一异常处理
 * Created by shanb on 2019-2-20.
 */
//必须加上次注解，自动装配的时候再注入成bean。
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvise {

    private static final String DEFAULT_ERROR_CODE = "default";

    @ExceptionHandler(SystemException.class)
    public ResultDTO<?> resolveSystemException(SystemException ex){
        return ResultDTO.builder().success(false).code(ex.getCode()).build();
    }

    /**
     * 用来处理bean validation异常
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultDTO<?> resolveConstraintViolationException(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        if(!CollectionUtils.isEmpty(constraintViolations)){
            StringBuilder msgBuilder = new StringBuilder();
            for(ConstraintViolation constraintViolation :constraintViolations){
                msgBuilder.append(constraintViolation.getMessage());
                break;
            }
            String errorMessage = msgBuilder.toString();
            return ResultDTO.builder().success(false).code(errorMessage).build();
        }
        return ResultDTO.builder().success(false).code(ex.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultDTO<?> resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        if(!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage());
                break;
            }
            String errorMessage = msgBuilder.toString();
            return ResultDTO.builder().success(false).code(errorMessage).build();
        }
        return ResultDTO.builder().success(false).code(ex.getMessage()).build();
    }

    @ExceptionHandler(BindException.class)
    public ResultDTO<?> resoveBindException(BindException ex){
        List<ObjectError> objectErrors = ex.getAllErrors();
        if(!CollectionUtils.isEmpty(objectErrors)) {
            StringBuilder msgBuilder = new StringBuilder();
            for (ObjectError objectError : objectErrors) {
                msgBuilder.append(objectError.getDefaultMessage());
                break;
            }
            String errorMessage = msgBuilder.toString();
            return ResultDTO.builder().success(false).code(errorMessage).build();
        }
        return ResultDTO.builder().success(false).code(ex.getMessage()).build();
    }

    @ExceptionHandler(Exception.class)
    public ResultDTO<?> resoveException(Exception ex){
        log.error(ex.getMessage(),ex);
        return ResultDTO.builder().success(false).code(DEFAULT_ERROR_CODE).build();
    }
}