package com.ddw.shop.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ddw
 * @date 2020/3/18 11:32
 **/
@Data
@Entity
public class Category {

    @Id
    private Long cid;
    private String name;
    private String isParent;
    private Long parentId;
    private String level;
    private String pathId;
    private String path;
}
