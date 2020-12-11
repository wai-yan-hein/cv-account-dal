/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.view.VGeneralVoucher;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface VGvDao {
    public List<VGeneralVoucher> search(String from, String to, String vouNo,
            String ref, String compCode, String projectId);
}
