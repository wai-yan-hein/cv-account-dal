/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.service;

import com.cv.account.api.dao.FontDao;
import com.cv.account.api.entity.Font;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
@Service
@Transactional
public class FontServiceImpl implements FontService {

    @Autowired
    private FontDao fontDao;

    @Override
    public List<Font> getAll() {
        return fontDao.getAll();
    }

}
