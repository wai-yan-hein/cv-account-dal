/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.TraderType;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface TraderTypeDao {

    public TraderType save(TraderType trader);

    public List<TraderType> findAll();

    public int delete(String id);

    public List<TraderType> search(String desp);

    public TraderType findById(String id);
}
