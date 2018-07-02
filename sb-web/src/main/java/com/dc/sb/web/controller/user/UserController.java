package com.dc.sb.web.controller.user;

import com.dc.sb.dao.dataobject.UsersDO;
import com.dc.sb.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        List<UsersDO> resultList=null;
        //序列化器，将key的值设置为字符串
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<UsersDO> list=(List<UsersDO>)redisTemplate.opsForValue().get("allUsers");

        if(null!=list){
            System.out.println("从缓存中读数据");
            resultList=list;
        }
        else{
            resultList=userService.getAllUserWithNoPage();
            System.out.println("从数据库中查数据");
            redisTemplate.opsForValue().set("allUsers",resultList);
        }
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
}
