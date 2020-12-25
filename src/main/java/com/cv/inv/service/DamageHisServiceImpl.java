/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;


import com.cv.accountswing.dummy.DamageVouSearch;
import com.cv.accountswing.util.Util1;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.cv.inv.dao.DamageDetailHisDao;
import com.cv.inv.dao.DamageHisDao;
import com.cv.inv.entity.DamageDetailHis;
import com.cv.inv.entity.DamageHis;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
@Service
@Transactional
public class DamageHisServiceImpl implements DamageHisService {

    private static final Logger logger = LoggerFactory.getLogger(DamageHisServiceImpl.class);

    @Autowired
    private DamageHisDao dao;
    @Autowired
    private DamageDetailHisDao detailDao;

    @Override
    public DamageHis save(DamageHis sdh) {
        return dao.save(sdh);
    }

    @Override
    public void save(DamageHis sdh, List<DamageDetailHis> listDamageDetail, String vouStatus, List<String> delList) {
        if (vouStatus.equals("EDIT")) {
            if (delList != null) {
                for (String detailId : delList) {
                    detailDao.delete(detailId);
                }
            }
        }
        dao.save(sdh);
        for (DamageDetailHis dh : listDamageDetail) {
            if (dh.getStock().getStockCode() != null) {
                dh.setDmgVouId(sdh.getDmgVouId());
                detailDao.save(dh);
            }
        }
    }

    @Override
    public List<DamageHis> search(String from, String to, String location, String remark, String vouNo) {
        return dao.search(from, to, location, remark, vouNo);
    }

    @Override
    public DamageHis findById(String id) {
        return dao.findById(id);
    }

    @Override
    public int delete(String vouNo) {
        return dao.delete(vouNo);
    }

    @Override
    public List<DamageVouSearch> searchM(String from, String to, String location,
            String remark, String vouNo) throws Exception {
        ResultSet rs = dao.searchM(from, to, location, remark, vouNo);
        List<DamageVouSearch> listVS = null;

        if (rs != null) {
            listVS = new ArrayList();
            while (rs.next()) {
                DamageVouSearch vs = new DamageVouSearch(
                        Util1.toDateStr(rs.getDate("dmg_date"), "yyyy-MM-dd HH:mm:ss"),
                        rs.getString("dmg_id"),
                        rs.getString("remark"),
                        rs.getDouble("amount"),
                        rs.getBoolean("deleted"),
                        rs.getString("location_name"),
                        rs.getString("user_short_name")
                );
                listVS.add(vs);
            }

            rs.close();
        }
        return listVS;
    }

}
