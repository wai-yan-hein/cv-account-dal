package com.cv.inv.h2.service;

import com.cv.inv.entity.VouId;
import com.cv.inv.h2.dao.SaleHisH2Dao;
import com.cv.inv.h2.entity.SaleHisH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author SAI
 */
@Service
@Transactional
public class SaleHisH2ServiceImpl implements SaleHisH2Service {

    @Autowired
    private SaleHisH2Dao hisDao;

    @Override
    public SaleHisH2 save(SaleHisH2 saleHis) throws Exception {
        hisDao.save(saleHis);
        return saleHis;
    }

    @Override
    public List<SaleHisH2> search(String fromDate, String toDate,
            String cusId, String vouStatusId, String remark, String stockCode, String userId) {
        return hisDao.search(fromDate, toDate, cusId, vouStatusId, remark, stockCode, userId);
    }

    @Override
    public SaleHisH2 findById(String id) {
        return hisDao.findById(id);
    }

    @Override
    public int delete(String vouNo) {
        return hisDao.delete(vouNo);
    }

    @Override
    public SaleHisH2 saveM(SaleHisH2 shH2, List<String> delSDH2, List<String> delSEH2,
            List<String> delSOH2, List<String> delSWH2, String status, VouId vouId){
        
        return null;

    }
}
