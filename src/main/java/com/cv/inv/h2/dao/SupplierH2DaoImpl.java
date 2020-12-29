/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.inv.h2.entity.SupplierH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class SupplierH2DaoImpl extends AbstractDao<Integer, SupplierH2> implements SupplierH2Dao{
    
    @Override
    public SupplierH2 save(SupplierH2 sup){
        persist(sup);
        return sup;
    }
    
    @Override
    public SupplierH2 findById(Integer id){
        SupplierH2 sup = getByKey(id);
        return sup;
    }
    
    @Override
    public List<SupplierH2> search(String code, String name, String address, 
            String phone, String compCode){
        String strSql = "select o from SupplierH2 o ";
        String strFilter = "";
        
        if(!compCode.equals("-")){
            if(strFilter.isEmpty()){
                strFilter = "o.compCode like '" + compCode + "'";
            }else{
                strFilter = strFilter + " and o.compCode like '" + compCode + "'";
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
        
        List<SupplierH2> listSup = findHSQL(strSql);
        return listSup;
    }
    
    @Override
    public int delete(Integer id){
        String strSql = "delete from SupplierH2 o where o.id = " + id;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }
}
