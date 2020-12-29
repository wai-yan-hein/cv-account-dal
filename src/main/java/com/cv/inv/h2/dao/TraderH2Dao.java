/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.entity.Trader;
import com.cv.inv.h2.entity.TraderH2;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface TraderH2Dao {

    public TraderH2 findById(Integer id);

    public List<TraderH2> searchTrader(String code, String name, String address,
            String phone, String parentCode, String compCode, String appTraderCode);

    public List<TraderH2> searchM(String updatedDate);

    public TraderH2 saveTrader(TraderH2 trader);

}
