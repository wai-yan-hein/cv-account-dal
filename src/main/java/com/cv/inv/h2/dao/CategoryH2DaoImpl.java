/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.CategoryH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SAI
 */
@Repository
public class CategoryH2DaoImpl extends AbstractDao<String, CategoryH2> implements CategoryH2Dao {

    @Override
    public CategoryH2 save(CategoryH2 item) {
        persist(item);
        return item;
    }

    @Override
    public CategoryH2 findById(String id) {
        CategoryH2 ch2 = getByKey(id);
        return ch2;
    }

    @Override
    public List<CategoryH2> search(String catName, String updatedDate) {
        String strFilter = "";

        if (!catName.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.catName like '%" + catName + "%'";
            } else {
                strFilter = strFilter + " and o.catName like '%" + catName + "%'";
            }
        }

        if (!updatedDate.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.updatedDate = '" + updatedDate + "'";
            } else {
                strFilter = strFilter + " and o.updatedDate = '" + updatedDate + "'";
            }
        }

        if (strFilter.isEmpty()) {
            strFilter = "select o from CategoryH2 o";
        } else {
            strFilter = "select o from CategoryH2 o where " + strFilter;
        }

        List<CategoryH2> listCH2 = findHSQL(strFilter);
        return listCH2;
    }

    @Override
    public int delete(String id) {
         String strSql = "delete from CategoryH2 o where o.catId = " + id;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

}
