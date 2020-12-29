/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.CustomerH2;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface CustomerH2Service {

    public CustomerH2 save(CustomerH2 cus, String traderCodeLength);

    public CustomerH2 findById(Integer id);

    public List<CustomerH2> search(String code, String name, String address,
            String phone, String compCode);

    public int delete(Integer id);
}
