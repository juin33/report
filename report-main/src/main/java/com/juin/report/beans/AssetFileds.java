package com.juin.report.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author kejun
 * @date 2019/6/26 上午10:21
 */
public class AssetFileds implements Serializable{
    private String org_name;
    //年度
    private String year;
    //资产总额
    private BigDecimal total_assets;
    //流动资产
    private BigDecimal total_current_assets;
    //货币资金
    private BigDecimal money_funds;
    //应收账款
    private BigDecimal receivables;
    //其他应收款
    private BigDecimal other_receivables;
    //存货
    private BigDecimal inventory;
    //其他流动资产
    private BigDecimal other_current_assets;
    //非流动资产合计
    private BigDecimal total_no_current_assets;
    //持有至到期投资
    private BigDecimal hold_investment_due;
    //长期股权投资
    private BigDecimal long_term_equity_investment;
    //投资性房地产
    private BigDecimal investment_property;
    //固定资产
    private BigDecimal fixed_assets;

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BigDecimal getTotal_assets() {
        return total_assets;
    }

    public void setTotal_assets(BigDecimal total_assets) {
        this.total_assets = total_assets;
    }

    public BigDecimal getTotal_current_assets() {
        return total_current_assets;
    }

    public void setTotal_current_assets(BigDecimal total_current_assets) {
        this.total_current_assets = total_current_assets;
    }

    public BigDecimal getMoney_funds() {
        return money_funds;
    }

    public void setMoney_funds(BigDecimal money_funds) {
        this.money_funds = money_funds;
    }

    public BigDecimal getReceivables() {
        return receivables;
    }

    public void setReceivables(BigDecimal receivables) {
        this.receivables = receivables;
    }

    public BigDecimal getOther_receivables() {
        return other_receivables;
    }

    public void setOther_receivables(BigDecimal other_receivables) {
        this.other_receivables = other_receivables;
    }

    public BigDecimal getInventory() {
        return inventory;
    }

    public void setInventory(BigDecimal inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getOther_current_assets() {
        return other_current_assets;
    }

    public void setOther_current_assets(BigDecimal other_current_assets) {
        this.other_current_assets = other_current_assets;
    }

    public BigDecimal getTotal_no_current_assets() {
        return total_no_current_assets;
    }

    public void setTotal_no_current_assets(BigDecimal total_no_current_assets) {
        this.total_no_current_assets = total_no_current_assets;
    }

    public BigDecimal getHold_investment_due() {
        return hold_investment_due;
    }

    public void setHold_investment_due(BigDecimal hold_investment_due) {
        this.hold_investment_due = hold_investment_due;
    }

    public BigDecimal getLong_term_equity_investment() {
        return long_term_equity_investment;
    }

    public void setLong_term_equity_investment(BigDecimal long_term_equity_investment) {
        this.long_term_equity_investment = long_term_equity_investment;
    }

    public BigDecimal getInvestment_property() {
        return investment_property;
    }

    public void setInvestment_property(BigDecimal investment_property) {
        this.investment_property = investment_property;
    }

    public BigDecimal getFixed_assets() {
        return fixed_assets;
    }

    public void setFixed_assets(BigDecimal fixed_assets) {
        this.fixed_assets = fixed_assets;
    }
}
