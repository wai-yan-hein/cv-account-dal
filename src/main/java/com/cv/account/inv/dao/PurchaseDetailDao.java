/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.inv.dao;

import com.cv.account.inv.entity.PurHisDetail;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface PurchaseDetailDao {

    public PurHisDetail save(PurHisDetail pd);

    public List<PurHisDetail> search(String glId);

    public int delete(String id);

}
