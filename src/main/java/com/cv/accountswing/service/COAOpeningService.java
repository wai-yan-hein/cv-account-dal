/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

/**
 *
 * @author winswe
 */
public interface COAOpeningService {

    public void GenerateZero(String tranIdH, String compCode, String currCode) throws Exception;

    public void deleteOpening(Long id) throws Exception;

    public void GenerateZeroGL(String opDate, String userCode, String compCode,
            String currCode, String dept, String coaGroup) throws Exception;

    public void generateZeroOpening(String opDate, String userCode, String compCode,
            String currCode, String dept, String coaGroup) throws Exception;
}
