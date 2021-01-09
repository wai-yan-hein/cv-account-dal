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
<<<<<<< HEAD
    public CompanyInfo save(CompanyInfo ci, String status, String userCode, String type);
    public CompanyInfo save(CompanyInfo ci);
=======

    public CompanyInfo save(CompanyInfo ci, String status, String userId, String type);

    public CompanyInfo saveM(CompanyInfo ci);

>>>>>>> 9d90b5663312bac2b0ac1ae2e6b571e906585deb
    public CompanyInfo findById(String code);


    public List<CompanyInfo> search(String code, String name, String phone, String address,
            String businessType, String owner);

    public int delete(String code);
}
