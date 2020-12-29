/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.MenuH2;
import com.cv.inv.h2.entity.PrivilegeH2;
import com.cv.inv.h2.entity.PrivilegeKeyH2;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface PrivilegeH2Service {

    public PrivilegeH2 save(PrivilegeH2 privilege);

    public PrivilegeH2 findById(PrivilegeKeyH2 key);

    public List<PrivilegeH2> search(String roleId, String menuId);

    public int delete(String roleId, String menuId);

    public List<PrivilegeH2> searchM();

    public void save(String roleId, List<MenuH2> listMenu);

    public void delete(String roleId, List<MenuH2> listMenu);

    public void copyPrivilege(String fromRoleId, String toRoleId) throws Exception;
}
