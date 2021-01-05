/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.CharacterNoH2;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface CharacterNoH2Dao {

    public CharacterNoH2 save(CharacterNoH2 ch);

    public List<CharacterNoH2> findAll();

    public int delete(String id);

    public CharacterNoH2 findById(String id);

    public Object search(String id);
}
