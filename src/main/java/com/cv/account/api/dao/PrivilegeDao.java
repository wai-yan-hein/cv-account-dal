/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.Privilege;
import com.cv.account.api.entity.PrivilegeKey;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface PrivilegeDao {

    public Privilege save(Privilege privilege);

    public Privilege findById(PrivilegeKey key);

    public List<Privilege> search(String roleId, String menuId);

    public List<Privilege> searchM();

    public int delete(String roleId, String menuId);

    public void copyPrivilege(String fromRoleId, String toRoleId) throws Exception;
}
