/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.COAOpeningDaoD;
import com.cv.accountswing.entity.AccOpeningD;
import com.cv.accountswing.entity.temp.TmpOpeningClosing;
import com.cv.accountswing.entity.view.VAccOpeningD;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class COAOpeningDServiceImpl implements COAOpeningDService {

    @Autowired
    private COAOpeningDaoD dao;

    @Override
    public AccOpeningD save(AccOpeningD aod) {
        return dao.save(aod);
    }

    @Override
    public AccOpeningD findById(Long id) {
        AccOpeningD aod = dao.findById(id);
        return aod;
    }

    @Override
    public List<VAccOpeningD> search(String tranIdH) {
        List<VAccOpeningD> listAOD = dao.search(tranIdH);
        return listAOD;
    }

    @Override
    public int delete(String tranId) {
        int cnt = dao.delete(tranId);
        return cnt;
    }

    @Override
    public void insertFilter(String coaCode, int level, String opDate,
            String curr, String userCode) throws Exception {
        dao.insertFilter(coaCode, level, opDate, curr, userCode);
    }

    @Override
    public List<TmpOpeningClosing> getOpBalance(String coaCode, int level, String opDate,
            String curr, String userCode) throws Exception {
        List<TmpOpeningClosing> listTOC = dao.getOpBalance(coaCode, level, opDate,
                curr, userCode);
        return listTOC;
    }

    @Override
    public void deleteTmp(String coaCode, String userCode) throws Exception {

    }

    @Override
    public void insertFilterGL(String coaCode, String opDate, int level,
            String curr, String userCode) throws Exception {
        dao.insertFilterGL(coaCode, opDate, level, curr, userCode);
    }

    @Override
    public List<TmpOpeningClosing> getOpBalanceGL(String coaCode, String opDate,
            String clDae, int level, String curr, String userCode, String dept) throws Exception {
        dao.genOpBalanceGL(coaCode, opDate, clDae, level, curr, userCode, dept);
        //List<TmpOpeningClosing> listTOC = dao.getOpBalanceGL(userCode, coaCode,);
        return null;
    }

    @Override
    public void genTriBalance(String compCode, String fromDate, String opDate, String tranDate,
            String coaCode, String currency, String dept, String cvId, String userCode) throws Exception {

        dao.genTriBalance(compCode, fromDate, opDate, tranDate, coaCode, currency, dept, cvId, userCode);
    }

    @Override
    public void genArAp(String compCode, String fromDate, String opDate, String tranDate,
            String coaCode, String currency, String dept, String cvId, String userCode) throws Exception {
        dao.genArAp(compCode, fromDate, opDate, tranDate, coaCode, currency, dept, cvId, userCode);
    }

    @Override
    public void genArAp1(String compCode, String fromDate, String opDate, String tranDate,
            String coaCode, String currency, String dept, String traderCode, String userCode) throws Exception {
        dao.genArAp1(compCode, fromDate, opDate, tranDate, coaCode, currency, dept, traderCode, userCode);
    }

    @Override
    public void genTriBalance1(String compCode, String fromDate, String opDate,
            String tranDate, String coaCode, String currency, String dept,
            String cvId, String userCode, String macId) throws Exception {
        dao.genTriBalance1(compCode, fromDate, opDate, tranDate, coaCode, currency, dept, cvId, userCode, macId);
    }

    @Override
    public List<TmpOpeningClosing> getOpBalanceGL1(String coaCode, String opDate,
            String clDae, int level, String curr, String userCode, String dept, String macId) throws Exception {
        dao.genOpBalanceGL1(coaCode, opDate, clDae, level, curr, userCode, dept, macId);
        List<TmpOpeningClosing> listTOC = dao.getOpBalanceGL(userCode, coaCode, macId);
        return listTOC;
    }

    @Override
    public List<TmpOpeningClosing> getOpBalanceByTrader(String coaCode, String opDate,
            String clDate, int level, String curr, String userCode, String dept, String traderCode, String macId, String compCode) throws Exception {
        dao.getOpBalanceByTrader(coaCode, opDate, clDate, level, curr, userCode, dept, traderCode, macId, compCode);
        List<TmpOpeningClosing> listTOC = dao.getOpBalanceGL(userCode, coaCode, macId);
        return listTOC;
    }
}
