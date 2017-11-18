// /*
//  * To change this license header, choose License Headers in Project Properties.
//  * To change this template file, choose Tools | Templates
//  * and open the template in the editor.
//  */
// package com.metamug.seed;


// import com.metamug.exec.RequestProcessable;
// import com.metamug.seed.entity.Customer;
// import java.util.Map;
// import javax.sql.DataSource;

// import groovy.lang.Binding;
// import groovy.util.GroovyScriptEngine;
// import groovy.util.ResourceException;
// import groovy.util.ScriptException;
// import java.io.IOException;
// import java.util.HashMap;
// import java.util.Map;
// import java.util.logging.Level;
// import java.util.logging.Logger;
// import org.codehaus.groovy.control.CompilationFailedException;

// /**
//  *
//  * @author deepak
//  */
// public class RequestProcessor implements RequestProcessable{

//     @Override
//     public Object process(Map<String, String> parameters, DataSource ds, Map<String, String> headers) {
//         	try{
// 	        	GroovyScriptEngine engine = new GroovyScriptEngine( ".");
// 	            Map<String, String> request = new HashMap();
// 	            request.put("url", parameters.get("url"));
// 	            Map<String, String> response = new HashMap();
// 	            response.put("param1", "World");
// 	            Binding binding = new Binding();
// 	            binding.setVariable("request", request);
// 	            binding.setVariable("response", response);
// 	            engine.run("/equant.groovy", binding);
// 	            return response.get("stock");
//             } catch (IOException | SecurityException | ResourceException | ScriptException | IllegalArgumentException ex) {
//             	Logger.getLogger(RequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
//             	return "{\"error\":\""+ex.getMessage()+"\"}";
//         	}
//     }
    
// }
