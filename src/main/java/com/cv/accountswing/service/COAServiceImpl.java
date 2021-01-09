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
<<<<<<< HEAD
            coa.setCode(getCOACode(macId, coa.getCompCode().toString()));
=======
            String compCode = coa.getCompCode();
            coa.setCode(getCOACode(macId, "ChartOfAccount", "-", compCode));
>>>>>>> 9d90b5663312bac2b0ac1ae2e6b571e906585deb
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

<<<<<<< HEAD
    private String getCOACode(Integer macId, String compCode) {
        SystemPropertyKey spk = new SystemPropertyKey("system.coa.code.length",
                compCode);
        SystemProperty sp = spService.findById(spk);
        int ttlLength = Integer.parseInt(sp.getPropValue());
        int seqNo = seqService.getSequence(macId, "COA", "-", compCode);
        String coaCode = compCode + "-" + String.format("%0" + ttlLength + "d", seqNo);
        return coaCode;
=======
    private String getCOACode(Integer macId, String option, String period, String compCode) {

        int seqNo = seqService.getSequence(macId, option, period, compCode);

        String tmpCatCode = String.format("%0" + 2 + "d", macId) + "-" + String.format("%0" + 3 + "d", seqNo);
        return tmpCatCode;
>>>>>>> 9d90b5663312bac2b0ac1ae2e6b571e906585deb
    }

    @Override
    public List<ChartOfAccount> searchWhereIn(String strList, String compCode) {
        return dao.searchWhereIn(strList, compCode);
    }

    @Override
    public List<ChartOfAccount> findAll() {
        return dao.findAll();
    }
}
