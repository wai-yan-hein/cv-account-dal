/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.UnitPatternH2Dao;
import com.cv.inv.h2.entity.UnitPatternH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SAI
 */
@Service
@Transactional
public class UnitPatternH2ServiceImpl implements UnitPatternH2Service{

    @Autowired
    private UnitPatternH2Dao dao;
    @Override
    public UnitPatternH2 save(UnitPatternH2 unit) {
   return dao.save(unit);
    }

    @Override
    public List<UnitPatternH2> findAll() {
    return dao.findAll();
    }

    @Override
    public int delete(String id) {
   return dao.delete(id);
   }
    
}
