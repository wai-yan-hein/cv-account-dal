/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

import com.cv.inv.entity.AccSetting;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface AccSettingService {

    public List<AccSetting> findAll();

    public AccSetting save(AccSetting setting);

    public AccSetting findByCode(String code);

}
