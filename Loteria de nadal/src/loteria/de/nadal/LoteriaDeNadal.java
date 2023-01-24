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

    static final int GORDO = 4000000;
    static final int SEGONPREMI = 1200000;
    static final int TERCERPREMI = 500000;
    static final int QUARTPREMI = 200000;
    static final int QUINTOPREMI = 60000;
    static final int PEDREADA = 1000;
    

    public static int getPremio(NumPremiado num) {
        return num.premio;
    }

    public static void main(String[] args) {
        String[] menu = {"Trobar Premis", "Veure Premis"};
        int numcupon;
        int menusurtida, premio;
        boolean sortir = false;
        NumPremiado[] numeros_premiados = Sorteig();
        while (!sortir) {
            System.out.println("Qué deseas consultar:");
            menusurtida = Menu(menu);
            switch (menusurtida) {
                case 1:
                    numcupon = LlegirInt(scan, "Introduce tu número: ", 0, 99999);
                    premio = ComprobarPremio(numcupon, numeros_premiados);
                    System.out.println(premio);
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
                if (array[j].premio < array[j+1].premio) {
                    aux.numero = array[j].numero;
                    aux.premio = array[j].premio;
                    array[j].numero = array[j+1].numero;
                    array[j].premio = array[j + 1].premio;
                    array[j+1].numero = aux.numero;
                    array[j + 1].premio = aux.premio;
                    haycambios = true;
                }
            }
        }
    }
    public static void MostrarPremios(NumPremiado[] num_premi) {
        BubbleSortPremis(num_premi);
        System.out.println("\nLoteria de Navidad");
        System.out.println("****************");
        for (int i = 0; i < num_premi.length; i++) {
            System.out.println("Número: " + num_premi[i].numero + " Premio: " + num_premi[i].premio);

        }
    }

    //Fem amb el rnd els numeros premiats
    public static int[] CrearBomboPremios() {
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

    public static int[] CrearBomboNumeros() {
        int[] bomboNumeros = new int[100000];
        for (int i = 0; i < bomboNumeros.length; i++) {
            bomboNumeros[i] = i;
        }
        return bomboNumeros;
    }
     
    public static NumPremiado[] Sorteig() { //Cada premio
        Random rnd = new Random();
        int[] bomboPremios = CrearBomboPremios();
        int[] bomboNumeros = CrearBomboNumeros();
        NumPremiado[] numeros_premiats = new NumPremiado[1807];
        int num_afegir, premi_afegir, posN, posP;
        for (int i = 0; i < numeros_premiats.length; i++) { //Dentro del listado de premios
            posN = rnd.nextInt(bomboNumeros.length - i);
            posP = rnd.nextInt(bomboPremios.length - i);
            num_afegir = bomboNumeros[posN]; //Escoger número del bombo
            premi_afegir = bomboPremios[posP]; //Escoger premio del bombo
            //Crear premio a partir de el número y premio sacados de los bombos
            numeros_premiats[i] = new NumPremiado();
            numeros_premiats[i].numero = num_afegir;
            numeros_premiats[i].premio = premi_afegir;
            //Eliminar premio del bombo
            bomboPremios[posP] = 0;
            BubbleSortRemove(bomboPremios);
            //Eliminar número del bombo
            bomboNumeros[posN] = -1;
            BubbleSortRemove(bomboNumeros);
        }
        return numeros_premiats;
    }
    public static int ComprobarPremio(int cupon, NumPremiado[] premiados) {
        int premio = 0;
        boolean ganador = false;
        for (int i = 0; i < premiados.length && ganador == false; i++) {
            if (cupon == premiados[i].numero) {
                premio = premiados[i].premio;
                ganador = true;
            } else if ((cupon == premiados[i].numero+1 || cupon == premiados[i].numero-1) && premiados[i].premio >= TERCERPREMI) {
                if (premiados[i].premio == GORDO) {
                    premio = 20000;
                } else if (premiados[i].premio == SEGONPREMI) {
                    premio = 12500;
                } else if (premiados[i].premio == TERCERPREMI) {
                    premio = 9600;
                }
                ganador = true;
            } else if (cupon >= (premiados[i].numero/100)*100 && cupon <= (premiados[i].numero/100)*100+99
                    && premiados[i].premio >= QUARTPREMI) {
                premio = 1000;
                ganador = true;
            } else if (cupon%100 == premiados[i].numero%100 && premiados[i].premio>=TERCERPREMI) {
                premio = 1000;
                ganador = true;
            } else if (cupon%10 == premiados[i].numero%10 && premiados[i].premio == GORDO) {
                premio = 200;
            }

        }
        return premio;
    }

    public static class NumPremiado {
        int numero;
        int premio;
    }
}
