/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.inv.service;

import com.cv.account.inv.dao.StockInOutDao;
import com.cv.account.inv.entity.StockInOut;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cv.account.inv.dao.StockInOutDetailDao;

/**
 *
 * @author Lenovo
 */
@Service
@Transactional
public class StockInOutServiceImpl implements StockInOutService {

    @Autowired
    private StockInOutDao dao;

    @Override
    public StockInOut findById(String id) {
        return dao.findById(id);
    }

    @Override
    public StockInOut save(StockInOut stock) {
        return dao.save(stock);
    }

    @Override
    public List<StockInOut> search(String batchCode, String date, String desp, String remark) {
        return dao.search(batchCode, date, desp, remark);
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

}
