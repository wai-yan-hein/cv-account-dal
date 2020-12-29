/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.MachineInfoH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SAI
 */
@Repository
public class MachineInfoH2DaoImpl extends AbstractDao<Integer, MachineInfoH2> implements MachineInfoH2Dao {

    @Override
    public MachineInfoH2 save(MachineInfoH2 machineInfo) throws Exception {
        persist(machineInfo);
        return machineInfo;
    }

    @Override
    public MachineInfoH2 findById(String id) throws Exception {
        return getByKey(Integer.parseInt(id));
    }

    @Override
    public List<MachineInfoH2> search(String name, String ip) {
        String strSql = "";

        if (!name.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "o.machineName like '%" + name + "%'";
            } else {
                strSql = strSql + " and o.machineName like '%" + name + "%'";
            }
        }

        if (!ip.equals("-")) {
            if (strSql.isEmpty()) {
                strSql = "o.ipAddress like '%" + ip + "%'";
            } else {
                strSql = strSql + " and o.ipAddress like '%" + ip + "%'";
            }
        }

        if (strSql.isEmpty()) {
            strSql = "select o from MachineInfoH2 o";
        } else {
            strSql = "select o from MachineInfoH2 o where " + strSql;
        }

        List<MachineInfoH2> listMI = findHSQL(strSql);
        return listMI;
    }

    @Override
    public int delete(String id) {
        String strSql = "delete from MachineInfoH2 o where o.machineId = " + id;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

}
