package com.robertson.domain.services;

import com.robertson.domain.SavingsAccount;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/*
    Http Service is designed to be a service layer that allows the program to send HTTP requests a restful web api
 */
public class HttpService {

    private final String baseUri = "http://accountsapi20181022063942.azurewebsites.net";

    /**
     * Performs HTTP Post request to the web api.
     * @param account The savings account object that contains the json data string required for HTTP request body.
     * @return Returns a boolean dependant on whether the Post request was successful or not.
     */
    public boolean postNewAccount(SavingsAccount account){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPostReq = new HttpPost(baseUri + "/api/Accounts");
        try {
            HttpEntity entity = new StringEntity(account.toStringJson());
            httpPostReq.setEntity(entity);
            httpPostReq.setHeader("content-type", "application/json");
            httpPostReq.setHeader("charset", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return execute(httpClient, httpPostReq);
    }

    /**
     * Performs HTTP Put request to the web api.
     * @param account The savings account object that contains the json data string required for HTTP request body.
     * @return Returns a boolean dependant on whether the Post request was successful or not.
     */
    public boolean updateAccount(SavingsAccount account){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPutReq = new HttpPut(baseUri + "/api/Accounts/" + account.getAccountNumber());
        try {
            StringEntity entity = new StringEntity(account.toStringJson());
            httpPutReq.setEntity(entity);
            httpPutReq.setHeader("content-type", "application/json");
            httpPutReq.setHeader("charset", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return execute(httpClient, httpPutReq);
    }

    // Performs the execution of the HTTP client request, returns boolean if the correct response code was returned
    // in the response from the server.
    private boolean execute(HttpClient httpClient, HttpUriRequest httpRequest){
        int statusCode = 0;
        try {
            HttpResponse response = httpClient.execute(httpRequest);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statusCode == 201 || statusCode == 204;
    }

}
