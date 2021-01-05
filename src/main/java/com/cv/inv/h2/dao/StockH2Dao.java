/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.StockH2;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface StockH2Dao {

    public StockH2 save(StockH2 stock);

    public StockH2 findById(String id);

    public List<StockH2> findAll();

    public int delete(String id);

    public Object getMax(String sql);

    public List<StockH2> findActiveStock();

    public List<StockH2> search(String stockType);

    public List<StockH2> searchC(String stockType);

    public List<StockH2> searchB(String stockType);

}
