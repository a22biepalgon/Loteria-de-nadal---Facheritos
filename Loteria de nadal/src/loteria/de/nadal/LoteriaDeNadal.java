
package loteria.de.nadal;

import java.util.Random;
import java.util.Scanner;
import static utils.Utils.*;

/**
 *Simulación lotería de Navidad y comprovación de premios
 * @author Facheritos
 */
public class LoteriaDeNadal {

    /**
     * @param args the command line arguments
     */
    static Scanner scan = new Scanner(System.in);

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
            System.out.println("Número: "+num_premi[i].numero+"Premio: "+num_premi[i].premio);
            
        }
    }
    
    //Fem amb el rnd els numeros premiats
    public static int[] CrearBomboPremios () {
        int[]bomboPremios = new int[1807];
        for (int i = 0; i < bomboPremios.length; i++) {
            switch (i) {
                case 1:
                    bomboPremios[i] = 4000000;
                    break;
                case 2:
                    bomboPremios[i] = 1200000;
                    break;
                case 3:
                    bomboPremios[i] = 500000;
                    break;
                case 4:
                case 5:
                    bomboPremios[i] = 200000;
                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                    bomboPremios[i] = 60000;
                    break;
                default:
                    bomboPremios[i] = 1000;
            }
            
        }
        return bomboPremios;
    }
    public static int[] CrearBomboNumeros () {
        int[] bomboNumeros = new int[100000];
        for (int i = 0; i < bomboNumeros.length; i++) {
            bomboNumeros[i] = i;
        }
        return bomboNumeros;
    }
    
    public static NumPremiado[] Sorteig() { //Cada premio
        Random rnd = new Random();
        int[]bomboPremios = CrearBomboPremios();
        int[]bomboNumeros = CrearBomboNumeros();
        NumPremiado[] numeros_premiats = new NumPremiado[1807];
        int num_afegir, premi_afegir;
        for (int i = 0; i < numeros_premiats.length; i++) { //Dentro del listado de premios
            num_afegir = bomboNumeros[rnd.nextInt(bomboNumeros.length-i)]; //Escoger número del bombo
            premi_afegir = bomboPremios[rnd.nextInt(bomboPremios.length-i)]; //Escoger premio del bombo
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

    public static class NumPremiado {
        int numero;
        int premio;
    }
}
