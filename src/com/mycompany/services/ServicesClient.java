/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Client;
import com.mycompany.statics.Statics;
import java.util.ArrayList;
import java.util.List;
import java.io.UnsupportedEncodingException;
import com.codename1.io.JSONParser;
import com.codename1.ui.Dialog;
import java.io.IOException;
import java.util.Map;
import com.mycompany.services.SessionManager;


/**
 *
 * @author user
 */
public class ServicesClient {

    public boolean resultOK;      
    ConnectionRequest req;
    static ServicesClient instance;
    ArrayList<Client> result = new ArrayList<>();
     String json;
   

    public ServicesClient() {
        req = new ConnectionRequest();
    }

    public static ServicesClient getInstance() {

        if (instance == null) {
            instance = new ServicesClient();
        }

        return instance;
    }

    public boolean AddClient(Client c) {
         String url = Statics.BASE_URL + "/client/new_client_json?nom=" + c.getNom()+ "&prenom=" + c.getPrenom() + 
                "&adresse=" + c.getAdresse() + "&sexe=" + c.getSexe() + 
                "&password=" + c.getPassword() + "&email=" + c.getMail()+ "&numtel=" + c.getNum_tel() ; 
        System.out.println( url );
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
            resultOK = req.getResponseCode() == 200 ; 
            req.removeResponseCodeListener(this);
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK; 
    }

    
 public boolean UpdateClient(Client c) {
    String url = Statics.BASE_URL + "/client/update_client_JSON?id=" + c.getId_client()+"&nom=" + c.getNom()+ "&prenom=" + c.getPrenom() + 
                "&adresse=" + c.getAdresse() + "&sexe=" + c.getSexe() + 
                "&password=" + c.getPassword() + "&email=" + c.getMail()+ "&numtel=" + c.getNum_tel() ; 
    req.setUrl(url);
     System.out.println(url);
    req.setPost(false);
    req.addArgument("id_client", String.valueOf(c.getId_client()));
    req.addArgument("nom", c.getNom());
    req.addArgument("prenom", c.getPrenom());
    req.addArgument("adresse", c.getAdresse());
    req.addArgument("sexe", c.getSexe());
    req.addArgument("password", c.getPassword());
    req.addArgument("email", c.getMail());
    req.addArgument("numtel", c.getNum_tel());

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
 
       public List<Client> fetchUsers() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/client/affichageJSON";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = (ArrayList<Client>) parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }

    //Parse
    public List<Client> parseTasks(String jsonText) {

        //var
        result = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");

            //4
            for (Map<String, Object> item : list) {

                Client c = new Client();
             if (item.get("id_client") != null) {
   c.setId_client((int) (double) item.get("id_client"));
}
                c.setNom((String) item.get("nom"));
                c.setPrenom((String) item.get("prenom"));
                c.setNum_tel((String) item.get("num_tel"));
                c.setAdresse((String) item.get("adresse"));
                c.setSexe((String) item.get("sexe"));
                c.setMail((String) item.get("mail"));
                c.setPassword((String) item.get("password"));
                result.add(c);
            }

        } catch (IOException ex) {
        }

        //End
        return result;
    }
 public boolean deleteC(Client c) {

        String url = Statics.BASE_URL + "/client/deleteclientJSON?id=" + c.getId_client();

      System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            
            resultOK = req.getResponseCode() == 200 ; 
            req.removeResponseCodeListener(this);
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

  public void login(String mail, String password) {

        String url = Statics.BASE_URL + "/client/loginJSON?mail=" + mail + "&password=" + password;
        System.out.println(url);
        req.setUrl(url);
        System.out.println(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {
                if (json.equals("incorrect password")) {
                    Dialog.show("Failure", "wrong password", "OK", null);
                } else if (json.equals("user not found")) {
                    Dialog.show("Failure", "Username not found", "OK", null);
                } else {
                    System.out.println("data ==" + json);
                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    System.out.println(user);
                    Client c = new Client();

                    double id = (Double) user.get("Id_client");
                    int intId = Double.valueOf(id).intValue();
                    
                    c.setId_client(intId);
                    c.setNom((String) user.get("nom"));
                    c.setPrenom((String) user.get("prenom"));
                    c.setAdresse((String) user.get("adresse"));
                    c.setMail((String) user.get("mail"));
                    c.setSexe((String)user.get("sexe"));
                    c.setNum_tel((String) user.get("numTel"));
                    SessionManager.getInstance().setCurrentUser(c);
                    System.out.println(c);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
               req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
 
 public void SendReset(Client c) {

        String url = Statics.BASE_URL + "/reset-password/requestMobile?mail=" + c.getMail();

      System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {
                if (json.equals("mail not found")) {
                    Dialog.show("Failure", "mail not found", "OK", null);
                } else if (json.equals("token error")) {
                    Dialog.show("Failure", "token error", "OK", null);
                }
                else if (json.equals("mail sent successfully"))
                {
                    Dialog.show("Success", "mail sent successfully", "OK", null);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
 
 
 
 
 
}
    
    
    
    
    
    
    
    
    
    
    
    
    


   

