/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Client;
import com.mycompany.services.ServicesClient;
import java.util.ArrayList;

/**
 *sabri sabri sabrééééééééé
 * @author user
 */
public class ListClientForm extends Form {

    public ListClientForm(Form previous) {
        ServicesClient sc = new ServicesClient();
        setTitle("List Client");
        SpanLabel ClientListSP = new SpanLabel();
        ClientListSP.setText(sc. fetchUsers().toString());
       ArrayList<Client> result = (ArrayList<Client>) sc. fetchUsers();
       if (result != null) {
     System.out.println(sc. fetchUsers());
        this.add(ClientListSP);
    } else {
            System.out.println("list empty");
        } 
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
