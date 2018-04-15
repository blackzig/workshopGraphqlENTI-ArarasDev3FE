/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.micheladrianomedeiros.enti_ararasdevfe;

import br.com.micheladrianomedeiros.enti_ararasdevfe.view.LoginView;
import tools.SSLTool;

/**
 *
 * @author Michel
 */
public class Main {
    
    public static void main(String args[]){
        SSLTool.disableCertificateValidation();
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }
    
}
