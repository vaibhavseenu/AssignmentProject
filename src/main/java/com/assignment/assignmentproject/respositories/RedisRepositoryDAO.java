package com.assignment.assignmentproject.respositories;

import com.assignment.assignmentproject.entities.ColumnData;
import com.assignment.assignmentproject.entities.TableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RedisRepositoryDAO {

    private final String REDIS_KEY = "sql_statements";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void saveStatement(String sqlStatement) {
        redisTemplate.opsForList().leftPush(REDIS_KEY, sqlStatement);
    }

    public List<String> getAllStatements() {
        return redisTemplate.opsForList().range(REDIS_KEY, 0, -1);
    }

}
