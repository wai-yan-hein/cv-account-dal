/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

/**
 *
 * @author winswe
 */
public interface COAOpeningDao {

    public void GenerateZero(String tranIdH, String compCode, String currCode) throws Exception;

    public void deleteOpening(Long id) throws Exception;

    public void GenerateZeroGL(String opDate, String userCode, String compCode,
            String currCode, String dept, String coaGroup) throws Exception;

    public void generateZeroOpening(String opDate, String userCode, String compCode,
            String currCode, String dept, String coaGroup) throws Exception;

    public void deleteOpeningGL(String opDate, String compCode, String currCode, String dept) throws Exception;

    public void deleteOpening(String opDate, String compCode, String currCode, String dept) throws Exception;

    public void insertCoaOpening(String opDate, String compCode, String currCode,
            String dept, String userCode) throws Exception;
}
