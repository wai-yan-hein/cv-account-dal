/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.UsrCompRoleH2Dao;
import com.cv.inv.h2.entity.UsrCompRoleH2;
import com.cv.inv.h2.entity.UsrCompRoleKeyH2;
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
public class UsrCompRoleH2ServiceImpl implements UsrCompRoleH2Service{
    
    @Autowired
    private UsrCompRoleH2Dao dao;
    
    @Override
    public UsrCompRoleH2 save(UsrCompRoleH2 ucr){
        return dao.save(ucr);
    }
    
    @Override
    public UsrCompRoleH2 findById(UsrCompRoleKeyH2 key){
        return dao.findById(key);
    }
    
    @Override
    public List<UsrCompRoleH2> search(String userId, String compCode, String roleId){
        return dao.search(userId, compCode, roleId);
    }
    
    @Override
    public List getAssignRole(String userId){
        return dao.getAssignRole(userId);
    }
    
    @Override
    public List getAssignCompany(String userId){
        return dao.getAssignCompany(userId);
    }
    
    @Override
    public List getAssignCompany(String userId, String roleId, String compId){
        return dao.getAssignCompany(userId, roleId, compId);
    }
    
    @Override
    public int delete(String userId, String compCode, String roleId){
        return dao.delete(userId, compCode, roleId);
    }
}
