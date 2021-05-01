/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.PrivilegeDao;
import com.cv.accountswing.entity.Menu;
import com.cv.accountswing.entity.Privilege;
import com.cv.accountswing.entity.PrivilegeKey;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

    @Autowired
    private PrivilegeDao dao;

    @Override
    public Privilege save(Privilege privilege) {
        return dao.save(privilege);
    }

    @Override
    public Privilege findById(PrivilegeKey key) {
        return dao.findById(key);
    }

    @Override
    public List<Privilege> search(String roleId, String menuId) {
        return dao.search(roleId, menuId);
    }

    @Override
    public int delete(String roleId, String menuId) {
        return dao.delete(roleId, menuId);
    }

    @Override
    public void save(String roleId, List<Menu> listMenu) {
        listMenu.forEach(menu -> {
            PrivilegeKey key = new PrivilegeKey();
            key.setMenuCode(menu.getCode());
            key.setRoleCode(roleId);
            Privilege privilege = new Privilege();
            privilege.setKey(key);
            privilege.setIsAllow(Boolean.FALSE);
            dao.save(privilege);
            if (menu.getChild() != null) {
                if (menu.getChild().size() > 0) {
                    save(roleId, menu.getChild());
                }
            }
        });
    }

    @Override
    public void delete(String roleId, List<Menu> listMenu) {
        listMenu.stream().map(menu -> {
            dao.delete(roleId, menu.getCode().toString());
            return menu;
        }).filter(menu -> (menu.getChild() != null)).filter(menu -> (menu.getChild().size() > 0)).forEachOrdered(menu -> {
            delete(roleId, menu.getChild());
        });
    }

    @Override
    public void copyPrivilege(String fromRoleId, String toRoleId) throws Exception {
        dao.copyPrivilege(fromRoleId, toRoleId);
    }

    @Override
    public List<Privilege> searchM() {
        return dao.searchM();
    }
}
