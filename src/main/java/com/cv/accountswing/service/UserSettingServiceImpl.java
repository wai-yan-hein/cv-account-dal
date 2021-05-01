/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.DefaultUserDao;
import com.cv.accountswing.entity.UserDefault;
import com.cv.accountswing.entity.UserDefaultKey;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Lenovo
 */
@Service
@Transactional
public class UserSettingServiceImpl implements UserSettingService {

    @Autowired
    private DefaultUserDao dao;

    @Override
    public UserDefault save(UserDefault du) {
        return dao.save(du);
    }

    @Override
    public List<UserDefault> search(String user) {
        return dao.search(user);

    }

    @Override
    public UserDefault findById(UserDefaultKey key) {
        return dao.findById(key);
    }

    @Override
    public List<UserDefault> search(String userCode, String compCode, String key) {
        return dao.search(userCode, compCode, key);
    }

    @Override
    public void delete(String roleCode, String compCode, String key) {
         dao.delete(roleCode, compCode, key);
    }

}
