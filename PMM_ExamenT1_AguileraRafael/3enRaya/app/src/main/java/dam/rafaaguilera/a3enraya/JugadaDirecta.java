package dam.rafaaguilera.a3enraya;


import android.widget.GridLayout;

public class JugadaDirecta implements AndroidJugada {

    private int bloquear[][] = {
            {1,3,4},            //0
            {0,3,4,5,2},        //1
            {1,4,5},            //2
            {0,1,4,7,6},        //3
            {0,1,2,3,5,6,7,8},  //4
            {2,1,4,7,8},        //5
            {3,4,7},            //6
            {6,3,4,5,8},        //7
            {4,5,7}
    };

    @Override
    public int play(GridLayout tablero) {

        if (tablero.getChildAt(4).isClickable())
            return 4;
        else
        {
            for (int i = 0; i < 9; i++) {
                if (tablero.getChildAt(i).getTag().equals("j")) {
                    for(int j = 0; j<bloquear[i].length;j++){
                        if (tablero.getChildAt(bloquear[i][j]).isClickable())
                            return bloquear[i][j];
                    }

                }
            }


        }

        return -1;
    }
}
