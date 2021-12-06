package com.zhaojie.Entity;

import lombok.Data;

/**
 * @Author ZhaoJie
 * @Version 1.0
 * @Data 06 11:50
 * @Email 17854262969@163.com
 */
@Data
public class Score {
    private Integer id;
    private String name;
    private String subject;
    private Integer score;
    private Integer locationId;

}
