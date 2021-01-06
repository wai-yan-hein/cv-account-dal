/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.dao.AccountDao;
import com.cv.accountswing.entity.AppUser;
import java.util.List;
import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao dao;

    @Autowired
    private SeqTableService seqService;

    @Override
    public AppUser saveAccount(AppUser au) {
        if (au.getAppUserCode() == null || au.getAppUserCode().isEmpty()) {
            Integer macId = au.getMacId();
            String compCode = au.getCompCode();
            au.setAppUserCode(getAppUserCode(macId, "AppUser", "-", compCode));
        }
        return dao.saveAccount(au);
    }

    @Override
    public AppUser findUserById(Integer id) {
        AppUser au = dao.findUserById(id);
        return au;
    }

    @Override
    public AppUser findUserByShort(String userShort) {
        AppUser au = dao.findUserByShort(userShort);
        return au;
    }

    @Override
    public AppUser findUserByEmail(String email) {
        AppUser au = dao.findUserByEmail(email);
        return au;
    }

    @Override
    public List<AppUser> search(String id, String userShort, String email, String owner) {
        List<AppUser> listAU = dao.search(id, userShort, email, owner);
        return listAU;
    }

    @Override
    public AppUser login(String user, String password) throws AuthenticationException {
        AppUser au = dao.login(user, password);
        return au;
    }

    private String getAppUserCode(Integer macId, String option, String period, String compCode) {

        int seqNo = seqService.getSequence(macId, option, period, compCode);

        String tmpCatCode = String.format("%0" + 2 + "d", macId) + "-" + String.format("%0" + 3 + "d", seqNo);
        return tmpCatCode;
    }
}
