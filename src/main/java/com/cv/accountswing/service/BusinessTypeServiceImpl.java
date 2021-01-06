/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.BusinessTypeDao;
import com.cv.accountswing.entity.BusinessType;
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
public class BusinessTypeServiceImpl implements BusinessTypeService{
    
    @Autowired
    private BusinessTypeDao dao;
    
     @Autowired
    private SeqTableService seqService;
    
    @Override
    public BusinessType save(BusinessType bt){
         if (bt.getCode() == null || bt.getCode().isEmpty()) {
            Integer macId = bt.getMacId();
            String compCode = bt.getCompCode();
            bt.setCode(getBusinessTypeCode(macId, "BusinessType", "-", compCode));
        }
        return dao.save(bt);
    }
    
    @Override
    public BusinessType findById(Integer id){
        return dao.findById(id);
    }
    
    @Override
    public List<BusinessType> getAllBusinessType(){
        List<BusinessType> listCT = dao.getAllBusinessType();
        return listCT;
    }
    
    @Override
    public int delete(String id){
        return dao.delete(id);
    }
    
     private String getBusinessTypeCode(Integer macId, String option, String period, String compCode) {

        int seqNo = seqService.getSequence(macId, option, period, compCode);

        String tmpCatCode = String.format("%0" + 3 + "d", seqNo);
        return tmpCatCode;
    }
}
