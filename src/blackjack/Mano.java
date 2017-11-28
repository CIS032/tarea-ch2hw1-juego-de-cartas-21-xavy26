/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;

/**
 *
 * @author Paredes
 */
public class Mano {

    private ArrayList<Carta> mano;

    public Mano() {
        mano = new ArrayList<>();
    }

    public void claro() {
        mano.clear();
    }

    public void añadirCarta(Carta c) {
        if (c == null) {
            throw new NullPointerException("No se puede agregar la carta a la mano");
        }
        mano.add(c);
    }

    public void quitarCarta(Carta c) {
        mano.remove(c);
    }

    public void quitarCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size()) {
            throw new IllegalArgumentException("En la mano no existe la posicion: "
                    + posicion);
        }
        mano.remove(posicion);
    }

    public int getContadorCartas() {
        return mano.size();
    }

    public Carta getCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size()) {
            throw new IllegalArgumentException("En la mano no existe la posicion: "
                    + posicion);
        }
        return mano.get(posicion);
    }

    public void ordenarPorTraje() {
        ArrayList<Carta> nuevaMano = new ArrayList<>();
        while (mano.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Carta c = mano.get(0);  // Minimal card.
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if (c1.getTraje() < c.getTraje()
                        || (c1.getTraje() == c.getTraje() && c1.getValor() < c.getValor())) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano = nuevaMano;
    }

    public void ordenarPorValor() {
        ArrayList<Carta> nuevaMano = new ArrayList<>();
        while (mano.size() > 0) {
            int pos = 0;
            Carta c = mano.get(0);
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if (c1.getValor() < c.getValor()
                        || (c1.getValor() == c.getValor() && c1.getTraje() < c.getTraje())) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano = nuevaMano;
    }
}
