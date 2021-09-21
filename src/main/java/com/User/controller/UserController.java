package com.User.controller;

import com.User.domain.Role;
import com.User.domain.User;
import com.User.service.RoleService;
import com.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private RoleService roleService;
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView){
        //调用业务逻辑方法,查找资源
        List<User> userList = service.list();
        //设置模型
        modelAndView.addObject("userList",userList);
        System.out.println("test:user-list");
        //设置视图
        modelAndView.setViewName("user-list");
        return modelAndView;
    }
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(ModelAndView modelAndView){
        List<Role> list = roleService.list();
        modelAndView.addObject("roleList",list);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String save(User user,Long[] roleIds){
        service.save(user,roleIds);
        return "redirect:/user/list";
    }
    @RequestMapping("/del/{userId}")
    public String delete(@PathVariable("userId") Long userId){
        service.del(userId);
        return "redirect:/user/list";
    }
    @RequestMapping("/login")
     public String login(String username, String password, HttpSession session){
        User user = service.login(username,password);
        if(user!=null){
            System.out.println("user_login");
            session.setAttribute("user",user);
            return "redirect:/index.jsp";
        }
        return "redirect:/login.jsp";
    }
}
