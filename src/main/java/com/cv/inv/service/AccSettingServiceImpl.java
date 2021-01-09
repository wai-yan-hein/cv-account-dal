/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

import com.cv.inv.entity.AccSetting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cv.inv.dao.AccSettingDao;

/**
 *
 * @author Lenovo
 */
@Service
@Transactional
public class AccSettingServiceImpl implements AccSettingService {

    @Autowired
    private AccSettingDao dao;

    @Override
    public List<AccSetting> findAll() {
        return dao.findAll();
    }

    @Override
    public AccSetting save(AccSetting setting) {
        return dao.save(setting);
    }

    @Override
    public AccSetting findByCode(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
