/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.PurHisDetailH2;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface PurchaseDetailH2Dao {

    public PurHisDetailH2 save(PurHisDetailH2 pd);

    public List<PurHisDetailH2> search(String glId);

    public int delete(String id);

}
