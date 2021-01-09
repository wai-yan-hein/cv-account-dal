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
import com.cv.inv.dao.SaleDetailDao;
import com.cv.inv.dao.SaleHisDao;
import com.cv.inv.entity.AccSetting;
import com.cv.inv.entity.SaleDetailKey;
import com.cv.inv.entity.SaleHisDetail;
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
 * @author Mg Kyaw Thura Aung
 */
@Service
@Transactional
public class SaleDetailServiceImpl implements SaleDetailService {

    private static final Logger logger = LoggerFactory.getLogger(SaleDetailServiceImpl.class);
    private final String SOURCE_PROG = "ACCOUNT";
    private final String tranSource = "ACCOUNT-SALE";
    private final int split_id = 2;
    private final String DELETE_OPTION = "INV_DELETE";
    @Autowired
    private AccSettingDao settingDao;

    @Autowired
    private SaleDetailDao dao;

    @Autowired
    private SaleHisDao hisDao;
    @Autowired
    private GlDao glDao;

    @Override
    public SaleHisDetail save(SaleHisDetail sdh) {
        return dao.save(sdh);
    }

    @Override
    public List<SaleHisDetail> search(String vouId) {
        return dao.search(vouId);
    }

    @Override
    public void save(SaleHis saleHis, List<SaleHisDetail> listSaleDetail,
            String vouStatus, List<String> deleteList) throws Exception {
        String retInDetailId;
        //serialize unique id
        for (int i = 0; i < listSaleDetail.size(); i++) {
            SaleHisDetail cSd = listSaleDetail.get(i);
            if (cSd.getUniqueId() == null) {
                if (i == 0) {
                    cSd.setUniqueId(1);
                } else {
                    SaleHisDetail pSd = listSaleDetail.get(i - 1);
                    cSd.setUniqueId(pSd.getUniqueId() + 1);
                }
            }
        }
        if (vouStatus.equals("EDIT")) {

            if (deleteList != null) {
                deleteList.forEach(detailId -> {
                    if (detailId != null) {
                        dao.delete(detailId);
                    }
                });
            }
        }
        hisDao.save(saleHis);
        String vouNo = saleHis.getVouNo();
        for (SaleHisDetail sd : listSaleDetail) {
            if (sd.getStock() != null) {
                if (sd.getSaleDetailKey() != null) {
                    sd.setSaleDetailKey(sd.getSaleDetailKey());
                } else {
                    retInDetailId = vouNo + '-' + sd.getUniqueId();
                    sd.setSaleDetailKey(new SaleDetailKey(vouNo, retInDetailId));
                }
                //  pd.setLocation(pur.getLocationId());
                dao.save(sd);
            }
        }
        //save to account 
        saveGl(saleHis);
    }
    
    @Override
    public SaleHisDetail saveM(SaleHis saleHis, List<SaleHisDetail> listSaleDetail,
            String vouStatus, List<String> deleteList) throws Exception {
        String retInDetailId;
        //serialize unique id
        for (int i = 0; i < listSaleDetail.size(); i++) {
            SaleHisDetail cSd = listSaleDetail.get(i);
            if (cSd.getUniqueId() == null) {
                if (i == 0) {
                    cSd.setUniqueId(1);
                } else {
                    SaleHisDetail pSd = listSaleDetail.get(i - 1);
                    cSd.setUniqueId(pSd.getUniqueId() + 1);
                }
            }
        }
        if (vouStatus.equals("EDIT")) {

            if (deleteList != null) {
                deleteList.forEach(detailId -> {
                    if (detailId != null) {
                        dao.delete(detailId);
                    }
                });
            }
        }
        hisDao.save(saleHis);
        String vouNo = saleHis.getVouNo();
        SaleHisDetail shd= new SaleHisDetail();
        for (SaleHisDetail sd : listSaleDetail) {
            if (sd.getStock() != null) {
                if (sd.getSaleDetailKey() != null) {
                    sd.setSaleDetailKey(sd.getSaleDetailKey());
                } else {
                    retInDetailId = vouNo + '-' + sd.getUniqueId();
                    sd.setSaleDetailKey(new SaleDetailKey(vouNo, retInDetailId));
                }
                //  pd.setLocation(pur.getLocationId());
              shd=  dao.save(sd);
            }
        }
        
        return  shd;

    }

