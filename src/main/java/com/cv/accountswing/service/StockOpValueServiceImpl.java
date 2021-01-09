/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.StockOpValueDao;
import com.cv.accountswing.entity.StockOpValue;
import com.cv.accountswing.entity.StockOpValueKey;
import com.cv.accountswing.util.Util1;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class StockOpValueServiceImpl implements StockOpValueService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StockOpValueServiceImpl.class);

    @Autowired
    StockOpValueDao dao;

    @Override
    public StockOpValue save(StockOpValue value, String userCode) {
        try {
            String tranDate = Util1.toDateStr(value.getKey().getTranDate(), "yyyy-MM-dd");
            String coaCode = value.getKey().getCoaCode();
            String currancy = value.getKey().getCurrency();
            String compCode = value.getKey().getCompCode();
            String dept = value.getKey().getDeptCode();
            dao.backup(tranDate, coaCode, dept, currancy, compCode, userCode, "EDIT");
        } catch (Exception ex) {
            LOGGER.error("Save Stock Op Value :" + ex.getMessage());

        }

        dao.save(value);
        return value;
    }

    @Override
    public StockOpValue findById(StockOpValueKey key) {
        return dao.findById(key);
    }

    @Override
    public List search(String from, String to, String coaCode, String currency,
            String dept, String compCode) {
        return dao.search(from, to, coaCode, currency, dept, compCode);
    }

    @Override
    public void backup(String tranDate, String coaCode, String dept, String currency,
            String compCode, String userCode, String option) throws Exception {
        dao.backup(tranDate, coaCode, dept, currency, compCode, userCode, option);
    }

    @Override
    public int delete(String tranDate, String coaCode, String dept, String currency,
            String compCode, String userCode) {
        try {
            dao.backup(tranDate, coaCode, dept, currency, compCode, userCode, "DELETE");
        } catch (Exception ex) {

        }

        int cnt = dao.delete(tranDate, coaCode, dept, currency, compCode);
        return cnt;
    }

    @Override
    public List<StockOpValue> findAll() {
        return dao.findAll();
    }
}
