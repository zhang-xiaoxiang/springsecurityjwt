package com.example.springsecurityjwt.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User:用户实体类
 * @author zhangxiaoxiang
 * @date 2019/10/28
 */


@Entity
@Table(name = "tb_user")
@Data
public class User {
 
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
 

}
