/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.inv.service;

import com.cv.account.api.dummy.SaleVouSearch;
import com.cv.account.inv.entity.SaleHis;
import java.util.List;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
public interface SaleHisService {

    public SaleHis save(SaleHis saleHis) throws Exception;

    public List<SaleHis> search(String fromDate, String toDate, String cusId, 
            String vouStatusId, String remark, String stockCode,String userId);
    
     public List<SaleVouSearch> searchM(String fromDate, String toDate, String cusId,
            String vouStatusId, String remark, String stockCode, String userId) throws Exception;

    public SaleHis findById(String id);

    public int delete(String vouNo);
}
