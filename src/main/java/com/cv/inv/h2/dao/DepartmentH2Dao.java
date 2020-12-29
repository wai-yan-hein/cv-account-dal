/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.DepartmentH2;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface DepartmentH2Dao {

    public DepartmentH2 save(DepartmentH2 dept);

    public DepartmentH2 findById(String id);

    public List<DepartmentH2> search(String code, String name, String compCode,
            String usrCode, String parentId);

    public int delete(String code);

    public List<DepartmentH2> findAll();
}
