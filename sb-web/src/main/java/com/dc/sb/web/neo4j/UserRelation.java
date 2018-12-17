package com.dc.sb.web.neo4j;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * 关系节点类型
 * @author DUCHONG
 * @since 2018-12-17 11:39
 **/
@RelationshipEntity(type = "UserRelation")
public class UserRelation {

    @GraphId
    private Long id;
    //开始节点
    @StartNode
    private UserNode startNode;
    //结束节点
    @EndNode
    private EndNode endNode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserNode getStartNode() {
        return startNode;
    }

    public void setStartNode(UserNode startNode) {
        this.startNode = startNode;
    }

    public EndNode getEndNode() {
        return endNode;
    }

    public void setEndNode(EndNode endNode) {
        this.endNode = endNode;
    }
}
