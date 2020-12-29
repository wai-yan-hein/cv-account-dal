/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.LocationH2Dao;
import com.cv.inv.h2.entity.LocationH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SAI
 */
@Service
@Transactional
public class LocationH2ServiceImpl implements LocationH2Service {

    @Autowired
    private LocationH2Dao dao;

    @Override
    public LocationH2 save(LocationH2 loc) {
        return dao.save(loc);
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);

    }

    @Override
    public LocationH2 findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<LocationH2> search(String parent) {
        return dao.search(parent);
    }

    @Override
    public List<LocationH2> findAll(){
        return dao.findAll();
    }
}
