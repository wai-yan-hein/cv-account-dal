/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.dao;

import com.cv.account.api.entity.ProjectTraderMapping;
import java.util.List;

/**
 *
 * @author winswe
 */
public interface ProjectTraderMappingDao {
    public ProjectTraderMapping save(ProjectTraderMapping ptm);
    public ProjectTraderMapping findById(Long id);
    public List search(String projectId, String traderId);
    public int delete(String projectId, String id);
}
