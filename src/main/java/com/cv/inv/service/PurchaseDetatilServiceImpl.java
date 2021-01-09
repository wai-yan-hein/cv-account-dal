/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

import com.cv.accountswing.dao.GlDao;
import com.cv.accountswing.entity.Gl;
import com.cv.accountswing.util.Util1;
import com.cv.inv.dao.AccSettingDao;
import com.cv.inv.dao.PurchaseHisDao;
import com.cv.inv.dao.PurchaseDetailDao;
import com.cv.inv.entity.AccSetting;
import com.cv.inv.entity.PurDetailKey;
import com.cv.inv.entity.PurHis;
import com.cv.inv.entity.PurHisDetail;
import com.cv.inv.entity.SaleHis;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
@Service
@Transactional
public class PurchaseDetatilServiceImpl implements PurchaseDetailService {

    private static final Logger logger = LoggerFactory.getLogger(RetInServiceImpl.class);
    private final String DELETE_OPTION = "INV_DELETE";
    private final String SOURCE_PROG = "ACCOUNT";
    @Autowired
    private AccSettingDao settingDao;

    @Autowired
    private GlDao glDao;

    @Autowired
    private PurchaseDetailDao dao;
    @Autowired
    private PurchaseHisDao purchaseHisDao;

    @Override
    public PurHisDetail save(PurHisDetail pd) {

        return dao.save(pd);
    }

    @Override
    public List<PurHisDetail> search(String glCode) {
        return dao.search(glCode);
    }

    @Override
    public void save(PurHis pur, List<PurHisDetail> listPD, List<String> delList) {
        String retInDetailId;
        for (int i = 0; i < listPD.size(); i++) {
            PurHisDetail cPD = listPD.get(i);
            if (cPD.getUniqueId() == null) {
                if (i == 0) {
                    cPD.setUniqueId(1);
                } else {
                    PurHisDetail pSd = listPD.get(i - 1);
                    cPD.setUniqueId(pSd.getUniqueId() + 1);
                }
            }
        }
        if (delList != null) {
            delList.forEach(detailId -> {
                try {
                    dao.delete(detailId);
                } catch (Exception ex) {
                    logger.error("delete purchase detail :" + ex.getMessage());
                }
            });
        }
        purchaseHisDao.save(pur);
        String vouNo = pur.getPurInvId();
        for (PurHisDetail pd : listPD) {
            if (pd.getStock() != null) {
                if (pd.getPurDetailKey() != null) {
                    pd.setPurDetailKey(pd.getPurDetailKey());
                } else {
                    retInDetailId = vouNo + '-' + pd.getUniqueId();
                    pd.setPurDetailKey(new PurDetailKey(vouNo, retInDetailId));
                }
                //  pd.setLocation(pur.getLocationId());
                dao.save(pd);
            }
        }
    }

