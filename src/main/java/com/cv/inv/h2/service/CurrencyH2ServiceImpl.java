/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.CurrencyH2Dao;
import com.cv.inv.h2.entity.CurrencyH2;
import com.cv.inv.h2.entity.CurrencyKeyH2;
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
public class CurrencyH2ServiceImpl implements CurrencyH2Service {

    @Autowired
    CurrencyH2Dao dao;

    @Override
    public CurrencyH2 save(CurrencyH2 cur) {
        cur = dao.save(cur);
        return cur;
    }

    @Override
    public CurrencyH2 findById(CurrencyKeyH2 id) {
        CurrencyH2 cur = dao.findById(id);
        return cur;
    }

    @Override
    public List<CurrencyH2> search(String code, String name, String compCode) {
        List<CurrencyH2> listCur = dao.search(code, name, compCode);
        return listCur;
    }

    @Override
    public int delete(String code, String compId) {
        int cnt = dao.delete(code, compId);
        return cnt;
    }

    @Override
    public CurrencyH2 findById(String id) {
        return dao.findById(id);
    }
}
