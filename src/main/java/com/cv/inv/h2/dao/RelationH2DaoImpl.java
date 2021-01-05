/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.RelationKeyH2;
import com.cv.inv.h2.entity.UnitRelationH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class RelationH2DaoImpl extends AbstractDao<RelationKeyH2, UnitRelationH2> implements RelationH2Dao {

    @Override
    public UnitRelationH2 save(UnitRelationH2 relation) {
        persist(relation);
        return relation;
    }

    @Override
    public UnitRelationH2 findByKey(RelationKeyH2 key) {
        UnitRelationH2 byKey = getByKey(key);
        return byKey;
    }

    @Override
    public List<UnitRelationH2> findAll() {
        String hsql = "select o from UnitRelationH2 o";
        List<UnitRelationH2> listRelation = findHSQL(hsql);
        return listRelation;
    }

    @Override
    public List<UnitRelationH2> search(String patternId) {
        String hsql = "select o from UnitRelationH2 o where o.unitKey.patternId = '" + patternId + "'";
        return findHSQL(hsql);
    }

}
