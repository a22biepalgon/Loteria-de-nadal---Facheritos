/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package loteria.de.nadal;

import java.util.Random;

/**
 *
 * @author ausias
 */
public class LoteriaDeNadal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] numeros_premiats = new int[1807];
        Sorteig(numeros_premiats);
        System.out.println("a");
    }
    
    //Fem amb el rnd els numeros premiats
    public static void Sorteig(int[] npremi){
        Random rnd = new Random();
        for (int i = 0; i < npremi.length; i++){
            int num_afegir  = rnd.nextInt(100000);
            //Afegirem 0 per a fer-lo de 5 digits???
            num_afegir = npremi[i]; 
        }
    }
    
}
