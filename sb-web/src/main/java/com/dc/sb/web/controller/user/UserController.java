package com.dc.sb.web.controller.user;

import com.dc.sb.dao.dataobject.UsersDO;
import com.dc.sb.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author DUCHONG
 * @since 2018-07-01 0:38
 **/
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @RequestMapping(value="/add")
    public String addUser(ModelMap mm){

        for (int i=0;i<10;i++){
            UsersDO usersDO=new UsersDO();
            usersDO.setUsername("u"+i);
            usersDO.setAge(i+10);
            userService.addUser(usersDO);
        }
        return "add success";
    }

    @RequestMapping(value="/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Integer id){

        UsersDO usersDO=userService.getUserById(id);
        if(null!=usersDO){
            userService.deleteUserById(id);
        }
        return "delete success";
    }

    @RequestMapping(value="/update/{id}")
    public String updateUser(@PathVariable(name = "id") Integer id){

        UsersDO usersDO=userService.getUserById(id);
        if(null!=usersDO){
            usersDO.setUsername("testUpdate");
            userService.updateUser(usersDO);
        }
        return "update success";
    }

    @RequestMapping(value = "/getAllUser")
    public String getUserListWithNoPage(){

        List<UsersDO> resultList=userService.getAllUserWithNoPage();

        return resultList.toString();
    }


    @RequestMapping(value = "/getList/{pageNum}/{pageSize}")
    public String getUserListWithPage(@PathVariable(name = "pageNum") Integer pageNum,@PathVariable(name = "pageSize") Integer pageSize){

        PageInfo pageInfo=userService.getUserListWithPage(pageNum,pageSize);
        List<UsersDO> list=pageInfo.getList();
        StringBuffer sb=new StringBuffer();
        for (UsersDO usersDO : list) {
            sb.append(usersDO.toString());
        }
        return sb.toString();
    }


    @GetMapping(value = "/test")
    public String  test(){
        ExecutorService executorService= Executors.newFixedThreadPool(20);

        for(int i=1 ; i<=10000;i++){

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    userService.getAllUserWithNoPage();
                }
            });
        }

        return "test over";
    }
}
