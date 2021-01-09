/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.CoaDeptMapDao;
import com.cv.accountswing.entity.CoaDeptMap;
import com.cv.accountswing.entity.CoaDeptMapKey;
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
public class CoaDeptMapServiceImpl implements CoaDeptMapService{
    @Autowired
    private CoaDeptMapDao dao;
    
    @Override
    public CoaDeptMap save(String compCode, String coaCode, String dept){
        CoaDeptMap cdm = new CoaDeptMap();
        CoaDeptMapKey cdmk = new CoaDeptMapKey();
        
        cdmk.setCoaCode(coaCode);
        cdmk.setCompCode(compCode);
        cdmk.setDeptCode(dept);
        cdm.setKey(cdmk);
        
        return dao.save(cdm);
    }
    
    @Override
    public CoaDeptMap findById(CoaDeptMapKey id){
        return dao.findById(id);
    }
    
    @Override
    public List<CoaDeptMap> search(String compCode, String coaCode, String dept){
        return dao.search(compCode, coaCode, dept);
    }
    
    @Override
    public List searchMap(String compCode, String coaCode, String dept){
        return dao.searchMap(compCode, coaCode, dept);
    }
    
    @Override
    public int delete(String compCode, String coaCode, String dept){
        return dao.delete(compCode, coaCode, dept);
    }
}
