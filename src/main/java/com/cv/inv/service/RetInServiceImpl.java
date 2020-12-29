/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;


import com.cv.accountswing.dummy.VouSearch;
import com.cv.accountswing.util.Util1;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.cv.inv.dao.RetInDao;
import com.cv.inv.dao.RetInDetailDao;
import com.cv.inv.entity.RetInCompoundKey;
import com.cv.inv.entity.RetInHisDetail;
import com.cv.inv.entity.RetInHis;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Wai Yan
 */
@Service
@Transactional
public class RetInServiceImpl implements RetInService {

    private static final Logger log = LoggerFactory.getLogger(RetInServiceImpl.class);

    @Autowired
    private RetInDao retInDao;

    @Autowired
    private RetInDetailDao dao;

    @Override
    public void save(RetInHis retIn, List<RetInHisDetail> listRetIn, List<String> delList) {
        String retInDetailId;
        try {
            if (delList != null) {
                delList.forEach(detailId -> {
                    dao.delete(detailId);
                });
            }
            retInDao.save(retIn);
            String vouNo = retIn.getRetInId();
            for (RetInHisDetail rd : listRetIn) {
                if (rd.getStock() != null) {
                    if (rd.getRetInKey() != null) {
                        rd.setRetInKey(rd.getRetInKey());
                    } else {
                        retInDetailId = vouNo + '-' + rd.getUniqueId();
                        rd.setRetInKey(new RetInCompoundKey(retInDetailId, vouNo));
                    }
                    dao.save(rd);
                }
            }

        } catch (Exception ex) {
            log.error("saveRetIn : " + ex.getStackTrace()[0].getLineNumber() + " - " + ex.getMessage());

        }

    }

    @Override
    public void delete(String retInId) {
        dao.delete(retInId);
    }

    @Override
    public List<RetInHis> search(String fromDate, String toDate, String cusId, String locId, String vouNo, String filterCode) {
        return retInDao.search(fromDate, toDate, cusId, locId, vouNo, filterCode);
    }

    @Override
    public List<VouSearch> searchM(String fromDate, String toDate, String cusId,
            String locId, String vouNo, String filterCode) throws Exception {
        ResultSet rs = retInDao.searchM(fromDate, toDate, cusId, locId, vouNo, filterCode);
        List<VouSearch> listVS = null;

        if (rs != null) {
            listVS = new ArrayList();
            while (rs.next()) {
                VouSearch vs = new VouSearch(
                        Util1.toDateStr(rs.getDate("ret_in_date"), "yyyy-MM-dd HH:mm:ss"),
                        rs.getString("ret_in_id"),
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
    public RetInHis findById(String id) {
        return retInDao.findById(id);
    }

    @Override
    public RetInHis saveM(RetInHis retIn) {
        return retInDao.save(retIn);
    }

}
