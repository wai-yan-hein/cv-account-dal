/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.RelationH2Dao;
import com.cv.inv.h2.entity.RelationKeyH2;
import com.cv.inv.h2.entity.UnitRelationH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
@Service
@Transactional
public class RelationH2ServiceImpl implements RelationH2Service {

    @Autowired
    private RelationH2Dao dao;

    @Override
    public UnitRelationH2 save(UnitRelationH2 relation) {
        return dao.save(relation);
    }

    @Override
    public UnitRelationH2 findByKey(RelationKeyH2 key) {
        return dao.findByKey(key);
    }

    @Override
    public List<UnitRelationH2> findAll() {
        return dao.findAll();
    }

    @Override
    public List<UnitRelationH2> search(String patternId) {
        return dao.search(patternId);
    }

}
