/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.accountswing.entity.COALevel;
import com.cv.inv.h2.entity.ChartOfAccountH2;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface COAH2Dao {

    public ChartOfAccountH2 save(ChartOfAccountH2 coa);

    public ChartOfAccountH2 findById(String id);

    public List<ChartOfAccountH2> search(String code, String name, String compCode,
            String level, String parent, String userParent, String usrCoaCode);

    public int delete(String code, String compCode);

    public List<ChartOfAccountH2> getParent(String compCode);

    public List<COALevel> getParentChildCOA(String compCode);

    public List<ChartOfAccountH2> getCOALevel3Above(String compCode);

    public List<ChartOfAccountH2> getCOALevel2Above(String compCode);

    public List<ChartOfAccountH2> getAllChild(String parent, String compCode);

    public List<ChartOfAccountH2> getChild(String compCode, String parent);

    public List<ChartOfAccountH2> getCOAWithLevel(String compCode, String level);

    public List<ChartOfAccountH2> getCompanyCOA(String compCode);

    public List<ChartOfAccountH2> getCompanyCOA(String compCode, String deptId, String projectId);

    public List<ChartOfAccountH2> searchWhereIn(String strList, String compCode);

}
