/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.StockTypeH2;
import java.util.List;

/**
 *
 * @author SAI
 */
public interface StockTypeH2Service {

    public StockTypeH2 save(StockTypeH2 item);

    public List<StockTypeH2> findAll();

    public int delete(String id);

}
