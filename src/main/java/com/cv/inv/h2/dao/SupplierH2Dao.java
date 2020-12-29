/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.SupplierH2;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface SupplierH2Dao {

    public SupplierH2 save(SupplierH2 sup);

    public SupplierH2 findById(Integer id);

    public List<SupplierH2> search(String code, String name, String address,
            String phone, String compCode);

    public int delete(Integer id);
}
