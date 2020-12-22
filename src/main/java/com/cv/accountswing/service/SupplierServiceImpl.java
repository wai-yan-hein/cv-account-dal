/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.SupplierDao;
import com.cv.accountswing.entity.Supplier;
import com.cv.accountswing.entity.SystemProperty;
import com.cv.accountswing.entity.SystemPropertyKey;
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
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierDao dao;
    @Autowired
    SeqTableService seqService;
    @Autowired
    SystemPropertyService spService;

    @Override
    public Supplier save(Supplier sup, String traderCodeLength) {
        if (sup.getTraderId().isEmpty() || sup.getTraderId() == null) {
            String tmpTraderId = getTraderId("SUP", "-", sup.getCompCode().toString(), traderCodeLength);
            sup.setTraderId(tmpTraderId);
        }
        sup = dao.save(sup);
        return sup;
    }

    @Override
    public Supplier findById(Integer id) {
        Supplier sup = dao.findById(id);
        return sup;
    }

    @Override
    public List<Supplier> search(String code, String name, String address,
            String phone, String compCode) {
        List<Supplier> listSup = dao.search(code, name, address, phone, compCode);
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