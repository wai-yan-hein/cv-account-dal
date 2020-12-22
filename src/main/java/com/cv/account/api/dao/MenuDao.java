/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.Menu;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface MenuDao {

    public Menu saveMenu(Menu menu);

    public Menu findById(String id);

    public List<Menu> search(String name, String nameMM, String parentId, String coaCode);

    public List<Menu> getParentChildMenu();

    public List<Menu> searchM(String updatedDate);

    public List getParentChildMenu(String roleId, String menuType);

    public int delete(String id);
}
