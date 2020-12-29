/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.TraderTypeH2;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface TraderTypeH2Dao {

    public TraderTypeH2 save(TraderTypeH2 trader);

    public List<TraderTypeH2> findAll();

    public int delete(String id);

    public List<TraderTypeH2> search(String desp);

    public TraderTypeH2 findById(String id);
}
