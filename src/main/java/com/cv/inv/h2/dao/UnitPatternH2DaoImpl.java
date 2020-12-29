/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.UnitPatternH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class UnitPatternH2DaoImpl extends AbstractDao<Integer, UnitPatternH2> implements UnitPatternH2Dao {

    @Override
    public UnitPatternH2 save(UnitPatternH2 unit) {
        persist(unit);
        return unit;
    }

    @Override
    public List<UnitPatternH2> findAll() {
        String hsql = "select o from UnitPatternH2 o ";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String delSql = "delete from unit_pattern_h2 where patter_id = " + id + " ";
        return execUpdateOrDelete(delSql);
    }

}
