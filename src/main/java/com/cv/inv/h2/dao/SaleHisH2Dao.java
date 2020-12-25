/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.entity.VouId;
import com.cv.inv.h2.entity.SaleHisH2;
import java.util.List;

/**
 *
 * @author SAI
 */
public interface SaleHisH2Dao {

    public SaleHisH2 save(SaleHisH2 sh);

    public List<SaleHisH2> search(String fromDate, String toDate, String cusId,
            String vouStatusId, String remark, String stockCode, String userId);

    public SaleHisH2 findById(String id);

    

    public int delete(String vouNo);

}
