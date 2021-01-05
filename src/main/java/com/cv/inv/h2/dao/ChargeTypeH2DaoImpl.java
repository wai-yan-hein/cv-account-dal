/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.AbstractDao;
import com.cv.inv.h2.entity.ChargeTypeH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
@Repository
public class ChargeTypeH2DaoImpl extends AbstractDao<String, ChargeTypeH2> implements ChargeTypeH2Dao {

    @Override
    public ChargeTypeH2 save(ChargeTypeH2 chargeType) {
        persist(chargeType);
        return chargeType;
    }

    @Override
    public List<ChargeTypeH2> findAll() {
        String hsql = "select o from ChargeTypeH2 o";
        return findHSQL(hsql);
    }

    @Override
    public int delete(String id) {
        String hsql = "delete from ChargeTypeH2 o where o.chargeTypeId='" + id + "'";
        return execUpdateOrDelete(hsql);
    }
    
      @Override
    public List<ChargeTypeH2> search(String ctId, String desp){
        String strFilter = "";
        
        
        if(!desp.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.chargeTypeId =" + desp ;
            }else{
                strFilter = strFilter + " and o.chargeTypeId =" + desp ;
            }
        }
        if(!desp.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.chargeTypeDesp like '%" + desp + "%'";
            }else{
                strFilter = strFilter + " and o.chargeTypeDesp like '%" + desp + "%'";
            }
        }
        
       /* if(!updatedDate.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.updatedDate = '" + updatedDate + "'";
            }else{
                strFilter = strFilter + " and o.updatedDate = '" + updatedDate + "'";
            }
        }
        */
        if(strFilter.isEmpty()){
            strFilter = "select o from ChargeTypeH2 o";
        }else{
            strFilter = "select o from ChargeTypeH2 o where " + strFilter;
        }
        
        List<ChargeTypeH2> listCT = findHSQL(strFilter);
        return listCT;
    }

}
