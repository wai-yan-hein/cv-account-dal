/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.accountswing.entity.Menu;
import com.cv.accountswing.entity.Privilege;
import com.cv.accountswing.entity.PrivilegeKey;
import com.cv.inv.h2.dao.PrivilegeH2Dao;
import com.cv.inv.h2.entity.MenuH2;
import com.cv.inv.h2.entity.PrivilegeH2;
import com.cv.inv.h2.entity.PrivilegeKeyH2;
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
public class PrivilegeH2ServiceImpl implements PrivilegeH2Service {

    @Autowired
    private PrivilegeH2Dao dao;

    @Override
    public PrivilegeH2 save(PrivilegeH2 privilege) {
        return dao.save(privilege);
    }

    @Override
    public PrivilegeH2 findById(PrivilegeKeyH2 key) {
        return dao.findById(key);
    }

    @Override
    public List<PrivilegeH2> search(String roleId, String menuId) {
        return dao.search(roleId, menuId);
    }

    @Override
    public int delete(String roleId, String menuId) {
        return dao.delete(roleId, menuId);
    }

    @Override
    public void save(String roleId, List<MenuH2> listMenu) {
        for (MenuH2 menu : listMenu) {
            PrivilegeKeyH2 key = new PrivilegeKeyH2();
            key.setMenuId(menu.getId());
            key.setRoleId(Integer.parseInt(roleId));

            PrivilegeH2 privilege = new PrivilegeH2();
            privilege.setKey(key);
            privilege.setIsAllow(Boolean.FALSE);
            dao.save(privilege);

            if (menu.getChild() != null) {
                if (menu.getChild().size() > 0) {
                    save(roleId, menu.getChild());
                }
            }
        }
    }

    @Override
    public void delete(String roleId, List<MenuH2> listMenu) {
        for (MenuH2 menu : listMenu) {
            dao.delete(roleId, menu.getId().toString());

            if (menu.getChild() != null) {
                if (menu.getChild().size() > 0) {
                    delete(roleId, menu.getChild());
                }
            }
        }
    }

    @Override
    public void copyPrivilege(String fromRoleId, String toRoleId) throws Exception {
        dao.copyPrivilege(fromRoleId, toRoleId);
    }

    @Override
    public List<PrivilegeH2> searchM() {
        return dao.searchM();
    }
}
