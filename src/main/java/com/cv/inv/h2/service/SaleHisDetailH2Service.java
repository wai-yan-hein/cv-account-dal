/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.SaleHisDetailH2;
import java.util.List;

/**
 *
 * @author SAI
 */
public interface SaleHisDetailH2Service {

    public SaleHisDetailH2 save(SaleHisDetailH2 sdh);

    public List<SaleHisDetailH2> search(String vouId);

    public int delete(String id);

}
