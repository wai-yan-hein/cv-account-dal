/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.mq.service;

import com.cv.accountswing.common.ReloadData;
import com.google.gson.JsonElement;

/**
 *
 * @author SAI
 */
public interface ReceiverService {
     public void doReceiverOperation(ReloadData rlData, String operationId, String entityType, Object data);

    public void doReceiverFileOperation(ReloadData rlData, String operationId, String entityType, JsonElement data);
    
}
