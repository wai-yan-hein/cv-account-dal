/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.accountswing.entity.Menu;
import com.cv.inv.h2.dao.MenuH2Dao;
import com.cv.inv.h2.entity.MenuH2;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class MenuH2ServiceImpl implements MenuH2Service {

    @Autowired
    private MenuH2Dao dao;

    @Override
    public MenuH2 saveMenu(MenuH2 menu) {
        return dao.saveMenu(menu);
    }

    @Override
    public MenuH2 findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<MenuH2> search(String name, String nameMM, String parentId, String coaCode) {
        return dao.search(name, nameMM, parentId, coaCode);
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }

    @Override
    public List<MenuH2> getParentChildMenu() {
        return dao.getParentChildMenu();
    }

    @Override
    public List getParentChildMenu(String roleId, String menuType) {
        return dao.getParentChildMenu(roleId, menuType);
    }

    @Override
    public List getReports(String roleId) {
        return dao.getReports(roleId);
    }

    @Override
    public List getReportList(String roleId, String partentCode) {
        return dao.getReportList(roleId, partentCode);
    }

    @Override
    public List<MenuH2> searchM(String updatedDate) {
        return dao.searchM(updatedDate);
    }
}
