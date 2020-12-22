/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.dao;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/dao/SaleHisDao.java
import com.cv.account.inv.entity.SaleHis;
import java.sql.ResultSet;
=======
import com.cv.inv.entity.SaleHis;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/dao/SaleHisDao.java
import java.util.List;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
public interface SaleHisDao {

    public SaleHis save(SaleHis sh);

    public List<SaleHis> search(String fromDate, String toDate, String cusId,
            String vouStatusId, String remark, String stockCode, String userId);

    public ResultSet searchM(String fromDate, String toDate, String cusId,
            String vouStatusId, String remark, String stockCode, String userId) throws Exception;

    public SaleHis findById(String id);

    public int delete(String vouNo);
}
