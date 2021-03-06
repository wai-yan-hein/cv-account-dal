/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.temp.TmpProfitAndLost;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class TmpProfitAndLostDaoImpl extends AbstractDao<Long, TmpProfitAndLost> implements TmpProfitAndLostDao {

    @Override
    public TmpProfitAndLost save(TmpProfitAndLost pal) throws Exception {
        persist(pal);
        return pal;
    }

    @Override
    public TmpProfitAndLost findById(Long id) {
        TmpProfitAndLost pal = getByKey(id);
        return pal;
    }

    @Override
    public List<TmpProfitAndLost> search(String compCode, String macId) {
        String hsql = "select o from TmpProfitAndLost o where o.compCode ='" + compCode + "' and o.macId = " + macId + "";
        List<TmpProfitAndLost> listTPAL = findHSQL(hsql);
        return listTPAL;
    }

    @Override
    public int delete(String id, String userCode, String compCode) throws Exception {
        String strFilter = "";

        if (!id.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "tran_id = " + id;
            } else {
                strFilter = strFilter + " and tran_id = " + id;
            }
        }

        if (!userCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "user_code = '" + userCode + "'";
            } else {
                strFilter = strFilter + " and user_code = '" + userCode + "'";
            }
        }

        if (!compCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "comp_code = " + compCode;
            } else {
                strFilter = strFilter + " and comp_code = " + compCode;
            }
        }

        int cnt = 0;
        if (!strFilter.isEmpty()) {
            strFilter = "delete from tmp_profit_lost where " + strFilter;
            cnt = execUpdateOrDelete(strFilter);
        }

        return cnt;
    }
}
