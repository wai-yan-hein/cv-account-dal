/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.dao;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/dao/DamageHisDao.java
import com.cv.account.inv.entity.DamageHis;
import java.sql.ResultSet;
=======
import com.cv.inv.entity.DamageHis;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/dao/DamageHisDao.java
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface DamageHisDao {

    public DamageHis save(DamageHis ph);

    public DamageHis findById(String id);

    public List<DamageHis> search(String from, String to, String location,
            String remark, String vouNo);
    
    public ResultSet searchM(String from, String to, String location,
            String remark, String vouNo)throws Exception;

    public int delete(String vouNo);
}
