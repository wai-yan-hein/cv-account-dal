/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.RegionH2;
import java.util.List;

/**
 *
 * @author WSwe
 */
public interface RegionH2Service {

    public RegionH2 save(RegionH2 region);

    public RegionH2 findById(String id);

    public List<RegionH2> search(String code, String name, String compId, String parentCode);

    public int delete(String code, String compCode);
}
