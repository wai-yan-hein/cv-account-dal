/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.service;

import com.cv.account.api.dao.VCrDrVoucherDao;
import com.cv.account.api.entity.view.VCrDrVoucher;
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
public class VCrDrVoucherServiceImpl implements VCrDrVoucherService {
    
    @Autowired
    VCrDrVoucherDao dao;
    
    @Override
    public List<VCrDrVoucher> search(String from, String to, String sourceAccId,
            String frmCurr, String dept, String vouNo, String compCode, String deptName,
            String splitId, String fromDesp, String naration, String remark, String toDesp){
        return dao.search(from, to, sourceAccId, frmCurr, dept, vouNo, compCode, 
                deptName, splitId, fromDesp, naration, remark, toDesp);
    }
}
