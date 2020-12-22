/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.Menu;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface MenuService {

    public Menu saveMenu(Menu menu);

    public Menu findById(String id);

    public List<Menu> search(String name, String nameMM, String parentId, String coaCode);

    public int delete(String id);

    public List<Menu> getParentChildMenu();

    public List getParentChildMenu(String roleId, String menuType);
<<<<<<< HEAD:src/main/java/com/cv/account/api/service/MenuService.java
    
     public List<Menu> searchM(String updatedDate);
=======

    public List getReports(String roleId);

    public List getReportList(String roleId, String partentCode);
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/accountswing/service/MenuService.java
}
