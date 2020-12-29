/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.accountswing.service.*;
import com.cv.inv.h2.dao.TraderH2Dao;
import com.cv.inv.h2.entity.TraderH2;
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
public class TraderH2ServiceImpl implements TraderH2Service {

    @Autowired
    TraderH2Dao dao;
    @Autowired
    SeqTableService seqService;
    @Autowired
    SystemPropertyService spService;

    @Override
    public TraderH2 findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<TraderH2> searchTrader(String code, String name, String address,
            String phone, String parentCode, String compCode, String appTraderCode) {
        List<TraderH2> listTR = dao.searchTrader(code, name, address, phone,
                parentCode, compCode, appTraderCode);
        return listTR;
    }

    @Override
    public TraderH2 saveTrader(TraderH2 trader) {

        return dao.saveTrader(trader);
    }

    @Override
    public List<TraderH2> searchM(String updatedDate) {
        return dao.searchM(updatedDate);
    }
}
