package com.User.controller;

import com.User.domain.Role;
import com.User.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView ){
        //调用业务逻辑层的方法，查找资源
        List<Role> roleList = roleService.list();
        //设置模型
        modelAndView.addObject("roleList",roleList);
        System.out.println("test:role_list");
        //设置视图
        modelAndView.setViewName("role-list");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/list";
    }
    @RequestMapping("/del/{roleId}")
    public String delete(@PathVariable("roleId") Long roleId){
        roleService.del(roleId);
        return "redirect:/role/list";
    }
}
