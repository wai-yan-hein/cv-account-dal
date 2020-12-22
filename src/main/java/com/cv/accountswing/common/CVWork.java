/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.common;


import com.cv.account.api.util.Util1;
import java.io.File;
import java.sql.Connection;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.hibernate.jdbc.Work;

/**
 *
 * @author SAI
 */
public class CVWork implements Work {

    private static final Logger logger = Logger.getLogger(CVWork.class);
    private  String strSql;
    private boolean status = false;
    private Connection con;
    String filePath = Util1.getAppWorkFolder() + File.separator + "zipFile" + File.separator;
    String fileName = UUID.randomUUID().toString();
    String zipFileName = "";

    public CVWork(String strSql) {
        this.strSql = strSql;
    }

    @Override
    public void execute(Connection con) {
        this.con = con;
        status = true;

    }

    public boolean isStatus() {
        return status;
    }

    public String getZipFileName() {
        return zipFileName;
    }

    public Connection getCon() {
        return con;
    }

}
