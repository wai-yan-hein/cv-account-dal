/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.view.VCOAOpening;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cv.accountswing.dao.VOpeningDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Lenovo
 */
@Service
@Transactional
public class VOpeningServiceImpl implements VOpeningService {

    @Autowired
    private VOpeningDao dao;

    @Override
    public List<VCOAOpening> search(String opDate, String sourceAccId, String userCode, 
            String compCode, String depId, String curCode,
            String traderType, String coaParent, String regCode) {
        return dao.search(opDate, sourceAccId, userCode, compCode, depId, curCode,
                traderType, coaParent, regCode);
    }

}
