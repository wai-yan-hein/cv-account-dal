/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.inv.service;



import com.cv.account.inv.dao.FileSizeDao;
import com.cv.account.inv.entity.FileSize;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author winswe
 */
@Service
@Transactional
public class FileSizeServiceImpl implements FileSizeService {

    @Autowired
    private FileSizeDao dao;

    @Override
    public FileSize save(FileSize fs) {
        return dao.save(fs);
    }

    @Override
    public FileSize findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<FileSize> search(String queue) {
        return dao.search(queue);
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }
}
