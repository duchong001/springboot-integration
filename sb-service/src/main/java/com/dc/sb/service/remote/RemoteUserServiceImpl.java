package com.dc.sb.service.remote;

import com.alibaba.dubbo.config.annotation.Service;
import com.dc.sb.dao.datainterface.UsersDOMapper;
import com.dc.sb.dao.dataobject.UsersDO;
import com.dc.sb.dao.dataobject.UsersQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * dubbo 服务service
 *
 * @author DUCHONG
 * @since 2018-07-03 18:29
 **/
@Component
@Service(version = "1.0.0",timeout = 10000)
public class RemoteUserServiceImpl implements RemoteUserService {

    private static final Logger logger = LoggerFactory.getLogger(RemoteUserServiceImpl.class);

    @Autowired
    private UsersDOMapper usersDOMapper;


    /**
     * 添加
     * @param user
     * @return
     */
    public int addUser(UsersDO user){
        return usersDOMapper.insert(user);
    }

    /**
     * 分页-list
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo getUserListWithPage(Integer pageNum, Integer pageSize){

        PageHelper.startPage(pageNum, pageSize);
        UsersQuery query=new UsersQuery();
        try{
            List<UsersDO> list=usersDOMapper.selectByExample(query);
            return new PageInfo(list);
        }
        catch (Exception e) {
            logger.error("UserService.getUserListWithPage error",e);
        }
        return null;
    }
}
