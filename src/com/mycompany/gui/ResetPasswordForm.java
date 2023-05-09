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
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Client;
import com.mycompany.services.ServicesClient;

/**
 *
 * @author user
 */
public class ResetPasswordForm extends Form {
     public ResetPasswordForm(Resources res){
        setTitle("Reset password");
        setLayout(BoxLayout.y());
        TextField tfId = new TextField("", "enter your e-mail");
        
       
        Button suppCbtn = new Button("Send");
        
   suppCbtn.addActionListener((ActionListener) (ActionEvent evt) -> {
           if ((tfId.getText().length()==0 ))
               Dialog.show("Alert", "enter you email", new Command("OK"));
           else
           {
                     ServicesClient sc = new ServicesClient();
                     Client c =new Client();
                     c.setMail(tfId.getText());
                            sc.SendReset(c);   
            }
               
               
              
                  });
                    addAll(tfId,suppCbtn);
                    getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e -> new SignInForm(res).show());
}
    
    
}
