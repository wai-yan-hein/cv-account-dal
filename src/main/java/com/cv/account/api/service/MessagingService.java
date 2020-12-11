/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.account.api.service;

import com.cv.account.api.entity.Gl;
import com.cv.account.api.entity.Trader;


/**
 *
 * @author winswe
 */
public interface MessagingService {
    public void sendPaymentToInv(Gl gl, Trader trader);
    public void sendDeletePaymentToInv(final long glId);
    public void sendPaymentToInvGV(final Gl gl, final Trader trader);
}
