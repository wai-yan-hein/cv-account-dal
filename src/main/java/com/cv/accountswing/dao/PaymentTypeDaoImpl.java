/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.PaymentType;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class PaymentTypeDaoImpl extends AbstractDao<Integer, PaymentType> implements PaymentTypeDao{
    
    @Override
    public PaymentType save(PaymentType pt){
        persist(pt);
        return pt;
    }
    
    @Override
    public PaymentType findById(Integer id){
        PaymentType pt = getByKey(id);
        return pt;
    }
    
    @Override
    public List<PaymentType> search(String name, String compCode){
        String strSql = "select o from PaymentType o ";
        String strFilter = "";
        
        if(!name.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.paymentTypeName like '%" + name + "%'";
            }else{
                strFilter = strFilter + " and o.paymentTypeName like '%" + name + "%'";
            }
        }
        
        if(!compCode.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.compCode = " + compCode;
            }else{
                strFilter = strFilter + " and o.compCode = " + compCode;
            }
        }
        
        if(!strFilter.isEmpty()){
            strSql = strSql + " where " + strFilter;
        }
        
        List<PaymentType> listPT = findHSQL(strSql);
        return listPT;
    }
    
    @Override
    public int delete(Integer id, String compCode){
        String strSql = "delete from PaymentType o where o.typeId = " + id +
                " and o.compCode = " + compCode;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
}
