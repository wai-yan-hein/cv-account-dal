/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.UsrCompRoleDao;
import com.cv.accountswing.entity.UsrCompRole;
import com.cv.accountswing.entity.UsrCompRoleKey;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class UsrCompRoleServiceImpl implements UsrCompRoleService{
    
    @Autowired
    private UsrCompRoleDao dao;
    
    @Override
    public UsrCompRole save(UsrCompRole ucr){
        return dao.save(ucr);
    }
    
    @Override
    public UsrCompRole findById(UsrCompRoleKey key){
        return dao.findById(key);
    }
    
    @Override
    public List<UsrCompRole> search(String userCode, String compCode, String roleId){
        return dao.search(userCode, compCode, roleId);
    }
    
    @Override
    public List getAssignRole(String userCode){
        return dao.getAssignRole(userCode);
    }
    
    @Override
    public List getAssignCompany(String userCode){
        return dao.getAssignCompany(userCode);
    }
    
    @Override
    public List getAssignCompany(String userCode, String roleId, String compCode){
        return dao.getAssignCompany(userCode, roleId, compCode);
    }
    
    @Override
    public int delete(String userCode, String compCode, String roleId){
        return dao.delete(userCode, compCode, roleId);
    }
}
