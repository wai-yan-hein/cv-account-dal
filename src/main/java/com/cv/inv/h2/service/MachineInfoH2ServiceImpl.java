/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.MachineInfoH2Dao;
import com.cv.inv.h2.entity.MachineInfoH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lenovo
 */
@Service
@Transactional
public class MachineInfoH2ServiceImpl implements MachineInfoH2Service {

    @Autowired
    private MachineInfoH2Dao machineInfoDao;

    @Override
    public MachineInfoH2 save(MachineInfoH2 machineInfo) throws Exception {
        return machineInfoDao.save(machineInfo);
    }

    @Override
    public int getMax(String machineName) throws Exception {
        return machineInfoDao.getMax(machineName);
    }

    @Override
    public List<MachineInfoH2> findAll() throws Exception {
        return machineInfoDao.findAll();
    }

    @Override
    public MachineInfoH2 findById(String id) throws Exception {
        return machineInfoDao.findById(id);
    }

    @Override
    public List<MachineInfoH2> search(String name, String ip) {
        return machineInfoDao.search(name, ip);
    }

    @Override
    public List<MachineInfoH2> searchM(String name) {
        return machineInfoDao.searchM(name);
    }
}
