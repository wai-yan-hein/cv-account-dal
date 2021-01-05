/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.ChargeTypeH2Dao;
import com.cv.inv.h2.entity.ChargeTypeH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
@Service
@Transactional
public class ChargeTypeH2ServiceImpl implements ChargeTypeH2Service{
    
    @Autowired
    private ChargeTypeH2Dao dao;

    @Override
    public ChargeTypeH2 save(ChargeTypeH2 chargeType) {
        dao.save(chargeType);
        return chargeType;
    }

    @Override
    public List<ChargeTypeH2> findAll() {
        return dao.findAll();
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }
     @Override
  public List<ChargeTypeH2> search(String ctId, String desp){
        return dao.search(ctId, desp);
    }
}
