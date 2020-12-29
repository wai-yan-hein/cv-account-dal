/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.dao.AccountH2Dao;
import com.cv.inv.h2.entity.AppUserH2;
import java.util.List;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SAI
 */
@Service
@Transactional
public class AccountH2ServiceImpl implements AccountH2Service {

    @Autowired
    private AccountH2Dao dao;

    @Override
    public AppUserH2 saveAccount(AppUserH2 au) {
        return dao.saveAccount(au);
    }

    @Override
    public AppUserH2 findUserByShort(String userShort) {
        return dao.findUserByShort(userShort);
    }

    @Override
    public List<AppUserH2> search(String id, String userShort, String email, String owner) {
        return dao.search(id, userShort, email, owner);
    }

    @Override
    public AppUserH2 login(String user, String password) throws AuthenticationException {
        return dao.login(user, password);
    }

    @Override
    public int delete(String userId) {
        return dao.delete(userId);
    }

    @Override
    public AppUserH2 finfById(String id) {
        return dao.finfById(id);
    }

}
