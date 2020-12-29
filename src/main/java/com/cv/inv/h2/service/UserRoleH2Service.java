/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.UserRoleH2;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface UserRoleH2Service {

    public UserRoleH2 save(UserRoleH2 role);

    public UserRoleH2 findById(Integer id);

    public List<UserRoleH2> search(String roleName, String compCode);

    public int delete(String id);

    public UserRoleH2 copyRole(String copyRoleId, String compCode);

    public List<UserRoleH2> searchM(String updatedDate);
}
