/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.SystemPropertyH2;
import com.cv.inv.h2.entity.SystemPropertyKeyH2;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface SystemPropertyH2Service {

    public SystemPropertyH2 save(SystemPropertyH2 sp);

    public SystemPropertyH2 findById(SystemPropertyKeyH2 id);

    public List<SystemPropertyH2> search(String code, String compCode, String value);

    public int delete(String code);

    public void copySystemProperty(String from, String to) throws Exception;
}
