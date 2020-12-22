/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.dao;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/dao/PurchaseHisDao.java
import com.cv.account.inv.entity.PurHis;
import java.sql.ResultSet;
=======
import com.cv.inv.entity.PurHis;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/dao/PurchaseHisDao.java
import java.util.List;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
public interface PurchaseHisDao {

    public PurHis save(PurHis sh);

    public List<PurHis> search(String fromDate, String toDate, String cusId, String vouStatusId, String remark);

    public ResultSet searchM(String fromDate, String toDate,
            String cusId, String vouStatusId, String remark)throws Exception;
    public PurHis findById(String id);

    public int delete(String vouNo);

}
