/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.SystemProperty;
import com.cv.account.api.entity.SystemPropertyKey;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface SystemPropertyDao {
    public SystemProperty save(SystemProperty sp);
    public SystemProperty findById(SystemPropertyKey id);
    public List<SystemProperty> search(String code, String compCode, String value);
    public int delete(String code);
    public void copySystemProperty(String from, String to) throws Exception;
}
