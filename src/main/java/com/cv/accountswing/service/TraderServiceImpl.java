/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.TraderDao;
import com.cv.accountswing.entity.Trader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author WSwe
 */
@Service
@Transactional
public class TraderServiceImpl implements TraderService {

    @Autowired
    TraderDao dao;
    @Autowired
    SeqTableService seqService;
    @Autowired
    SystemPropertyService spService;

    @Override
    public Trader findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<Trader> searchTrader(String code, String name, String address,
            String phone, String parentCode, String compCode, String appTraderCode) {
        List<Trader> listTR = dao.searchTrader(code, name, address, phone,
                parentCode, compCode, appTraderCode);
        return listTR;
    }

    @Override
    public Trader saveTrader(Trader td) {

       /* if (td.getCode() == null || td.getCode().isEmpty()) {
            Integer macId = td.getMacId();
            String compCode = td.getCompCode();
            td.setCode(getTraderCode(macId, "Trader", "-", compCode));
        }*/
        return dao.saveTrader(td);
    }

    @Override
    public List<Trader> searchM(String updatedDate) {
        return dao.searchM(updatedDate);
    }

    private String getTraderCode(Integer macId, String option, String period, String compCode) {

        int seqNo = seqService.getSequence(macId, option, period, compCode);

        String tmpCatCode = String.format("%0" + 3 + "d", seqNo);
        return tmpCatCode;
    }
}
