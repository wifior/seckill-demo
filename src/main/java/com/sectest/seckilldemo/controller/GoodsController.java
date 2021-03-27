package com.sectest.seckilldemo.controller;

import com.sectest.seckilldemo.entity.User;
import com.sectest.seckilldemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/toList")
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, @CookieValue("userTicket")String ticket){

        if(!StringUtils.hasLength(ticket)){
            return "login";
        }
        //User user = (User) session.getAttribute(ticket);
         User user = userService.getUserByCookie(ticket,request,response);
        if(null==user){
            return "login";
        }
        model.addAttribute("user",user);
        return "goodsList";
    }
}
