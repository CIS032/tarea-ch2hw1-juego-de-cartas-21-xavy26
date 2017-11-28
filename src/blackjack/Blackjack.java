/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author Paredes
 */
public class Blackjack {

    public static String cadena;

    public static void main(String[] args) {

        int dinero; 
        int apuesta;
        boolean ganaUsuario;

        System.out.println("Bienvenido al juego de BLACKJACK");
        System.out.println();
        cadena = "Bienvenido al juego de BLACKJACK\n\n";
        dinero = 100;
        Scanner lector = new Scanner(System.in);

        while (true) {
            System.out.println("Usted tiene " + dinero + " dolares.");
            cadena += "Usted tiene " + dinero + " dolares.\n";
            do {
                System.out.println("¿Cuánto dinero quiere apostar?  (Ingresar 0 para finalizar.)");
                System.out.print("? ");
                cadena += "¿Cuánto dinero quiere apostar?  (Ingresar 0 para finalizar.)?\n";
                apuesta = lector.nextInt();
                if (apuesta < 0 || apuesta > dinero) {
                    System.out.println("Su respuesta debe esta entre 0 y " + dinero + '.');
                    cadena += "Su respuesta debe esta entre 0 y " + dinero + ".\n";
                }
            } while (apuesta < 0 || apuesta > dinero);
            if (apuesta == 0) {
                BlackjackLogs.escribirArchivo();
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
                System.out.println("Parece que se ha quedado sin dinero!");
                cadena += "\nParece que se ha quedado sin dinero!\n";
                break;
            }
        }

        System.out.println();
        System.out.println("Se va con $" + dinero + '.');
        cadena += "\nSe va con $" + dinero + ".\n";
    }

