/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.PurHisDetailH2;
import com.cv.inv.h2.entity.PurHisH2;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface PurchaseDetailH2Service {

    public PurHisDetailH2 save(PurHisDetailH2 pd);

    public List<PurHisDetailH2> search(String glId);

    public void save(PurHisH2 gl, List<PurHisDetailH2> pd,List<String> delList);

}
