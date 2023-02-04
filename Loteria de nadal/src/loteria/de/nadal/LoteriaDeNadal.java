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
    static final int GORDO = 4000000;
    static final int SEGONPREMI = 1200000;
    static final int TERCERPREMI = 500000;
    static final int QUARTPREMI = 200000;
    static final int QUINTOPREMI = 60000;
    static final int PEDREADA = 1000;
    static final int QUANTITATPREMIS = 1807;
    static final int REINTEGRO = 200;
    static int quantGordo = 1;
    static int quantSegund = 1;
    static int quantTercer = 1;
    static int quantCuart = 2;
    static int quantQuint = 8;
    static int quantPremis = quantGordo + quantSegund + quantTercer + quantCuart + quantQuint;
    static String rojo = "\033[31m";
    static String verde = "\033[32m";
    static String celeste = "\033[36m";
    static String reset = "\u001B[0m";

    /**
     * Variable per al nom del premi 1 = Primer premi 2 = Segon premi 3 = Tercer
     * premi 4 = 4t premi 5 = Cinqué premi 6 = Reintegro 7 = 2últimas 8 =
     * Centena 9 = Aprox Gordo 10 = Aprox 2ndo 11 = Aprox 3r 12 = Pedrea
     *
     *
     * Pedrea = 7
     */
    static int nompremi = 0;

    public static void main(String[] args) {
        //Creamos la constante para el numero maximo de billete
        final int MAXIMBITLLETS = 99999;
        //Creamos un string con las opciones del menu
        String[] menu = {"Revisar premio de cupón", "Consultar todos los premios"};
        //Creeamos los integers del premio, numero de cupon, i la opcion seleccionada
        int numcupon;
        int menusurtida, premio;
        //Creeamos la variable para salir del programa
        boolean sortir = false;
        //Creeamos el resultado del sorteo en una variable de tipo NumPremiado llamando a la funcion Sorteo()
        NumPremiado[] numeros_premiados = Sorteo();
        BubbleSortPremis(numeros_premiados);
        //Creeamos un bucle para ejecutar el programa hasta que el usuario quiera salir
        while (!sortir) {
            //Imprimimos el menu
            System.out.println("Qué deseas consultar:");
            menusurtida = Menu(menu);

            //Hacemos lo que pide dependiendo de la entrada del usuario
            switch (menusurtida) {
                //Comprovamos un numero
                case 1:
                    //Leemos el numero del cupon
                    numcupon = LlegirInt(scan, "Introduce tu número: ", 0, MAXIMBITLLETS);
                    //Comprovamos cuanto ha ganado
                    premio = ComprobarPremio(numcupon, numeros_premiados);
                    //Imprimimos el resultado
                    String nomDelPremi = darNombre(nompremi);
                    nompremi = 0;
                    System.out.println(verde + "Has ganado " + nomDelPremi + " con una cantidad de " + premio / 10 + " al décimo" + reset);
                    break;
                //Mostramos los premios y su numero correspondiente
                case 2:
                    System.out.println(verde + "\nLoteria de Navidad" + reset);
                    System.out.println(celeste + "****************" + reset);
                    MostrarPremios(numeros_premiados, "Numero", "Premio");
                    break;
                //Salimos del programa
                case 3:
                    sortir = true;
                    System.out.println(verde + "MUCHAS GRACIAS POR PARTICIPAR" + reset);
                    break;
            }
        }
    }

