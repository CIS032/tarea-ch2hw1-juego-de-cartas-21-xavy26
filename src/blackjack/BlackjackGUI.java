/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import javax.swing.JOptionPane;

/**
 *
 * @author Paredes
 */
public class BlackjackGUI {

    public static void main(String[] args) {

        int dinero;
        int apuesta;
        boolean ganaUsuario;

        JOptionPane.showMessageDialog(null, "Bienvenido al juego de BLACKJACK\n\n");

        dinero = 100;
        while (true) {

            do {
                apuesta = Integer.parseInt(JOptionPane.showInputDialog("¿Cuánto dinero quiere apostar?  (Ingresar 0 para finalizar.)?", "Usted tiene " + dinero + " dolares."));
                if (apuesta < 0 || apuesta > dinero) {
                    JOptionPane.showMessageDialog(null, "Su respuesta debe esta entre 0 y " + dinero + '.');
                }
            } while (apuesta < 0 || apuesta > dinero);
            if (apuesta == 0) {
                break;
            }
            ganaUsuario = jugarBlackjack();
            if (ganaUsuario) {
                dinero = dinero + apuesta;
            } else {
                dinero = dinero - apuesta;
            }
            System.out.println();
            if (dinero == 0) {
                JOptionPane.showMessageDialog(null, "Parece que se ha quedado sin dinero!");
                break;
            }
        }
        JOptionPane.showMessageDialog(null, "Se va con $" + dinero + '.');
    }

    static boolean jugarBlackjack() {
        Mazo cubierta = new Mazo();
        ManoBlackjack manoRepartidor = new ManoBlackjack();
        ManoBlackjack manoUsuario = new ManoBlackjack();

        cubierta.barajar();
        manoRepartidor.añadirCarta(cubierta.ofertaCartas());
        manoRepartidor.añadirCarta(cubierta.ofertaCartas());
        manoUsuario.añadirCarta(cubierta.ofertaCartas());
        manoUsuario.añadirCarta(cubierta.ofertaCartas());

        String cad;

        if (manoRepartidor.getBlackjackValor() == 21) {
            cad = "Repartidor tiene la " + manoRepartidor.getCarta(0)
                    + " y la " + manoRepartidor.getCarta(1) + ".";
            cad += "\nUsuario tiene la " + manoUsuario.getCarta(0)
                    + " y la " + manoUsuario.getCarta(1) + ".";
            cad += "\nReapartidor tiene BLACKJACK.  Repartidor gana.";
            JOptionPane.showMessageDialog(null, cad);
            return false;
        }

        if (manoUsuario.getBlackjackValor() == 21) {
            cad = "Repartidor tiene la " + manoRepartidor.getCarta(0)
                    + " y la " + manoRepartidor.getCarta(1) + ".\n";
            cad += "Usuario tiene la " + manoUsuario.getCarta(0)
                    + " y la " + manoUsuario.getCarta(1) + ".\n";
            cad += "\nUdted tiene BLACKJACK.   Usted gana";
            JOptionPane.showMessageDialog(null, cad);
            return true;
        }

        while (true) {
            cad = "Tus cartas son:\n";
            for (int i = 0; i < manoUsuario.getContadorCartas(); i++) {
                cad += "    " + manoUsuario.getCarta(i) + "\n";
            }
            cad += "Tu total es: " + manoUsuario.getBlackjackValor();
            cad += "\n\nEl repartidor muestra el " + manoRepartidor.getCarta(0);
            String accionUsuario;
            do {
                accionUsuario = JOptionPane.showInputDialog(cad, "Acierto (A) o Soporte (S)? ").toUpperCase();
                if (!"A".equals(accionUsuario) && !"S".equals(accionUsuario)) {
                    JOptionPane.showMessageDialog(null, "Por favor responde A o S:  ");
                }
            } while (!"A".equals(accionUsuario) && !"S".equals(accionUsuario));

            if ("S".equals(accionUsuario)) {
                break;
            } else {
                Carta nuevaCarta = cubierta.ofertaCartas();
                manoUsuario.añadirCarta(nuevaCarta);
                cad = "Aciertos del usuario.\n";
                cad += "Tu carta es " + nuevaCarta;
                cad += "\nTu total ahora es " + manoUsuario.getBlackjackValor();
                JOptionPane.showMessageDialog(null, cad);
                if (manoUsuario.getBlackjackValor() > 21) {
                    cad = "Has perdido yendo por encima de 21. Pierdes.\n";
                    cad += "\n\nLa otra carta del distribuidor era la " + manoRepartidor.getCarta(1);
                    JOptionPane.showMessageDialog(null, cad);
                    return false;
                }
            }
        }
        cad = "Soportes de usuario.\n\n";
        cad += "\nLas cartas del repartidor son";
        cad += "\n    " + manoRepartidor.getCarta(0);
        cad += "\n    " + manoRepartidor.getCarta(1);
        while (manoRepartidor.getBlackjackValor() <= 16) {
            Carta nuevaCarta = cubierta.ofertaCartas();
            cad += "\nEl repartidor acierta y obtiene " + nuevaCarta;
            JOptionPane.showMessageDialog(null, "El repartidor acierta y obtiene " + nuevaCarta);
            manoRepartidor.añadirCarta(nuevaCarta);
            if (manoRepartidor.getBlackjackValor() > 21) {
                cad += "\nRepartidor perdio por ir por encima de 21. Ganas";
                return true;
            }
        }
        cad += "\nEl total del repartidor es " + manoRepartidor.getBlackjackValor();
        if (manoRepartidor.getBlackjackValor() == manoUsuario.getBlackjackValor()) {
            cad += "\n\nRepartidor gana en empate. Tu pierdes.";
            JOptionPane.showMessageDialog(null, cad);
            return false;
        } else if (manoRepartidor.getBlackjackValor() > manoUsuario.getBlackjackValor()) {
            cad += "\n\nRepartidor gana, " + manoRepartidor.getBlackjackValor()
                    + " puntos a " + manoUsuario.getBlackjackValor() + ".";
            JOptionPane.showMessageDialog(null, cad);
            return false;
        } else {
            cad += "\n\nTu ganas, " + manoUsuario.getBlackjackValor()
                    + " puntos a " + manoRepartidor.getBlackjackValor() + ".";
            JOptionPane.showMessageDialog(null, cad);
            return true;
        }
    }
}
