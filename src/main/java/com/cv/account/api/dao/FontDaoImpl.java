/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.Font;
import com.cv.account.api.entity.FontKey;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class FontDaoImpl extends AbstractDao<FontKey, Font> implements FontDao{
    
    @Override
    public List<Font> getAll(){
        String strSql = "select o from Font o";
        List<Font> listFont = findHSQL(strSql);
        return listFont;
    }
}
