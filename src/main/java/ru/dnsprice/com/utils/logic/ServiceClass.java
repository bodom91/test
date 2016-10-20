package ru.dnsprice.com.utils.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import ru.dnsprice.com.model.Goods;
import ru.dnsprice.com.utils.api.Compains;
import ru.dnsprice.com.utils.connection.HttpConnectionToApi;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shestakov.m on 17.10.2016.
 */
@Service("serviceClass")
public class ServiceClass {

    @Resource
    private Compains compains;

    //Получаем все предложения по городу
    public ArrayList<Goods> getAll(String city) {
        ArrayList<Goods> list = new ArrayList<Goods>();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jObjectPage = (JSONObject) compains.getGoods(city, "1", "1000");
        //Получаем количество страниц
        JSONObject objPager = (JSONObject) jObjectPage.get("pager");
        JSONArray arrayOffers;
        String pagesCount = String.valueOf(objPager.get("pagesCount"));
        int Pages = Integer.parseInt(pagesCount);
        //ПОлучаем список товаров
        for (int i = 1; i <= Pages; i++) {
            jObjectPage = (JSONObject) compains.getGoods(city, String.valueOf(i), "1000");
            arrayOffers = (JSONArray) jObjectPage.get("offers");
            parseGoods(arrayOffers, list);
        }
        return list;
    }

    //Парсим полученый массив в товары
    public void parseGoods(JSONArray array, List<Goods> goodsGet) {
        ObjectMapper objectMapper = new ObjectMapper();
        for (Object goods : array) {
            Goods good = null;
            try {
                good = objectMapper.readValue(goods.toString(), Goods.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            goodsGet.add(good);
        }
    }

    //Парсим ставки в стрингу JSON
    public String getJsonPut(ArrayList<String> offers){
        JSONArray arr = new JSONArray();
        JSONObject result = new JSONObject();
        for (String tovar : offers){
            JSONObject obj = new JSONObject();
            String[] resource = tovar.split(" ");
            obj.put("offerId",resource[0]);
            obj.put("bid",resource[1]);
            obj.put("cbid",resource[2]);
            arr.add(obj);
        }
        result.put("bids",arr);
        StringBuilder postFinal = new StringBuilder();
        postFinal.append(result);
        return String.valueOf(postFinal);
    }

    //Заливка ставок
    public String loadPrice(String codeShop, String token, String id, ArrayList<String> offers) {
        String load = getJsonPut(offers);
        String responseString = "";
        try {
        HttpClient httpClient = HttpConnectionToApi.getConnection();
        HttpPut httpPut = HttpConnectionToApi.httpPut(codeShop, token, id);
        httpPut.addHeader(new BasicHeader("Content-Type", "application/json"));
        StringEntity enity = new StringEntity(load, "UTF-8");
        httpPut.setEntity(enity);
        HttpResponse response = httpClient.execute(httpPut);
        HttpEntity resp = response.getEntity();
        String h  = EntityUtils.toString(resp, "UTF-8");
            responseString = responseString + "Залито 500 ";
            System.out.println("Залито");
            return responseString;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(responseString);
            return responseString;
        }
    }

    //Сравниваем два массива (наши товары и товары маркета)
    public ArrayList<String> equalsArray(HashMap<String, String> ourPrice, List<Goods> yandexPrice) {
        ArrayList<String> finalbids = new ArrayList();
        for (int i = 0; i < yandexPrice.size(); i++) {
            if (ourPrice.containsKey(yandexPrice.get(i).getId())) {
                String bid = ourPrice.get(yandexPrice.get(i).getId()).substring(0,4);
                finalbids.add(yandexPrice.get(i).getId() + " " + bid + " " + bid);
            }
        }
        return finalbids;
    }
}
