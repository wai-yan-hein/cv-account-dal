/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.view.VDescription;
import com.cv.accountswing.entity.view.VMenuClass;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface VDescriptionDao {

    public List<VDescription> getDescriptions();

    public List<VMenuClass> getMenuClass();
}
