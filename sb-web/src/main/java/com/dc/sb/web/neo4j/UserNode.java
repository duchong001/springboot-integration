package com.dc.sb.web.neo4j;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * 节点实体类型-别名为User
 * @author DUCHONG
 * @since 2018-12-17 11:32
 **/
@NodeEntity(label = "User")
public class UserNode {
    //图形id
    @GraphId
    private Long nodeId;
    //属性
    @Property
    private String userId;
    //属性
    @Property
    private String userName;
    //属性
    @Property
    private int age;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
