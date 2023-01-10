/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 * Llibreria d'utilitats
 *
 * @author biel
 * @version version
 *
 */

/**
 * Crea un punt amb les seves coordenades x i y
 */
class Punt {

    float x;
    float y;
}

public class Utils {
// <editor-fold defaultstate="collapsed" desc="Implementació de LlegirInt()">

    private static Scanner scan = null;
    
    /**
     * Aquesta funció llegeix el teclat i comprova que l'introduit sigui un numero
     * @return Retorna el numero enter introduit per teclat
     */
    public static int LlegirInt() {
        int result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirInt(scan);

        return result;
    }

    /**
     * Aquesta funció demana un numero amb un missatge i comprova que sigui un numero
     * @param missatge Demana el numero amb un missatge ontroduit com a parametre
     * @return Retorna el numero enter que ha llegit del teclat
     */
    public static int LlegirInt(String missatge) {
        int result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirInt(scan, missatge);

        return result;
    }

    /**
     * Serveix per a demanr un numero i comprovarlo amb el teu scanner i no el de .utils
     * @param scan Pots posar el teu scanner, i n o utilitzar el de .utils
     * @return retorna el numero llegit del teclat
     */
    public static int LlegirInt(Scanner scan) {
        return LlegirInt(scan, null);
    }

