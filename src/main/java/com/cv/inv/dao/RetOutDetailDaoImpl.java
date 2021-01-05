/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.entity.RetOutHisDetail;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class RetOutDetailDaoImpl extends AbstractDao<String, RetOutHisDetail> implements RetOutDetailDao {

    @Override
    public List<RetOutHisDetail> search(String glId) {
        String strFilter = "";
        if (!glId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "v.outCompoundKey.vouNo = '" + glId + "'";
            } else {
                strFilter = strFilter + " and v.outCompoundKey.vouNo = '" + glId + "'";
            }
        }
        String strSql = "select v from RetOutDetailHis v";

        List<RetOutHisDetail> listDH = null;
        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
            listDH = findHSQL(strSql);
        }

        return listDH;
    }

    @Override
    public int delete(String id) throws Exception{
        String strSql = "delete from ret_out_detail_his where ret_out_detail_id = '" + id + "'";
        execSQL(strSql);
        return 1;
    }

    @Override
    public RetOutHisDetail save(RetOutHisDetail pd) {
        persist(pd);
        return pd;
    }
}
