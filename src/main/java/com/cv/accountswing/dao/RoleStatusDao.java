/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.RoleStatus;
import com.cv.accountswing.entity.RoleStatusKey;

/**
 *
 * @author winswe
 */
public interface RoleStatusDao {

    public boolean findById(RoleStatusKey key);

    public RoleStatus save(RoleStatus status);
}
