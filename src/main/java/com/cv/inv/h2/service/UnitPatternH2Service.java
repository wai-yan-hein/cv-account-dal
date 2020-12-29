/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.UnitPatternH2;
import java.util.List;

/**
 *
 * @author SAI
 */
public interface UnitPatternH2Service {

    public UnitPatternH2 save(UnitPatternH2 unit);

    public List<UnitPatternH2> findAll();

    public int delete(String id);
}
