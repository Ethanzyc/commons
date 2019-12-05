package com.ethanzyc.commons.mongodb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author ethan
 * @date 2019/8/28 22:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("user")
public class User {
    private Integer id;
    private String name;
    private Date date;
    private User parentUser;
}
