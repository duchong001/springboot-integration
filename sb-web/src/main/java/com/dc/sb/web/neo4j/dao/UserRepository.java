package com.dc.sb.web.neo4j.dao;

import com.dc.sb.web.neo4j.UserNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DUCHONG
 * @since 2018-12-17 11:42
 **/
@Component
public interface UserRepository extends GraphRepository<UserNode> {

    @Query("MATCH (n: User) RETURN n")
    List<UserNode> getUserNodeList();

    @Query("CREATE (n: User{age:{age},userName:{userName}}) RETURN n")
    List<UserNode> addUserNodeList(@Param("userName") String userName, @Param("age") int age);

}
