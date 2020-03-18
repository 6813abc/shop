package com.ddw.shop.entity;

import com.ddw.shop.pk.UserAddressPk;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @author cyg
 * @date 2020/3/17 17:12
 **/
@Data
@Entity
@IdClass(UserAddressPk.class)
public class UserAddress {

    @Id
    private String phone;
    @Id
    private Long addressId;
}
