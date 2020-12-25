/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.SaleHisDetailH2Dao;
import com.cv.inv.h2.entity.SaleHisDetailH2;
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
public class SaleHisDetailH2ServiceImpl implements SaleHisDetailH2Service {

    @Autowired
    private SaleHisDetailH2Dao shdDao;

    @Override
    public SaleHisDetailH2 save(SaleHisDetailH2 sdh) {
        return shdDao.save(sdh);
    }

    @Override
    public List<SaleHisDetailH2> search(String vouId) {
        return shdDao.search(vouId);

    }

    @Override
    public int delete(String id) {
        return shdDao.delete(id);
    }

}
