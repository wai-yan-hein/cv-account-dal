/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.dao;

import com.cv.accountswing.entity.Menu;
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

<<<<<<< HEAD:src/main/java/com/cv/account/api/dao/MenuDao.java
    public List<Menu> searchM(String updatedDate);

=======
>>>>>>> b9f0fe79da6831b8a9010e055b7731427530312d:src/main/java/com/cv/accountswing/dao/MenuDao.java
    public List getParentChildMenu(String roleId, String menuType);

    public int delete(String id);

    public List getReports(String roleId);

    public List getReportList(String roleId, String partentCode);
}
