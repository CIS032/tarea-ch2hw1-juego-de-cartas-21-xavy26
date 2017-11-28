/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paredes
 */
public class BlackjackLogs {

    public static void escribirArchivo() {
        String cad = Blackjack.cadena;
        try (BufferedWriter escribir = new BufferedWriter(new FileWriter("logs.txt"))) {
            escribir.write(cad);
            escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(BlackjackLogs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
