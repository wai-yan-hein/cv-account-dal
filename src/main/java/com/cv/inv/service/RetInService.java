/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/service/RetInService.java
import com.cv.account.api.dummy.VouSearch;
import com.cv.account.inv.entity.RetInHisDetail;
import com.cv.account.inv.entity.RetInHis;
import java.sql.ResultSet;
=======
import com.cv.inv.entity.RetInHisDetail;
import com.cv.inv.entity.RetInHis;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/service/RetInService.java
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface RetInService {

    public void save(RetInHis retIn, List<RetInHisDetail> listRetIn, List<String> delList);

    public RetInHis saveM(RetInHis retIn);

    public void delete(String retInId);

    public List<RetInHis> search(String fromDate, String toDate, String cusId,
            String locId, String vouNo, String filterCode);

    public List<VouSearch> searchM(String fromDate, String toDate, String cusId,
            String locId, String vouNo, String filterCode) throws Exception;

    public RetInHis findById(String id);
}
