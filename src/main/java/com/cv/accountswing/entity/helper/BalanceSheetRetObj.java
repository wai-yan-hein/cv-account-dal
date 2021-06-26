/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cv.accountswing.entity.helper;

/**
 *
 * @author winswe
 */
public class BalanceSheetRetObj {

    private double fixedAss;
    private double currentAss;
    private double capital;
    private double liabilitie;
    private double profit;

    public BalanceSheetRetObj() {
        fixedAss = 0.0;
        currentAss = 0.0;
        capital = 0.0;
        liabilitie = 0.0;
        profit = 0.0;
    }

    public double getFixedAss() {
        return fixedAss;
    }

    public void setFixedAss(double fixedAss) {
        this.fixedAss += fixedAss;
    }

    public double getCurrentAss() {
        return currentAss;
    }

    public void setCurrentAss(double currentAss) {
        this.currentAss += currentAss;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital += capital;
    }

    public double getLiabilitie() {
        return liabilitie;
    }

    public void setLiabilitie(double liabilitie) {
        this.liabilitie += liabilitie;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit += profit;
    }

    public double getTotalAss() {
        return fixedAss + currentAss;
    }

    public double getTotalCapital() {
        return liabilitie + capital;
    }

}
