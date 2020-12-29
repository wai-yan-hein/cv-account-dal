/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.UserRoleH2Dao;
import com.cv.inv.h2.entity.UserRoleH2;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class UserRoleH2ServiceImpl implements UserRoleH2Service {

    @Autowired
    private UserRoleH2Dao dao;

    @Override
    public UserRoleH2 save(UserRoleH2 role) {
        return dao.save(role);
    }

    @Override
    public UserRoleH2 findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<UserRoleH2> search(String roleName, String compCode) {
        return dao.search(roleName, compCode);
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public UserRoleH2 copyRole(String copyRoleId, String compCode) {
        UserRoleH2 old = findById(Integer.parseInt(copyRoleId));
        UserRoleH2 newRole = new UserRoleH2();

        BeanUtils.copyProperties(old, newRole);
        newRole.setRoleId(null);
        newRole.setCompCode(Integer.parseInt(compCode));

        return save(newRole);
    }

    @Override
    public List<UserRoleH2> searchM(String updatedDate) {
        return dao.searchM(updatedDate);
    }
}
