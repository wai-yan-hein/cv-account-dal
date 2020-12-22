/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/service/DamageHisService.java
import com.cv.account.api.dummy.DamageVouSearch;
import com.cv.account.api.dummy.VouSearch;
import com.cv.account.inv.entity.DamageDetailHis;
import com.cv.account.inv.entity.DamageHis;
=======
import com.cv.accountswing.entity.Gl;
import com.cv.inv.entity.DamageDetailHis;
import com.cv.inv.entity.DamageHis;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/service/DamageHisService.java
import java.util.List;

/**
 *
 * @author Mg Kyaw Thura Aung
 */
public interface DamageHisService {

    public DamageHis save(DamageHis sdh);

    public List<DamageHis> search(String from, String to, String location,
            String remark, String vouNo);

    public void save(DamageHis sdh, List<DamageDetailHis> listDamageDetail, 
            String vouStatus, List<String> delList);

    public DamageHis findById(String id);
    
    public List<DamageVouSearch> searchM(String from, String to, String location,
            String remark, String vouNo) throws Exception;

    public int delete(String vouNo);
}
