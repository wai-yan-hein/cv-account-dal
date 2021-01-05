/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.accountswing.service.*;
import com.cv.accountswing.entity.SystemProperty;
import com.cv.accountswing.entity.SystemPropertyKey;
import com.cv.inv.h2.dao.CustomerH2Dao;
import com.cv.inv.h2.entity.CustomerH2;
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
public class CustomerH2ServiceImpl implements CustomerH2Service {

    @Autowired
    CustomerH2Dao dao;
    @Autowired
    SeqTableService seqService;
    @Autowired
    SystemPropertyService spService;

    @Override
    public CustomerH2 save(CustomerH2 cus, String traderCodeLength) {
        if (cus.getTraderId() == null || cus.getTraderId().isEmpty()) {
            String tmpTraderId = getTraderId("CUS", "-", cus.getCompCode().toString(), traderCodeLength);
            cus.setTraderId(tmpTraderId);
        }
        return dao.save(cus);
    }

    @Override
    public CustomerH2 findById(Integer id) {
        CustomerH2 cus = dao.findById(id);
        return cus;
    }

    @Override
    public CustomerH2 saveM(CustomerH2 cus) {
        return dao.save(cus);
    }

    @Override
    public List<CustomerH2> search(String code, String name, String address,
            String phone, String compCode) {
        List<CustomerH2> listCus = dao.search(code, name, address, phone, compCode);
        return listCus;
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

    private boolean isAutoGenerate(int compCode) {
        boolean status = false;
        SystemPropertyKey spk = new SystemPropertyKey("system.trader.id.auto.generate",
                compCode);
        SystemProperty sp = spService.findById(spk);

        if (sp != null) {
            if (sp.getPropValue() != null) {
                if (sp.getPropValue().equals("Y")) {
                    status = true;
                }
            }
        }

        return status;
    }
}
