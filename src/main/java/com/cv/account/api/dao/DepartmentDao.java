/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.Department;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface DepartmentDao {

    public Department save(Department dept);

    public Department findById(String id);

    public List<Department> search(String code, String name, String compCode,
            String usrCode, String parentId);

    public int delete(String code);

    public List<Department> findAll();
}
