/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.StockBrandH2;

/**
 *
 * @author SAI
 */
public interface StockBrandH2Service {

    public StockBrandH2 save(StockBrandH2 brand);

    public StockBrandH2 findById(String id);

    public int delete(String id);

}
