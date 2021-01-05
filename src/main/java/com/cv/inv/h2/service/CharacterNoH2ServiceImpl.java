/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.CharacterNoH2Dao;
import com.cv.inv.h2.entity.CharacterNoH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
@Transactional
@Service
public class CharacterNoH2ServiceImpl implements CharacterNoH2Service {

    @Autowired
    private CharacterNoH2Dao dao;

    @Override
    public CharacterNoH2 save(CharacterNoH2 ch) {
        return dao.save(ch);
    }

    @Override
    public List<CharacterNoH2> findAll() {
        return dao.findAll();
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public CharacterNoH2 findById(String id) {
        return dao.findById(id);
    }

}
