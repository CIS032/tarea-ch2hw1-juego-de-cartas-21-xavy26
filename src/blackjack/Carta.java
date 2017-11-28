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
public class Carta {

    public final static int ESPADAS = 0;
    public final static int CORAZONES = 1;
    public final static int DIAMANTES = 2;
    public final static int TREBOL = 3;
    public final static int JOKER = 4;

    public final static int AS = 1;
    public final static int JOTA = 11;
    public final static int REYNA = 12;
    public final static int REY = 13;

    private final int TRAJE;
    private final int VALOR;

    public Carta() {
        TRAJE = JOKER;
        VALOR = 1;
    }

    public Carta(int valor, int traje) {
        if (traje != ESPADAS && traje != CORAZONES && traje != DIAMANTES
                && traje != TREBOL && traje != JOKER) {
            throw new IllegalArgumentException("Traje de cartas ilegal");
        }
        if (traje != JOKER && (valor < 1 || valor > 13)) {
            throw new IllegalArgumentException("Traje de cartas ilegal");
        }
        VALOR = valor;
        TRAJE = traje;
    }

    public int getTraje() {
        return TRAJE;
    }

    public int getValor() {
        return VALOR;
    }

    public String getTrajeComoString() {
        switch (TRAJE) {
            case ESPADAS:
                return "Espadas";
            case CORAZONES:
                return "Corazones";
            case DIAMANTES:
                return "Diamantes";
            case TREBOL:
                return "Trebol";
            default:
                return "Joker";
        }
    }

    public String getValorComoEstring() {
        if (TRAJE == JOKER) {
            return "" + VALOR;
        } else {
            switch (VALOR) {
                case 1:
                    return "As";
                case 2:
                    return "2";
                case 3:
                    return "3";
                case 4:
                    return "4";
                case 5:
                    return "5";
                case 6:
                    return "6";
                case 7:
                    return "7";
                case 8:
                    return "8";
                case 9:
                    return "9";
                case 10:
                    return "10";
                case 11:
                    return "Jota";
                case 12:
                    return "Reyna";
                default:
                    return "Rey";
            }
        }
    }

    @Override
    public String toString() {
        if (TRAJE == JOKER) {
            if (VALOR == 1) {
                return "Joker";
            } else {
                return "Joker #" + VALOR;
            }
        } else {
            return getValorComoEstring() + " de " + getTrajeComoString();
        }
    }
}
