package simple.test.com.myapplication.rate;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import mtclient.common.storage.StringStore;

/**
 * Created by Administrator on 2016/4/12.
 */
public class ExchangeRate {
    private static String URL = "http://apis.baidu.com/apistore/currencyservice/currency?fromCurrency=USD&toCurrency=CNY&amount=1";
    private static final String API_STORE_KEY = "eee7682664a67d735cae6aa784213249";
    private static String KEY_EXCHANGE_RATE = "key_exchange_rate";

    public static double usd2CNY() {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", API_STORE_KEY);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            double rate = new JSONObject(result).getJSONObject("retData").getDouble("currency");
            StringStore.saveObject(KEY_EXCHANGE_RATE, rate);
            return rate;
        } catch (Exception e) {
        }
        Double cache = StringStore.retrieveObject(KEY_EXCHANGE_RATE, Double.class);
        if (cache != null) {
            return cache;
        }
        return 6.460900;
    }

}