    /**
     * Demana un numero i el comprova, entre els dos numeros de valor min i valor max, amb un missatge
     * @param scan Pots posar el teu scanner
     * @param missatge Escull quin missatge surt per pantalla a l'hora de demanar el numero
     * @param valorMin El valor minim acceptable per el numero a retornar
     * @param valorMax El valor maxim acceptable per el numero a retornar
     * @return Retorna un enter llegit del teclat, que estigui entre valorMin i valorMax
     */
    public static int LlegirInt(Scanner scan, String missatge, int valorMin, int valorMax) {
        int result = 0;
        do {
            result = LlegirInt(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    /**
     * Demana un Integer amb un Missatge i el nostre escaner
     * @param scan Pot ser el nostre escanerr si volem
     * @param missatge El missatge que volem que surti per pamntalla a l'hora de demanar el numero
     * @return Retorna el integer llegit del teclat
     */
    public static int LlegirInt(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        int result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextInt();
            if (dadesCorrectes) {
                result = scan.nextInt();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Implementació de LlegirFloat()">
    /**
     * LLegeix un float i el comprova abans de retornar-lo a
     * @return Retorna un valor float 
     */
    public static float LlegirFloat() {
        float result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirFloat(scan);

        return result;
    }

    /**
     * Serveix per a demanar un float amb un missatge que passarem com a parametre
     * @param missatge POdem demanar un numero amb aquest missatge com a parametre
     * @return Retorna el float llegit per el teclat
     */
    public static float LlegirFloat(String missatge) {
        float result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirFloat(scan, missatge);

        return result;
    }

    /**
     * Serveix per a llegir un float amb el nostre scan
     * @param scan Serveix per a posar un scanner que no sigui el nostre
     * @return Retorna el float llegit per el teclat
     */
    public static float LlegirFloat(Scanner scan) {
        return LlegirFloat(scan, null);
    }

    /**
     * Serveix per a llegir un float amb un missatge, i que estigui entre un minim i un maxim
     * @param scan Podem posar el nostre scanner
     * @param missatge Podem dir el missatge que volem que imprimeixi el programa
     * @param valorMin Diem el valor minim del numero a introduir
     * @param valorMax Diem el valor maxim del numero a introduir
     * @return retorna un float introduit per el teclat
     */
    public static float LlegirFloat(Scanner scan, String missatge, float valorMin, float valorMax) {
        float result = 0;
        do {
            result = LlegirFloat(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    /**
     * Serveix per a demanr un numero amb el nostre escaner i un missatge
     * @param scan Podem dir el nostre escaner
     * @param missatge Podem dirt un missatge per a que imprimeixi abans de demanr el numero
     * @return Retorna unn float introduit al teclat
     */
    public static float LlegirFloat(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        float result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextFloat();
            if (dadesCorrectes) {
                result = scan.nextFloat();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }
    // </editor-fold>
    
    /**
     * LLegeix un double i el comprova abans de retornar-lo a
     * @return Retorna un valor double 
     */
    public static double LlegirDouble() {
        double result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirDouble(scan);

        return result;
    }

    /**
     * Serveix per a demanar un double amb un missatge que passarem com a parametre
     * @param missatge POdem demanar un numero amb aquest missatge com a parametre
     * @return Retorna el double llegit per el teclat
     */
    public static double LlegirDouble(String missatge) {
        double result;

        if (scan == null) {
            scan = new Scanner(System.in);
        }
        result = LlegirDouble(scan, missatge);

        return result;
    }

    /**
     * Serveix per a llegir un double amb el nostre scan
     * @param scan Serveix per a posar un scanner que no sigui el nostre
     * @return Retorna el double llegit per el teclat
     */
    public static double LlegirDouble(Scanner scan) {
        return LlegirDouble(scan, null);
    }

    /**
     * Serveix per a llegir un double amb un missatge, i que estigui entre un minim i un maxim
     * @param scan Podem posar el nostre scanner
     * @param missatge Podem dir el missatge que volem que imprimeixi el programa
     * @param valorMin Diem el valor minim del numero a introduir
     * @param valorMax Diem el valor maxim del numero a introduir
     * @return retorna un double introduit per el teclat
     */
    public static double LlegirDouble(Scanner scan, String missatge, float valorMin, float valorMax) {
        double result = 0;
        do {
            result = LlegirDouble(scan, missatge);
        } while (result < valorMin || result > valorMax);

        return result;
    }

    /**
     * Serveix per a demanr un numero amb el nostre escaner i un missatge
     * @param scan Podem dir el nostre escaner
     * @param missatge Podem dirt un missatge per a que imprimeixi abans de demanr el numero
     * @return Retorna unn double introduit al teclat
     */
    public static double LlegirDouble(Scanner scan, String missatge) {
        boolean dadesCorrectes;
        double result = 0;
        do {
            if (missatge != null) {
                System.out.print(missatge);
            }
            dadesCorrectes = scan.hasNextDouble();
            if (dadesCorrectes) {
                result = scan.nextDouble();
            } else if (scan.hasNext()) {
                scan.nextLine();
            }
        } while (!dadesCorrectes);

        return result;
    }

    /**
     * Serveix per a Imprimir un menú i recollir la selecció donant-li un array amb les opcions del menú
     * @param array_opcions Demana un array String[] per a imprimir les opcions del menu
     * @return Retorna un numero enter depenent de la entrada seleccionada
     */
    public static int Menu(String[] array_opcions) {
        int resultat;

        for (int i = 0; i < array_opcions.length; i++) {
            System.out.println(i + 1 + ") " + array_opcions[i]);
        }
        System.out.println(array_opcions.length + 1 + ") Sortir");
        resultat = LlegirInt(scan, "Opció seleccionada: ", 1, array_opcions.length);

        return resultat;
    }

    /**
     * Serveix per a endreçar un array de int de mes petit a més gran
     * @param array_int Hem de posar el array int[]
     * @param izq Diem per quin index ha de començar a endreçar l'array, sol ser zero
     * @param der Diem l'ultim index que ha de tenir en compte per a endreçar, sol ser array.length -1
     */
    public static void QuicksortInt(int[] array_int, int izq, int der) {

        int pivote = array_int[izq];
        int i = izq;
        int j = der;
        int aux;

        while (i < j) {
            while (array_int[i] <= pivote && i < j) {
                i++;
            }
            while (array_int[j] > pivote) {
                j--;
            }
            if (i < j) {
                aux = array_int[i];
                array_int[i] = array_int[j];
                array_int[j] = aux;
            }
        }

        array_int[izq] = array_int[j];
        array_int[j] = pivote;

        if (izq < j - 1) {
            QuicksortInt(array_int, izq, j - 1);
        }
        if (j + 1 < der) {
            QuicksortInt(array_int, j + 1, der);
        }
    }
    
    public static float mitja_vector (float[] v){
        //Recorrem el vector per anar sumant els elements
        float suma_elements = 0;
        for (int i=0;i<=v.length-1;i++){
            suma_elements = suma_elements + v[i];
        }
        //Calculem la mitja dividint la suma_elements entre la longitud del vector
        float mitja = suma_elements/v.length;
        //Retornem
        return mitja;
    }
    
    /**
     * Fa la suma d'un vector d'enters
     * @param vector Vector d'enters
     * @return Retorna la suma dels elements del vector
     */
    public static int suma_elements_vector (int[] vector){
         //Recorrem el vector per anar sumant els elements
        int suma_elements = 0;
        for (int i=0;i<=vector.length-1;i++){
            suma_elements = suma_elements + vector[i];
        }
        //Retornem
        return suma_elements;
    }
    
    /**
     * Dona un punt resultant de sumar un punt més un vector
     * @param punt Punt
     * @param vector Vector 
     * @return Retorna el punt resultant
     */
    public static Punt suma_punt_mes_vector (Punt punt, Punt vector){
        Punt punt_resultat;
        punt_resultat = new Punt();
        punt_resultat.x = punt.x + vector.x;
        punt_resultat.y = punt.y + vector.y;
        
        return punt_resultat;
    }
    
    /**
     * Dona un nou vector resultant de multiplicar un vector per un escalar
     * @param vector Vector
     * @param factor_escala Escalar
     * @return Retorna el vector resultant
     */
    public static Punt escalar_per_vector (Punt vector, float factor_escala){
        Punt punt_resultat;
        punt_resultat = new Punt();
        punt_resultat.x = factor_escala*vector.x;
        punt_resultat.y = factor_escala*vector.y;
        
        return punt_resultat;
    }
    
    /**
     * Comprova si dos punts són iguals
     * @param punt1 Primer punt
     * @param punt2 Segon punt
     * @return Retorna un boolean que és true si els punts són iguals i false si els punts són diferents
     */
    public static boolean comparacio_punts (Punt punt1, Punt punt2){
        if (punt1.x==punt2.x && punt1.y==punt2.y){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Valida una contrasenya segons les següents condicions:
     * -Mínim 8 caràcters
     * -Màxim 15 caràcters 
     * -Ha d'incloure almenys una majúscula, una minúscula, un número i un símbol 
     * -No pot ser cap dels inclosos a la següent llista:
     *      Admin_00, rooT12AaBbCc!, super_2000, #321Cba# 
     * -No pot tenir cap seqüència de 3 o més caràcters consecutius del tipus 123, abc, ...
     * @param pwd La contrasenya o password
     * @return Retorna un boolean que es true si la contrasenya és vàlida i false si no és vàlida
     */
    public boolean IsValid(String pwd)
   {
       boolean result = true;
       
       /**
        * Aquí cal desenvolupar la funció
        */
       
       //CONSTANTS 
       final int LONGITUD_MINIMA_PWD = 8;
       final int LONGITUD_MAXIMA_PWD = 15;
       final String ARRAY_PWD_PROHIBITS [] = {"Admin_00","rooT12AaBbCc!","super_2000","#321Cba#"};
       final int INDEX_2 = 2; //El número 2 dins dels índexos que utilitzem per comprovar les seqüències de tres o més caràcters consecutius
      
       //Comprovem que el password té entre 8 i 15 caràcters
       if (pwd.length()<LONGITUD_MINIMA_PWD || LONGITUD_MAXIMA_PWD<pwd.length()){
           result = false;
       }
       
       /*Comprovem que el password contingui almenys una majúscula, una minúscula, un número i un símbol. Ho fem recorrent el
       password i utilitzant comptadors*/
       
       //Recorrem el String comprovant si és majúscula o minúscula i ho sumem a un comptador
       int comptador_minuscules = 0;
       int comptador_majuscules = 0;
       int comptador_numeros = 0;
       int comptador_simbols = 0;
       for (int i=0;i<=pwd.length()-1;i++){
           //MAJÚSCULA I MINÚSCULA
           if ('a'<=pwd.charAt(i) && pwd.charAt(i)<='z'){
               comptador_minuscules++;
           }
           else if ('A'<=pwd.charAt(i) && pwd.charAt(i)<='Z'){
               comptador_majuscules++;
           }
           //NÚMERO
           else if (Character.isDigit(pwd.charAt(i))){
               comptador_numeros++;
           }
           //SÍMBOL
           else{
               comptador_simbols++;
           }
           //SEQÜÈNCIA TRES CARÀCTERS CONSECUTIUS
           if (i>=INDEX_2){
               //Passem tots els caràcters a integers
               int caracter_dos_indexos_enrere = pwd.charAt(i - 2);
               int caracter_un_index_enrere = pwd.charAt(i - 1);
               int caracter_actual = pwd.charAt(i);
               //Comprovem si són una seqüència de caràcters consecutius
               if (caracter_dos_indexos_enrere == caracter_actual - 2 && caracter_un_index_enrere == caracter_actual - 1) {
                   result = false;
               }
           }
       }
       //Si algun dels comptadors és igual a 0, el password no és vàlid
       if (comptador_minuscules==0 || comptador_majuscules==0){
           result = false;
       }
       //Si comptador_numeros és igual a 0, el password no és vàlid
       if (comptador_numeros==0){
           result = false;
       }
       //Si comptador_simbols és igual a 0, el password no és vàlid
       if (comptador_simbols==0){
           result = false;
       }
       
       //Comprovem que el password no pertany a la llista de passwords prohibits recorrent el ARRAY_PWD_PROHIBITS
       for (int i=0;i<=ARRAY_PWD_PROHIBITS.length-1;i++){
           if (ARRAY_PWD_PROHIBITS[i].equals(pwd)){
               result = false;
           }
       }
        
       return result;
   }
}
