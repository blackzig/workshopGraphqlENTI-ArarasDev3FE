/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.micheladrianomedeiros.enti_ararasdevfe.endpoint;

/**
 *
 * @author Michel
 */
public class Server {
    
    private static String URL_SERVIDOR = "https://localhost:8443/graphql";
    
    private static String ID_OPERATOR;
    private static String PASS;

    public static String getURL_SERVIDOR() {
        return URL_SERVIDOR;
    }

    public static void setURL_SERVIDOR(String URL_SERVIDOR) {
        Server.URL_SERVIDOR = URL_SERVIDOR;
    }

    public static String getID_OPERATOR() {
        return ID_OPERATOR;
    }

    public static void setID_OPERATOR(String ID_OPERATOR) {
        Server.ID_OPERATOR = ID_OPERATOR;
    }

    public static String getPASS() {
        return PASS;
    }

    public static void setPASS(String PASS) {
        Server.PASS = PASS;
    }
    
    
}
