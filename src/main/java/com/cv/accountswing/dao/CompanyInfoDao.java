/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.CompanyInfo;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface CompanyInfoDao {
    public CompanyInfo save(CompanyInfo ci);
    public CompanyInfo findById(String code);
    public List<CompanyInfo> search(String code, String name, String phone, String address,
            String businessType, String owner);
    public int delete(String code);
}
