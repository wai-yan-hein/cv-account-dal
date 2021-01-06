/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

import com.cv.accountswing.service.SeqTableService;
import com.cv.inv.entity.StockBrand;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cv.inv.dao.StockBrandDao;

/**
 *
 * @author Lenovo
 */
@Service
@Transactional
public class StockBrandServiceImpl implements StockBrandService {

    @Autowired
    private StockBrandDao dao;

    @Autowired
    private SeqTableService seqService;

    @Override
    public StockBrand save(StockBrand sb) {
        if (sb.getBrandCode() == null || sb.getBrandCode().isEmpty()) {
            Integer macId = sb.getMacId();
            String compCode = sb.getCompCode();
            sb.setBrandCode(getStockBrandCode(macId, "StockBrand", "-", compCode));
        }
        return dao.save(sb);
    }

    @Override
    public List<StockBrand> findAll() {
        return dao.findAll();
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    private String getStockBrandCode(Integer macId, String option, String period, String compCode) {

        int seqNo = seqService.getSequence(macId, option, period, compCode);

        String tmpCatCode = String.format("%0" + 2 + "d", macId) + "-" + String.format("%0" + 3 + "d", seqNo);
        return tmpCatCode;
    }
}
