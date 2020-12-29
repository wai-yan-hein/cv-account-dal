/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.PaymentTypeH2;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface PaymentTypeH2Dao {

    public PaymentTypeH2 save(PaymentTypeH2 pt);

    public PaymentTypeH2 findById(Integer id);

    public List<PaymentTypeH2> search(String name, String compId);

    public int delete(Integer code, String compId);
}
