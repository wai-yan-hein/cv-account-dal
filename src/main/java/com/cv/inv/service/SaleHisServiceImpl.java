/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

import com.cv.accountswing.dummy.SaleVouSearch;
import com.cv.accountswing.util.Util1;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cv.inv.dao.SaleHisDao;
import com.cv.inv.entity.SaleHis;
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
public class SaleHisServiceImpl implements SaleHisService {

    @Autowired
    private SaleHisDao hisDao;

    @Override
    public SaleHis save(SaleHis saleHis) throws Exception {
        hisDao.save(saleHis);
        return saleHis;
    }

    @Override
    public List<SaleHis> search(String fromDate, String toDate, String cusId,
            String vouStatusId, String remark, String stockCode, String userId) {
        return hisDao.search(fromDate, toDate, cusId, vouStatusId, remark, stockCode, userId);
    }

    @Override
    public SaleHis findById(String id) {
        return hisDao.findById(id);
    }

    @Override
    public int delete(String vouNo) throws Exception {
        return hisDao.delete(vouNo);
    }

    @Override
    public List<SaleVouSearch> searchM(String fromDate, String toDate, String cusId,
            String vouStatusId, String remark, String stockCode, String userId) throws Exception {
        ResultSet rs = hisDao.searchM(fromDate, toDate, cusId, vouStatusId, remark, stockCode, userId);
        List<SaleVouSearch> listVS = null;

        if (rs != null) {
            listVS = new ArrayList();
            while (rs.next()) {
                SaleVouSearch vs = new SaleVouSearch(
                        Util1.toDateStr(rs.getDate("sale_date"), "yyyy-MM-dd HH:mm:ss"),
                        rs.getString("voucher_no"),
                        rs.getString("remark"),
                        rs.getString("trader_name"),
                        rs.getDouble("grand_total"),
                        rs.getBoolean("deleted"),
                        rs.getString("user_short_name")
                );
                listVS.add(vs);
            }

            rs.close();
        }
        return listVS;
    }
}
