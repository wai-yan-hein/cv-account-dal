/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.PaymentTypeH2Dao;
import com.cv.inv.h2.entity.PaymentTypeH2;
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
public class PaymentTypeH2ServiceImpl implements PaymentTypeH2Service {

    @Autowired
    PaymentTypeH2Dao dao;

    @Override
    public PaymentTypeH2 save(PaymentTypeH2 pt) {
        pt = dao.save(pt);
        return pt;
    }

    @Override
    public PaymentTypeH2 findById(Integer id) {
        PaymentTypeH2 pt = dao.findById(id);
        return pt;
    }

    @Override
    public List<PaymentTypeH2> search(String name, String compId) {
        List<PaymentTypeH2> listPT = dao.search(name, compId);
        return listPT;
    }

    @Override
    public int delete(Integer code, String compId) {
        int cnt = dao.delete(code, compId);
        return cnt;
    }
}
