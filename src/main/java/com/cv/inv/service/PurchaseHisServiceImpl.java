/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/service/PurchaseHisServiceImpl.java
import com.cv.account.api.dummy.VouSearch;
import com.cv.account.api.util.Util1;
import com.cv.account.inv.dao.PurchaseHisDao;
import com.cv.account.inv.entity.PurHis;
import java.sql.ResultSet;
import java.util.ArrayList;
=======

import com.cv.inv.dao.PurchaseHisDao;
import com.cv.inv.entity.PurHis;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/service/PurchaseHisServiceImpl.java
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
@Service
@Transactional
public class PurchaseHisServiceImpl implements PurchaseHisService {

    @Autowired
    private PurchaseHisDao hisDao;

    @Override
    public PurHis save(PurHis purHis) throws Exception {
        hisDao.save(purHis);
        return purHis;
    }

    @Override
    public List<PurHis> search(String fromDate, String toDate, String cusId, String vouStatusId, String remark) {
        return hisDao.search(fromDate, toDate, cusId, vouStatusId, remark);
    }

    @Override
    public List<VouSearch> searchM(String fromDate, String toDate,
            String cusId, String vouStatusId, String remark) throws Exception {
        ResultSet rs = hisDao.searchM(fromDate, toDate, cusId, vouStatusId, remark);
        List<VouSearch> listVS = null;

        if (rs != null) {
            listVS = new ArrayList();
            while (rs.next()) {
                VouSearch vs = new VouSearch(
                        Util1.toDateStr(rs.getDate("pur_date"), "yyyy-MM-dd HH:mm:ss"),
                        rs.getString("pur_inv_id"),
                        rs.getString("remark"),
                        rs.getString("trader_name"),
                        rs.getDouble("vou_total"),
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

    @Override
    public PurHis findById(String id) {
        return hisDao.findById(id);
    }

    @Override
    public int delete(String vouNo) {
        return hisDao.delete(vouNo);
    }

}
