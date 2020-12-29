/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.dao;

import com.cv.inv.h2.entity.MachineInfoH2;
import java.util.List;

/**
 *
 * @author SAI
 */
public interface MachineInfoH2Dao {

    public MachineInfoH2 save(MachineInfoH2 machineInfo) throws Exception;

    public MachineInfoH2 findById(String id) throws Exception;

    public List<MachineInfoH2> search(String name, String ip);

    public int delete(String id);

}
