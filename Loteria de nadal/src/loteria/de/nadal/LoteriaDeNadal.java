package loteria.de.nadal;

import java.util.Random;
import java.util.Scanner;
import static utils.Utils.*;

/**
 * Simulación lotería de Navidad y comprovación de premios
 *
 * @author Facheritos
 */
public class LoteriaDeNadal {

    /**
     * @param args the command line arguments
     */
    static Scanner scan = new Scanner(System.in);
    static Random rnd = new Random();

    /**
     * Variable per al nom del premi
     */
    static String nompremi = "";

    public static void main(String[] args) {
        final int MAXIMBITLLETS = 99999;
        String[] menu = {"Revisar premio de cupón", "Consultar todos los premios"};
        int numcupon;
        int menusurtida, premio;
        boolean sortir = false;
        NumPremiado[] numeros_premiados = Sorteig();
        BubbleSortPremis(numeros_premiados);

        while (!sortir) {
            System.out.println("Qué deseas consultar:");
            menusurtida = Menu(menu);
            switch (menusurtida) {
                case 1:
                    numcupon = LlegirInt(scan, "Introduce tu número: ", 0, MAXIMBITLLETS);
                    premio = ComprobarPremio(numcupon, numeros_premiados);
                    String nombrepremio = nompremi;
                    System.out.println("Has ganado " + nombrepremio + " con una cantidad de " + premio);
                    break;
                case 2:
                    MostrarPremios(numeros_premiados);
                    break;
                case 3:
                    sortir = true;
                    break;
            }
        }
    }

