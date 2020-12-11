/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.service;

import com.cv.account.api.entity.UsrCompRole;
import com.cv.account.api.entity.UsrCompRoleKey;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface UsrCompRoleService {
    public UsrCompRole save(UsrCompRole ucr);
    public UsrCompRole findById(UsrCompRoleKey key);
    public List<UsrCompRole> search(String userId, String compCode, String roleId);
    public List getAssignRole(String userId);
    public List getAssignCompany(String userId);
    public List getAssignCompany(String userId, String roleId, String compId);
    public int delete(String userId, String compCode, String roleId);
}
