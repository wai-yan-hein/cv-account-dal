/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.ChargeTypeH2;
import java.util.List;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
public interface ChargeTypeH2Dao {

    public ChargeTypeH2 save(ChargeTypeH2 chargeType);

    public List<ChargeTypeH2> findAll();

    public int delete(String id);
    
  public List<ChargeTypeH2> search(String ctId, String desp);
}
