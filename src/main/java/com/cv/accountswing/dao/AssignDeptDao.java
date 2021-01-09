/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.AssignDept;
import com.cv.accountswing.entity.AssignDeptKey;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface AssignDeptDao {
    public AssignDept save(AssignDept ad);
    public AssignDept findById(AssignDeptKey key);
    public List search(String compCode, String roleId);
    public int delete(String compCode, String roleId, String deptCode);
    public void updateNew(String compCode, String roleId) throws Exception;
}
