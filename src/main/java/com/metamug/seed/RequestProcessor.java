/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metamug.seed;


import com.metamug.exec.RequestProcessable;
import com.metamug.seed.entity.Customer;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author deepak
 */
public class RequestProcessor implements RequestProcessable{

    @Override
    public Object process(Map<String, String> parameters, DataSource ds, Map<String, String> headers) {
        Customer c = new Customer("Irfan");
        return c ;
    }
    
}
