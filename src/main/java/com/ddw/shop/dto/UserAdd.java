package com.ddw.shop.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ddw
 * @date 2020/3/17 10:38
 **/
@Data
public class UserAdd {

    private String phone;
    private String password;
    private String name;
    private String sex;
    private String address;
    private String baseCode;
    /**
     * 余额
     **/
    private String money;
}
