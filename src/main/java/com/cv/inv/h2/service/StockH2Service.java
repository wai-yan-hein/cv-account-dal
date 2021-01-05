/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.entity.Stock;
import com.cv.inv.h2.entity.StockH2;
import com.cv.inv.h2.entity.StockTypeH2;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface StockH2Service {

    public StockH2 save(StockH2 stock, StockTypeH2 item, String status);
    
    public StockH2 saveM(StockH2 stock);

    public StockH2 findById(String id);

    public List<StockH2> findAll();

    public int delete(String id);

    public List<StockH2> findActiveStock();

    public List<StockH2> search(String stockType);

    public List<StockH2> searchC(String stockCat);

    public List<StockH2> searchB(String stockBrand);

}
