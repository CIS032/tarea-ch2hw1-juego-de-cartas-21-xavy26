/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author Paredes
 */
public class ManoBlackjack extends Mano {

    public int getBlackjackValor() {

        int val;
        boolean as;

        int cartas;

        val = 0;
        as = false;
        cartas = getContadorCartas();

        for (int i = 0; i < cartas; i++) {

            Carta carta;
            int valCarta;
            carta = getCarta(i);
            valCarta = carta.getValor();
            if (valCarta > 10) {
                valCarta = 10;
            }
            if (valCarta == 1) {
                as = true;
            }
            val = val + valCarta;
        }

        if (as == true && val + 10 <= 21) {
            val = val + 10;
        }

        return val;

    }
}
