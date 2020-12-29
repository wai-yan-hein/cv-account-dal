/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.TraderTypeH2Dao;
import com.cv.inv.h2.entity.TraderTypeH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
@Transactional
@Service
public class TraderTypeServiceImpl implements TraderTypeH2Service {

    @Autowired
    private TraderTypeH2Dao dao;

    @Override
    public TraderTypeH2 save(TraderTypeH2 ch) {
        return dao.save(ch);
    }

    @Override
    public List<TraderTypeH2> findAll() {
        return dao.findAll();
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public TraderTypeH2 findById(String id) {
        return dao.findById(id);
    }
    
       @Override
    public List<TraderTypeH2> search(String desp){
        return dao.search(desp);
    }

}