// <editor-fold defaultstate="collapsed" desc="Mostrar Premios">    
    /**
     * Función para ordenar el listado de número premiados en base a su valor
     * monetario (metodo de la burbuja).
     *
     * @param array listado de premios
     */
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

    /**
     * Función para mostrar el listado completo de números premiados
     *
     * @param num_premi array de número premiados
     */
    public static void MostrarPremios(NumPremiado[] num_premi, String paraulaNumero, String paraulaPremio) {
        String numeroS = "";
        for (int i = 0; i < num_premi.length; i++) {
            numeroS = "" + num_premi[i].numero;
            //Añadir ceros a los números de menos de 5 cifras
            while (numeroS.length() < 5) {
                numeroS = "0" + numeroS;
            }
            System.out.println(rojo + (i + 1) + ". " + reset + celeste + paraulaNumero + ": " + numeroS + reset + " " + verde + paraulaPremio + ": " + num_premi[i].premio + reset);

        }
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Sorteo Premios">
    /**
     * Generación de un bombo de premios monetarios
     *
     * @return bombo de premios
     */
    public static int[] CrearBomboPremios() {

        int[] bomboPremios = new int[QUANTITATPREMIS];
        for (int i = 0; i < bomboPremios.length; i++) {
            if (i < quantPremis) {
                for (int j = 0; j < quantGordo; j++) {
                    bomboPremios[i] = GORDO;
                    i++;
                }
                for (int j = 0; j < quantSegund; j++) {
                    bomboPremios[i] = SEGONPREMI;
                    i++;
                }
                for (int j = 0; j < quantTercer; j++) {
                    bomboPremios[i] = TERCERPREMI;
                    i++;
                }
                for (int j = 0; j < quantCuart; j++) {
                    bomboPremios[i] = QUARTPREMI;
                    i++;
                }
                for (int j = 0; j < quantQuint; j++) {
                    bomboPremios[i] = QUINTOPREMI;

                    i++;
                }
            } else {
                bomboPremios[i] = PEDREADA;
            }
        }
        return bomboPremios;
    }

    /**
     * Función para desplazar premios sacados del bombo, al final del array
     *
     * @param position posición del premio sacado
     * @param bomboPremios bombo de premios
     */
    public static void ActualizarBomboPremios(int position, int[] bomboPremios) {
        bomboPremios[position] = 0;
        BubbleSortRemove(bomboPremios);
    }

    /**
     * Función para generar el sorteo de los "1807" premios
     *
     * @return array de premios compuestos por el número del premio y el premio
     * monetario
     */
    public static NumPremiado[] Sorteo() { //Cada premio
        final int PEDREA = 1000;
        final int NUMPREMIS = 1807;
        int[] bomboPremios = CrearBomboPremios();
        NumPremiado[] numeros_premiats = new NumPremiado[NUMPREMIS];
        int num_afegir = 0;
        int premi_afegir, posP;
        for (int i = 0; i < numeros_premiats.length; i++) { //Crear todos los premios
            boolean repeatnum = true;
            while (repeatnum) {
                num_afegir = rnd.nextInt(100000); //Generar número aleatorio
                repeatnum = false;
                for (int j = 0; j < i && !repeatnum; j++) {//Comprobar que el número generado aleatoriamente no haya salido anteriormente
                    if (num_afegir == numeros_premiats[j].numero) {
                        repeatnum = true;
                    }
                }
            }

            //Escoger número del bombo
            posP = rnd.nextInt(bomboPremios.length - i);
            premi_afegir = bomboPremios[posP]; //Escoger premio del bombo
            if (premi_afegir == 0) {
                premi_afegir = PEDREA;
            }
            //Crear premio a partir de el número y premio sacados de los bombos
            numeros_premiats[i] = new NumPremiado();
            numeros_premiats[i].numero = num_afegir;
            numeros_premiats[i].premio = premi_afegir;
            //Eliminar premio monetario del bombo
            ActualizarBomboPremios(posP, bomboPremios);

        }
        return numeros_premiats;
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Comprobar Premios">
    /**
     * Esta función sirve para comparar el numero en un switch i dependiendo
     * cada caso devolver un string distinto
     *
     * @param nombre necesita la variable global nompremi para saber que premio
     * ha tocado
     * @return Devuelve un string del premio que nos ha tocado
     */
    public static String darNombre(int nombre) {
        String resultat = "";
        switch (nombre) {
            case 1:
                resultat = "el gordo";
                break;
            case 2:
                resultat = "el segundo premio";
                break;
            case 3:
                resultat = "el tercer premio";
                break;
            case 4:
                resultat = "el cuarto premio";
                break;
            case 5:
                resultat = "el quinto premio";
                break;
            case 6:
                resultat = "el reintegro";
                break;
            case 7:
                resultat = "el premio a las 2 últimas cifras";
                break;
            case 8:
                resultat = "el premio a la centena del gordo";
                break;
            case 9:
                resultat = "el premio a la aproximación del gordo";
                break;
            case 10:
                resultat = "el premio a la aproximación del 2ndo premio";
                break;
            case 11:
                resultat = "el premio a la aproximación del 3er premio";
                break;
            case 12:
                resultat = "la pedrea";
                break;
            default:
                resultat = "un abrazo";
                break;
        }
        return resultat;
    }

    /**
     * Esta función comprueba si el cupón ha sido premiado, primero de todo por
     * premio mayor, seguidamente por aproximación, entonces por centena, si
     * todavía no ha sido premiado mira las 2 útlimas cifras, antes de acabar la
     * pedrea, y por último el reintegro
     *
     * @param cupon Necesita el numero de cupon a comprovar
     * @param premiados Necesita el array de los numeros premiados ya ordenado
     * @return Devuelve un integer con la cantidad que ha ganado el cupona
     * comprovar
     */
    public static int ComprobarPremio(int cupon, NumPremiado[] premiados) {
        //Creamos la variable premio
        int premio = 0;

        //Miramos si ha tocado algun premio principal
        premio = ComprobarBombo(cupon, premiados);

        //Si no ha tocado miramos la aproximaión
        if (premio == 0) {
            premio = ComprobarAproximacion(cupon, premiados);
        }
        //Si sigue sin tocar nada miramos la centena
        if (premio == 0) {
            premio = ComprobarCentena(cupon, premiados);
        }
        //Si sigue sin tocar nada miramos las 2 últimas cifras del cupón
        if (premio == 0) {
            premio = ComprobarUltimas(cupon, premiados);
        }
        //Si sigue sin tocar nada miramos la pedrea
        if (premio == 0) {
            premio = ComprobarBombo2(cupon, premiados);
        }
        //Por último miramos si se lleva el reintegro
        premio += ComprobarReintegro(cupon, premiados, premio);

        //Devolvemos el valor premiado
        return premio;
    }

    /**
     * Comprueba si el cupón tiene la misma última cifra del gordo, sin tener en
     * cuenta el gordo
     *
     * @param cupon Integer con el numero a comprobar
     * @param premiados Array con los numeros premiados ya ordenado de mayor a
     * menor
     * @param premioactual El premio aumulado hasta ahora por dicho cupón
     * @return Devuelve el valor del reintegro, o 0, dependiendo de si el cupon
     * és premiado o no
     */
    public static int ComprobarReintegro(int cupon, NumPremiado[] premiados, int premioactual) {
        //Creeamos la constante con el valor del premio
        //Creamos la variable premio
        int premio = 0;
        //Miramos si el útlimo numero coincide con el último numero del gordo
        if (cupon % 10 == premiados[0].numero % 10) {
            //Miramos que el numero no sea el gordo
            if (cupon != premiados[0].numero) {
                //Assignamos el valor de la constante a premio
                premio = REINTEGRO;
                //Si todavía no habia ningún premio ponemos la vairable nompremi con reintegro
                if (premioactual == 0) {
                    nompremi = 6;
                }
            }
        }
        //Devlvemos la variable premio
        return premio;
    }

    /**
     * Esta función comprueba las 2 útlimas cifras del cupón con uno de los 3
     * premios mayores
     *
     * @param cupon Numero del premio a comprobar
     * @param premiados Array de los numeros premiados ordenado de mayor a menor
     * @return Devuelve un integer con el premio que le toca al cupón
     */
    public static int ComprobarUltimas(int cupon, NumPremiado[] premiados) {
        //Creamos la constante del premio a las 2 útlimas cifras
        final int PREMI2ULTIMAS = 1000;

        //Cramos la variablre premio
        int premio = 0;
        //Creamos una variable con las 2 últimas cifras de cupón
        int ultimas2 = cupon % 100;
        //Miramos si coinciden las 2 últimas cifras con las 2 ultimas de un premio mayor
        for (int i = 0; i <= 2; i++) {
            if (ultimas2 == premiados[i].numero % 100) {
                premio = PREMI2ULTIMAS;
                nompremi = 7;
            }
        }

        //Devolvemos el premio
        return premio;
    }

    /**
     * Esta función comprueva si el cupón està en la misma centena que algún
     * premio mayor
     *
     * @param cupon Numero a comprovar
     * @param premiados Array de los numeros premiados
     * @return Devuelve el premio correspondiente al cupon
     */
    public static int ComprobarCentena(int cupon, NumPremiado[] premiados) {
        //Creamos la constante del valor del premio
        final int PREMICENTENA = 1000;
        //Creamos la variable a devolver
        int premio = 0;
        //Comprovamos si la centena correspo0nde a alguna de ls 4 premios mayores
        for (int i = 0; i <= 4; i++) {
            if (cupon >= (premiados[i].numero / 100) * 100 && cupon <= (premiados[i].numero / 100) * 100) {
                premio = PREMICENTENA;
                nompremi = 8;
            }
        }

        //Devolvemos el valor de premio
        return premio;
    }

    /**
     * Esta función comprueva si el numero del cupon és un numero inferior o
     * superior a uno de los 3 primeros premios
     *
     * @param cupon Numero a comprobar
     * @param premiados Array con los numeros premiados ordenado de mayor a
     * menor
     * @return Devuelve el valor del premio correspondiente al cupon
     */
    public static int ComprobarAproximacion(int cupon, NumPremiado[] premiados) {
        //Creamos las constantes con los valores de los premios
        final int PREMIAPROX3 = 9600;
        final int PREMIAPROX2 = 12500;
        final int PREMIAPROX1 = 20000;

        //Creamos la avriable a devolver
        int premio = 0;
        //Miramos si el cupon se aproxima al primer premio
        if (cupon == premiados[0].premio - 1 || cupon == premiados[0].premio + 1) {
            premio = PREMIAPROX1;
            nompremi = 9;
            //Miramos si el cupon se aproxima al 2do premio
        } else if (cupon == premiados[1].premio - 1 || cupon == premiados[1].premio + 1) {
            premio = PREMIAPROX2;
            nompremi = 10;
            //Miramos si el cupon se aproxima al 3er premio
        } else if (cupon == premiados[2].premio - 1 || cupon == premiados[2].premio + 1) {
            premio = PREMIAPROX3;
            nompremi = 11;
        }
        //Devolvemos el valor de premio
        return premio;
    }

    /**
     * Esta función comprueba si el cupón corresponde a alguno de los premios
     * del 1ro a los 5tos
     *
     * @param cupon Numero a comprobar
     * @param premiados Array de los numeros premiados ordenado de mayor a menor
     * @return Devuelve el premio correspondiente al cupon
     */
    public static int ComprobarBombo(int cupon, NumPremiado[] premiados) {
        /*Creamos la constante con al cantidad de numeros mayores que hay - 1
        1 primer premio
        1 segundo premio
        1 terceer premio
        2 cuartos premios
        8 quintos premios
         */
        final int TOTALGUANYADORS = 12;
        //Creamos las variables del programa
        boolean ganador = false;
        int premio = 0;

        //Mirmos si el cupon corresponde con alguno de estos premios y le assignamos el premio si és asi
        for (int i = 0; i <= TOTALGUANYADORS && ganador == false; i++) {
            if (cupon == premiados[i].numero) {
                premio = premiados[i].premio;
                ganador = true;
                nompremi = nombrePremiado(premio, premiados);
            }
        }

        //Devolvemos el valor de premio
        return premio;
    }

    /**
     * Esta funión comprueba si el cupón corresponde a la pedrea
     *
     * @param cupon Numero a comprobar
     * @param premiados Array de numeros premiados ordenado de mayor a menor
     * @return Devuelve el premio correspondiente al cupón
     */
    public static int ComprobarBombo2(int cupon, NumPremiado[] premiados) {
        //Creamos la constante de todos los premios mayores que hay - 1
        final int TOTALGUANYADORS = 12;
        //Creamos las variable del programa
        boolean ganador = false;
        int premio = 0;

        //Miramos si el cupon corresponde a la pedrea
        for (int i = TOTALGUANYADORS; i < premiados.length && ganador == false; i++) {
            if (cupon == premiados[i].numero) {
                premio = premiados[i].premio;
                ganador = true;
                nompremi = 12;
            }
        }

        //Devolvemos el premio
        return premio;
    }

    /**
     * Función para informar sobre el tipo de premio que hemos ganado.
     *
     * @param premio dinero ganado
     * @param premiados conjunto de premios
     * @return tipo de premio obtenido
     */
    public static int nombrePremiado(int premio, NumPremiado[] premiados) {
        int resultat = 0;

        if (premio == premiados[0].premio) {
            resultat = 1;
        } else if (premio == premiados[1].premio) {
            resultat = 2;
        } else if (premio == premiados[2].premio) {
            resultat = 3;
        } else if (premio == premiados[3].premio || premio == premiados[4].premio) {
            resultat = 4;
        } else if (premio == premiados[5].premio || premio == premiados[6].premio || premio == premiados[7].premio || premio == premiados[8].premio || premio == premiados[9].premio || premio == premiados[10].premio || premio == premiados[11].premio || premio == premiados[12].premio) {
            resultat = 5;
        }

        return resultat;
    }

    // </editor-fold>
    /**
     * Clase de números premiados
     */
    public static class NumPremiado {

        int numero;
        int premio;
    }

}
