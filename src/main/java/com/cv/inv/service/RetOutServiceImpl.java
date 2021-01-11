/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

import com.cv.accountswing.dummy.VouSearch;
import com.cv.accountswing.entity.Gl;
import com.cv.accountswing.service.GlService;
import com.cv.accountswing.util.Util1;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cv.inv.dao.RetOutDao;
import com.cv.inv.dao.RetOutDetailDao;
import com.cv.inv.entity.AccSetting;
import com.cv.inv.entity.RetOutCompoundKey;
import com.cv.inv.entity.RetOutHisDetail;
import com.cv.inv.entity.RetOutHis;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lenovo
 */
@Service
@Transactional
public class RetOutServiceImpl implements RetOutService {

    private static final Logger log = LoggerFactory.getLogger(RetOutServiceImpl.class);
    private final String SOURCE_PROG = "ACCOUNT";
    @Autowired
    private GlService glService;
    @Autowired
    private AccSettingService settingService;
    @Autowired
    private RetOutDao retOutDao;
    @Autowired
    private RetOutDetailDao dao;

    @Override
    public void save(RetOutHis retOut, List<RetOutHisDetail> listRetIn, List<String> delList) {
        for (int i = 0; i < listRetIn.size(); i++) {
            RetOutHisDetail cRD = listRetIn.get(i);
            if (cRD.getUniqueId() == null) {
                if (i == 0) {
                    cRD.setUniqueId(1);
                } else {
                    RetOutHisDetail pRD = listRetIn.get(i - 1);
                    cRD.setUniqueId(pRD.getUniqueId() + 1);
                }
            }
        }
        if (delList != null) {
            delList.forEach(detailId -> {
                try {
                    dao.delete(detailId);
                } catch (Exception ex) {
                    log.error("Delete Return Out Detail :" + ex.getMessage());
                }
            });
        }
        retOutDao.save(retOut);
        String vouNo = retOut.getVouNo();
        listRetIn.stream().filter(rd -> (rd.getStock() != null)).map(rd -> {
            if (rd.getOutCompoundKey() != null) {
                rd.setOutCompoundKey(rd.getOutCompoundKey());
            } else {
                String retInDetailId = vouNo + '-' + rd.getUniqueId();
                rd.setOutCompoundKey(new RetOutCompoundKey(retInDetailId, vouNo));
            }
            return rd;
        }).forEachOrdered(rd -> {
            dao.save(rd);
        });
        saveGl(retOut);

    }

    @Override
    public void delete(String retInId) throws Exception {
        retOutDao.delete(retInId);
    }

    @Override
    public List<RetOutHis> search(String fromDate, String toDate, String cusId, String locId, String vouNo, String filterCode) {
        return retOutDao.search(fromDate, toDate, cusId, locId, vouNo, filterCode);
    }

    @Override
    public List<VouSearch> searchM(String fromDate, String toDate,
            String cusId, String locId, String vouNo, String filterCode) throws Exception {
        ResultSet rs = retOutDao.searchM(fromDate, toDate, cusId, locId, vouNo, filterCode);
        List<VouSearch> listVS = null;

        if (rs != null) {
            listVS = new ArrayList();
            while (rs.next()) {
                VouSearch vs = new VouSearch(
                        Util1.toDateStr(rs.getDate("ret_out_date"), "yyyy-MM-dd HH:mm:ss"),
                        rs.getString("ret_out_id"),
                        rs.getString("remark"),
                        rs.getString("trader_name"),
                        rs.getDouble("vou_total"),
                        rs.getBoolean("deleted"),
                        rs.getString("location_name"),
                        rs.getString("user_short_name")
                );
                listVS.add(vs);
            }

            rs.close();
        }
        return listVS;
    }

    @Override
    public RetOutHis findById(String id) {
        return retOutDao.findById(id);
    }

