package org.example.exception;

//自定义的运行异常
public class BusinessException extends RuntimeException{

//    只创建异常对象
    public BusinessException() {
        super();
    }

    //    创建异常对象与异常信息
    public BusinessException(String message) {
        super(message);
    }
}
