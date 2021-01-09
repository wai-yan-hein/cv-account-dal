/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.AssignDept;
import com.cv.accountswing.entity.AssignDeptKey;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class AssignDeptDaoImpl extends AbstractDao<AssignDeptKey, AssignDept> implements AssignDeptDao{
    
    @Override
    public AssignDept save(AssignDept ad){
        persist(ad);
        return ad;
    }
    
    @Override
    public AssignDept findById(AssignDeptKey key){
        AssignDept ad = getByKey(key);
        return ad;
    }
    
    @Override
    public List search(String compCode, String roleId){
        String strFilter = "";
        
        if(!compCode.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.key.compCode = " + compCode;
            }else{
                strFilter = strFilter + " and o.key.compCode = " + compCode;
            }
        }
        
        if(!roleId.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.key.roleId = " + roleId;
            }else{
                strFilter = strFilter + " and o.key.roleId = " + roleId;
            }
        }
        
        List list = null;
        if(!strFilter.isEmpty()){
            strFilter = "select o from VAssignDept o where " + strFilter;
            list = findHSQL(strFilter);
        }
        
        return list;
    }
    
    @Override
    public int delete(String compCode, String roleId, String deptCode){
        int cnt = 0;
        String strFilter = "";
        
        if(!compCode.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.compCode = " + compCode;
            }else{
                strFilter = strFilter + " and o.compCode = " + compCode;
            }
        }
        
        if(!roleId.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.roleId = " + roleId;
            }else{
                strFilter = strFilter + " and o.roleId = " + roleId;
            }
        }
        
        if(!deptCode.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.deptCode = " + deptCode;
            }else{
                strFilter = strFilter + " and o.deptCode = " + deptCode;
            }
        }
        
        if(!strFilter.isEmpty()){
            strFilter = "delete from AssignDept o where " + strFilter;
            cnt = execUpdateOrDelete(strFilter);
        }
        
        return cnt;
    }
    
    @Override
    public void updateNew(String compCode, String roleId) throws Exception{
        String strSql = "insert into assign_dept(comp_code, role_id, dept_code)\n" +
            "select comp_code," + roleId + ", dept_code\n" +
            "from department\n" +
            "where comp_code = " + compCode + " and dept_code not in " +
            "(select dept_code from assign_dept where comp_code = " + compCode + 
            " and role_id = " + roleId + ")";
        execSQL(strSql);
    }
}
