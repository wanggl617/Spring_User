package com.User.interceptor;

import com.User.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrivilegeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //逻辑： 判断用户是否登录，即判断 session 中是否有use
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            //没有登录
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return  false;
        }
        //放行
        return  true;
    }
}
