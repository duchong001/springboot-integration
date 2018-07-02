package com.dc.sb.service;

import com.dc.sb.dao.datainterface.UsersDOMapper;
import com.dc.sb.dao.dataobject.UsersDO;
import com.dc.sb.dao.dataobject.UsersQuery;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DUCHONG
 * @since 2018-07-01 0:25
 **/
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
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
    public PageInfo getUserListWithPage( Integer pageNum, Integer pageSize){

            PageHelper.startPage(pageNum, pageSize);
            UsersQuery query=new UsersQuery();
            try{
                List<UsersDO>list=usersDOMapper.selectByExample(query);
                return new PageInfo(list);
            }
            catch (Exception e) {
                logger.error("UserService.getUserListWithPage error",e);
            }
            return null;
    }

    /**
     * 无分页List
     * @return
     */
    public List<UsersDO> getAllUserWithNoPage(){

        UsersQuery query=new UsersQuery();
        try{
            List<UsersDO>list=usersDOMapper.selectByExample(query);
            return list;
        }
        catch (Exception e) {
            logger.error("UserService.getAllUserWithNoPage error",e);
        }
        return null;
    }
    /**
     * 通过主键获取
     * @param id
     * @return
     */
    public UsersDO getUserById(Integer id){

        return usersDOMapper.selectByPrimaryKey(id);
    }
    /**
     * 修改
     * @param user
     * @return
     */
    public int updateUser(UsersDO user){
        return usersDOMapper.updateByPrimaryKey(user);
    }

    public int deleteUserById(Integer id){
        return usersDOMapper.deleteByPrimaryKey(id);
    }
}