    private void saveGl(RetOutHis rh) {
        Date glDate = rh.getRetOutDate();
        Integer macId = rh.getMacId();
        String vouNo = rh.getVouNo();
        String compCode = rh.getCurrency().getKey().getCompCode();
        String curCode = rh.getCurrency().getKey().getCode();
        String cusCode = rh.getCustomer().getCode();
        float vouTotal = Util1.getFloat(rh.getVouTotal());
        float payment = Util1.getFloat(rh.getPaid());
        String vouTtlAccId = "";
        String sourceAccount = "";
        String payAccId = "";
        String depCode = "";
        AccSetting setting = settingService.findByCode("Return Out");
        if (setting.getVouAccount() != null) {
            vouTtlAccId = setting.getVouAccount().getCode();
        }
        /*if (setting.getDisAccount() != null) {
        discAccId = setting.getDisAccount().getCode();
        }*/
        if (setting.getPayAccount() != null) {
            payAccId = setting.getPayAccount().getCode();
        }
        /* if (setting.getTaxAccount() != null) {
        taxAccId = setting.getTaxAccount().getCode();
        }*/
        if (setting.getDepartment() != null) {
            depCode = setting.getDepartment().getDeptCode();
        }
        if (setting.getSoureAccount() != null) {
            sourceAccount = setting.getSoureAccount().getCode();
        }
        boolean isDeleted = false;
        String tranSource = "INVENTORY" + "-RETURN OUT";
        String remark = "";
        int split_id = 6;

        List<Gl> listGL = glService.search("-", "-", "-", "-", "-", "-", "-", "-", "-", vouNo, "-", "-", compCode, tranSource, "-", "-", "-");

        boolean vTtlNeed = true;
        boolean payNeed = true;

        if (listGL != null) {
            if (!listGL.isEmpty()) {
                for (Gl gl : listGL) {
                    if (isDeleted) {
                        //glDao.delete(gl.getGlCode(), DELETE_OPTION);
                    } else {
                        if (gl.getAccountId().equals(vouTtlAccId)) {
                            vTtlNeed = false;
                            if (vouTotal != 0) {
                                //glDao.delete(gl.getGlCode(), "Update");
                                gl.setCrAmt(vouTotal);
                                gl.setDescription(vouNo + remark);
                            } else {
                                //glDao.delete(gl.getGlCode(), DELETE_OPTION);
                            }
                        } else if (gl.getAccountId().equals(payAccId)) {
                            payNeed = false;
                            if (payment != 0) {
                                //glDao.delete(gl.getGlCode(), "Update");
                                gl.setDrAmt(payment);
                                gl.setDescription(vouNo + remark);
                            } else {
                                //glDao.delete(gl.getGlCode(), DELETE_OPTION);
                            }
                        }

                        gl.setGlDate(glDate);
                        gl.setTraderCode(cusCode);
                        gl.setFromCurId(curCode);
                    }
                }
            } else {
                listGL = new ArrayList();
            }
        } else {

            listGL = new ArrayList();
        }

        if (isDeleted) {
            vTtlNeed = false;
            payNeed = false;
        }

        if (vouTotal != 0 && vTtlNeed) {
            Gl glVouTotal = new Gl();
            glVouTotal.setSourceAcId(sourceAccount);
            glVouTotal.setAccountId(vouTtlAccId);
            glVouTotal.setCompCode(compCode);
            glVouTotal.setCrAmt(vouTotal);
            glVouTotal.setDescription(vouNo + remark);
            glVouTotal.setGlDate(glDate);
            glVouTotal.setCreatedBy(SOURCE_PROG);
            glVouTotal.setCreatedDate(Util1.getTodayDate());
            glVouTotal.setTraderCode(cusCode);
            glVouTotal.setTranSource(tranSource);
            glVouTotal.setVouNo(vouNo);
            glVouTotal.setReference("Return Out Voucher Total");
            glVouTotal.setSplitId(split_id);
            glVouTotal.setFromCurId(curCode);
            glVouTotal.setDeptId(depCode);
            glVouTotal.setMacId(macId);
            listGL.add(glVouTotal);
        }

        if (payment != 0 && payNeed) {
            Gl glVouPay = new Gl();
            glVouPay.setSourceAcId(sourceAccount);
            glVouPay.setAccountId(payAccId);
            glVouPay.setCompCode(compCode);
            glVouPay.setDrAmt(payment);
            glVouPay.setDescription(vouNo + remark);
            glVouPay.setGlDate(glDate);
            glVouPay.setCreatedBy(SOURCE_PROG);
            glVouPay.setCreatedDate(Util1.getTodayDate());
            glVouPay.setTraderCode(cusCode);
            glVouPay.setTranSource(tranSource);
            glVouPay.setVouNo(vouNo);
            glVouPay.setReference("Return Out Voucher Payment");
            glVouPay.setSplitId(split_id);
            glVouPay.setFromCurId(curCode);
            glVouPay.setDeptId(depCode);
            glVouPay.setMacId(macId);
            listGL.add(glVouPay);
        }

        if (!listGL.isEmpty()) {
            for (Gl gl : listGL) {
                try {
                    glService.save(gl);
                } catch (Exception ex) {
                    log.error("Save Return Out GL :" + ex.getMessage());
                }
            }
        }

    }

}
