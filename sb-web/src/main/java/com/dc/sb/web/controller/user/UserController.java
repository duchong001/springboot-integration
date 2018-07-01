package com.dc.sb.web.controller.user;

import com.dc.sb.dao.dataobject.UsersDO;
import com.dc.sb.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author DUCHONG
 * @since 2018-07-01 0:38
 **/
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value="/add",method= RequestMethod.GET)
    public String addUser(ModelMap mm){
        for (int i=0;i<10;i++){
            UsersDO usersDO=new UsersDO();
            usersDO.setUsername("u"+i);
            usersDO.setAge(i+10);
        }
        return "add success";
    }

    @RequestMapping(value="update")
    public ModelAndView updateUser(ModelMap mm){

        mm.addAttribute("update","success");
        return new ModelAndView("",mm);
    }

    @RequestMapping(value = "/getList")
    public String getUserList(ModelMap mm){

        PageInfo pageInfo=userService.getUserList(1,5);
        mm.addAttribute("pageinfo",pageInfo);
        return mm.toString();
    }
}
