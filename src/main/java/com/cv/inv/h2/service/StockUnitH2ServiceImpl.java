/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.StockUnitH2Dao;
import com.cv.inv.h2.entity.StockUnitH2;
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
public class StockUnitH2ServiceImpl implements StockUnitH2Service {

    @Autowired
    private StockUnitH2Dao dao;

    @Override
    public StockUnitH2 save(StockUnitH2 unit) {
        return dao.save(unit);
    }

    @Override
    public List<StockUnitH2> findAll() {
        return dao.findAll();
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

}
