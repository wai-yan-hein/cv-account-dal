/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.UsrCompRole;
import com.cv.accountswing.entity.UsrCompRoleKey;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface UsrCompRoleService {
    public UsrCompRole save(UsrCompRole ucr);
    public UsrCompRole findById(UsrCompRoleKey key);
<<<<<<< HEAD
    public List<UsrCompRole> search(String userCode, String compCode, String roleId);
    public List getAssignRole(String userCode);
    public List getAssignCompany(String userCode);
    public List getAssignCompany(String userCode, String roleId, String compCode);
    public int delete(String userCode, String compCode, String roleId);
=======
    public List<UsrCompRole> search(String userId, String compCode, String roleId);
    public List getAssignRole(String userId);
    public List getAssignCompany(String userId);
    public List getAssignCompanySelect(String userId)throws Exception;
    public List getAssignCompany(String userId, String roleId, String compId);
    public int delete(String userId, String compCode, String roleId);
>>>>>>> 9d90b5663312bac2b0ac1ae2e6b571e906585deb
}
