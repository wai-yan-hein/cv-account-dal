/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.ChartOfAccount;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class COADaoImpl extends AbstractDao<String, ChartOfAccount> implements COADao {

    @Override
    public ChartOfAccount save(ChartOfAccount coa) {
        persist(coa);
        return coa;
    }

    @Override
    public ChartOfAccount findById(String id) {
        ChartOfAccount coa = getByKey(id);
        return coa;
    }

    @Override
    public List<ChartOfAccount> search(String code, String name, String compCode,
            String coaLevel, String parent, String userParent, String usrCoaCode) {
        String strSql = "select o from ChartOfAccount o ";
        String strFilter = "";

        if (!code.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.code like '" + code + "%'";
            } else {
                strFilter = strFilter + " and o.code like '" + code + "%'";
            }
        }

        if (!name.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.coaNameEng like '%" + name + "%'";
            } else {
                strFilter = strFilter + " and o.coaNameEng like '%" + name + "%'";
            }
        }

        if (!compCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.compCode = '" + compCode + "'";
            } else {
                strFilter = strFilter + " and o.compCode = '" + compCode + "'";
            }
        }

        if (!coaLevel.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.coaLevel = " + coaLevel;
            } else {
                strFilter = strFilter + " and o.coaLevel = " + coaLevel;
            }
        }

        if (!parent.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.coaParent = '" + parent + "'";
            } else {
                strFilter = strFilter + " and o.coaParent = '" + parent + "'";
            }
        }

        if (!userParent.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.parentUsrCode like '" + userParent + "%'";
            } else {
                strFilter = strFilter + " and o.parentUsrCode like '" + userParent + "%'";
            }
        }

        if (!usrCoaCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.coaCodeUsr = '" + usrCoaCode + "'";
            } else {
                strFilter = strFilter + " and o.coaCodeUsr = '" + usrCoaCode + "'";
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        strSql = strSql + " order by o.coaCodeUsr asc";

        List<ChartOfAccount> listCOA = findHSQL(strSql);
        return listCOA;
    }

    @Override
    public int delete(String code, String compCode) {
        String strSql = "delete from ChartOfAccount o where o.code = '"
                + code + "' and o.compCode = '" + compCode + "'";
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

    @Override
    public List<ChartOfAccount> getParent(String compCode) {
        String strSql = "select o from ChartOfAccount o where o.option = 'SYS' and o.compCode = '" + compCode + "'"
                + " order by o.coaCodeUsr asc";
        List<ChartOfAccount> listCOA = findHSQL(strSql);
        return listCOA;
    }

    /*    @Override
    public List<COALevel> getParentChildCOA(String compCode) {
        String strSql = "select o from COALevel o ";
        String strFilter = "";

        if (!compCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.compCode is null";
            } else {
                strFilter = strFilter + " and o.compCode is null";
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<COALevel> listCOAL = findHSQLPC(strSql, "compFilter", "companyParam",
                compCode);
        return listCOAL;
    }
     */
    @Override
    public List<ChartOfAccount> getCOALevel3Above(String compCode) {
        String strSql = "select o from ChartOfAccount o where o.compCode = '"
                + compCode + "' and o.coaLevel >= 3 order by o.coaLevel, o.coaNameEng";
        List<ChartOfAccount> listCOA = findHSQL(strSql);
        return listCOA;
    }

    @Override
    public List<ChartOfAccount> getCOALevel2Above(String compCode) {
        String strSql = "select o from ChartOfAccount o where o.compCode = '"
                + compCode + "' and o.coaLevel >= 2 order by o.coaLevel, o.coaNameEng";
        List<ChartOfAccount> listCOA = findHSQL(strSql);
        return listCOA;
    }

    @Override
    public List<ChartOfAccount> getAllChild(String parent, String compCode) {
        String strSql = "select o from ChartOfAccount o where o.compCode = '"
                + compCode + "' and o.code = '" + parent + "'";
        List<ChartOfAccount> listAllChild = findHSQL(strSql);
        if (listAllChild == null) {
            listAllChild = new ArrayList();
        }
        getChild(listAllChild, parent, compCode);
        return listAllChild;
    }

    private void getChild(List<ChartOfAccount> listAllChild, String parent, String compCode) {
        String strSql = "select o from ChartOfAccount o where o.compCode = '"
                + compCode + "' and o.coaParent = '" + parent + "'";
        List<ChartOfAccount> listCOA = findHSQL(strSql);

        if (listCOA != null) {
            if (!listCOA.isEmpty()) {
                listAllChild.addAll(listCOA);
            }
            listCOA.forEach(coa -> {
                getChild(listAllChild, coa.getCode(), compCode);
            });
        }

    }

    @Override
    public List<ChartOfAccount> getChild(String compCode, String parent) {
        String strSql = "select o from ChartOfAccount o where o.compCode = '"
                + compCode + "' and o.coaParent = '" + parent + "' order by o.coaCodeUsr asc";
        List<ChartOfAccount> listCOA = findHSQL(strSql);
        return listCOA;
    }

    @Override
    public List<ChartOfAccount> getCOAWithLevel(String compCode, String coaLevel) {
        String strSql = "select o from ChartOfAccount o where o.compCode = '"
                + compCode + "' and o.coaLevel = " + coaLevel;
        List<ChartOfAccount> listCOA = findHSQL(strSql);
        return listCOA;
    }

    @Override
    public List<ChartOfAccount> getCompanyCOA(String compCode) {
        String strSql = "select o from ChartOfAccount o where o.compCode = '"
                + compCode + "' or o.coaParent = '#' order by o.coaLevel, o.coaNameEng";
        List<ChartOfAccount> listCOA = findHSQL(strSql);
        return listCOA;
    }

    @Override
    public List<ChartOfAccount> getCompanyCOA(String compCode, String deptId, String projectId) {
        String strSql = "select o from ChartOfAccount o where (o.compCode = '"
                + compCode + "' or o.coaParent = '#') and o.code in "
                + "(select i.coaCode from ProjectCOAMapping i where i.projectId = "
                + projectId + ") order by o.coaLevel, o.coaNameEng";
        List<ChartOfAccount> listCOA = findHSQL(strSql);

        if (listCOA.isEmpty()) {
            strSql = "select o from ChartOfAccount o where (o.compCode = '"
                    + compCode + "' or o.coaParent = '#') and o.code in (select i.key.coaCode "
                    + "from CoaDeptMap i where i.key.deptCode = '" + deptId + "') order by o.coaLevel, o.coaNameEng";
            listCOA = findHSQL(strSql);
        }

        if (listCOA.isEmpty()) {
            strSql = "select o from ChartOfAccount o where o.compCode = '"
                    + compCode + "' or o.coaParent = '#' order by o.coaLevel, o.coaNameEng";
            listCOA = findHSQL(strSql);
        }

        return listCOA;
    }

    @Override
    public List<ChartOfAccount> searchWhereIn(String strList, String compCode) {
        String hsql = "select o from ChartOfAccount o where o.code in (" + strList + ") "
                + "and o.active = true and o.compCode = " + compCode + "";
        return findHSQL(hsql);
    }

    @Override
    public List<ChartOfAccount> findAll() {
        String hsql = "select o from ChartOfAccount o  order by o.coaLevel";
        List<ChartOfAccount> ListCOA = findHSQL(hsql);
        return ListCOA;

    }

    @Override
    public List<ChartOfAccount> getLevelOneTwo(String compCode) {
        String hsql = "select o from ChartOfAccount o where o.coaLevel in (1,2) and o.compCode = '" + compCode + "'";
        return findHSQL(hsql);
    }
}
