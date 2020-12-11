/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.service;

import com.cv.account.api.entity.SystemPropertyTemplate;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface SystemPropertyTemplateService {
    public SystemPropertyTemplate save(SystemPropertyTemplate spt);
    public List<SystemPropertyTemplate> search(String propKey, String compType);
    public int delete(String id);
}
