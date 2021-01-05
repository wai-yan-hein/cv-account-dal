/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.RelationKeyH2;
import com.cv.inv.h2.entity.UnitRelationH2;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface RelationH2Dao {

    public UnitRelationH2 save(UnitRelationH2 relation);

    public UnitRelationH2 findByKey(RelationKeyH2 key);

    public List<UnitRelationH2> findAll();

    public List<UnitRelationH2> search(String patternId);

}
