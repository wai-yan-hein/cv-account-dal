/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.AppUserH2;
import java.util.List;
import javax.naming.AuthenticationException;

/**
 *
 * @author SAI
 */
public interface AccountH2Service {

    public AppUserH2 saveAccount(AppUserH2 au);

    public AppUserH2 findUserByShort(String userShort);

    public List<AppUserH2> search(String id, String userShort, String email, String owner);

    public AppUserH2 login(String user, String password) throws AuthenticationException;

    public int delete(String userId);

    public AppUserH2 finfById(String id);

}
