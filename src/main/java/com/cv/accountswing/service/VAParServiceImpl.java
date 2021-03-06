/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.VAParDao;
import com.cv.accountswing.entity.view.VApar;
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
public class VAParServiceImpl implements VAParService {

    @Autowired
    private VAParDao dao;

    @Override
    public List<VApar> getApAr(String userCode, String compCode, String traderType) {
        return dao.getApAr(userCode, compCode, traderType);
    }
}
