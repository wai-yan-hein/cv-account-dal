/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.temp.TmpOpeningClosing;
import com.cv.accountswing.entity.view.VAccOpeningD;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface COAOpeningDService {

    public List<VAccOpeningD> search(String tranIdH);

    public int delete(String tranId);

    public void insertFilter(String coaCode, int level, String opDate,
            String curr, String userCode) throws Exception;

    public List<TmpOpeningClosing> getOpBalance(String coaCode, int level, String opDate,
            String curr, String userCode) throws Exception;

    public void deleteTmp(String coaCode, String userCode) throws Exception;

    public void insertFilterGL(String coaCode, String opDate, int level,
            String curr, String userCode) throws Exception;

    public List<TmpOpeningClosing> getOpBalanceGL(String coaCode, String opDate,
            String clDae, int level, String curr, String userCode, String dept) throws Exception;

    public List<TmpOpeningClosing> getOpBalanceGL1(String coaCode, String opDate,
            String clDae, int level, String curr, String userCode, String dept, String macId) throws Exception;

    public void genTriBalance(String compCode, String fromDate, String opDate, String tranDate,
            String coaCode, String currency, String dept, String cvId, String userCode) throws Exception;

    public void genTriBalance1(String compCode, String opDate,
            String tranDate, String coaCode, String currency, String dept,
            String cvId, String userCode, String macId) throws Exception;

    public void genArAp(String compCode, String fromDate, String opDate, String tranDate,
            String coaCode, String currency, String dept, String cvId, String userCode) throws Exception;

    public void genArAp1(String compCode, String fromDate, String opDate, String tranDate,
            String coaCode, String currency, String dept, String traderCode, String userCode) throws Exception;

    public List<TmpOpeningClosing> getOpBalanceByTrader(String coaCode, String opDate,
            String clDate, int level, String curr, String userCode, String dept, String traderCode, String macID, String compCode) throws Exception;

}
