# springboot-exceptionhandler

**springboot捕获全局异常和局部异常特殊处理**

## springboot捕获全局异常关键点
    1、创建全局异常处理类
        用@ControllerAdvice标识全局异常处理类
    2、在全局异常处理类中创建异常捕获方法，在方法中进行处理，value值为捕获的异常
        @ExceptionHandler(value = MyException.class)
    3、isAjax()方法是通过判断request请求是否为Ajax请求
        如果是Ajax请求的方法抛出异常，就返回错误信息
        如果不是Ajax请求的方法抛出异常，就跳转到错误页面，例如404页面、系统异常页面
## SpringBoot 其他异常处理方式         
    https://blog.csdn.net/qq_24598601/article/details/89243914
    SimpleMappingExceptionResolver 异常处理映射
    HandlerExceptionResolver 异常处理