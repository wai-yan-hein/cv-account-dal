/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.service;

import com.cv.account.api.entity.view.VCoaTree;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface VCoaTreeService {
    public List<VCoaTree> getCompanyCoa(String compId);
}
