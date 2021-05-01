/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.UserDefault;
import com.cv.accountswing.entity.UserDefaultKey;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public interface UserSettingService {

    public UserDefault save(UserDefault du);

    public UserDefault findById(UserDefaultKey key);

    public List<UserDefault> search(String roleCode, String compCode, String key);

    public List<UserDefault> search(String user);

    public void delete(String roleCode, String compCode, String key);
}
