/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.view.VApar;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class VAParDaoImpl extends AbstractDao<Integer, VApar> implements VAParDao {

    @Override
    public List<VApar> getApAr(String macId, String compCode, String traderType) {
        String strSql = "select o from VApar o where o.key.macId = " + macId
                + " and o.key.compCode = '" + compCode + "'";
        if (!traderType.equals("-")) {
            strSql = strSql + " and trader_code like '" + traderType + "%'";
        }
        List<VApar> listApAr = findHSQL(strSql);
        return listApAr;
    }
}
