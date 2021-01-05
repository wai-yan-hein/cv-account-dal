/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.UsrCompRoleH2;
import com.cv.inv.h2.entity.UsrCompRoleKeyH2;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface UsrCompRoleH2Service {

    public UsrCompRoleH2 save(UsrCompRoleH2 ucr);

    public UsrCompRoleH2 findById(UsrCompRoleKeyH2 key);

    public List<UsrCompRoleH2> search(String userId, String compCode, String roleId);

    public List getAssignRole(String userId);

    public List getAssignCompany(String userId);

    public List getAssignCompany(String userId, String roleId, String compId);

    public int delete(String userId, String compCode, String roleId);
}
