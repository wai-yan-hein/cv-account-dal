/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.Staff;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class StaffDaoImpl extends AbstractDao<String, Staff> implements StaffDao {

    @Override
    public Staff save(Staff cus) {
        persist(cus);
        return cus;
    }

    @Override
    public Staff findById(String id) {
        Staff cus = getByKey(id);
        return cus;
    }

    @Override
    public List<Staff> search(String code, String name, String address,
            String phone, String compCode) {
        String strSql = "select o from Staff o ";
        String strFilter = "";

        if (!compCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.compCode = '" + compCode + "'";
            } else {
                strFilter = strFilter + " and o.compCode = '" + compCode + "'";
            }
        }

        if (!code.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.code = '" + code + "'";
            } else {
                strFilter = strFilter + " and o.code = '" + code + "'";
            }
        }

        if (!name.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.traderName like '%" + name + "%'";
            } else {
                strFilter = strFilter + " and o.traderName like '%" + name + "%'";
            }
        }

        if (!address.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.address like '%" + address + "%'";
            } else {
                strFilter = strFilter + " and o.address like '%" + address + "%'";
            }
        }

        if (!phone.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.phone like '%" + phone + "%'";
            } else {
                strFilter = strFilter + " and o.phone like '%" + phone + "%'";
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<Staff> listCus = findHSQL(strSql);
        return listCus;
    }

    @Override
    public int delete(Integer id) {
        String strSql = "delete from Staff o where o.id = " + id;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

}
