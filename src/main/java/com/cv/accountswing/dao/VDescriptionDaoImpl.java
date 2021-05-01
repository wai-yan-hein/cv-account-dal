/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.view.VDescription;
import com.cv.accountswing.entity.view.VGl;
import com.cv.accountswing.entity.view.VMenuClass;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class VDescriptionDaoImpl extends AbstractDao<String, VDescription> implements VDescriptionDao {

    @Override
    public List<VDescription> getDescriptions() {
        String hsql = "select o from VDescription o";
        return findHSQL(hsql);
    }

    @Override
    public List<VMenuClass> getMenuClass() {
        String hsql = "select o from VMenuClass o";
        return findHSQLList(hsql);
    }

}
