package com.zxp.entity.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user_card")
public class UserCard {
    @Id
    @GeneratedValue
    private int id;
    private String userID;
    private int count;
}
