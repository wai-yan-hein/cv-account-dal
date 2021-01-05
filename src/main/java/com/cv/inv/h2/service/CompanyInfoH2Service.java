/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.CompanyInfoH2;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface CompanyInfoH2Service {

    public CompanyInfoH2 save(CompanyInfoH2 ci, String status, String userId, String type);
    
    public CompanyInfoH2 saveM(CompanyInfoH2 ci);
    
    public List getAssignCompany(String userId);

    public CompanyInfoH2 findById(Integer code);

    public List<CompanyInfoH2> search(String code, String name, String phone, String address,
            String businessType, String owner);

    public int delete(String code);
}
