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

    static final int gordo = 4000000;
    static final int segonpremi = 1200000;
    static final int tercerpremi = 500000;
    static final int quartpremi = 200000;
    static final int quintopremi = 60000;
    static final int pedreada = 1000;

    public static int getPremio(NumPremiado num) {
        return num.premio;
    }

    public static void main(String[] args) {
        String[] menu = {"Trobar Premis", "Veure Premis"};
        int numcupon;
        int menusurtida, premio;
        boolean sortir = false;
        NumPremiado[] numeros_premiados = Sorteig(); //ERROR
        while (!sortir) {
            System.out.println("Qué deseas consultar:");
            menusurtida = Menu(menu);
            switch (menusurtida) {
                case 1:
                    numcupon = LlegirInt(scan, "Introduce tu número: ", 0, 99999);
                    premio = ComprobarPremio(numcupon, numeros_premiados);
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

    public static void MostrarPremios(NumPremiado[] num_premi) {
        System.out.println("Gordo de Navidad");
        System.out.println("****************");
        for (int i = 0; i < 10; i++) {
            System.out.println("Número: " + num_premi[i].numero + "Premio: " + num_premi[i].premio);

        }
    }

    //Fem amb el rnd els numeros premiats
    public static int[] CrearBomboPremios() {
        int[] bomboPremios = new int[1807];
        for (int i = 0; i < bomboPremios.length; i++) {
            switch (i) {
                case 1:
                    bomboPremios[i] = gordo;
                    break;
                case 2:
                    bomboPremios[i] = segonpremi;
                    break;
                case 3:
                    bomboPremios[i] = tercerpremi;
                    break;
                case 4:
                case 5:
                    bomboPremios[i] = quartpremi;
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    bomboPremios[i] = quintopremi;
                    break;
                default:
                    bomboPremios[i] = pedreada;
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
        int num_afegir, premi_afegir;
        for (int i = 0; i < numeros_premiats.length; i++) { //Dentro del listado de premios
            num_afegir = bomboNumeros[rnd.nextInt(bomboNumeros.length - i)]; //Escoger número del bombo
            premi_afegir = bomboPremios[rnd.nextInt(bomboPremios.length - i)]; //Escoger premio del bombo
            //Crear premio a partir de el número y premio sacados de los bombos
            numeros_premiats[i] = new NumPremiado();
            numeros_premiats[i].numero = num_afegir;
            numeros_premiats[i].premio = premi_afegir;
            //Eliminar premio del bombo
            bomboPremios[premi_afegir] = 0;
            BubbleSortRemove(bomboPremios);
            //Eliminar número del bombo
            bomboNumeros[num_afegir] = -1;
            BubbleSortRemoveNums(bomboNumeros);
        }
        return numeros_premiats;
    }

    public static int ComprobarPremio(int cupon, NumPremiado[] premiados) {
        int premio = 0;
        boolean premioprincipal = false;
        for (int i = 0; i < premiados.length && premioprincipal == false; i++) {
            if (cupon == premiados[i].numero) {
                premio = premiados[i].premio;
                premioprincipal = true;
            } else if ((cupon + 1 == premiados[i].numero || cupon - 1 == premiados[i].numero) && premiados[i].numero >= tercerpremi) {
                if (premiados[i].premio == gordo) {
                    premio = 20000;
                } else if (premiados[i].premio == segonpremi) {
                    premio = 12500;
                } else if (premiados[i].premio == tercerpremi) {
                    premio = 9600;
                }
            } else if (cupon >= premiados[i].numero && cupon <= premiados[i].numero
                    && premiados[i].premio >= tercerpremi) {

            }

        }
        return premio;
    }

    public static class NumPremiado {

        int numero;
        int premio;
    }
}
