/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.COADao;
import com.cv.accountswing.dao.CompanyInfoDao;
import com.cv.accountswing.entity.ChartOfAccount;
import com.cv.accountswing.entity.CompanyInfo;
import com.cv.accountswing.entity.SystemProperty;
import com.cv.accountswing.entity.SystemPropertyKey;
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
public class CompanyInfoServiceImpl implements CompanyInfoService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyInfoServiceImpl.class);

    @Autowired
    private CompanyInfoDao dao;
    @Autowired
    private COADao coaDao;
    @Autowired
    private SeqTableService seqService;
    @Autowired
    private SystemPropertyService spService;

    @Override
    public CompanyInfo saveM(CompanyInfo ci) {
        return dao.save(ci);
    }

    @Override
    public CompanyInfo save(CompanyInfo ci, String status, String userCode, String type) {
        if (ci.getCompCode() == null || ci.getCompCode().isEmpty()) {
            String compCode = getCompCode(ci.getMacId());
            ci.setCompCode(compCode);
            //copy system property
            List<CompanyInfo> listCI = dao.search("-", "-", "-", "-", ci.getBusinessType().getCode(), "-");
            if (!listCI.isEmpty()) {
                String oldCompCode = listCI.get(0).getCompCode();
                List<SystemProperty> listSys = spService.search("-", oldCompCode, "-");
                if (!listSys.isEmpty()) {
                    for (SystemProperty sys : listSys) {
                        SystemPropertyKey key = new SystemPropertyKey();
                        key.setCompCode(compCode);
                        key.setPropKey(sys.getKey().getPropKey());
                        SystemProperty property = new SystemProperty();
                        property.setPropValue(sys.getPropValue());
                        property.setRemark(sys.getRemark());
                        property.setKey(key);
                        spService.save(property);
                    }
                }

            }
        }
        ci = dao.save(ci);


        /*if (status.equals("NEW")) {
            String businessType = ci.getBusinessType().getCode();
            List<CompanyInfo> listCI = dao.search("-", "-", "-", "-", businessType.toString(), "-");
            String oldCompCode = "-";
            String newCompCode = ci.getCompCode().toString();

            if (listCI != null) {
                if (!listCI.isEmpty()) {
                    oldCompCode = listCI.get(0).getCompCode().toString();
                }
            }

            if (!oldCompCode.equals("-")) {
                SystemPropertyKey spk = new SystemPropertyKey("system.coa.code.length",
                        oldCompCode);
                SystemProperty sp = spService.findById(spk);
                int ttlLength = Integer.parseInt(sp.getPropValue());
                List<ChartOfAccount> listLevel2COA = coaDao.getCOAWithLevel(oldCompCode, "2");

                try {
                    spService.copySystemProperty(oldCompCode, newCompCode);

                    for (ChartOfAccount tmpCOA : listLevel2COA) {

                        String newCOACode = getCOACode(tmpCOA.getMacId(), newCompCode, ttlLength);
                        ChartOfAccount newCOA = new ChartOfAccount();

                        BeanUtils.copyProperties(tmpCOA, newCOA);
                        newCOA.setPrvCoaCode(newCOA.getCode());
                        newCOA.setCode(newCOACode);
                        newCOA.setCompCode(newCompCode);
                        coaDao.save(newCOA);

                        List<SystemProperty> listSP = spService.search("-", newCompCode, tmpCOA.getCode());
                        for (SystemProperty tmpSP : listSP) {
                            //   tmpSP.setPropValue(newCOACode);
                            spService.save(tmpSP);
                        }

                        insertChild(oldCompCode, newCompCode, newCOA.getPrvCoaCode(),
                                newCOA.getCode(), ttlLength);
                    }

                    //Copy user role
                    spk = new SystemPropertyKey("system.default.admin.role",
                            newCompCode);
                    String oldRoleId = spService.findById(spk).getPropValue();
                    UserRole newRole = userRoleService.copyRole(oldRoleId, newCompCode);

                    ci.setRoleCode(newRole.getRoleCode());

                    //Copy privilege
                    privilegeService.copyPrivilege(oldRoleId, newRole.getRoleCode().toString());

                    if (!type.equals("-")) {
                        //Assign role to user
                        UsrCompRoleKey newKey = new UsrCompRoleKey();
                        newKey.setCompCode(newCompCode);
                        newKey.setRoleCode(newRole.getRoleCode());
                        newKey.setUserCode(userCode);

                        UsrCompRole newUserRole = new UsrCompRole();
                        newUserRole.setKey(newKey);

                        usrCompRoleService.save(newUserRole);

                        //Change user status to finished
                        AppUser user = accountService.findUserById(Integer.parseInt(userCode));
                        user.setCreateStatus("FINISHED");
                        accountService.saveAccount(user);
                    }
                } catch (Exception ex) {
                    logger.error("save : " + ex.getMessage());
                }

            }
        }*/
        return ci;
    }

    private void insertChild(String oldCompCode, String newCompCode, String parent,
            String currParent, int ttlLength) {
        List<ChartOfAccount> listCOA = coaDao.getChild(oldCompCode, parent);

        for (ChartOfAccount tmpCOA : listCOA) {

            String newCOACode = getCOACode(tmpCOA.getMacId(), newCompCode, ttlLength);
            ChartOfAccount newCOA = new ChartOfAccount();
            BeanUtils.copyProperties(tmpCOA, newCOA);
            newCOA.setPrvCoaCode(newCOA.getCode());
            newCOA.setCode(newCOACode);
            newCOA.setCompCode(newCompCode);
            newCOA.setCoaParent(currParent);
            coaDao.save(newCOA);

            insertChild(oldCompCode, newCompCode, newCOA.getPrvCoaCode(), newCOA.getCode(),
                    ttlLength);
        }
    }

    @Override
    public CompanyInfo findById(String code) {
        CompanyInfo ci = dao.findById(code);
        return ci;
    }

    @Override
    public List<CompanyInfo> search(String code, String name, String phone, String address,
            String businessType, String owner) {
        List<CompanyInfo> listCI = dao.search(code, name, phone, address, businessType, owner);
        return listCI;
    }

    @Override
    public int delete(String code) {
        int cnt = dao.delete(code);
        return cnt;
    }

    private String getCOACode(Integer macId, String compCode, int ttlLength) {
        int seqNo = seqService.getSequence(macId, "COA", "-", compCode);
        String coaCode = compCode + "-" + String.format("%0" + ttlLength + "d", seqNo);
        return coaCode;
    }

    private String getCompCode(Integer macId) {
        int seqNo = seqService.getSequence(macId, "Company", "-", "-");
        String coaCode = String.format("%0" + 3 + "d", macId) + String.format("%0" + 4 + "d", seqNo);
        return coaCode;
    }

    @Override
    public CompanyInfo save(CompanyInfo ci) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
