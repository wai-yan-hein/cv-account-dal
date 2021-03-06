/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.Trader;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface TraderDao {

    public Trader findById(String id);

    public List<Trader> searchTrader(String code, String name, String address,
            String phone, String parentCode, String compCode, String appTraderCode);

    public List<Trader> searchM(String updatedDate);

    public Trader saveTrader(Trader trader);

    public List<Trader> search(String regionCode, String coaCode);

}
