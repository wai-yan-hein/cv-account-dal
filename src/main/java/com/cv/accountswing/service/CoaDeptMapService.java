/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.service;

import com.cv.accountswing.entity.CoaDeptMap;
import com.cv.accountswing.entity.CoaDeptMapKey;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface CoaDeptMapService {
    public CoaDeptMap save(String compCode, String coaCode, String dept);
    public CoaDeptMap findById(CoaDeptMapKey id);
    public List<CoaDeptMap> search(String compCode, String coaCode, String dept);
    public List searchMap(String compCode, String coaCode, String dept);
    public int delete(String compCode, String coaCode, String dept);
}
