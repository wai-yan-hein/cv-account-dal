/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.dao;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/dao/RetInDao.java
import com.cv.account.inv.entity.RetInHis;
import java.sql.ResultSet;
=======
import com.cv.inv.entity.RetInHis;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/dao/RetInDao.java
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface RetInDao {

    public RetInHis save(RetInHis retInHis);

    public RetInHis findById(String id);

    public List<RetInHis> search(String fromDate, String toDate, String cusId,
            String locId, String vouNo, String filterCode);

    public ResultSet searchM(String fromDate, String toDate, String cusId,
            String locId, String vouNo, String filterCode) throws Exception;
}
