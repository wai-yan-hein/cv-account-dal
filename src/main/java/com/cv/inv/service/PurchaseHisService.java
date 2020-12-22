/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/service/PurchaseHisService.java
import com.cv.account.api.dummy.VouSearch;
import com.cv.account.inv.entity.PurHis;
=======
import com.cv.inv.entity.PurHis;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/service/PurchaseHisService.java
import java.util.List;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
public interface PurchaseHisService {

    public PurHis save(PurHis purHis) throws Exception;

    public List<PurHis> search(String fromDate, String toDate, String cusId, String vouStatusId, String remark);

    public List<VouSearch> searchM(String fromDate, String toDate,
            String cusId, String vouStatusId, String remark) throws Exception;

    public PurHis findById(String id);

    public int delete(String vouNo);
}
