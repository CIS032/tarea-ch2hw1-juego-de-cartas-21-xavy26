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
public class Mazo {

    private Carta[] cubierta;
    private int cartasUsadas;

    public Mazo() {
        this(false);
    }

    public Mazo(boolean incluirJokers) {
        if (incluirJokers) {
            cubierta = new Carta[54];
        } else {
            cubierta = new Carta[52];
        }
        int cartaCt = 0;
        for (int traje = 0; traje <= 3; traje++) {
            for (int valor = 1; valor <= 13; valor++) {
                cubierta[cartaCt] = new Carta(valor, traje);
                cartaCt++;
            }
        }
        if (incluirJokers) {
            cubierta[52] = new Carta(1, Carta.JOKER);
            cubierta[53] = new Carta(2, Carta.JOKER);
        }
        cartasUsadas = 0;
    }

    public void barajar() {
        for (int i = cubierta.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Carta temp = cubierta[i];
            cubierta[i] = cubierta[rand];
            cubierta[rand] = temp;
        }
        cartasUsadas = 0;
    }

    public int cartasIzquierda() {
        return cubierta.length - cartasUsadas;
    }

    public Carta ofertaCartas() {
        if (cartasUsadas == cubierta.length) {
            throw new IllegalStateException("No cards are left in the deck.");
        }
        cartasUsadas++;
        return cubierta[cartasUsadas - 1];

    }

    public boolean tieneJokers() {
        return (cubierta.length == 54);
    }
}