    public static void BubbleSortPremis(NumPremiado[] array) {
        boolean haycambios = true;
        for (int i = 0; i < array.length && haycambios == true; i++) {
            haycambios = false;
            NumPremiado aux = new NumPremiado();
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j].premio < array[j + 1].premio) {
                    aux.numero = array[j].numero;
                    aux.premio = array[j].premio;
                    array[j].numero = array[j + 1].numero;
                    array[j].premio = array[j + 1].premio;
                    array[j + 1].numero = aux.numero;
                    array[j + 1].premio = aux.premio;
                    haycambios = true;
                }
            }
        }
    }

    public static void MostrarPremios(NumPremiado[] num_premi) {
        String numeroS = "";
        System.out.println("\nLoteria de Navidad");
        System.out.println("****************");
        for (int i = 0; i < num_premi.length; i++) {
            numeroS = "" + num_premi[i].numero;
            while (numeroS.length() < 5) {
                numeroS = "0" + numeroS;
            }
            System.out.println((i + 1) + ". Número: " + numeroS + " Premio: " + num_premi[i].premio);

        }
    }

    //Fem amb el rnd els numeros premiats
    public static int[] CrearBomboPremios() {
        final int GORDO = 4000000;
        final int SEGONPREMI = 1200000;
        final int TERCERPREMI = 500000;
        final int QUARTPREMI = 200000;
        final int QUINTOPREMI = 60000;
        final int PEDREADA = 1000;

        int[] bomboPremios = new int[1807];
        for (int i = 0; i < bomboPremios.length; i++) {
            switch (i) {
                case 1:
                    bomboPremios[i] = GORDO;
                    break;
                case 2:
                    bomboPremios[i] = SEGONPREMI;
                    break;
                case 3:
                    bomboPremios[i] = TERCERPREMI;
                    break;
                case 4:
                case 5:
                    bomboPremios[i] = QUARTPREMI;
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    bomboPremios[i] = QUINTOPREMI;
                    break;
                default:
                    bomboPremios[i] = PEDREADA;
            }

        }
        return bomboPremios;
    }

    public static void ActualizarBomboPremios(int position, int[] bomboPremios) {
        bomboPremios[position] = 0;
        BubbleSortRemove(bomboPremios);
    }

    public static NumPremiado[] Sorteig() { //Cada premio
        int[] bomboPremios = CrearBomboPremios();
        NumPremiado[] numeros_premiats = new NumPremiado[1807];
        int num_afegir = 0;
        int premi_afegir, posP;
        for (int i = 0; i < numeros_premiats.length; i++) { //Dentro del listado de premios
            boolean repeatnum = true;
            while (repeatnum) {
                num_afegir = rnd.nextInt(100000);
                repeatnum = false;
                for (int j = 0; j < i && !repeatnum; j++) {
                    if (num_afegir == numeros_premiats[j].numero) {
                        repeatnum = true;
                    }
                }
            }

            //Escoger número del bombo
            posP = rnd.nextInt(bomboPremios.length - i);
            premi_afegir = bomboPremios[posP]; //Escoger premio del bombo
            //Crear premio a partir de el número y premio sacados de los bombos
            numeros_premiats[i] = new NumPremiado();
            numeros_premiats[i].numero = num_afegir;
            numeros_premiats[i].premio = premi_afegir;
            //Eliminar premio del bombo
            ActualizarBomboPremios(posP, bomboPremios);

        }
        return numeros_premiats;
    }

    public static int ComprobarPremio(int cupon, NumPremiado[] premiados) {
        int premio = 0;

        premio = ComprobarBombo(cupon, premiados);

        if (premio == 0) {
            premio = ComprobarAproximacion(cupon, premiados);

        }
        if (premio == 0) {
            premio = ComprobarCentena(cupon, premiados);
        }
        if (premio == 0) {
            premio = ComprobarUltimas(cupon, premiados);
        }
        if (premio == 0) {
            premio = ComprobarReintegro(cupon, premiados);
        }

        return premio;
    }

    public static int ComprobarReintegro(int cupon, NumPremiado[] premiados) {
        final int PREMIREINTEGRO = 200;
        int premio = 0;
        if (cupon % 10 == premiados[1].numero % 10) {
            premio = PREMIREINTEGRO;
            nompremi = "el reintegro";
        }
        return premio;
    }

    public static int ComprobarUltimas(int cupon, NumPremiado[] premiados) {
        final int PREMI2ULTIM = 1000;

        int premio = 0;
        int ultimas2 = cupon % 100;
        for (int i = 0; i <= 2; i++) {
            if (ultimas2 == premiados[i].numero % 100) {
                premio = PREMI2ULTIM;
                nompremi = "las 2 últimas cifras del grodo";
            }
        }

        return premio;
    }

    public static int ComprobarCentena(int cupon, NumPremiado[] premiados) {
        final int PREMICENTENA = 1000;

        int premio = 0;
        for (int i = 0; i <= 4; i++) {
            if (cupon >= (premiados[i].numero / 100) * 100 && cupon <= (premiados[i].numero / 100) * 100) {
                premio = PREMICENTENA;
                nompremi = "el premio a la centena";
            }
        }

        return premio;
    }

    public static int ComprobarAproximacion(int cupon, NumPremiado[] premiados) {
        final int PREMIAPROX3 = 9600;
        final int PREMIAPROX2 = 12500;
        final int PREMIAPROX1 = 20000;
        int premio = 0;
        if (cupon == premiados[0].premio - 1 || cupon == premiados[0].premio + 1) {
            premio = PREMIAPROX1;
            nompremi = "la aproximación al gordo";
        } else if (cupon == premiados[1].premio - 1 || cupon == premiados[1].premio + 1) {
            premio = PREMIAPROX2;
            nompremi = "la aproximación al 2do premio";
        } else if (cupon == premiados[2].premio - 1 || cupon == premiados[2].premio + 1) {
            premio = PREMIAPROX3;
            nompremi = "la aproximación al 3er premio";
        }
        return premio;
    }

    public static int ComprobarBombo(int cupon, NumPremiado[] premiados) {
        boolean ganador = false;
        int premio = 0;

        for (int i = 0; i < premiados.length && ganador == false; i++) {
            if (cupon == premiados[i].numero) {
                premio = premiados[i].premio;
                ganador = true;
                nompremi = nombrePremiado(premio, premiados);
            }
        }

        return premio;
    }
    public static String nombrePremiado(int premio, NumPremiado[] premiados){
        String resultat = "";
        
        if (premio == premiados[0].premio){
                    resultat = "el gordo";
                }else if (premio == premiados[1].premio){
                    resultat = "el segundo premio";
                }else if (premio == premiados[2].premio){
                    resultat = "el tercer premio";
                }else if (premio == premiados[3].premio || premio == premiados[4].premio){
                    resultat = "el cuarto premio";
                }else if (premio == premiados[5].premio || premio == premiados[6].premio || premio == premiados[7].premio || premio == premiados[8].premio || premio == premiados[9].premio || premio == premiados[10].premio || premio == premiados[11].premio || premio == premiados[12].premio ){
                    resultat = "el quinto premio";
                }
        
        return resultat;
    }

    public static class NumPremiado {

        int numero;
        int premio;
    }

    public static class Numeros {

        int numero;
        int contadorDecimos = 0;

    }
}
