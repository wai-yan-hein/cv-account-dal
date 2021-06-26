/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.temp.TmpBalanceSheet;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class TmpBalanceSheetDaoImpl extends AbstractDao<Long, TmpBalanceSheet> implements TmpBalanceSheetDao {

    @Override
    public List<TmpBalanceSheet> search(String compCode, String macId) {
        String hsql = "select o from TmpBalanceSheet o where o.compCode = '" + compCode + "' and o.macId = " + macId + "";
        return findHSQL(hsql);
    }

}
