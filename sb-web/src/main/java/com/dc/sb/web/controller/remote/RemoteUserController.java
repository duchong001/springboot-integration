package com.dc.sb.web.controller.remote;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dc.sb.service.RemoteUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * dubbo消费者controller
 * @author DUCHONG
 * @since 2018-07-03 18:44
 **/
@RestController
public class RemoteUserController {

    //timeout 可以不指定，但是version一定要指定 不然会找不到服务 直连需要加url="dubbo://localhost:20880"
    @Reference(version = "1.0.0")
    private RemoteUserService remoteUserService;


    @RequestMapping(value="/dubbo/say/{name}")
    public String sayHello(@PathVariable("name") String name){

        String result=remoteUserService.sayHello(name);
        return result;
    }


}
