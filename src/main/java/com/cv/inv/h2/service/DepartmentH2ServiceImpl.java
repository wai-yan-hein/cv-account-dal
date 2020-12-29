/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.accountswing.service.*;
import com.cv.accountswing.entity.SystemProperty;
import com.cv.accountswing.entity.SystemPropertyKey;
import com.cv.inv.h2.dao.DepartmentH2Dao;
import com.cv.inv.h2.entity.DepartmentH2;
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
public class DepartmentH2ServiceImpl implements DepartmentH2Service {

    @Autowired
    DepartmentH2Dao dao;
    @Autowired
    SeqTableService seqService;
    @Autowired
    SystemPropertyService spService;

    @Override
    public DepartmentH2 save(DepartmentH2 dept) {
        if (dept.getDeptCode().isEmpty()) {
            dept.setDeptCode(getDeptCode(dept.getCompCode().toString()));
        }
        dept = dao.save(dept);
        return dept;
    }

    @Override
    public DepartmentH2 findById(String id) {
        DepartmentH2 dept = dao.findById(id);
        return dept;
    }

    @Override
    public List<DepartmentH2> search(String code, String name, String compCode, String userCode, String partentId) {
        List<DepartmentH2> listDept = dao.search(code, name, compCode, userCode, partentId);
        return listDept;
    }

    @Override
    public int delete(String code) {
        int cnt = dao.delete(code);
        return cnt;
    }

    private String getDeptCode(String compCode) {
        SystemPropertyKey spk = new SystemPropertyKey("system.dept.code.length",
                Integer.parseInt(compCode));
        SystemProperty sp = spService.findById(spk);
        int ttlLength = Integer.parseInt(sp.getPropValue());
        int seqNo = seqService.getSequence("DEPT", "-", compCode);
        String coaCode = compCode + "-" + String.format("%0" + ttlLength + "d", seqNo);
        return coaCode;
    }

    @Override
    public List<DepartmentH2> findAll() {
        return dao.findAll();
    }

}
