/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Client;
import com.mycompany.services.ServicesClient;
import com.mycompany.services.SessionManager;
import java.io.IOException;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {
     
    public SignInForm(Resources res) {
        super(new BorderLayout());
         
        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        //getTitleArea().setUIID("Container");
        //setUIID("SignIn");

       // add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setText("firas@esprit.tn");
        password.setText("123456");
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button ForgotPassword = new Button("Forgot password ?");
        
       

      
        Label doneHaveAnAccount = new Label("Don't have an account?");

        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                createLineSeparator(),
                FlowLayout.encloseCenter(doneHaveAnAccount),
                ForgotPassword
               
        );  
        
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        ForgotPassword.addActionListener(e -> new ResetPasswordForm(res).show());
        signIn.addActionListener(e -> {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                Dialog.show("Remplir tout", "", "Annuler", "OK");
            } else {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                String email = username.getText();
                String pwd = password.getText();
                ServicesClient.getInstance().login(email, pwd);
                 System.out.println(SessionManager.getInstance().getCurrentUser());
                if (SessionManager.getInstance().getCurrentUser().getId_client() != 0 )
                {
                    new HomeForm().show();
                }
                else {
                    show();
                }
                
            }
        });
    }
}
