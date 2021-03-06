/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.COADao;
import com.cv.accountswing.dao.COAOpeningDao;
import com.cv.accountswing.entity.ChartOfAccount;
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
public class COAServiceImpl implements COAService {

    @Autowired
    private COADao dao;
    @Autowired
    private COAOpeningDao coaOpDao;
    @Autowired
    SeqTableService seqService;
    @Autowired
    SystemPropertyService spService;

    @Override
    public ChartOfAccount save(ChartOfAccount coa) {
        if (coa.getCode() == null || coa.getCode().isEmpty()) {
            Integer macId = coa.getMacId();
            String compCode = coa.getCompCode();
            coa.setCode(getCOACode(macId, compCode));
        }

        return dao.save(coa);
    }

    @Override
    public ChartOfAccount save(ChartOfAccount coa, String opDate) throws Exception {
        coaOpDao.insertCoaOpening(opDate, coa.getCompCode().toString(), "-", "-", coa.getCreatedBy());
        return save(coa);
    }

    @Override
    public ChartOfAccount findById(String id) {
        ChartOfAccount coa = dao.findById(id);
        return coa;
    }

    @Override
    public List<ChartOfAccount> search(String code, String name, String compCode,
            String level, String parent, String userParent, String usrCoaCode) {
        List<ChartOfAccount> listCOA = dao.search(code, name, compCode, level,
                parent, userParent, usrCoaCode);
        return listCOA;
    }

    @Override
    public int delete(String code, String compCode) {
        int cnt = dao.delete(code, compCode);
        return cnt;
    }

    @Override
    public List<ChartOfAccount> getParent(String compCode) {
        List<ChartOfAccount> listCOA = dao.getParent(compCode);
        return listCOA;
    }

    /* @Override
    public List<COALevel> getParentChildCOA(String compCode) {
        List<COALevel> listCOAL = dao.getParentChildCOA(compCode);
        return listCOAL;
    }
     */
    @Override
    public List<ChartOfAccount> getCOALevel3Above(String compCode) {
        List<ChartOfAccount> listCOA = dao.getCOALevel3Above(compCode);
        return listCOA;
    }

    @Override
    public List<ChartOfAccount> getCOALevel2Above(String compCode) {
        List<ChartOfAccount> listCOA = dao.getCOALevel2Above(compCode);
        return listCOA;
    }

    @Override
    public List<ChartOfAccount> getAllChild(String parent, String compCode) {
        List<ChartOfAccount> listCOA = dao.getAllChild(parent, compCode);
        return listCOA;
    }

    @Override
    public List<ChartOfAccount> getCompanyCOA(String compCode) {
        List<ChartOfAccount> listCOA = dao.getCompanyCOA(compCode);
        return listCOA;
    }

    @Override
    public List<ChartOfAccount> getCompanyCOA(String compCode, String deptId, String projectId) {
        List<ChartOfAccount> listCOA = dao.getCompanyCOA(compCode, deptId, projectId);
        return listCOA;
    }

    @Override
    public List<ChartOfAccount> getChild(String compCode, String parent) {
        List<ChartOfAccount> listCOA = dao.getChild(compCode, parent);
        return listCOA;
    }

    private String getCOACode(Integer macId, String compCode) {
        int seqNo = seqService.getSequence(macId, "COA", "-", compCode);
        String coaCode = String.format("%0" + 3 + "d", macId) + "-" + String.format("%0" + 5 + "d", seqNo);
        return coaCode;
    }

    @Override
    public List<ChartOfAccount> searchWhereIn(String strList, String compCode
    ) {
        return dao.searchWhereIn(strList, compCode);
    }

    @Override
    public List<ChartOfAccount> findAll() {
        return dao.findAll();
    }

    @Override
    public List<ChartOfAccount> getLevelOneTwo(String compCode) {
        return dao.getLevelOneTwo(compCode);
    }
}
