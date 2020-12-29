/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.StockBrandH2Dao;
import com.cv.inv.h2.entity.StockBrandH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SAI
 */
@Service
@Transactional
public class StockBrandH2ServiceImpl implements StockBrandH2Service {

    @Autowired
    private StockBrandH2Dao dao;

    @Override
    public StockBrandH2 save(StockBrandH2 brand) {
        return dao.save(brand);
    }

    @Override
    public StockBrandH2 findById(String id) {
        return dao.findById(id);
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

}
