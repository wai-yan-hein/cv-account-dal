/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.AssignCOA;
import com.cv.accountswing.entity.AssignCOAKey;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface AssignCOAService {
    public AssignCOA save(AssignCOA acoa);
    public AssignCOA findById(AssignCOAKey key);
    public List search(String compCode, String roleId, String deptCode);
    public int delete(String compCode, String roleId, String deptCode, String coaCode);
    public void updateNew(String compCode, String roleId, String deptCode) throws Exception;
}
