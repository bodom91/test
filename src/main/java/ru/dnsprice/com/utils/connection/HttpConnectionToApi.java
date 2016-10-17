package ru.dnsprice.com.utils.connection;

import static ru.dnsprice.com.utils.data.Data.YANDEX;
import static ru.dnsprice.com.utils.data.Data.COMPAIGNS;
import static ru.dnsprice.com.utils.data.Data.BIDS;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.util.List;

/**
 * Created by shestakov.m on 06.10.2016.
 */
public class HttpConnectionToApi {

    //Create Http connection
    public static HttpClient getConnection() {
        HttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
        return httpClient;
    }

    //Take http response from server with header + authorization
    public static HttpResponse httpResponse(String url, String token, String id) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Authorization", "OAuth oauth_token=" + token + ",oauth_client_id=" + id);
        HttpResponse httpResponse = getConnection().execute(httpGet);
        return httpResponse;
    }

    public static HttpPut httpPut(String codeShop, String token, String id) {
        HttpPut httpput = new HttpPut(YANDEX + COMPAIGNS + codeShop + BIDS);
        httpput.addHeader("Authorization", "OAuth oauth_token=" + token + ",oauth_client_id=" + id);
        return httpput;
    }


}
