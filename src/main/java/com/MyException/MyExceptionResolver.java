package com.MyException;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionResolver implements HandlerExceptionResolver {
    /**
     *
     * @param e Exception : 异常对象
     * @return ModelAndView : 跳转的错误视图信息
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof MyException){
            //进行响应的操作
        }else if(e instanceof  ClassCastException){
            //
        }
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
