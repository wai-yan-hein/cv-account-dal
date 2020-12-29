/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.CurrencyH2;
import com.cv.inv.h2.entity.CurrencyKeyH2;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface CurrencyH2Dao {

    public CurrencyH2 save(CurrencyH2 cur);

    public CurrencyH2 findById(CurrencyKeyH2 id);

    public CurrencyH2 findById(String id);

    public List<CurrencyH2> search(String code, String name, String compCode);

    public int delete(String code, String compCode);
}
