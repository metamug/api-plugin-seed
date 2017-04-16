/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metamug.seed;

import com.google.gson.Gson;
import com.metamug.exec.RequestProcessable;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author deepak
 */
public class RequestProcessor implements RequestProcessable{

    @Override
    public Object process(Map<String, String> parameters, DataSource ds, Map<String, String> headers) {
        if(headers.get("Accept").equals("application/json")){
            Gson gson = new Gson();
            String json = gson.toJson(parameters);
            return json;
        }else{
            return "Invalid Request Header";
        }
    }
    
}
