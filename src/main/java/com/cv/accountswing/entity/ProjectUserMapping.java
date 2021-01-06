/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author winswe
 */
@Entity
@Table(name = "prj_usr_mapping")
public class ProjectUserMapping implements java.io.Serializable {

    private Long id;
    private String projecCode;
    private String userCode;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "prj_id")
    public String getProjecCode() {
        return projecCode;
    }

    public void setProjecCode(String projecCode) {
        this.projecCode = projecCode;
    }

    @Column(name = "user_id")

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

}
