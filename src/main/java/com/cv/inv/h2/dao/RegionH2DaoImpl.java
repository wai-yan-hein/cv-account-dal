/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.inv.h2.entity.RegionH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WSwe
 */
@Repository
public class RegionH2DaoImpl extends AbstractDao<Integer, RegionH2> implements RegionH2Dao {

    @Override
    public RegionH2 save(RegionH2 region) {
        persist(region);
        return region;
    }

    @Override
    public RegionH2 findById(String id) {
        RegionH2 region = getByKey(Integer.parseInt(id));
        return region;
    }

    @Override
    public List<RegionH2> search(String code, String name, String compCode, String parentCode) {
        String strSql = "select o from RegionH2 o ";
        String strFilter = "";

        if (!code.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.regionCode = '" + code + "'";
            } else {
                strFilter = strFilter + " and o.regionCode = '" + code + "'";
            }
        }

        if (!name.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.regionName like '%" + name + "%'";
            } else {
                strFilter = strFilter + " and o.regionName like '%" + name + "%'";
            }
        }

        if (!compCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.compId = '" + compCode + "'";
            } else {
                strFilter = strFilter + " and o.compId = '" + compCode + "'";
            }
        }
        if (!parentCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.parentRegion = '" + parentCode + "'";
            } else {
                strFilter = strFilter + " and o.parentRegion = '" + parentCode + "'";
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<RegionH2> listRegion = findHSQL(strSql);
        return listRegion;
    }

    @Override
    public int delete(String code, String compCode) {
        String strSql = "delete from RegionH2 o where o.regId = '"
                + code + "' and o.compId = " + compCode;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
}
