/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.service;

import com.cv.account.api.dao.ProjectCOAMappingDao;
import com.cv.account.api.entity.ProjectCOAMapping;
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
public class ProjectCOAMappingServiceImpl implements ProjectCOAMappingService{
    
    @Autowired
    private ProjectCOAMappingDao dao;
    
    @Override
    public ProjectCOAMapping save(ProjectCOAMapping pcm){
        return dao.save(pcm);
    }
    
    @Override
    public ProjectCOAMapping findById(Long id){
        return dao.findById(id);
    }
    
    @Override
    public List search(String projectId, String coaCode){
        return dao.search(projectId, coaCode);
    }
    
    @Override
    public int delete(String projectId, String id){
        return dao.delete(projectId, id);
    }
}
