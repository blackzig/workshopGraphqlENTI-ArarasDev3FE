package br.com.micheladrianomedeiros.enti_ararasdevfe.endpoint;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.DateTypeDeserializer;
import tools.Tools;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Michel
 */
public class GenericEndpoint<T> {

    public T returnObject(String json, Class<T> cls) {
        HttpURLConnection uRLConnection = connectionURL();
        try {
            BufferedReader response = mountBuffer(uRLConnection, json);
            Tools t = new Tools();
            String realJson = t.dataToJson(response);
            System.out.println("realJson " + realJson);
            Gson gson = prepareGson();
            return gson.fromJson(realJson, cls);
        } catch (JsonSyntaxException e) {
            System.out.println("ERROR JsonSyntaxException " + e.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(GenericEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<T> list(String json, Class<T> cls) {
        HttpURLConnection urlConnection = connectionURL();
        InputStream inputStream = null;

        List<T> list = new ArrayList<>();
        try {

            BufferedReader responseBuffer = mountBuffer(urlConnection, json);

            Tools t = new Tools();
            String realJson = t.dataArrayToJson(responseBuffer);

            Gson gson = prepareGson();

            JsonArray arry = new JsonParser().parse(realJson).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }

            urlConnection.disconnect();

        } catch (IOException e) {
            System.out.println("ERROR>>>>" + e.getMessage());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    System.out.println("ERROR list " + ex.getMessage());
                }
            }
        }
        return list;
    }

    private HttpURLConnection connectionURL() {
        String userPassword = "Admin" + ":" + "123";
        String basicAuth = "Basic " + new String(new Base64().encode(userPassword.getBytes()));
        HttpURLConnection urlConnection = null;

        try {
            URL targetUrl = new URL(Server.getURL_SERVIDOR());
            urlConnection = (HttpURLConnection) targetUrl.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(20000 /* milliseconds */);
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Authorization", basicAuth);
            urlConnection.setRequestProperty("Content-Type", "application/json");

        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return urlConnection;
    }

    private BufferedReader mountBuffer(HttpURLConnection uRLConnection, String json) {
        BufferedReader responseBuffer = null;

        try {
            String value = new String(json.getBytes("UTF-16"));

            try (DataOutputStream wr = new DataOutputStream(uRLConnection.getOutputStream())) {
                wr.writeBytes(value);
                wr.flush();
            }

            responseBuffer = new BufferedReader(new InputStreamReader(
                    (uRLConnection.getInputStream()), StandardCharsets.UTF_8));

        } catch (IOException e) {
            System.out.println("ERROR mountBuffer " + e.getMessage());
        }
        return responseBuffer;
    }

    private Gson prepareGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateTypeDeserializer());
        Gson gson = builder.create();
        return gson;
    }

}
