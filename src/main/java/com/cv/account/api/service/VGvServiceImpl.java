/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.service;

import com.cv.account.api.dao.VGvDao;
import com.cv.account.api.entity.view.VGeneralVoucher;
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
public class VGvServiceImpl implements VGvService{
    
    @Autowired
    private VGvDao dao;
    
    @Override
    public List<VGeneralVoucher> search(String from, String to, String vouNo,
            String ref, String compCode, String projectId){
        List<VGeneralVoucher> listVGV = dao.search(from, to, vouNo, ref, compCode, projectId);
        return listVGV;
    }
}
