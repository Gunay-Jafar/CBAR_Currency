/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isb.service;

import isb.dao.CurrencyCbarDAO;
import isb.model.CurrencyCBAR;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author User
 */
@WebService(serviceName = "IsbWs")
public class IsbWs {

    /**
     * This is a sample web service operation
     *
     * @param code
     * @return
     */
    @WebMethod(operationName = "getCurrencyByCode")
    public CurrencyCBAR getCurrency(@WebParam(name = "code") String code) {
        CurrencyCBAR currencyCBAR = new CurrencyCbarDAO().getCurrencyByCode(code);
        return currencyCBAR;
    }

    @WebMethod(operationName = "getList")
    public List<CurrencyCBAR> getCurrencyList(@WebParam(name = "code") String code) {
        List<CurrencyCBAR> currencyCBAR = new CurrencyCbarDAO().getListByCode(code);
        return currencyCBAR;
    }

    @WebMethod(operationName = "insert")
    public String insertData(@WebParam(name = "insert") String insert) {
        String response = new CurrencyCbarDAO().insertAllCurrencyRates(new Date());
        return response;
    }
}
