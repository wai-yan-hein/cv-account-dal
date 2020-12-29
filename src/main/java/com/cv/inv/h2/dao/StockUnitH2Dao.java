/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.StockUnitH2;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface StockUnitH2Dao {

    public StockUnitH2 save(StockUnitH2 unit);

    public List<StockUnitH2> findAll();

    public int delete(String id);
}
