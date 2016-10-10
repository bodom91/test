package ru.dnsprice.com.utils.api;

import static ru.dnsprice.com.utils.data.Data.YANDEX;
import static ru.dnsprice.com.utils.data.Data.COMPAINS;
import static ru.dnsprice.com.utils.data.Data.COMPAIGNS;
import static ru.dnsprice.com.utils.data.Data.OFFERS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sun.tracing.dtrace.Attributes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import ru.dnsprice.com.model.City;
import ru.dnsprice.com.model.Goods;
import ru.dnsprice.com.service.CityService;
import ru.dnsprice.com.service.CityServiceImpl;
import ru.dnsprice.com.utils.connection.HttpConnectionToApi;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shestakov.m on 05.10.2016.
 */
@Service ("getCompain")
public class Compains implements Reciever {

    public String getCompains(){
        String responseString = null;
        try {
        HttpResponse response = HttpConnectionToApi.httpResponse(YANDEX + COMPAINS,
                "AQAAAAAW7HREAAND_oJ25b48yEpylzgd3Rjrzrk","9ad1fe90a69d4725bae74396f1b52ae8");
        HttpEntity enty = response.getEntity();
        responseString = EntityUtils.toString(enty, "UTF-8");
        responseString = responseString.substring(13,responseString.length());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return responseString;
    }

    public JSONObject getGoods(String id, String page) {
        String responseString = "";
        JSONObject object = new JSONObject();
        try {
            HttpResponse response = HttpConnectionToApi.httpResponse(YANDEX + COMPAIGNS + id + OFFERS + "page=" + page + "&pageSize=100",
                    "AQAAAAAW7HREAAND_oJ25b48yEpylzgd3Rjrzrk","9ad1fe90a69d4725bae74396f1b52ae8");
            HttpEntity enty = response.getEntity();
            responseString = EntityUtils.toString(enty, "UTF-8");
            object = jsonParse(responseString);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    //Парсим строку ответа в объект JSON
    public JSONObject jsonParse(String stringParse) {
        JSONObject answer = new JSONObject();
        try {
            JSONParser jsonParser = new JSONParser();
            answer = (JSONObject) jsonParser.parse(stringParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return answer;
    }

}
