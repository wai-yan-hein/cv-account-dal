/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.CharacterNoH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class CharacterNoH2DaoImpl extends AbstractDao<String, CharacterNoH2> implements CharacterNoH2Dao {

    @Override
    public CharacterNoH2 save(CharacterNoH2 ch) {
        persist(ch);
        return ch;
    }

    @Override
    public List<CharacterNoH2> findAll() {
        String hsql = "select o from CharacterNoH2 o";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from CharacterNoH2 o where o.ch='" + id + "'";
        return execUpdateOrDelete(hsql);
    }

    @Override
    public CharacterNoH2 findById(String id) {
        return getByKey(id);
    }

    @Override
    public Object search(String id) {
        String sql = "select from CharacterNoH2 where o.ch ='" + id + "'";
        return getAggregate(sql);
    }

}
