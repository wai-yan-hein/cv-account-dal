/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/service/RetOutService.java
import com.cv.account.api.dummy.VouSearch;
import com.cv.account.inv.entity.RetOutHisDetail;
import com.cv.account.inv.entity.RetOutHis;
=======
import com.cv.inv.entity.RetOutHisDetail;
import com.cv.inv.entity.RetOutHis;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/service/RetOutService.java
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface RetOutService {

    public void save(RetOutHis retIn, List<RetOutHisDetail> listRetIn, List<String> delList);

    public void delete(String retInId);

    public List<RetOutHis> search(String fromDate, String toDate, String cusId,
            String locId, String vouNo, String filterCode);

    public List<VouSearch> searchM(String fromDate, String toDate,
            String cusId, String locId, String vouNo, String filterCode) throws Exception;

    public RetOutHis findById(String id);
}
