/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.UserRoleDao;
import com.cv.accountswing.entity.UserRole;
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
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao dao;

    @Autowired
    SeqTableService seqService;

    @Override
    public UserRole save(UserRole ur) {
        if (ur.getRoleCode() == null || ur.getRoleCode().isEmpty()) {
            Integer macId = ur.getMacId();
            String compCode = ur.getCompCode();
            ur.setRoleCode(getUserRoleCode(macId, "UserRole", "-", compCode));
        }
        return dao.save(ur);
    }

    @Override
    public UserRole findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<UserRole> search(String roleName, String compCode) {
        return dao.search(roleName, compCode);
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public UserRole copyRole(String copyRoleId, String compCode) {
        UserRole old = findById(Integer.parseInt(copyRoleId));
        UserRole newRole = new UserRole();

        BeanUtils.copyProperties(old, newRole);
        newRole.setRoleCode(null);
        newRole.setCompCode(compCode);

        return save(newRole);
    }

    @Override
    public List<UserRole> searchM(String updatedDate) {
        return dao.searchM(updatedDate);
    }

    private String getUserRoleCode(Integer macId, String option, String period, String compCode) {

        int seqNo = seqService.getSequence(macId, option, period, compCode);

        String tmpCatCode = String.format("%0" + 3 + "d", macId) + "-" + String.format("%0" + 4 + "d", seqNo);
        return tmpCatCode;
    }
}
