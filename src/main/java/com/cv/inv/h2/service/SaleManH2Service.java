/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.SaleManH2;
import java.util.List;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
public interface SaleManH2Service {

    public SaleManH2 save(SaleManH2 saleMan);

    public List<SaleManH2> findAll();

    public int delete(String id);
    
     public SaleManH2 findById(String id);
}
