/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Client;
public class HomeForm extends Form{
    Form current;
    public HomeForm(){
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choose an option"));
        Button btnAddClient = new Button ("Add Client");
        Button btnListClient = new Button ("List Client");
        Button btnmodifierClient = new Button ("Modifier");
         Button btnSuppC = new Button ("Supprimer");
        btnAddClient.addActionListener(e -> new AddClientForm(current).show());
        btnListClient.addActionListener(e -> new ListClientForm(current).show());
        btnmodifierClient.addActionListener(e ->new updateclientForm(current).show()); // passer le client à la forme de mise à jour
        btnSuppC.addActionListener(e ->new deleteForm(current).show()); 
        addAll(btnAddClient,btnListClient,btnmodifierClient,btnSuppC);
    }
    
}