    private void saveGl(SaleHis sh) throws Exception {
        String vouNo = sh.getVouNo();
        String compCode = sh.getCurrency().getKey().getCompCode();
        String curCode = sh.getCurrency().getKey().getCode();
        String cusCode = sh.getTraderId().getCode();
        String vouTtlAccId = "";
        String sourceAccount = "";
        String discAccId = "";
        String payAccId = "";
        String taxAccId = "";
        String depCode = "";
        AccSetting setting = settingDao.findByCode("Purchase");
        if (setting.getVouAccount() != null) {
            vouTtlAccId = setting.getVouAccount().getCode();
        }
        if (setting.getDisAccount() != null) {
            discAccId = setting.getDisAccount().getCode();
        }
        if (setting.getPayAccount() != null) {
            payAccId = setting.getPayAccount().getCode();
        }
        if (setting.getTaxAccount() != null) {
            taxAccId = setting.getTaxAccount().getCode();
        }
        if (setting.getDepartment() != null) {
            depCode = setting.getDepartment().getDeptCode();
        }
        if (setting.getSoureAccount() != null) {
            sourceAccount = setting.getSoureAccount().getCode();
        }
        boolean isDeleted = false;
        String tranSource = "INVENTORY" + "-PUR";
        String remark = "";
        int split_id = 3;
        double vouTotal = 0.0;
        double discount = 0.0;
        double payment = 0.0;
        double tax = 0.0;
        Date purDate = null;
        List<Gl> listGL = glDao.search("-", "-", "-", "-", "-", "-", "-", "-", "-", vouNo, "-", "-", compCode, tranSource, "-", "-", "-");

        boolean vTtlNeed = true;
        boolean discNeed = true;
        boolean payNeed = true;
        boolean taxNeed = true;

        if (listGL != null) {
            if (!listGL.isEmpty()) {
                for (Gl gl : listGL) {
                    if (isDeleted) {
                        glDao.delete(gl.getGlCode(), DELETE_OPTION);
                    } else {
                        if (gl.getAccountId().equals(vouTtlAccId)) {
                            vTtlNeed = false;
                            if (vouTotal != 0) {
                                glDao.delete(gl.getGlCode(), "Update");
                                gl.setCrAmt(vouTotal);
                                gl.setDescription(vouNo + remark);
                            } else {
                                glDao.delete(gl.getGlCode(), DELETE_OPTION);
                            }
                        } else if (gl.getAccountId().equals(discAccId)) {
                            discNeed = false;
                            if (discount != 0) {
                                glDao.delete(gl.getGlCode(), "Update");
                                gl.setDrAmt(discount);
                                gl.setDescription(vouNo + remark);
                            } else {
                                glDao.delete(gl.getGlCode(), DELETE_OPTION);
                            }
                        } else if (gl.getAccountId().equals(payAccId)) {
                            payNeed = false;
                            if (payment != 0) {
                                glDao.delete(gl.getGlCode(), "Update");
                                gl.setDrAmt(payment);
                                gl.setDescription(vouNo + remark);
                            } else {
                                glDao.delete(gl.getGlCode(), DELETE_OPTION);
                            }
                        } else if (gl.getAccountId().equals(taxAccId)) {
                            taxNeed = false;
                            if (tax != 0) {
                                glDao.delete(gl.getGlCode(), "Update");
                                gl.setDrAmt(tax);
                                gl.setDescription(vouNo + remark);
                            } else {
                                glDao.delete(gl.getGlCode(), DELETE_OPTION);
                            }
                        }

                        gl.setGlDate(purDate);
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
            discNeed = false;
            payNeed = false;
            taxNeed = false;
        }

        if (vouTotal != 0 && vTtlNeed) {
            Gl glVouTotal = new Gl();
            glVouTotal.setSourceAcId(sourceAccount);
            glVouTotal.setAccountId(vouTtlAccId);
            glVouTotal.setCompCode(compCode);
            glVouTotal.setCrAmt(vouTotal);
            glVouTotal.setDescription(vouNo + remark);
            glVouTotal.setGlDate(purDate);
            glVouTotal.setCreatedBy(SOURCE_PROG);
            glVouTotal.setCreatedDate(Util1.getTodayDate());
            glVouTotal.setTraderCode(cusCode);
            glVouTotal.setTranSource(tranSource);
            glVouTotal.setVouNo(vouNo);
            glVouTotal.setReference("Purchase Voucher Total");
            glVouTotal.setSplitId(split_id);
            glVouTotal.setFromCurId(curCode);
            glVouTotal.setDeptId(depCode);
            listGL.add(glVouTotal);
        }

        if (discount != 0 && discNeed) {
            Gl glDiscount = new Gl();
            glDiscount.setSourceAcId(sourceAccount);
            glDiscount.setAccountId(discAccId);
            glDiscount.setCompCode(compCode);
            glDiscount.setDrAmt(discount);
            glDiscount.setDescription(vouNo + remark);
            glDiscount.setGlDate(purDate);
            glDiscount.setCreatedBy(SOURCE_PROG);
            glDiscount.setCreatedDate(Util1.getTodayDate());
            glDiscount.setTraderCode(cusCode);
            glDiscount.setTranSource(tranSource);
            glDiscount.setVouNo(vouNo);
            glDiscount.setReference("Purchase Voucher Discount");
            //glDiscount.setSplitId(split_id);
            glDiscount.setFromCurId(curCode);
            glDiscount.setDeptId(depCode);
            listGL.add(glDiscount);
        }

        if (payment != 0 && payNeed) {
            Gl glVouPay = new Gl();
            glVouPay.setSourceAcId(sourceAccount);
            glVouPay.setAccountId(payAccId);
            glVouPay.setCompCode(compCode);
            glVouPay.setDrAmt(payment);
            glVouPay.setDescription(vouNo + remark);
            glVouPay.setGlDate(purDate);
            glVouPay.setCreatedBy(SOURCE_PROG);
            glVouPay.setCreatedDate(Util1.getTodayDate());
            glVouPay.setTraderCode(cusCode);
            glVouPay.setTranSource(tranSource);
            glVouPay.setVouNo(vouNo);
            glVouPay.setReference("Purchase Voucher Payment");
            //glVouPay.setSplitId(split_id);
            glVouPay.setFromCurId(curCode);
            glVouPay.setDeptId(depCode);
            listGL.add(glVouPay);
        }

        if (tax != 0 && taxNeed) {
            Gl glVouTax = new Gl();
            glVouTax.setSourceAcId(sourceAccount);
            glVouTax.setAccountId(taxAccId);
            glVouTax.setCompCode(compCode);
            glVouTax.setCrAmt(tax);
            glVouTax.setDescription(vouNo + remark);
            glVouTax.setGlDate(purDate);
            glVouTax.setCreatedBy(SOURCE_PROG);
            glVouTax.setCreatedDate(Util1.getTodayDate());
            glVouTax.setTraderCode(depCode);
            glVouTax.setTranSource(tranSource);
            glVouTax.setVouNo(vouNo);
            glVouTax.setReference("Purchase Voucher Tax");
            //glVouTax.setSplitId(split_id);
            glVouTax.setFromCurId(curCode);
            glVouTax.setDeptId(depCode);
            listGL.add(glVouTax);
        }

        if (!listGL.isEmpty()) {
            //saveBatchGL(listGL);
        }

    }
}
