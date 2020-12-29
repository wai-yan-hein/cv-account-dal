/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.StockUnitH2;
import java.util.List;

/**
 *
 * @author SAI
 */
public interface StockUnitH2Service {

    public StockUnitH2 save(StockUnitH2 unit);

    public List<StockUnitH2> findAll();

    public int delete(String id);

}
