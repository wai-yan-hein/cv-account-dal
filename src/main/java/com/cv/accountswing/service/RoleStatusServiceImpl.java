/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.RoleStatusDao;
import com.cv.accountswing.entity.RoleStatus;
import com.cv.accountswing.entity.RoleStatusKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class RoleStatusServiceImpl implements RoleStatusService {

    @Autowired
    private RoleStatusDao dao;

    @Override
    public RoleStatus save(RoleStatus status) {
        return dao.save(status);
    }

    @Override
    public boolean checkPermission(String roleCode, String key) {
        return dao.findById(new RoleStatusKey(roleCode, key));
    }

}
