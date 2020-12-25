/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.entity.temp;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(name="tmp_vou_code_filter_h2")
public class VouCodeFilterH2 implements Serializable{
      private VouCodeFilterKeyH2 key;

    public VouCodeFilterH2(){ /*key = new VouCodeFilterKey();*/ }
    
    public VouCodeFilterH2(VouCodeFilterKeyH2 key){
      this.key = key;
    }
    
    @EmbeddedId
    public VouCodeFilterKeyH2 getKey() {
        return key;
    }

    public void setKey(VouCodeFilterKeyH2 key) {
        this.key = key;
    }
}
