/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.inv.h2.service;

import com.cv.inv.h2.entity.MachineInfoH2;
import java.util.List;

/**
 *
 * @author lenovo
 */
public interface MachineInfoH2Service {

    public MachineInfoH2 save(MachineInfoH2 machineInfo) throws Exception;

    public int getMax(String machineName) throws Exception;

    public List<MachineInfoH2> findAll() throws Exception;

    public MachineInfoH2 findById(String id) throws Exception;

    public List<MachineInfoH2> search(String name, String ip);

    public List<MachineInfoH2> searchM(String name);

}
