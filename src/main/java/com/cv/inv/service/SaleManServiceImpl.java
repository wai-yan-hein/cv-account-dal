/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

import com.cv.accountswing.service.SeqTableService;
import com.cv.inv.dao.SaleManDao;
import com.cv.inv.entity.SaleMan;
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
public class SaleManServiceImpl implements SaleManService {

    @Autowired
    private SaleManDao dao;
    @Autowired
    private SeqTableService seqService;

    @Override
    public SaleMan save(SaleMan sm) {
         if (sm.getSaleManCode()== null || sm.getSaleManCode().isEmpty()) {
            Integer macId = sm.getMacId();
            String compCode = sm.getCompCode();
            String getCode=getSaleManCode(macId, "Category", "-", compCode);
            sm.setSaleManCode(getCode);
        }
        return dao.save(sm);
    }

    @Override
    public List<SaleMan> findAll() {
        return dao.findAll();
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public SaleMan findById(String id) {
        return dao.findById(id);
    }

     private String getSaleManCode(Integer macId, String option, String period, String compCode) {

        int seqNo = seqService.getSequence(macId, option, period, compCode);

        String tmpCatCode = String.format("%0" + 2 + "d", macId) + "-" + String.format("%0" + 3 + "d", seqNo);
        return tmpCatCode;
    }
}
