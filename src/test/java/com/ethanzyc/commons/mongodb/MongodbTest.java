package com.ethanzyc.commons.mongodb;

import com.ethanzyc.commons.CommonsApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

/**
 * @author ethan
 * @date 2019/8/28 22:30
 */
public class MongodbTest extends CommonsApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testSave() {
        User user = User.builder().id(1).name("子").date(new Date()).parentUser(
                User.builder().id(2).name("父").build()
        ).build();
        User save = mongoTemplate.save(user);
        System.err.println(save);
        System.out.println(save);
    }
}
