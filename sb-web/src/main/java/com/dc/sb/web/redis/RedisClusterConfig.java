package com.dc.sb.web.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * redis 集群配置类
 * 需要时直接   @Autowired JedisCluster
 * @author DUCHONG
 * @since 2018-12-17 17:28
 **/
@Configuration
public class RedisClusterConfig {

    @Value("spring.redis.cluster.nodes")
    private String redisNodes;

    @Bean
    public JedisCluster getJedisCluster(){

        Set<HostAndPort> nodes=new HashSet<HostAndPort>() ;

        String [] redisnodes=redisNodes.split(",");
        for (String redisnode : redisnodes) {

            String [] arr=redisnode.split(":");
            HostAndPort hostAndPort=new HostAndPort(arr[0],Integer.parseInt(arr[1]));
            nodes.add(hostAndPort);
        }

        return new JedisCluster(nodes);
    }
}