    static boolean jugarBlackjack() {

        Scanner lector = new Scanner(System.in);
        Mazo cubierta = new Mazo();
        ManoBlackjack manoRepartidor = new ManoBlackjack();
        ManoBlackjack manoUsuario = new ManoBlackjack();

        cubierta.barajar();
        manoRepartidor.añadirCarta(cubierta.ofertaCartas());
        manoRepartidor.añadirCarta(cubierta.ofertaCartas());
        manoUsuario.añadirCarta(cubierta.ofertaCartas());
        manoUsuario.añadirCarta(cubierta.ofertaCartas());

        System.out.println();
        System.out.println();
        cadena += "\n\n";
        if (manoRepartidor.getBlackjackValor() == 21) {
            System.out.println("Repartidor tiene la " + manoRepartidor.getCarta(0)
                    + " y la " + manoRepartidor.getCarta(1) + ".");
            System.out.println("Usuario tiene la " + manoUsuario.getCarta(0)
                    + " y la " + manoUsuario.getCarta(1) + ".");
            System.out.println();
            System.out.println("Reapartidor tiene BLACKJACK.  Repartidor gana.");
            cadena += "Repartidor tiene la " + manoRepartidor.getCarta(0) + " y la " + manoRepartidor.getCarta(1) + ".\n";
            cadena += "Usuario tiene la " + manoUsuario.getCarta(0) + " y la " + manoUsuario.getCarta(1) + ".\n";
            return false;
        }

        if (manoUsuario.getBlackjackValor() == 21) {
            System.out.println("Repartidor tiene la " + manoRepartidor.getCarta(0)
                    + " y la " + manoRepartidor.getCarta(1) + ".");
            System.out.println("Usuario tiene la " + manoUsuario.getCarta(0)
                    + " y la " + manoUsuario.getCarta(1) + ".");
            System.out.println();
            System.out.println("Udted tiene BLACKJACK.   Usted gana");
            cadena += "Repartidor tiene la " + manoRepartidor.getCarta(0) + " y la " + manoRepartidor.getCarta(1) + ".\n";
            cadena += "Usuario tiene la " + manoUsuario.getCarta(0) + " y la " + manoUsuario.getCarta(1) + ".\n";
            return true;
        }

        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("Tus cartas son:");
            cadena += "\n\nTus cartas son";
            for (int i = 0; i < manoUsuario.getContadorCartas(); i++) {
                System.out.println("    " + manoUsuario.getCarta(i));
                cadena += "    " + manoUsuario.getCarta(i);
            }
            System.out.println("Tu total es: " + manoUsuario.getBlackjackValor());
            System.out.println();
            System.out.println("El repartidor muestra el " + manoRepartidor.getCarta(0));
            System.out.println();
            System.out.print("Acierto (A) o Soporte (S)? ");
            cadena += "Tu total es: " + manoUsuario.getBlackjackValor() + "\nEl repartidor muestra el " + manoRepartidor.getCarta(0)
                    + "\nAcierto (A) o Soporte (S)? ";
            String accionUsuario;
            do {
                accionUsuario = lector.nextLine().toUpperCase();
                if (!"A".equals(accionUsuario) && !"S".equals(accionUsuario)) {
                    System.out.print("Por favor responde A o S:  ");
                    cadena += "Por favor responde A o S:  ";
                }
            } while (!"A".equals(accionUsuario) && !"S".equals(accionUsuario));

            if ("S".equals(accionUsuario)) {
                break;
            } else {
                Carta nuevaCarta = cubierta.ofertaCartas();
                manoUsuario.añadirCarta(nuevaCarta);
                System.out.println();
                System.out.println("Aciertos del usuario.");
                System.out.println("Tu carta es " + nuevaCarta);
                System.out.println("Tu total ahora es " + manoUsuario.getBlackjackValor());
                cadena += "Aciertos del Usuario\nTu carta es " + nuevaCarta + "\nTu total ahora es "
                        + manoUsuario.getBlackjackValor();
                if (manoUsuario.getBlackjackValor() > 21) {
                    System.out.println();
                    System.out.println("Has perdido yendo por encima de 21. Pierdes.");
                    System.out.println("La otra carta del distribuidor era la " + manoRepartidor.getCarta(1));
                    cadena += "Has perdido yendo por encima de 21. Pierdes.\nLa otra carta del reaprtidor era la " + manoRepartidor.getCarta(1) + "\n";
                    return false;
                }
            }
        }
        System.out.println();
        System.out.println("Soportes de usuario.");
        System.out.println("Las cartas del repartidor son");
        System.out.println("    " + manoRepartidor.getCarta(0));
        System.out.println("    " + manoRepartidor.getCarta(1));
        cadena += "\nSoportes de usuario\n.Las cartas del repartidor son"
                + "\n     " + manoRepartidor.getCarta(0) + "\n     " + manoRepartidor.getCarta(1);
        while (manoRepartidor.getBlackjackValor() <= 16) {
            Carta nuevaCarta = cubierta.ofertaCartas();
            System.out.println("\nEl repartidor acierta y obtiene " + nuevaCarta);
            cadena += "\nEl repartidor acierta y obtiene " + nuevaCarta;
            manoRepartidor.añadirCarta(nuevaCarta);
            if (manoRepartidor.getBlackjackValor() > 21) {
                System.out.println();
                System.out.println("Repartidor perdio por ir por encima de 21. Ganas");
                cadena += "\n\nRepartidor perdio por ir por encima de 21. Ganas";
                return true;
            }
        }
        System.out.println("El total del repartidor es " + manoRepartidor.getBlackjackValor());
        cadena += "\nEl total del repartidor es " + manoRepartidor.getBlackjackValor();
        System.out.println();
        cadena += "\n";
        if (manoRepartidor.getBlackjackValor() == manoUsuario.getBlackjackValor()) {
            System.out.println("Repartidor gana en empate. Tu pierdes.");
            cadena += "\nRepartidor gana en empate. Tu pierdes.";
            return false;
        } else if (manoRepartidor.getBlackjackValor() > manoUsuario.getBlackjackValor()) {
            System.out.println("Repartidor gana, " + manoRepartidor.getBlackjackValor()
                    + " puntos a " + manoUsuario.getBlackjackValor() + ".");
            cadena += "\nRepartidor gana, " + manoRepartidor.getBlackjackValor()
                    + " puntos a " + manoUsuario.getBlackjackValor() + ".";
            return false;
        } else {
            System.out.println("Tu ganas, " + manoUsuario.getBlackjackValor()
                    + " puntos a " + manoRepartidor.getBlackjackValor() + ".");
            cadena += "Tu ganas, " + manoUsuario.getBlackjackValor()
                    + " puntos a " + manoRepartidor.getBlackjackValor() + ".\n";
            return true;
        }
    }
}
