/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
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
public class updateclientForm extends Form{
        public updateclientForm(Form previous){
        setTitle("Mettre a jour");
        setLayout(BoxLayout.y());
        TextField tfId= new TextField("", "id_client");
        TextField tfname = new TextField("", "nom");
        TextField tfprenom = new TextField("", "prenom");
        TextField tfnum_tel = new TextField("", "num_tel");
        TextField tfadresse = new TextField("", "adresse");
        TextField tfsexe = new TextField("", "sexe");
        TextField tfmail = new TextField("", "mail");
        TextField tfpassword = new TextField("", "password", 20, TextField.PASSWORD);
        Button Modifierbtn = new Button("Modifier");
        
        Modifierbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
           if ((tfId.getText().length()==0||tfname.getText().length()==0||tfprenom.getText().length()==0||tfadresse.getText().length()==0||tfsexe.getText().length()==0||tfmail.getText().length()==0||tfpassword.getText().length()==0 ))
               Dialog.show("Alert", "fill all the fields", new Command("OK"));
           else
           {
                      ServicesClient sc = new ServicesClient();
                      Client c =new Client (Integer.parseInt(tfId.getText()),tfname.getText(),tfprenom.getText(),tfnum_tel.getText(),tfadresse.getText(),tfsexe.getText(),tfmail.getText(),tfpassword.getText());
                   
                           boolean test = sc.UpdateClient(c);
                    if (!test)       
                       Dialog.show("ERROR", "SERVER ERROR", new Command("OK"));
                   
                   else {
                       Dialog.show("success", "client modifié avec succes", new Command("OK"));
                   }
                   
            }
               
               
              
                  });
                    addAll(tfId,tfname,tfprenom,tfnum_tel,tfadresse,tfsexe,tfmail,tfpassword,Modifierbtn);
                    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK,e->previous.showBack());
}
}
     