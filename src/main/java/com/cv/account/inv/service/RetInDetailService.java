/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.inv.service;

import com.cv.account.inv.entity.PurHis;
import com.cv.account.inv.entity.PurHisDetail;
import com.cv.account.inv.entity.RetInHisDetail;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface RetInDetailService {

    public RetInHisDetail save(RetInHisDetail pd);

    public List<RetInHisDetail> search(String glId);

  //  public void save(PurHis gl, List<PurchaseDetail> pd,List<String> delList);

}
