/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.AssignCOA;
import com.cv.account.api.entity.AssignCOAKey;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface AssignCOADao {
    public AssignCOA save(AssignCOA acoa);
    public AssignCOA findById(AssignCOAKey key);
    public List search(String compId, String roleId, String deptCode);
    public int delete(String compId, String roleId, String deptCode, String coaCode);
    public void updateNew(String compId, String roleId, String deptCode) throws Exception;
}
