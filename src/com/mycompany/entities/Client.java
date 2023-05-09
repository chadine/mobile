/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author user
 */
public class Client {
    private int id_client;
    private String nom;
    private String prenom;
    private String num_tel;
    private String adresse;
    private String sexe;
    private String mail;
    private String password;

    public Client(int id_client, String nom, String prenom, String num_tel, String adresse, String sexe, String mail, String password) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.adresse = adresse;
        this.sexe = sexe;
        this.mail = mail;
        this.password = password;
    }

    public Client(String nom, String prenom, String num_tel, String adresse, String sexe, String mail, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.num_tel = num_tel;
        this.adresse = adresse;
        this.sexe = sexe;
        this.mail = mail;
        this.password=password;
    }

   //constructor
    public Client() {
        
    }
    

  

    public int getId_client() {
        return id_client;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    

    public Client(int id_client) {
        this.id_client = id_client;
    }

    public Client(String nom, String mail) {
        this.nom = nom;
        this.mail = mail;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
  

   

    @Override
    public String toString() {
        return "Client{" +  "nom=" + nom + ", num_tel=" + num_tel + ",  adresse=" + adresse + ", sexe=" + sexe + ", mail=" + mail + ",password=" + password + '}';
    }

  

    
}

