/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

import com.cv.accountswing.service.SeqTableService;
import com.cv.inv.dao.LocationDao;
import com.cv.inv.entity.Location;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDao dao;

    @Autowired
    private SeqTableService seqService;

    @Override
    public Location save(Location loc) {
        if (loc.getLocationCode() == null || loc.getLocationCode().isEmpty()) {
            Integer macId = loc.getMacId();
            String compCode = loc.getCompCode();
            loc.setLocationCode(getLocationCode(macId, "Location", "-", compCode));
        }
        return dao.save(loc);
    }

    @Override
    public List<Location> findAll() {
        return dao.findAll();
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public Location findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<Location> search(String parent) {
        return dao.search(parent);
    }

    private String getLocationCode(Integer macId, String option, String period, String compCode) {

        int seqNo = seqService.getSequence(macId, option, period, compCode);

        String tmpCatCode = String.format("%0" + 3 + "d", macId) + "-" + String.format("%0" + 4 + "d", seqNo);
        return tmpCatCode;
    }
}
