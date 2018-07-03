package com.dc.sb.web.controller.remote;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dc.sb.dao.dataobject.UsersDO;
import com.dc.sb.service.remote.RemoteUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * dubbo消费者controller
 *
 * @author DUCHONG
 * @since 2018-07-03 18:44
 **/
@Controller
public class RemoteUserController {

    @Reference(url = "dubbo://localhost:20880")
    private RemoteUserService remoteUserService;


    @RequestMapping(value="/dubbo/add")
    public String addUser(ModelMap mm){

        for (int i=11;i<20;i++){

            UsersDO usersDO=new UsersDO();
            usersDO.setUsername("u"+i);
            usersDO.setAge(i+10);
            remoteUserService.addUser(usersDO);
        }
        return "dubbo add success";
    }


    @RequestMapping(value = "/dubbo/getList/{pageNum}/{pageSize}")
    public String getUserListWithPage(@PathVariable(name = "pageNum") Integer pageNum, @PathVariable(name = "pageSize") Integer pageSize){

        PageInfo pageInfo=remoteUserService.getUserListWithPage(pageNum,pageSize);
        List<UsersDO> list=pageInfo.getList();
        StringBuffer sb=new StringBuffer();
        for (UsersDO usersDO : list) {
            sb.append(usersDO.toString());
        }
        return sb.toString();
    }
}
