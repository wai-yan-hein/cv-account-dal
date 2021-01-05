/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import java.sql.ResultSet;
import com.cv.inv.entity.PurHis;
import com.cv.inv.h2.entity.PurHisH2;
import java.util.List;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
public interface PurchaseHisH2Dao {

    public PurHisH2 save(PurHisH2 sh);

    public List<PurHisH2> search(String fromDate, String toDate, String cusId, String vouStatusId, String remark);

    public ResultSet searchM(String fromDate, String toDate,
            String cusId, String vouStatusId, String remark) throws Exception;

    public PurHisH2 findById(String id);

    public int delete(String vouNo);

}
