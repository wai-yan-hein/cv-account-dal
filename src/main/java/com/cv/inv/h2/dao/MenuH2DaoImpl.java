/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.dao.*;
import com.cv.accountswing.entity.view.VRoleMenu;
import com.cv.inv.h2.entity.MenuH2;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author winswe
 */
@Repository
public class MenuH2DaoImpl extends AbstractDao<String, MenuH2> implements MenuH2Dao {

    @Override
    public MenuH2 saveMenu(MenuH2 menu) {
        persist(menu);
        return menu;
    }

    @Override
    public MenuH2 findById(String id) {
        MenuH2 menu = getByKey(id);
        return menu;
    }

    @Override
    public List<MenuH2> search(String name, String nameMM, String parentId, String coaCode) {
        String strSql = "select o from MenuH2 o ";
        String strFilter = "";

        if (!name.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.text like '" + name + "%'";
            } else {
                strFilter = strFilter + " and o.text like '" + name + "%'";
            }
        }

        if (!nameMM.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.menuNameMM like '" + nameMM + "%'";
            } else {
                strFilter = strFilter + " and o.menuNameMM like '" + nameMM + "%'";
            }
        }

        if (!parentId.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.parent = " + parentId;
            } else {
                strFilter = strFilter + " and o.parent = " + parentId;
            }
        }
        if (!coaCode.equals("-")) {
            if (strFilter.isEmpty()) {
                strFilter = "o.soureAccCode = " + coaCode;
            } else {
                strFilter = strFilter + " and o.soureAccCode = " + coaCode;
            }
        }

        if (!strFilter.isEmpty()) {
            strSql = strSql + " where " + strFilter;
        }

        List<MenuH2> listMenu = findHSQL(strSql);
        return listMenu;
    }

    @Override
    public int delete(String id) {
        String strSql = "delete from Menu o where o.id = " + id;
        int cnt = execUpdateOrDelete(strSql);
        return cnt;
    }

    @Override
    public List<MenuH2> getParentChildMenu() {
        String strSql = "select o from Menu o where o.parent = '1'";
        List<MenuH2> listRootMenu = findHSQL(strSql);
        listRootMenu.forEach(parent -> {
            getChild(parent);
        });

        return listRootMenu;
    }

    private MenuH2 getChild(MenuH2 parent) {
        String strSql = "select o from Menu o where o.parent = '" + parent.getId() + "'";
        List<MenuH2> listChild = findHSQL(strSql);

        if (listChild != null) {
            if (listChild.size() > 0) {
                parent.setChild(listChild);
                listChild.forEach(parentMenu -> {
                    getChild(parentMenu);
                });
            }
        }

        return parent;
    }

    @Override
    public List getParentChildMenu(String roleId, String menuType) {
        /*String strSql = "select o from VRoleMenu o where o.key.roleId = " + roleId
                + " and o.parent = '1' order by o.orderBy";*/
        String strSql ="select m from MenuH2 m where m.parent = '1' and "
                +" m.id in(select p.key.menuId from PrivilegeH2 p where p.isAllow=true and p.key.roleId = "+ roleId +") order by m.orderBy";
        List listRootMenu = findHSQL(strSql);
        for (int i = 0; i < listRootMenu.size(); i++) {
            MenuH2 parent = (MenuH2) listRootMenu.get(i);
            getChild(parent, roleId, "-");
        }

        return listRootMenu;
    }

    private void getChild(MenuH2 parent, String roleId, String menuType) {
        /* String strSql = "select o from VRoleMenu o where o.parent = '" + parent.getKey().getMenuId()
                + "' and o.key.roleId = " + roleId + "";*/
        String strSql = "select m from MenuH2 m where m.parent=" + parent.getId()
                + " and m.id in(select p.key.menuId from PrivilegeH2 p where p.isAllow=true and p.key.roleId='" + roleId + "') order by m.orderBy";
        if (!menuType.equals("-")) {
            strSql = strSql + " and m.menuType = '" + menuType + "'";
        }
        List listChild = findHSQL(strSql);

        if (listChild != null) {
            if (listChild.size() > 0) {
                parent.setChild(listChild);
                for (int i = 0; i < listChild.size(); i++) {
                    MenuH2 child = (MenuH2) listChild.get(i);
                    getChild(child, roleId, menuType);
                }
            }
        }
    }

    @Override
    public List<MenuH2> searchM(String updatedDate) {
        String strSql = "select o from MenuH2 o where o.updatedDate > '" + updatedDate + "'";
        List<MenuH2> listMU = findHSQL(strSql);
        return listMU;

    }

    @Override
    public List getReports(String roleId) {
        String hsql = "select o from VRoleMenu o where o.key.roleId = " + roleId + " and o.menuType = 'Reports' and o.isAllow = true";
        return findHSQL(hsql);
    }

    @Override
    public List getReportList(String roleId, String parentCode) {
        String hsql = "select o from VRoleMenu o where o.key.roleId = " + roleId + " and o.isAllow = true"
                + "  and o.parent ='" + parentCode + "'";
        return findHSQL(hsql);
    }
}
