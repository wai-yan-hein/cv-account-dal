/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.RegionH2Dao;
import com.cv.inv.h2.entity.RegionH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author WSwe
 */
@Service
@Transactional
public class RegionH2ServiceImpl implements RegionH2Service {

    @Autowired
    RegionH2Dao dao;

    @Override
    public RegionH2 save(RegionH2 region) {
        region = dao.save(region);
        return region;
    }

    @Override
    public RegionH2 findById(String id) {
        RegionH2 region = dao.findById(id);
        return region;
    }

    @Override
    public List<RegionH2> search(String code, String name, String compId, String parentCode) {
        List<RegionH2> listRegion = dao.search(code, name, compId, parentCode);
        return listRegion;
    }

    @Override
    public int delete(String code, String compCode) {
        int cnt = dao.delete(code, compCode);
        return cnt;
    }
}
