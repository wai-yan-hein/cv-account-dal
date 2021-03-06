/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.Supplier;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface SupplierService {

    public Supplier save(Supplier sup, String traderCodeLength);
    
    public Supplier save(Supplier sup);

    public Supplier findById(String id);

    public List<Supplier> search(String code, String name, String address,
            String phone, String compCode);

    public int delete(Integer id);
}
