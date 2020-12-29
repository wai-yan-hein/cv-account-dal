/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.LocationH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SAI
 */
@Repository
public class LocationH2DaoImpl extends AbstractDao<Integer, LocationH2> implements LocationH2Dao {

    @Override
    public LocationH2 save(LocationH2 loc) {
        persist(loc);
        return loc;
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from LocationH2 o where o.locationId='" + id + "'";
        return execUpdateOrDelete(hsql);
    }

    @Override
    public LocationH2 findById(String id) {
        return getByKey(Integer.parseInt(id));

    }
    
     @Override
    public List<LocationH2> findAll() {
        String hsql = "select o from LocationH2 o";
        return findHSQL(hsql);
    }

    @Override
    public List<LocationH2> search(String parent) {
        String hsql = "select o from LocationH2 o where o.parent ='" + parent + "'";
        return findHSQL(hsql);
    }

}
