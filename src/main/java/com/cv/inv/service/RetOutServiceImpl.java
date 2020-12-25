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
import com.cv.inv.dao.RetOutDao;
import com.cv.inv.dao.RetOutDetailDao;
import com.cv.inv.entity.RetOutCompoundKey;
import com.cv.inv.entity.RetOutHisDetail;
import com.cv.inv.entity.RetOutHis;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lenovo
 */


@Service
@Transactional
public class RetOutServiceImpl implements RetOutService {

    private static final Logger logger = LoggerFactory.getLogger(RetOutServiceImpl.class);
    private RetOutHisDetail retOutDetailHis;
    @Autowired
    private RetOutDao retOutDao;
    @Autowired
    private RetOutDetailDao dao;

    @Override
    public void save(RetOutHis retOut, List<RetOutHisDetail> listRetIn, List<String> delList) {

        String retInDetailId;
        RetOutCompoundKey key;

        try {
            if (delList != null) {
                for (String detailId : delList) {
                    dao.delete(detailId);
                }
            }
            retOutDao.save(retOut);
            String vouNo = retOut.getRetOutId();
            for (RetOutHisDetail rd : listRetIn) {
                if (rd.getStock() != null) {
                    if (rd.getOutCompoundKey() != null) {
                        rd.setOutCompoundKey(rd.getOutCompoundKey());
                    } else {
                        retInDetailId = vouNo + '-' + rd.getUniqueId();
                        rd.setOutCompoundKey(new RetOutCompoundKey(retInDetailId, vouNo));
                    }
                    dao.save(rd);
                }

            }

        } catch (Exception ex) {
            logger.error("saveRetIn : " + ex.getStackTrace()[0].getLineNumber() + " - " + ex.getMessage());

        }

    }

    @Override
    public void delete(String retInId) {
        dao.delete(retInId);
    }

    @Override
    public List<RetOutHis> search(String fromDate, String toDate, String cusId, String locId, String vouNo, String filterCode) {
        return retOutDao.search(fromDate, toDate, cusId, locId, vouNo, filterCode);
    }

    @Override
    public List<VouSearch> searchM(String fromDate, String toDate,
            String cusId, String locId, String vouNo, String filterCode) throws Exception {
        ResultSet rs = retOutDao.searchM(fromDate, toDate, cusId, locId, vouNo, filterCode);
        List<VouSearch> listVS = null;

        if (rs != null) {
            listVS = new ArrayList();
            while (rs.next()) {
                VouSearch vs = new VouSearch(
                        Util1.toDateStr(rs.getDate("ret_out_date"), "yyyy-MM-dd HH:mm:ss"),
                        rs.getString("ret_out_id"),
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
    public RetOutHis findById(String id) {
        return retOutDao.findById(id);
    }

}
