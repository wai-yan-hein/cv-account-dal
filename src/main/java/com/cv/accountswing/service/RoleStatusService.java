/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.RoleStatus;

/**
 *
 * @author winswe
 */
public interface RoleStatusService {

    public boolean checkPermission(String roleCode, String key);

    public RoleStatus save(RoleStatus status);

}
