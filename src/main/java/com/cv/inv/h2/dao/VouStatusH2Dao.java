/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import java.util.List;
import com.cv.inv.h2.entity.VouStatusH2;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
public interface VouStatusH2Dao {

    public VouStatusH2 save(VouStatusH2 vouStatus);

    public List<VouStatusH2> findAll();

    public int delete(String id);

    public VouStatusH2 findById(String id);

    public List<VouStatusH2> search(String statusDesp);

}
