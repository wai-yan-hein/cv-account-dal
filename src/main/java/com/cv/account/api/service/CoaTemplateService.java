/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.service;

import com.cv.account.api.entity.CoaTemplate;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface CoaTemplateService {
    public CoaTemplate save(CoaTemplate coat);
    public List<CoaTemplate> search(String companyType, String coaCode);
    public int delete(String id);
}
