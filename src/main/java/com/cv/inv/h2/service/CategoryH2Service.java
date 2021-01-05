/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.CategoryH2;
import java.util.List;

/**
 *
 * @author SAI
 */
public interface CategoryH2Service {

    public CategoryH2 save(CategoryH2 item);
    
      public List<CategoryH2> findAll();

    public CategoryH2 findById(String id);

    public List<CategoryH2> search(String catName, String updatedDate);

    public int delete(String id);

}
