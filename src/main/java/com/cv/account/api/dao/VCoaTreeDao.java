/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.view.VCoaTree;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface VCoaTreeDao {
    public List<VCoaTree> getCompanyCoa(String compId);
}
