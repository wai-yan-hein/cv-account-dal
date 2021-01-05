/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.entity.RetInCompoundKey;
import com.cv.inv.entity.RetInHisDetail;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public class RetInDetailDaoImpl extends AbstractDao<RetInCompoundKey, RetInHisDetail> implements RetInDetailDao {

    @Override
    public RetInHisDetail save(RetInHisDetail pd) {
        persist(pd);
        return pd;
    }

    @Override
    public List<RetInHisDetail> search(String retInCode) {
        String strFilter = "";
        if (!retInCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.retInKey.vouNo = '" + retInCode + "'";
            } else {
                strFilter = strFilter + " and o.retInKey.vouNo = '" + retInCode + "'";
            }
        }
        String strSql = "select o from RetInHisDetail o";

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }
        return findHSQL(strSql);
    }

    @Override
    public int delete(String id) throws Exception {
        String strSql = "delete from ret_in_his_detail where ret_in_detail_id = '" + id + "'";
        execSQL(strSql);
        return 1;
    }
}
