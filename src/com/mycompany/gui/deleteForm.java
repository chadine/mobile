/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Client;
import com.mycompany.services.ServicesClient;

/**
 *
 * @author user
 */
public class deleteForm extends Form {
     public deleteForm(Form previous){
        setTitle("Delete Client");
        setLayout(BoxLayout.y());
        TextField tfId = new TextField("", "id_client");
        
       
        Button suppCbtn = new Button("Supprimer");
        
   suppCbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
           if ((tfId.getText().length()==0 ))
               Dialog.show("Alert", "fill all the fields", new Command("OK"));
           else
           {
                     ServicesClient sc = new ServicesClient();
                   Client c =new Client (Integer.parseInt(tfId.getText()));
                   
                           boolean test = sc.deleteC(c);
                    if (!test)       
                       Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
                   
                   else {
                       Dialog.show("success", "client supprimé avec succes", new Command("OK"));
                   }
                   
            }
               
               
              
                  });
                    addAll(tfId,suppCbtn);
                    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
}
    
    
}
