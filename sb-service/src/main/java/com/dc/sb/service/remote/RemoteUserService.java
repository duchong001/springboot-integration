package com.dc.sb.service.remote;

import com.dc.sb.dao.dataobject.UsersDO;
import com.github.pagehelper.PageInfo;

public interface RemoteUserService {


    int addUser(UsersDO user);

    PageInfo getUserListWithPage(Integer pageNum, Integer pageSize);
}
