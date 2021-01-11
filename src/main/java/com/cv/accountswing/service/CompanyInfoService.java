/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.CompanyInfo;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface CompanyInfoService {

    public CompanyInfo save(CompanyInfo ci, String status, String userCode, String type);


    public CompanyInfo saveM(CompanyInfo ci);

    public CompanyInfo findById(String code);

    public List<CompanyInfo> search(String code, String name, String phone, String address,
            String businessType, String owner);

    public int delete(String code);
}
