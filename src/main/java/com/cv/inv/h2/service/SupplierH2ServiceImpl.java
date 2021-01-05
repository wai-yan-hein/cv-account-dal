/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.accountswing.service.*;
import com.cv.accountswing.entity.Supplier;
import com.cv.inv.h2.dao.SupplierH2Dao;
import com.cv.inv.h2.entity.SupplierH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class SupplierH2ServiceImpl implements SupplierH2Service {

    @Autowired
    SupplierH2Dao dao;
    @Autowired
    SeqTableService seqService;
    @Autowired
    SystemPropertyService spService;

    @Override
    public SupplierH2 save(SupplierH2 sup, String traderCodeLength) {
        if (sup.getTraderId().isEmpty() || sup.getTraderId() == null) {
            String tmpTraderId = getTraderId("SUP", "-", sup.getCompCode().toString(), traderCodeLength);
            sup.setTraderId(tmpTraderId);
        }
        sup = dao.save(sup);
        return sup;
    }
    
    @Override
    public SupplierH2 saveM(SupplierH2 sup){
        return dao.save(sup);
    }

    @Override
    public SupplierH2 findById(Integer id) {
        SupplierH2 sup = dao.findById(id);
        return sup;
    }

    @Override
    public List<SupplierH2> search(String code, String name, String address,
            String phone, String compCode) {
        List<SupplierH2> listSup = dao.search(code, name, address, phone, compCode);
        return listSup;
    }

    @Override
    public int delete(Integer id) {
        int cnt = dao.delete(id);
        return cnt;
    }

    private String getTraderId(String option, String period, String compCode, String traderCodeLength) {
        int ttlLength = 5;
        if (traderCodeLength != null) {
            ttlLength = Integer.parseInt(traderCodeLength);
        }
        int seqNo = seqService.getSequence(option, period, compCode);
        String tmpTraderId = option.toUpperCase() + String.format("%0" + ttlLength + "d", seqNo);
        return tmpTraderId;
    }

}
