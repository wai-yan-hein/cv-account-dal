/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.accountswing.util.Util1;
import com.cv.inv.dao.CharacterNoDao;
import com.cv.inv.entity.CharacterNo;
import com.cv.inv.entity.StockType;
import com.cv.inv.entity.Stock;
import com.cv.inv.h2.dao.StockH2Dao;
import com.cv.inv.h2.entity.StockH2;
import com.cv.inv.h2.entity.StockTypeH2;
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
@Transactional
@Service
public class StockH2ServiceImpl implements StockH2Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockH2Service.class);
    @Autowired
    private StockH2Dao dao;
    @Autowired
    private CharacterNoDao chDao;

    @Override
    public StockH2 save(StockH2 stock, StockTypeH2 item, String status) {
        if (status.equals("NEW")) {
            if (stock.getStockCode().isEmpty()) {
                stock.setStockCode(getMedCode(stock.getStockName(), item));
            } else {
                StockH2 findById = dao.findById(stock.getStockCode());
                if (findById != null) {
                }

            }
        }

        return dao.save(stock);
    }
    @Override
     public StockH2 saveM(StockH2 stock){
         return dao.save(stock);
     }

    @Override
    public StockH2 findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<StockH2> findAll() {
        return dao.findAll();
    }

    @Override
    public List<StockH2> findActiveStock() {
        return dao.findActiveStock();
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    private String getMedCode(String stockName, StockTypeH2 itemType) {
        String medCode = "";
        CharacterNo characterNo = null;

        if (!stockName.equals("")) {
            try {
                for (int i = 0; i < stockName.length(); i++) {
                    String strTmp = stockName.substring(i, i + 1).toUpperCase();
                    if (!strTmp.trim().equals("")) { //Space character detection
                        if (Util1.isNumber(strTmp)) {
                            i = stockName.length();
                            characterNo = new CharacterNo(" ", "00");
                        } else {
                            characterNo = chDao.findById(stockName);
                            if (characterNo != null) {
                                i = stockName.length();
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                LOGGER.error("getMedCode : " + ex.getStackTrace()[0].getLineNumber() + " - " + ex.toString());
            }
            String strType = "";
            if (characterNo != null) {
                strType = characterNo.getStrNumber();
            }
            medCode = itemType.getItemTypeCode() + strType;
            int typeLength = itemType.getItemTypeCode().length();

            try {
                Object maxMedId = dao.getMax("select max(o.stock_code) from stock o where o.stock_code like '" + medCode + "%'");
                int ttlLength = 5;// get from system property
                int leftLength = ttlLength - medCode.length();

                if (maxMedId == null) {
                    for (int i = 0; i < leftLength - 1; i++) {
                        medCode = medCode + "0";
                    }

                    medCode = medCode + "1";
                } else {
                    int begin = typeLength + 2;
                    String tmpMedSerial = maxMedId.toString().substring(begin);

                    Integer tmpSerial = Integer.parseInt(tmpMedSerial) + 1;
                    tmpMedSerial = tmpSerial.toString();

                    for (int i = 0; i < (leftLength - tmpMedSerial.length()); i++) {
                        medCode = medCode + "0";
                    }

                    medCode = medCode + tmpMedSerial;
                }
            } catch (Exception ex) {
                LOGGER.error("getMedCode : " + ex.getStackTrace()[0].getLineNumber() + " - " + ex.toString());
            }
        }

        return medCode;
    }

    @Override
    public List<StockH2> search(String stockType) {
        return dao.search(stockType);
    }

    @Override
    public List<StockH2> searchC(String stockCat) {
        return dao.searchC(stockCat);
    }

    @Override
    public List<StockH2> searchB(String stockBrand) {
        return dao.searchB(stockBrand);
    }

}
