/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.accountswing.entity.Customer;
import com.cv.inv.h2.entity.CustomerH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class CustomerH2DaoImpl extends AbstractDao<Integer, CustomerH2> implements CustomerH2Dao{
    
    @Override
    public CustomerH2 save(CustomerH2 cus){
        persist(cus);
        return cus;
    }
    
    @Override
    public CustomerH2 findById(Integer id){
        CustomerH2 cus = getByKey(id);
        return cus;
    }
    
    @Override
    public List<CustomerH2> search(String code, String name, String address, 
            String phone, String compCode){
        String strSql = "select o from CustomerH2 o ";
        String strFilter = "";
        
        if(!compCode.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.compCode = '" + compCode + "'";
            }else{
                strFilter = strFilter + " and o.compCode = '" + compCode + "'";
            }
        }
        
        if(!code.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.traderId = '" + code + "'";
            }else{
                strFilter = strFilter + " and o.traderId = '" + code + "'";
            }
        }
        
        if(!name.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.traderName like '%" + name + "%'";
            }else{
                strFilter = strFilter + " and o.traderName like '%" + name + "%'";
            }
        }
        
        if(!address.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.address like '%" + address + "%'";
            }else{
                strFilter = strFilter + " and o.address like '%" + address + "%'";
            }
        }
        
        if(!phone.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.phone like '%" + phone + "%'";
            }else{
                strFilter = strFilter + " and o.phone like '%" + phone + "%'";
            }
        }
        
        if(!strFilter.isEmpty()){
            strSql = strSql + " where " + strFilter;
        }
        
        List<CustomerH2> listCus = findHSQL(strSql);
        return listCus;
    }
    
    @Override
    public int delete(Integer id){
        String strSql = "delete from CustomerH2 o where o.id = " + id;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
}
