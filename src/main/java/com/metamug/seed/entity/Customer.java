/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metamug.seed.entity;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author deepak
 */
 @XmlRootElement
 public class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
 }