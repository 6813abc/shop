package com.ddw.shop.pk;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author cyg
 * @date 2020/3/17 17:12
 **/
@Data
public class UserAddressPk implements Serializable {

    private String phone;
    private Long addressId;
}
