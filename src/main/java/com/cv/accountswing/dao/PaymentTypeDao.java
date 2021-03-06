/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.PaymentType;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface PaymentTypeDao {
    public PaymentType save(PaymentType pt);
    public PaymentType findById(Integer id);
    public List<PaymentType> search(String name, String compCode);
    public int delete(Integer code, String compCode);
}
