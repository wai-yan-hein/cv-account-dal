/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;


import com.cv.accountswing.dummy.VouSearch;
import com.cv.inv.h2.entity.PurHisH2;
import java.util.List;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
public interface PurchaseHisH2Service {

    public PurHisH2 save(PurHisH2 purHis) throws Exception;

    public List<PurHisH2> search(String fromDate, String toDate, String cusId, String vouStatusId, String remark);

    public List<VouSearch> searchM(String fromDate, String toDate,
            String cusId, String vouStatusId, String remark) throws Exception;

    public PurHisH2 findById(String id);

    public int delete(String vouNo);
}
