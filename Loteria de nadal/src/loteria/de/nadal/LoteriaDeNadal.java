/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loteria.de.nadal;

import java.util.Random;
import java.util.Scanner;
import static utils.Utils.*;
/**
 *
 * @author ausias
 */
public class LoteriaDeNadal {

    /**
     * @param args the command line arguments
     */
    static Scanner scan = new Scanner (System.in);
    
    public static void main(String[] args) {
        // TODO code application logic here
        int[] numeros_premiats = new int[1807];
        Sorteig(numeros_premiats);
        String[] menu = {"Trobar Premis", "Veure Premis"};
        int numcupon;
        int menusurtida, premio;
        boolean sortir = false;
        
        while (!sortir) {
            menusurtida = Menu(menu);
            switch (menusurtida) {
                case 1:
                    numcupon = LlegirInt(scan,"Introduce tu número: ",0,99999);
                    Comprovar(numcupon, numeros_premiats);
                    break;
                case 2:
                    break;
                case 3:
                    sortir = true;
                    break;
            }
        }
    }

    //Fem amb el rnd els numeros premiats
    public static void Sorteig(int[] npremi) {
        Random rnd = new Random();
        int num_afegir;
        for (int i = 0; i < npremi.length; i++) {
            //Afegirem 0 per a fer-lo de 5 digits???
            num_afegir = rnd.nextInt(100000);
            
        }
    }
    public static void Comprovar (int numcupon,int[] numeros_premiats) {
        for ()
                
        
    }

}
