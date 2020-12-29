/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.LocationH2;
import java.util.List;

/**
 *
 * @author SAI
 */
public interface LocationH2Service {

    public LocationH2 save(LocationH2 loc);
    
    public List<LocationH2> findAll();

    public int delete(String id);

    public LocationH2 findById(String id);

    public List<LocationH2> search(String parent);

}
