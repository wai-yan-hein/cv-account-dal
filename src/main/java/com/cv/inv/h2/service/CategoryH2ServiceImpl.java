/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.CategoryH2Dao;
import com.cv.inv.h2.entity.CategoryH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SAI
 */
@Service
@Transactional
public class CategoryH2ServiceImpl implements CategoryH2Service {

    @Autowired
    private CategoryH2Dao dao;

    @Override
    public CategoryH2 save(CategoryH2 item) {
        return dao.save(item);
    }

    @Override
    public CategoryH2 findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<CategoryH2> search(String catName, String updatedDate) {
        return dao.search(catName, updatedDate);
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

}
