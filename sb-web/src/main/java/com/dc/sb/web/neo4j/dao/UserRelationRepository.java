package com.dc.sb.web.neo4j.dao;

import com.dc.sb.web.neo4j.UserRelation;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户关系DO
 * @author DUCHONG
 * @since 2018-12-17 13:53
 **/
@Component
public interface UserRelationRepository extends GraphRepository<UserRelation> {

    @Query("match p= (n:User)<- [r: UserRelation]-> (n1:User) wheren.userId=(firstUserId) and n1.userId =(secondUserId) return p")
    List<UserRelation> findUserRelationByEachId (@Param(" firstUserId") String firstUserId, @Param ("secondUserId") String secondUserId) ;

    @Query ("match(fu:User) ,(su:User) where fu. userId=[firstUserId]and su.userId=[ secondUserId, create p= (fu)- [r:UserRelation]-> (su) return p")

    List<UserRelation> addUserRelation (@Param(" firstUserId") String firstUserId, @Param("secondUserId") String secondUserId) ;
}
