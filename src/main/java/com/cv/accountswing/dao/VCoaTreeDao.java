/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.view.VCoaTree;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface VCoaTreeDao {
    public List<VCoaTree> getCompanyCoa(String compCode);
}
