/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.inv.service;


import com.cv.account.inv.entity.FileSize;
import java.util.List;

/**
 *
 * @author Sai
 */
public interface FileSizeService {

    public FileSize save(FileSize fs);

    public FileSize findById(Integer id);

    public List<FileSize> search(String queue);

    public int delete(String id);
}
