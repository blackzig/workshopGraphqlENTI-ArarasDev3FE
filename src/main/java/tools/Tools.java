/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author zigui
 */
public class Tools {

    public String dataToJson(BufferedReader responseBuffer) throws IOException {
        int count = 0;
        String output;
        String realJson = null;
        while ((output = responseBuffer.readLine()) != null) {
            System.out.println(output);
            for (int i = 0; i < output.length(); i++) {
                String letter = Character.toString(output.charAt(i));
                if (letter.equals("{")) {
                    count++;
                    if (count == 3) {
                        realJson = output.substring(i, output.length() - 2);
                        break;
                    }
                }
            }
        }
        return realJson;
    }

    public String dataArrayToJson(BufferedReader responseBuffer) throws IOException {
        String output;
        String realJson = null;
        while ((output = responseBuffer.readLine()) != null) {
            System.out.println(output);
            int firstBracket = output.indexOf("[");
            realJson = "[" + output.substring(firstBracket + 1, output.length() - 3) + "]";
        }
        return realJson;
    }
}
