/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.StockTypeH2Dao;
import com.cv.inv.h2.entity.StockTypeH2;
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
public class StockTypeH2ServiceImpl implements StockTypeH2Service {

    @Autowired
    private StockTypeH2Dao dao;

    @Override
    public StockTypeH2 save(StockTypeH2 item) {
        return dao.save(item);
    }

    @Override
    public List<StockTypeH2> findAll() {
        return dao.findAll();
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

}
