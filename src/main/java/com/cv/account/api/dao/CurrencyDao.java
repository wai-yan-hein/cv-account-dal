/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.Currency;
import com.cv.account.api.entity.CurrencyKey;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface CurrencyDao {

    public Currency save(Currency cur);

    public Currency findById(CurrencyKey id);

    public Currency findById(String id);

    public List<Currency> search(String code, String name, String compCode);

    public int delete(String code, String compCode);
}
