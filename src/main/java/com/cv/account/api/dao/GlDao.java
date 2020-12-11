/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.Gl;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface GlDao {

    public Gl save(Gl gl) throws Exception;

    public List<Gl> saveBatchGL(List<Gl> listGL) throws Exception;

    public Gl findById(Long glId);

    public List<Gl> search(String from, String to, String desp, String sourceAcId,
            String acId, String frmCurr, String toCurr, String reference, String dept,
            String vouNo, String cvId, String chequeNo, String compCode, String tranSource,
            String glVouNo, String splitId, String projectId);

    public int delete(Long glId, String option) throws Exception;
}
