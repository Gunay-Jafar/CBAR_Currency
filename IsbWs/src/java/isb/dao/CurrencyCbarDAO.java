package isb.dao;

import isb.config.DBHelper;
import isb.config.HibernateUtilCbar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import isb.model.CurrencyCBAR;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class CurrencyCbarDAO {

    public void insertCurrencyCbar(CurrencyCBAR currencyCbar) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtilCbar.getInstance().openSession();
            transaction = session.beginTransaction();
            session.save(currencyCbar);
            transaction.commit();
        } catch (HibernateException he) {
            HibernateUtilCbar.rollBack(transaction);
        } finally {
            HibernateUtilCbar.close(session);
        }
    }

    public List<CurrencyCBAR> getCurrencyRates(Date currencyDate) {
        List<CurrencyCBAR> currencyCbars = new ArrayList<CurrencyCBAR>();
        try {
            String mainUrl = "http://www.cbar.az/currencies/";
            String date = new SimpleDateFormat("dd.MM.yyyy").format(currencyDate);
            String extension = ".xml";
            String url = mainUrl + date + extension;
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            String wholeText = "";
            while ((inputLine = in.readLine()) != null) {
                wholeText += inputLine;
            }
            in.close();
            JSONObject jsonObject = XML.toJSONObject(wholeText);
            JSONObject json = jsonObject.getJSONObject("ValCurs");
            JSONArray js = json.getJSONArray("ValType");
            JSONObject currencyRates = (JSONObject) js.get(1);
            JSONArray valutes = currencyRates.getJSONArray("Valute");
            for (int i = 0; i < valutes.length(); i++) {
                String code = (String) valutes.getJSONObject(i).get("Code");
                String name = (String) valutes.getJSONObject(i).getString("Name");
                int nominal = (int) valutes.getJSONObject(i).getInt("Nominal");
                double value = (double) valutes.getJSONObject(i).getDouble("Value");
                CurrencyCBAR currencyCbar = new CurrencyCBAR(code, nominal, name, value, currencyDate);
                currencyCbars.add(currencyCbar);
            }
        } catch (Exception e) {

        }
        return currencyCbars;
    }

    public String insertAllCurrencyRates(Date date) {
        List<CurrencyCBAR> currencyCbars = new CurrencyCbarDAO().getCurrencyRates(date);
        for (CurrencyCBAR currencyCbar : currencyCbars) {
            this.insertCurrencyCbar(currencyCbar);
        }
        return "ok";
    }

    public CurrencyCBAR getCurrencyByCode(String code) {
        CurrencyCBAR currencyCBAR = new CurrencyCBAR();
        try {
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ISB_CURRENCYCBAR where code=" + code + "");
            while (rs.next()) {
                currencyCBAR.setCode(rs.getString("code"));
                currencyCBAR.setCurrencyDate(rs.getDate("currencyDate"));
                currencyCBAR.setName(rs.getString("name"));
                currencyCBAR.setNominal(rs.getInt("nominal"));
                currencyCBAR.setValue(rs.getDouble("value"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencyCBAR;
    }

    public List<CurrencyCBAR> getListByCode(String code) {
        List<CurrencyCBAR> list = new ArrayList<CurrencyCBAR>();
        try {
            Connection conn = DBHelper.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ISB_CURRENCYCBAR where code=" + code + "");
            while (rs.next()) {
                CurrencyCBAR currencyCBAR = new CurrencyCBAR();
                currencyCBAR.setCode(rs.getString("code"));
                currencyCBAR.setCurrencyDate(rs.getDate("currencyDate"));
                currencyCBAR.setName(rs.getString("name"));
                currencyCBAR.setNominal(rs.getInt("nominal"));
                currencyCBAR.setValue(rs.getDouble("value"));
                list.add(currencyCBAR);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
