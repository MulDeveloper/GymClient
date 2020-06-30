
package dev.muldev.gestiongym.backendgym.Security;

import java.util.Random;


public class GeneraPass {
    
    public GeneraPass(){
        
    }
    
    public String creaPass(String fecha) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 2) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        String pass = saltStr+fecha;
        
        
        return pass;

    }
}
