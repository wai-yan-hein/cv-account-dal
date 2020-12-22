/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.service;

<<<<<<< HEAD:src/main/java/com/cv/account/inv/service/VouIdServiceImpl.java
import com.cv.account.inv.dao.VouIdDao;
import com.cv.account.inv.entity.CompoundKey;
import com.cv.account.inv.entity.VouId;
import java.util.List;
=======
import com.cv.inv.dao.VouIdDao;
import com.cv.inv.entity.CompoundKey;
import com.cv.inv.entity.VouId;
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/inv/service/VouIdServiceImpl.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lenovo
 */
@Service
@Transactional
public class VouIdServiceImpl implements VouIdService {

    @Autowired
    private VouIdDao voudIdDao;

    @Override
    public VouId save(VouId vouId) {
        return voudIdDao.save(vouId);
    }

    @Override
    public Object getMax(String machineName, String vouType, String vouPeriod) throws Exception {
        return voudIdDao.getMax(machineName, vouType, vouPeriod);
    }

    @Override
    public Object find(CompoundKey key) {
        return voudIdDao.find(key);
        }
    
    @Override
    public List<VouId> search(String machineName, String vouType, String period){
        return voudIdDao.search(machineName, vouType, period);
    }

}
