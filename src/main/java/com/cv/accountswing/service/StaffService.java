/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.Staff;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface StaffService {

    public Staff save(Staff cus, String traderCodeLength);

    public Staff save(Staff cus);

    public Staff findById(String id);
    
    public List<Staff> search(String code, String name, String address,
            String phone, String compCode);

    public int delete(Integer id);
}
