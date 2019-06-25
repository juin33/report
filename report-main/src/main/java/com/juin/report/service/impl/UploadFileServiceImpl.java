package com.juin.report.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.juin.report.beans.AssetContants;
import com.juin.report.dao.domain.Assents;
import com.juin.report.dao.domain.Flow;
import com.juin.report.dao.domain.Liabilities;
import com.juin.report.dao.domain.Profit;
import com.juin.report.dao.mapper.AssentsMapper;
import com.juin.report.dao.mapper.FlowMapper;
import com.juin.report.dao.mapper.LiabilitiesMapper;
import com.juin.report.dao.mapper.ProfitMapper;
import com.juin.report.service.UploadFileService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author kejun
 * @date 2019/6/24 下午4:05
 */
@Service
public class UploadFileServiceImpl implements UploadFileService{
    private static final Logger logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    @Autowired
    private AssentsMapper assentsMapper;
    @Autowired
    private LiabilitiesMapper liabilitiesMapper;
    @Autowired
    private ProfitMapper profitMapper;
    @Autowired
    private FlowMapper flowMapper;

    @Override
    public void importExcel(String filePath) throws ParseException, IOException {
        HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile(filePath)));
//        doSheet1(book);
//        doSheet2(book);
        doSheet3(book);
    }

    private void doSheet3(HSSFWorkbook book){
        HSSFSheet sheet = book.getSheetAt(2);
        if(null == sheet) return;
        Flow flow = new Flow();
        HashMap<String,Double> profitMap = new HashMap<>();

        for (int i = 8; i < sheet.getLastRowNum()+1; i++) {
            HSSFRow row = sheet.getRow(i);
            //流量
            if (null != row && null != row.getCell(0)) {
                String key = row.getCell(0).getStringCellValue().replaceAll("\\s*", "");
                try {
                    if (StringUtils.isNotBlank(key))
                        profitMap.put(key, row.getCell(3).getNumericCellValue());
                } catch (Exception e) {
                    logger.error("error:{}", e);
                }
            }

            if (null != row && null != row.getCell(9)) {
                String key = row.getCell(9).getStringCellValue().replaceAll("\\s*", "");
                try {
                    if (StringUtils.isNotBlank(key))
                        profitMap.put(key, row.getCell(12).getNumericCellValue());
                } catch (Exception e) {
                    logger.error("error:{}", e);
                }
            }
        }
        String month = sheet.getRow(3).getCell(0).getStringCellValue().replace("年度","");
        String orgName = sheet.getRow(5).getCell(0).getStringCellValue();
        flow = saveFlowResult(profitMap,flow);
        flow.setOrg_name(orgName);
        flow.setMonth(month);
        flowMapper.insertSelective(flow);
        logger.info("导入流量表成功");
    }

    private void doSheet2(HSSFWorkbook book) throws ParseException {
        HSSFSheet sheet = book.getSheetAt(1);
        if(null == sheet) return;
        Profit profit = new Profit();
        HashMap<String,Double> profitMap = new HashMap<>();

        for (int i = 5; i < sheet.getLastRowNum()+1; i++) {
            HSSFRow row = sheet.getRow(i);
            //资产
            if (null != row && null != row.getCell(0)) {
                String key = row.getCell(0).getStringCellValue().replaceAll("\\s*", "");
                try {
                    if (StringUtils.isNotBlank(key))
                        profitMap.put(key, row.getCell(3).getNumericCellValue());
                } catch (Exception e) {
                    logger.error("error:{}", e);
                }
            }
        }
        String month = sheet.getRow(3).getCell(0).getStringCellValue().replace("年度","");
        String orgName = sheet.getRow(5).getCell(0).getStringCellValue();
        profit = saveProfitResult(profitMap,profit);
        profit.setOrg_name(orgName);
        profit.setMonth(month);
        profitMapper.insertSelective(profit);
        logger.info("导入利润表成功");
    }

    private void doSheet1(HSSFWorkbook book) throws ParseException {
        HSSFSheet sheet = book.getSheetAt(0);
        if(null == sheet) return;
        Assents preAssent = new Assents();
        Assents aftAssent = new Assents();
        Liabilities preLiabs = new Liabilities();
        Liabilities aftLiabs = new Liabilities();
        logger.info("rowNum:{}",sheet.getLastRowNum());
        HashMap<String,Double> preAssentMap = new HashMap<>();
        HashMap<String,Double> aftAssentMap = new HashMap<>();
        HashMap<String,Double> preLiabilitiesMap = new HashMap<>();
        HashMap<String,Double> aftLiabilitiesMap = new HashMap<>();

        for (int i = 6; i < sheet.getLastRowNum()+1; i++) {
            HSSFRow row = sheet.getRow(i);
            //资产
            if(null != row && null != row.getCell(0)){
                String key = row.getCell(0).getStringCellValue().replaceAll("\\s*","");
                try {
                    if(StringUtils.isNotBlank(key))
                        preAssentMap.put(key,row.getCell(3).getNumericCellValue());
                    if(StringUtils.isNotBlank(key))
                        aftAssentMap.put(key,row.getCell(2).getNumericCellValue());
                } catch (Exception e) {
                    logger.error("error:{}",e);
                }
            }
            //负债
            if(null != row && null != row.getCell(4)){
                String key = row.getCell(4).getStringCellValue().replaceAll("\\s*","");
                try {
                    if(StringUtils.isNotBlank(key))
                        preLiabilitiesMap.put(key,row.getCell(7).getNumericCellValue());
                    if(StringUtils.isNotBlank(key))
                        aftLiabilitiesMap.put(key,row.getCell(6).getNumericCellValue());
                } catch (Exception e) {
                    logger.error("error:{}",e);
                }
            }
        }
        Date month = changeDate(sheet.getRow(1).getCell(0).getStringCellValue());
        String orgName = sheet.getRow(3).getCell(0).getStringCellValue();
        //资产期末数
        aftAssent = saveAssetResult(aftAssentMap,aftAssent);
        aftAssent.setStatus((byte)1);
        aftAssent.setOrg_name(orgName);
        aftAssent.setMonth(month);
        //资产期初数
        preAssent = saveAssetResult(preAssentMap,preAssent);
        preAssent.setStatus((byte)0);
        preAssent.setOrg_name(orgName);
        preAssent.setMonth(month);
        assentsMapper.insertSelective(preAssent);
        assentsMapper.insertSelective(aftAssent);
        logger.info("导入资产信息成功");

        //负债期末数
        aftLiabs = saveLiabilitiesResult(aftLiabilitiesMap,aftLiabs);
        aftLiabs.setStatus((byte)1);
        aftLiabs.setOrg_name(orgName);
        aftLiabs.setMonth(month);
        //负债期初数
        preLiabs = saveLiabilitiesResult(preLiabilitiesMap,preLiabs);
        preLiabs.setStatus((byte)0);
        preLiabs.setOrg_name(orgName);
        preLiabs.setMonth(month);
        liabilitiesMapper.insertSelective(preLiabs);
        liabilitiesMapper.insertSelective(aftLiabs);
        logger.info("导入负债信息成功");
    }

    private BigDecimal changeNum(Double num){
        if(null != num)
            return new BigDecimal(num);
        return BigDecimal.ZERO;
    }

    private Date changeDate(String dateStr) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.parse(dateStr);
    }

    private Assents saveAssetResult(HashMap<String,Double> map,Assents assents){
        assents.setMoney_funds(changeNum(map.get(AssetContants.AC_01.getMsg())));
        assents.setTrading_financial_assets(changeNum(map.get(AssetContants.AC_02.getMsg())));
        assents.setNotes_receivable(changeNum(map.get(AssetContants.AC_03.getMsg())));
        assents.setReceivables(changeNum(map.get(AssetContants.AC_04.getMsg())));
        assents.setPrepayment(changeNum(map.get(AssetContants.AC_05.getMsg())));
        assents.setInterest_receivable(changeNum(map.get(AssetContants.AC_06.getMsg())));
        assents.setDividends_receivable(changeNum(map.get(AssetContants.AC_07.getMsg())));
        assents.setOther_receivables(changeNum(map.get(AssetContants.AC_08.getMsg())));
        assents.setInventory(changeNum(map.get(AssetContants.AC_09.getMsg())));
        assents.setNon_current_assets_12m(changeNum(map.get(AssetContants.AC_10.getMsg())));
        assents.setOther_current_assets(changeNum(map.get(AssetContants.AC_11.getMsg())));
        assents.setTotal_current_assets(changeNum(map.get(AssetContants.AC_12.getMsg())));
        assents.setFinancial_assets_available_for_sale(changeNum(map.get(AssetContants.AC_13.getMsg())));
        assents.setHold_investment_due(changeNum(map.get(AssetContants.AC_14.getMsg())));
        assents.setLong_term_equity_investment(changeNum(map.get(AssetContants.AC_15.getMsg())));
        assents.setLong_term_receivables(changeNum(map.get(AssetContants.AC_16.getMsg())));
        assents.setInvestment_property(changeNum(map.get(AssetContants.AC_17.getMsg())));
        assents.setFixed_assets(changeNum(map.get(AssetContants.AC_18.getMsg())));
        assents.setConstruction_in_progress(changeNum(map.get(AssetContants.AC_19.getMsg())));
        assents.setEngineering_material(changeNum(map.get(AssetContants.AC_20.getMsg())));
        assents.setDisposal_of_fixed_assets(changeNum(map.get(AssetContants.AC_21.getMsg())));
        assents.setProductive_biological_asset(changeNum(map.get(AssetContants.AC_22.getMsg())));
        assents.setOil_and_gas_assets(changeNum(map.get(AssetContants.AC_23.getMsg())));
        assents.setIntangible_assets(changeNum(map.get(AssetContants.AC_24.getMsg())));
        assents.setDevelopment_expenditure(changeNum(map.get(AssetContants.AC_25.getMsg())));
        assents.setGoodwill(changeNum(map.get(AssetContants.AC_26.getMsg())));
        assents.setLong_term_unamortized_expenses(changeNum(map.get(AssetContants.AC_27.getMsg())));
        assents.setDeferred_tax_assets(changeNum(map.get(AssetContants.AC_28.getMsg())));
        assents.setOther_no_current_assets(changeNum(map.get(AssetContants.AC_29.getMsg())));
        assents.setTotal_no_current_assets(changeNum(map.get(AssetContants.AC_30.getMsg())));
        assents.setTotal_assets(changeNum(map.get(AssetContants.AC_31.getMsg())));
        logger.info("assent:{}", JSONObject.toJSONString(assents));
        return assents;
    }

    private Liabilities saveLiabilitiesResult(HashMap<String,Double> map,Liabilities liabilities){
        liabilities.setShort_term_borrowing(changeNum(map.get(AssetContants.BC_01.getMsg())));
        liabilities.setTrading_financial_liabilities(changeNum(map.get(AssetContants.BC_02.getMsg())));
        liabilities.setNotes_payable(changeNum(map.get(AssetContants.BC_03.getMsg())));
        liabilities.setAccounts_payable(changeNum(map.get(AssetContants.BC_04.getMsg())));
        liabilities.setDeposit_received(changeNum(map.get(AssetContants.BC_05.getMsg())));
        liabilities.setEmployee_pay_payable(changeNum(map.get(AssetContants.BC_06.getMsg())));
        liabilities.setTax_payable(changeNum(map.get(AssetContants.BC_07.getMsg())));
        liabilities.setAccrual_interest_payable(changeNum(map.get(AssetContants.BC_08.getMsg())));
        liabilities.setDividends_payable(changeNum(map.get(AssetContants.BC_09.getMsg())));
        liabilities.setOther_payables(changeNum(map.get(AssetContants.BC_10.getMsg())));
        liabilities.setNon_current_liabilities_due_12m(changeNum(map.get(AssetContants.BC_11.getMsg())));
        liabilities.setOther_current_liabilities(changeNum(map.get(AssetContants.BC_12.getMsg())));
        liabilities.setTotal_current_liabilities(changeNum(map.get(AssetContants.BC_13.getMsg())));
        liabilities.setLong_term_borrowing(changeNum(map.get(AssetContants.BC_14.getMsg())));
        liabilities.setBonds_payable(changeNum(map.get(AssetContants.BC_15.getMsg())));
        liabilities.setLong_term_payable(changeNum(map.get(AssetContants.BC_16.getMsg())));
        liabilities.setAccount_payable_special_funds(changeNum(map.get(AssetContants.BC_17.getMsg())));
        liabilities.setAnticipation_liabilities(changeNum(map.get(AssetContants.BC_18.getMsg())));
        liabilities.setDeferred_income_tax_liabilities(changeNum(map.get(AssetContants.BC_19.getMsg())));
        liabilities.setOther_non_current_liabilities(changeNum(map.get(AssetContants.BC_20.getMsg())));
        liabilities.setTotal_non_current_liabilities(changeNum(map.get(AssetContants.BC_21.getMsg())));
        liabilities.setTotal_liabilities(changeNum(map.get(AssetContants.BC_22.getMsg())));
        liabilities.setPaicl_up_capital(changeNum(map.get(AssetContants.BC_23.getMsg())));
        liabilities.setCapital_reserve(changeNum(map.get(AssetContants.BC_24.getMsg())));
        liabilities.setTreasury_stock(changeNum(map.get(AssetContants.BC_25.getMsg())));
        liabilities.setEarned_surplus(changeNum(map.get(AssetContants.BC_26.getMsg())));
        liabilities.setUndistributed_profit(changeNum(map.get(AssetContants.BC_27.getMsg())));
        liabilities.setEquity_attributable_parent_company(changeNum(map.get(AssetContants.BC_28.getMsg())));
        liabilities.setMinority_equity(changeNum(map.get(AssetContants.BC_29.getMsg())));
        liabilities.setTotal_shareholders_equity(changeNum(map.get(AssetContants.BC_30.getMsg())));
        liabilities.setTotal_liabilities_shareholders_equity(changeNum(map.get(AssetContants.BC_31.getMsg())));
        logger.info("liabilities:{}",JSONObject.toJSONString(liabilities));
        return liabilities;
    }

    private Profit saveProfitResult(HashMap<String,Double> map,Profit profit){
        profit.setOperating_income(changeNum(map.get(AssetContants.PR_01.getMsg())));
        profit.setOperating_costs(changeNum(map.get(AssetContants.PR_02.getMsg())));
        profit.setBusiness_tax_and_surcharges(changeNum(map.get(AssetContants.PR_03.getMsg())));
        profit.setSelling_expenses(changeNum(map.get(AssetContants.PR_04.getMsg())));
        profit.setManage_expenses(changeNum(map.get(AssetContants.PR_05.getMsg())));
        profit.setFinancial_expense(changeNum(map.get(AssetContants.PR_06.getMsg())));
        profit.setAssets_impairment_loss(changeNum(map.get(AssetContants.PR_07.getMsg())));
        profit.setChanges_in_fair_net_income(changeNum(map.get(AssetContants.PR_08.getMsg())));
        profit.setInvestment_income(changeNum(map.get(AssetContants.PR_09.getMsg())));
        profit.setInvestment_joint_ventures_income(changeNum(map.get(AssetContants.PR_10.getMsg())));
        profit.setOperating_profit(changeNum(map.get(AssetContants.PR_11.getMsg())));
        profit.setOperating_outer_income(changeNum(map.get(AssetContants.PR_12.getMsg())));
        profit.setNo_current_assent_loss(changeNum(map.get(AssetContants.PR_13.getMsg())));
        profit.setTotal_profit(changeNum(map.get(AssetContants.PR_14.getMsg())));
        profit.setTax_cost(changeNum(map.get(AssetContants.PR_15.getMsg())));
        profit.setRetained_profits(changeNum(map.get(AssetContants.PR_16.getMsg())));
        profit.setRetained_profits_parent_company(changeNum(map.get(AssetContants.PR_17.getMsg())));
        profit.setMinority_interest_income(changeNum(map.get(AssetContants.PR_18.getMsg())));
        profit.setEarnings_per_share(changeNum(map.get(AssetContants.PR_19.getMsg())));
        profit.setEarnings_per_share_basic(changeNum(map.get(AssetContants.PR_20.getMsg())));
        profit.setEarnings_per_share_attenuation(changeNum(map.get(AssetContants.PR_21.getMsg())));
        return profit;
    }

    private Flow saveFlowResult(HashMap<String,Double> map,Flow flow){
        flow.setCash_flow_from_operating_activities(changeNum(map.get(AssetContants.FW_01.getMsg())));
        flow.setSelling_goods_and_providing_services_cash(changeNum(map.get(AssetContants.FW_02.getMsg())));
        flow.setRefun_of_tax_levies(changeNum(map.get(AssetContants.FW_03.getMsg())));
        flow.setOther_cash_related_operating_activities(changeNum(map.get(AssetContants.FW_04.getMsg())));
        flow.setCash_inflows_operating_activities(changeNum(map.get(AssetContants.FW_05.getMsg())));
        flow.setCash_payment_for_goods_and_services(changeNum(map.get(AssetContants.FW_06.getMsg())));
        flow.setCash_paid_employees(changeNum(map.get(AssetContants.FW_07.getMsg())));
        flow.setTax_payments(changeNum(map.get(AssetContants.FW_08.getMsg())));
        flow.setPayment_other_cash_related_business_activities(changeNum(map.get(AssetContants.FW_09.getMsg())));
        flow.setCash_outflow_operating_activities(changeNum(map.get(AssetContants.FW_10.getMsg())));
        flow.setNet_cash_flow_from_operating_activities(changeNum(map.get(AssetContants.FW_11.getMsg())));
        flow.setCash_flow_from_investment_activities(changeNum(map.get(AssetContants.FW_12.getMsg())));
        flow.setProceeds_from_sell_of_investment(changeNum(map.get(AssetContants.FW_13.getMsg())));
        flow.setCash_received_on_investment_income(changeNum(map.get(AssetContants.FW_14.getMsg())));
        flow.setNet_cash_fixed_intangible_other_long_term_assets(changeNum(map.get(AssetContants.FW_15.getMsg())));
        flow.setNet_cash_by_subsidiaries_and_other_business_units(changeNum(map.get(AssetContants.FW_16.getMsg())));
        flow.setOther_cash_related_to_investment_activities(changeNum(map.get(AssetContants.FW_17.getMsg())));
        flow.setCash_inflow_from_investment_activities(changeNum(map.get(AssetContants.FW_18.getMsg())));
        flow.setCash_paid_fixed_intangible_other_long_term_assets(changeNum(map.get(AssetContants.FW_19.getMsg())));
        flow.setCash_paid_for_investment(changeNum(map.get(AssetContants.FW_20.getMsg())));
        flow.setNet_cash_paid_by_subsidiaries_and_other_business_units(changeNum(map.get(AssetContants.FW_21.getMsg())));
        flow.setPay_other_cash_related_to_investment_activities(changeNum(map.get(AssetContants.FW_22.getMsg())));
        flow.setCash_outflow_for_investment_activities(changeNum(map.get(AssetContants.FW_23.getMsg())));
        flow.setNet_cash_flow_from_investment_activities(changeNum(map.get(AssetContants.FW_24.getMsg())));
        flow.setCash_flow_from_fundraising_activities(changeNum(map.get(AssetContants.FW_25.getMsg())));
        flow.setReceipts_equity_securities(changeNum(map.get(AssetContants.FW_26.getMsg())));
        flow.setSubsidiaries_receive_cash_from_minority_investors(changeNum(map.get(AssetContants.FW_27.getMsg())));
        flow.setReceipts_from_loan(changeNum(map.get(AssetContants.FW_28.getMsg())));
        flow.setReceipt_of_other_cash_related_fund_raising_activities(changeNum(map.get(AssetContants.FW_29.getMsg())));
        flow.setCash_inflow_from_financing_activities(changeNum(map.get(AssetContants.FW_30.getMsg())));
        flow.setPayment_for_debt(changeNum(map.get(AssetContants.FW_31.getMsg())));
        flow.setCash_to_distribute_dividends_or_pay_interest(changeNum(map.get(AssetContants.FW_32.getMsg())));
        flow.setDividends_paid_by_subsidiary_to_minority_shareholders(changeNum(map.get(AssetContants.FW_33.getMsg())));
        flow.setPayment_of_other_cash_related_fund_raising_activities(changeNum(map.get(AssetContants.FW_34.getMsg())));
        flow.setCash_outflow_financing_activities(changeNum(map.get(AssetContants.FW_35.getMsg())));
        flow.setNet_cash_flow_from_financing_activities(changeNum(map.get(AssetContants.FW_36.getMsg())));
        flow.setEffect_of_exchange_rate_and_cash_equivalents(changeNum(map.get(AssetContants.FW_37.getMsg())));
        flow.setNet_increase_in_cash_and_cash_equivalents(changeNum(map.get(AssetContants.FW_38.getMsg())));
        flow.setBeginning_balance_cash_and_cash_equivalents(changeNum(map.get(AssetContants.FW_39.getMsg())));
        flow.setEnding_balance_cash_and_cash_equivalents(changeNum(map.get(AssetContants.FW_40.getMsg())));
        return flow;
    }
}
