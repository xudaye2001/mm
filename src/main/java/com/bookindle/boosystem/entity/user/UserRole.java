package com.bookindle.boosystem.entity.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author longzhonghua
 * @data 2018/11/04 22:30
 */
@Data
@Entity
@Table(name = "sys_role")
public class UserRole {

    @Id
    @GeneratedValue
    private long id;

    private String rolename;
    private String cnname;

}
