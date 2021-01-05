/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.inv.h2.entity.SystemPropertyH2;
import com.cv.inv.h2.entity.SystemPropertyKeyH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author WSwe
 */
@Repository
public class SystemPropertyH2DaoImpl extends AbstractDao<SystemPropertyKeyH2, SystemPropertyH2> implements SystemPropertyH2Dao{
    
    @Override
    public SystemPropertyH2 save(SystemPropertyH2 sp){
        persist(sp);
        return sp;
    }
    
    @Override
    public SystemPropertyH2 findById(SystemPropertyKeyH2 id){
        SystemPropertyH2 sp = getByKey(id);
        return sp;
    }
    
    @Override
    public List<SystemPropertyH2> search(String code, String compCode, String value){
        String strSql = "select o from SystemPropertyH2 o ";
        String strFilter = "";
        
        if(!code.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.key.propKey like '" + code + "%'";
            }else{
                strFilter = strFilter + " and o.key.propKey like '" + code + "%'";
            }
        }
        
        if(!compCode.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.key.compCode like '" + compCode + "'";
            }else{
                strFilter = strFilter + " and o.key.compCode like '" + compCode + "'";
            }
        }
        
        if(!value.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.propValue = '" + value + "'";
            }else{
                strFilter = strFilter + " and o.propValue = '" + value + "'";
            }
        }
        
        if(!strFilter.isEmpty()){
            strSql = strSql + " where " + strFilter;
        }
        
        List<SystemPropertyH2> listSP = findHSQL(strSql);
        return listSP;
    }
    
    @Override
    public int delete(String code){
        String strSql = "delete from SystemPropertyH2 o where o.key.propKey = '" + code + "'";
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
    
    @Override
    public void copySystemProperty(String from, String to) throws Exception{
        String strSql = "insert into sys_prop(prop_key, prop_value, remark, comp_code) " +
                        "select prop_key, prop_value, remark, " + to +
                        " from sys_prop where comp_code = " + from;
        execSQL(strSql);
    }
}
