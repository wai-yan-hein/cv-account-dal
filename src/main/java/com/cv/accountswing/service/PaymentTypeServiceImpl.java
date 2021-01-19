/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.PaymentTypeDao;
import com.cv.accountswing.entity.PaymentType;
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
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    PaymentTypeDao dao;

    @Autowired
    private SeqTableService seqService;

    @Override
    public PaymentType save(PaymentType pt) {
        if (pt.getTypeCode() == null || pt.getTypeCode().isEmpty()) {
            Integer macId = pt.getMacId();
            String compCode = pt.getCompCode();
            pt.setTypeCode(getPaymentTypeCode(macId, "PaymentType", "-", compCode));
        }
        return dao.save(pt);
    }

    @Override
    public PaymentType findById(Integer id) {
        PaymentType pt = dao.findById(id);
        return pt;
    }

    @Override
    public List<PaymentType> search(String name, String compCode) {
        List<PaymentType> listPT = dao.search(name, compCode);
        return listPT;
    }

    @Override
    public int delete(Integer code, String compCode) {
        int cnt = dao.delete(code, compCode);
        return cnt;
    }

    private String getPaymentTypeCode(Integer macId, String option, String period, String compCode) {

        int seqNo = seqService.getSequence(macId, option, period, compCode);

        String tmpCatCode = String.format("%0" + 3 + "d", macId) + "-" + String.format("%0" + 4 + "d", seqNo);
        return tmpCatCode;
    }
}
