/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.entity;

import com.cv.accountswing.entity.*;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author winswe
 */
@Entity
@Table(name="usr_comp_role_h2")
public class UsrCompRoleH2 implements java.io.Serializable{
    private UsrCompRoleKeyH2 key;

    @EmbeddedId
    public UsrCompRoleKeyH2 getKey() {
        return key;
    }

    public void setKey(UsrCompRoleKeyH2 key) {
        this.key = key;
    }
}
