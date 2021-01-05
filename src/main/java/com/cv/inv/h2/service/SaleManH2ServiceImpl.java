/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.SaleManH2Dao;
import com.cv.inv.h2.entity.SaleManH2;
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
public class SaleManH2ServiceImpl implements SaleManH2Service {

    @Autowired
    private SaleManH2Dao dao;

    @Override
    public SaleManH2 save(SaleManH2 saleMan) {
        dao.save(saleMan);
        return saleMan;
    }

    @Override
    public List<SaleManH2> findAll() {
        return dao.findAll();
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public SaleManH2 findById(String id) {
        return dao.findById(id);
    }

}
