/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "role_setting")
public class UserDefault implements Serializable {

    @EmbeddedId
    private UserDefaultKey key;

    public UserDefaultKey getKey() {
        return key;
    }

    public void setKey(UserDefaultKey key) {
        this.key = key;
    }

}
