/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

import java.util.Map;

/**
 *
 * @author Lenovo
 */
public interface SReportService {

    public void generateStockBalance(String stockCode, String locId, String compId, String macId);

    public void generateSaleByStock(String stockCode, String regionCode, String macId);

    public void reportViewer(String reportPath, String filePath, String fontPath,
            Map<String, Object> parameters);
}