/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

import com.cv.accountswing.service.SeqTableService;
import com.cv.inv.dao.VouStatusDao;
import com.cv.inv.entity.VouStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
@Service
@Transactional
public class VouStatusServiceImpl implements VouStatusService {

    @Autowired
    private VouStatusDao vouDao;

    @Autowired
    private SeqTableService seqService;

    @Override
    public VouStatus save(VouStatus vs) {
        if (vs.getVouStatusCode() == null || vs.getVouStatusCode().isEmpty()) {
            Integer macId = vs.getMacId();
            String compCode = vs.getCompCode();
            vs.setVouStatusCode(getVouStatusCode(macId, "UnitPattern", "-", compCode));
        }
        return vouDao.save(vs);
    }

    @Override
    public List<VouStatus> findAll() {
        return vouDao.findAll();
    }

    @Override
    public int delete(String id) {
        return vouDao.delete(id);
    }

    @Override
    public VouStatus findById(String id) {
        return vouDao.findById(id);
    }

    @Override
    public List<VouStatus> search(String statusDesp) {
        return vouDao.search(statusDesp);
    }

    private String getVouStatusCode(Integer macId, String option, String period, String compCode) {

        int seqNo = seqService.getSequence(macId, option, period, compCode);

        String tmpCatCode = macId + "-" + String.format("%0" + 3 + "d", seqNo);
        return tmpCatCode;
    }
}
