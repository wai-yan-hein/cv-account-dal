/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.accountswing.service.*;
import com.cv.accountswing.entity.AppUser;
import com.cv.inv.h2.dao.COAH2Dao;
import com.cv.inv.h2.dao.CompanyInfoH2Dao;
import com.cv.inv.h2.entity.ChartOfAccountH2;
import com.cv.inv.h2.entity.CompanyInfoH2;
import com.cv.inv.h2.entity.SystemPropertyH2;
import com.cv.inv.h2.entity.SystemPropertyKeyH2;
import com.cv.inv.h2.entity.UserRoleH2;
import com.cv.inv.h2.entity.UsrCompRoleH2;
import com.cv.inv.h2.entity.UsrCompRoleKeyH2;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author WSwe
 */
@Service
@Transactional
public class CompanyInfoH2ServiceImpl implements CompanyInfoH2Service {

    private static final Logger logger = LoggerFactory.getLogger(CompanyInfoH2ServiceImpl.class);

    @Autowired
    private CompanyInfoH2Dao dao;
    @Autowired
    private COAH2Dao coaDao;
    @Autowired
    private SeqTableService seqService;
    @Autowired
    private SystemPropertyH2Service spService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserRoleH2Service userRoleService;
    @Autowired
    private PrivilegeH2Service privilegeService;
    @Autowired
    private UsrCompRoleH2Service usrCompRoleService;

    private String getCOACode(String compCode, int ttlLength) {
        int seqNo = seqService.getSequence("COA", "-", compCode);
        String coaCode = compCode + "-" + String.format("%0" + ttlLength + "d", seqNo);
        return coaCode;
    }

    @Override
    public CompanyInfoH2 save(CompanyInfoH2 ci, String status, String userId, String type) {
        ci = dao.save(ci);

        if (status.equals("NEW")) {
            Integer businessType = ci.getBusinessType();
            List<CompanyInfoH2> listCI = dao.search("-", "-", "-", "-", businessType.toString(), "-");
            String oldCompCode = "-";
            String newCompCode = ci.getCompId().toString();

            if (listCI != null) {
                if (!listCI.isEmpty()) {
                    oldCompCode = listCI.get(0).getCompId().toString();
                }
            }

            if (!oldCompCode.equals("-")) {
                SystemPropertyKeyH2 spk = new SystemPropertyKeyH2("system.coa.code.length",
                        Integer.parseInt(oldCompCode));
                SystemPropertyH2 sp = spService.findById(spk);
                int ttlLength = Integer.parseInt(sp.getPropValue());
                List<ChartOfAccountH2> listLevel2COA = coaDao.getCOAWithLevel(oldCompCode, "2");

                try {
                    spService.copySystemProperty(oldCompCode, newCompCode);

                    for (ChartOfAccountH2 tmpCOA : listLevel2COA) {
                        String newCOACode = getCOACode(newCompCode, ttlLength);
                        ChartOfAccountH2 newCOA = new ChartOfAccountH2();

                        BeanUtils.copyProperties(tmpCOA, newCOA);
                        newCOA.setPrvCoaCode(newCOA.getCode());
                        newCOA.setCode(newCOACode);
                        newCOA.setCompCode(Integer.parseInt(newCompCode));
                        coaDao.save(newCOA);

                        List<SystemPropertyH2> listSP = spService.search("-", newCompCode, tmpCOA.getCode());
                        for (SystemPropertyH2 tmpSP : listSP) {
                            tmpSP.setPropValue(newCOACode);
                            spService.save(tmpSP);
                        }

                        insertChild(oldCompCode, newCompCode, newCOA.getPrvCoaCode(),
                                newCOA.getCode(), ttlLength);
                    }

                    //Copy user role
                    spk = new SystemPropertyKeyH2("system.default.admin.role",
                            Integer.parseInt(newCompCode));
                    String oldRoleId = spService.findById(spk).getPropValue();
                    UserRoleH2 newRole = userRoleService.copyRole(oldRoleId, newCompCode);

                    ci.setRoleId(newRole.getRoleId());

                    //Copy privilege
                    privilegeService.copyPrivilege(oldRoleId, newRole.getRoleId().toString());

                    if (!type.equals("-")) {
                        //Assign role to user
                        UsrCompRoleKeyH2 newKey = new UsrCompRoleKeyH2();
                        newKey.setCompCode(Integer.parseInt(newCompCode));
                        newKey.setRoleId(newRole.getRoleId());
                        newKey.setUserId(Integer.parseInt(userId));

                        UsrCompRoleH2 newUserRole = new UsrCompRoleH2();
                        newUserRole.setKey(newKey);

                        usrCompRoleService.save(newUserRole);

                        //Change user status to finished
                        AppUser user = accountService.findUserById(Integer.parseInt(userId));
                        user.setCreateStatus("FINISHED");
                        accountService.saveAccount(user);
                    }
                } catch (Exception ex) {
                    logger.error("save : " + ex.getMessage());
                }

            }
        }

        return ci;
    }

    @Override
    public CompanyInfoH2 saveM(CompanyInfoH2 ci) {
        return dao.save(ci);
    }

    private void insertChild(String oldCompCode, String newCompCode, String parent,
            String currParent, int ttlLength) {
        List<ChartOfAccountH2> listCOA = coaDao.getChild(oldCompCode, parent);

        for (ChartOfAccountH2 tmpCOA : listCOA) {
            String newCOACode = getCOACode(newCompCode, ttlLength);
            ChartOfAccountH2 newCOA = new ChartOfAccountH2();

            BeanUtils.copyProperties(tmpCOA, newCOA);
            newCOA.setPrvCoaCode(newCOA.getCode());
            newCOA.setCode(newCOACode);
            newCOA.setCompCode(Integer.parseInt(newCompCode));
            newCOA.setParent(currParent);
            coaDao.save(newCOA);

            insertChild(oldCompCode, newCompCode, newCOA.getPrvCoaCode(), newCOA.getCode(),
                    ttlLength);
        }
    }

    @Override
    public CompanyInfoH2 findById(Integer code) {
        CompanyInfoH2 ci = dao.findById(code);
        return ci;
    }

    @Override
    public List getAssignCompany(String userId){
        return dao.getAssignCompany(userId);
    }
    @Override
    public List<CompanyInfoH2> search(String code, String name, String phone, String address,
            String businessType, String owner) {
        List<CompanyInfoH2> listCI = dao.search(code, name, phone, address, businessType, owner);
        return listCI;
    }

    @Override
    public int delete(String code) {
        int cnt = dao.delete(code);
        return cnt;
    }
}
