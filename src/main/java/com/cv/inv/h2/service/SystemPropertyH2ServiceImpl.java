/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.SystemPropertyH2Dao;
import com.cv.inv.h2.entity.SystemPropertyH2;
import com.cv.inv.h2.entity.SystemPropertyKeyH2;
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
public class SystemPropertyH2ServiceImpl implements SystemPropertyH2Service {

    @Autowired
    SystemPropertyH2Dao dao;

    @Override
    public SystemPropertyH2 save(SystemPropertyH2 sp) {
        sp = dao.save(sp);
        return sp;
    }

    @Override
    public SystemPropertyH2 findById(SystemPropertyKeyH2 id) {
        SystemPropertyH2 sp = dao.findById(id);
        return sp;
    }

    @Override
    public List<SystemPropertyH2> search(String code, String compCode, String value) {
        List<SystemPropertyH2> listSP = dao.search(code, compCode, value);
        return listSP;
    }

    @Override
    public int delete(String code) {
        int cnt = dao.delete(code);
        return cnt;
    }

    @Override
    public void copySystemProperty(String from, String to) throws Exception {
        dao.copySystemProperty(from, to);
    }
}
