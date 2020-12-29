/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.VouStatusH2Dao;
import com.cv.inv.h2.entity.VouStatusH2;
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
public class VouStatusH2ServiceImpl implements VouStatusH2Service {

    @Autowired
    private VouStatusH2Dao vouDao;

    @Override
    public VouStatusH2 save(VouStatusH2 vouStatus) {
        return vouDao.save(vouStatus);
    }

    @Override
    public List<VouStatusH2> findAll() {
        return vouDao.findAll();
    }

    @Override
    public int delete(String id) {
        return vouDao.delete(id);
    }

    @Override
    public VouStatusH2 findById(String id) {
        return vouDao.findById(id);
    }

    @Override
    public List<VouStatusH2> search(String statusDesp) {
        return vouDao.search(statusDesp);
    }
}
