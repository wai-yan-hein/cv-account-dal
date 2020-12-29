/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.MenuH2;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface MenuH2Dao {

    public MenuH2 saveMenu(MenuH2 menu);

    public MenuH2 findById(String id);

    public List<MenuH2> search(String name, String nameMM, String parentId, String coaCode);

    public List<MenuH2> getParentChildMenu();

    public List<MenuH2> searchM(String updatedDate);

    public List getParentChildMenu(String roleId, String menuType);

    public int delete(String id);

    public List getReports(String roleId);

    public List getReportList(String roleId, String partentCode);
}
