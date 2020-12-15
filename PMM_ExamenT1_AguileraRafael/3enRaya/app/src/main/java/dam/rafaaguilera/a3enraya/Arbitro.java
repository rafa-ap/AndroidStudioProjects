package dam.rafaaguilera.a3enraya;

import android.widget.GridLayout;

public class Arbitro {
    public Arbitro(){

    }

    public char testWinner (GridLayout tablero, String jugador) {

        if (tablero.getChildAt(0).getTag().equals(jugador) && tablero.getChildAt(1).getTag().equals(jugador) && tablero.getChildAt(2).getTag().equals(jugador)) {
            return jugador.charAt(0);
        }
        if (tablero.getChildAt(3).getTag().equals(jugador) && tablero.getChildAt(4).getTag().equals(jugador) && tablero.getChildAt(5).getTag().equals(jugador)) {
            return jugador.charAt(0);
        }
        if (tablero.getChildAt(6).getTag().equals(jugador) && tablero.getChildAt(7).getTag().equals(jugador) && tablero.getChildAt(8).getTag().equals(jugador)) {
            return jugador.charAt(0);
        }

        if (tablero.getChildAt(0).getTag().equals(jugador) && tablero.getChildAt(3).getTag().equals(jugador) && tablero.getChildAt(6).getTag().equals(jugador)) {
            return jugador.charAt(0);
        }
        if (tablero.getChildAt(1).getTag().equals(jugador) && tablero.getChildAt(4).getTag().equals(jugador) && tablero.getChildAt(7).getTag().equals(jugador)) {
            return jugador.charAt(0);
        }
        if (tablero.getChildAt(2).getTag().equals(jugador) && tablero.getChildAt(5).getTag().equals(jugador) && tablero.getChildAt(8).getTag().equals(jugador)) {
            return jugador.charAt(0);
        }

        if (tablero.getChildAt(0).getTag().equals(jugador) && tablero.getChildAt(4).getTag().equals(jugador) && tablero.getChildAt(8).getTag().equals(jugador)) {
            return jugador.charAt(0);
        }
        if (tablero.getChildAt(2).getTag().equals(jugador) && tablero.getChildAt(4).getTag().equals(jugador) && tablero.getChildAt(6).getTag().equals(jugador)) {
            return jugador.charAt(0);
        }

        for (int i = 0; i < 9; i++) {
            if (tablero.getChildAt(i).getTag().equals("v")) return 'n';
        }


        return 't';
    }
}