    private void saveGl(SaleHis sh) throws Exception {
        String compCode = sh.getCurrency().getKey().getCompCode();
        String curCode = sh.getCurrency().getKey().getCode();
        String cusCode = sh.getTraderId().getCode();
        String vouNo = sh.getVouNo();
        String vouTtlAccId = "";
        String sourceAccount = "";
        String discAccId = "";
        String payAccId = "";
        String taxAccId = "";
        String depCode = "";
        AccSetting setting = settingDao.findByCode("Sale");
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
        Double vouTotal = 0.0;
        String remark = "";
        Date saleDate = null;
        Double discount = 0.0;
        Double payment = 0.0;
        Double tax = 0.0;

        boolean isDeleted = false;
        List<Gl> listGL = glDao.search("-", "-", "-", "-", "-", "-", "-", "-", "-", vouNo,
                "-", "-", compCode, tranSource, "-", "-", "-");
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
                                gl.setDrAmt(vouTotal);
                                gl.setDescription(vouNo + remark);
                            } else {
                                glDao.delete(gl.getGlCode(), DELETE_OPTION);
                            }
                        } else if (gl.getAccountId().equals(discAccId)) {
                            discNeed = false;
                            if (discount != 0) {
                                glDao.delete(gl.getGlCode(), "Update");
                                gl.setCrAmt(discount);
                                gl.setDescription(vouNo + remark);
                            } else {
                                glDao.delete(gl.getGlCode(), DELETE_OPTION);
                            }
                        } else if (gl.getAccountId().equals(payAccId)) {
                            payNeed = false;
                            if (payment != 0) {
                                glDao.delete(gl.getGlCode(), "Update");
                                gl.setCrAmt(payment);
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

                        gl.setGlDate(saleDate);
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
        if (vouTotal != 0) {
            Gl glVouTotal = new Gl();
            glVouTotal.setSourceAcId(sourceAccount);
            glVouTotal.setAccountId(vouTtlAccId);
            glVouTotal.setCompCode(compCode);
            glVouTotal.setDrAmt(vouTotal);
            glVouTotal.setDescription(vouNo + remark);
            glVouTotal.setGlDate(saleDate);
            glVouTotal.setCreatedBy(SOURCE_PROG);
            glVouTotal.setCreatedDate(Util1.getTodayDate());
            glVouTotal.setTraderCode(cusCode);
            glVouTotal.setTranSource(tranSource);
            glVouTotal.setVouNo(vouNo);
            glVouTotal.setReference("Sale Voucher Total");
            glVouTotal.setSplitId(split_id);
            glVouTotal.setFromCurId(curCode);
            glVouTotal.setDeptId(depCode);
            listGL.add(glVouTotal);
        }

        if (discount != 0) {
            Gl glDiscount = new Gl();
            glDiscount.setSourceAcId(sourceAccount);
            glDiscount.setAccountId(discAccId);
            glDiscount.setCompCode(compCode);
            glDiscount.setCrAmt(discount);
            glDiscount.setDescription(vouNo + remark);
            glDiscount.setGlDate(saleDate);
            glDiscount.setCreatedBy(SOURCE_PROG);
            glDiscount.setCreatedDate(Util1.getTodayDate());
            glDiscount.setTraderCode(cusCode);
            glDiscount.setTranSource(tranSource);
            glDiscount.setVouNo(vouNo);
            glDiscount.setReference("Sale Voucher Discount");
            glDiscount.setDeptId(depCode);
            glDiscount.setSplitId(split_id);
            glDiscount.setFromCurId(curCode);
            listGL.add(glDiscount);
        }

        if (payment != 0) {
            Gl glVouPay = new Gl();
            glVouPay.setSourceAcId(sourceAccount);
            glVouPay.setAccountId(payAccId);
            glVouPay.setCompCode(compCode);
            glVouPay.setCrAmt(payment);
            glVouPay.setDescription(vouNo + remark);
            glVouPay.setGlDate(saleDate);
            glVouPay.setCreatedBy(SOURCE_PROG);
            glVouPay.setCreatedDate(Util1.getTodayDate());
            glVouPay.setTraderCode(cusCode);
            glVouPay.setTranSource(tranSource);
            glVouPay.setVouNo(vouNo);
            glVouPay.setReference("Sale Voucher Payment");
            glVouPay.setDeptId(depCode);
            glVouPay.setSplitId(split_id);
            glVouPay.setFromCurId(curCode);
            listGL.add(glVouPay);
        }

        if (tax != 0) {
            Gl glVouTax = new Gl();
            glVouTax.setSourceAcId(sourceAccount);
            glVouTax.setAccountId(taxAccId);
            glVouTax.setCompCode(compCode);
            glVouTax.setCrAmt(tax);
            glVouTax.setDescription(vouNo + remark);
            glVouTax.setGlDate(saleDate);
            glVouTax.setCreatedBy(SOURCE_PROG);
            glVouTax.setCreatedDate(Util1.getTodayDate());
            glVouTax.setTraderCode(cusCode);
            glVouTax.setTranSource(tranSource);
            glVouTax.setVouNo(vouNo);
            glVouTax.setReference("Sale Voucher Tax");
            glVouTax.setDeptId(depCode);
            glVouTax.setSplitId(split_id);
            glVouTax.setFromCurId(curCode);
            listGL.add(glVouTax);
        }

        if (!listGL.isEmpty()) {
            listGL.forEach((gl) -> {
                try {
                    glDao.save(gl);
                } catch (Exception ex) {
                    logger.error("SaveToGl :" + ex.getMessage());
                }
            });
        }

    }
}
