/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.COAOpeningDao;
import com.cv.accountswing.util.Util1;
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
public class COAOpeningServiceImpl implements COAOpeningService {

    @Autowired
    private COAOpeningDao dao;

    @Override
    public void GenerateZero(String tranIdH, String compCode, String currCode) throws Exception {
        dao.GenerateZero(tranIdH, compCode, currCode);
    }

    @Override
    public void deleteOpening(Long id) throws Exception {
        dao.deleteOpening(id);
    }

    @Override
    public void GenerateZeroGL(String opDate, String userCode, String compCode,
            String currCode, String dept, String coaGroup) throws Exception {
        opDate = Util1.toDateStrMYSQL(opDate, "dd/MM/yyyy");
        dao.deleteOpeningGL(opDate, compCode, currCode, dept);
        dao.GenerateZeroGL(opDate, userCode, compCode, currCode, dept, coaGroup);

    }

    @Override
    public void generateZeroOpening(String opDate, String userCode, String compCode, String currCode, String dept, String coaGroup) throws Exception {
        opDate = Util1.toDateStrMYSQL(opDate, "dd/MM/yyyy");
        dao.deleteOpening(opDate, compCode, currCode, dept);
        dao.generateZeroOpening(opDate, userCode, compCode, currCode, dept, coaGroup);
    }
}
