package loteria.de.nadal;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;
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
    //Definimosn los objetos scanner y random
    static Scanner scan = new Scanner(System.in);
    static Random rnd = new Random();
    //Definimos las constantes de los premios
    static final int GORDO = 4000000;
    static final int SEGONPREMI = 1200000;
    static final int TERCERPREMI = 500000;
    static final int QUARTPREMI = 200000;
    static final int QUINTOPREMI = 60000;
    static final int PEDREADA = 1000;
    static final int QUANTITATPREMIS = 1807;
    static final int REINTEGRO = 200;
    static final int PREMICENTENA = 1000;
    static final int PREMI2ULTIMAS = 1000;
    static final int PREMIAPROX3 = 9600;
    static final int PREMIAPROX2 = 12500;
    static final int PREMIAPROX1 = 20000;
    static final int MAXIMBITLLETS = 99999;
    //Definimos las constantes de cuantos premios hay
    static int quantGordo = 1;
    static int quantSegund = 1;
    static int quantTercer = 1;
    static int quantCuart = 2;
    static int quantQuint = 8;
    static int quantPremis = quantGordo + quantSegund + quantTercer + quantCuart + quantQuint;
    public static int arrayByteLength = 8 * QUANTITATPREMIS;
    //Definición de nombres para los ficheros binarios y de texto
    public static final String NOM_FTX_LOTERIAS_BIN = "./loterias.dat";
    public static final String NOM_FTX_LOTERIASINDEX_BIN = "./loteriasindx.dat";
    public static final String NOM_FTX_COLLAS = "./colles.dat";
    public static final String NOM_FTX_USR = ".usuariscolles.dat";
    //Definimos los colores
    static String rojo = "\033[31m";
    static String verde = "\033[32m";
    static String celeste = "\033[36m";
    static String reset = "\u001B[0m";

    //Numeros de linias del fichero por frase
    static int PRIMERAOPCIONMENU = 1;
    static int SEGUNDAOPCIONMENU = 2;
    static int CREARCOLLA = 3;
    static int TERCERALINIA = 4;
    static int CUARTALINIA = 5;
    static int QUINTALINIA = 6;
    static int SEXTALINIA = 7;
    static int SEPTIMALINIA = 8;
    static int OCTAVALINIA = 9;
    static int NOVENALINIA = 10;
    static int DECIMALINIA = 11;
    static int ONCEAVALINIA = 12;

    /**
     * Variable per al nom del premi 1 = Primer premi 2 = Segon premi 3 = Tercer
     * premi 4 = 4t premi 5 = Cinqué premi 6 = Reintegro 7 = 2últimas 8 =
     * Centena 9 = Aprox Gordo 10 = Aprox 2ndo 11 = Aprox 3r 12 = Pedrea
     */
    static int nompremi = 0;

    public static void main(String[] args) throws IOException {
        String idioma = EscogerIdioma();
        //Creamos un string con las opciones del menu
        String[] loteriaTipo = {"Nuevo sorteo", "Sorteo anterior"};
        String[] menu = {RetornarLinia(idioma, PRIMERAOPCIONMENU), RetornarLinia(idioma, SEGUNDAOPCIONMENU), RetornarLinia(idioma, CREARCOLLA)};
        //Creamos la variable para salir del programa
        boolean sortir = false;
        //Creamos el resultado del sorteo en una variable de tipo NumPremiado llamando a la funcion Sorteo()
        NumPremiado[] numeros_premiados = tipoLoteria(loteriaTipo);
        BubbleSortPremis(numeros_premiados);
        //Creamos un bucle para ejecutar el programa hasta que el usuario quiera salir
        while (!sortir) {
            sortir = BucleOpciones(menu, numeros_premiados, idioma);
        }
    }

    public static boolean BucleOpciones(String[] menu, NumPremiado[] premiados, String idioma) throws IOException {

        boolean sortir = false;
        int menusurtida, numcupon, premio, year;
        System.out.println(RetornarLinia(idioma, TERCERALINIA));
        menusurtida = Menu(menu);

        //Hacemos lo que pide dependiendo de la entrada del usuario
        switch (menusurtida) {
            //Comprovamos un numero
            case 1:
                //Leemos el numero del cupon
                numcupon = LlegirInt(scan, RetornarLinia(idioma, CUARTALINIA), 0, MAXIMBITLLETS);
                //Comprovamos cuanto ha ganado
                premio = ComprobarPremio(numcupon, premiados);
                //Imprimimos el resultado
                String nomDelPremi = darNombre(nompremi, idioma);
                nompremi = 0;
                System.out.println(verde + RetornarLinia(idioma, QUINTALINIA) + nomDelPremi + RetornarLinia(idioma, SEXTALINIA) + premio / 10 + RetornarLinia(idioma, SEPTIMALINIA) + reset);
                break;
            //Mostramos los premios y su numero correspondiente
            case 2:
                System.out.println(verde + RetornarLinia(idioma, OCTAVALINIA) + reset);
                System.out.println(celeste + "****************" + reset);
                MostrarPremios(premiados, RetornarLinia(idioma, NOVENALINIA), RetornarLinia(idioma, DECIMALINIA));
                break;
            //Salimos del programa
            case 3:
                CrearColla(idioma, premiados);
                break;
            case 4:
                sortir = true;
                System.out.println(verde + RetornarLinia(idioma, ONCEAVALINIA) + reset);

                break;

        }
        return sortir;
    }

    public static NumPremiado[] tipoLoteria(String[] tipos) throws FileNotFoundException, IOException {
        int year = 0;
        boolean noArray = false;
        NumPremiado[] arrayPremios = new NumPremiado[QUANTITATPREMIS];
        while (!noArray) {
            System.out.println("Qué deseas consultar:");
            int opcion = MenuBucle(tipos);
            if (opcion == 1) {
                arrayPremios = Sorteo();
                year = IntroducirAnyo();
                while (!ComprobarValidezAnyo(year)) {
                    year = IntroducirAnyo();
                }
                ComprobarValidezAnyo(year);
                GuardarDatos(arrayPremios, year);
                noArray = true;
            } else if (opcion == 2) {
                MostrarAnyos();
                year = IntroducirAnyo();
                while (ComprobarValidezAnyo(year)) {
                    year = IntroducirAnyo();
                }
                long posicion = BuscarPosicionIndice(year);
                if (posicion != -1) {
                    noArray = true;
                    arrayPremios = ExtraerDatos(posicion);
                }
            }
        }
        return arrayPremios;
    }

    public static int IntroducirAnyo() {
        int year = LlegirInt("Introduce el año del sorteo: ");
        return year;
    }

    public static void MostrarAnyos() {
        DataInputStream dis = AbrirFicheroLecturaBinario(NOM_FTX_LOTERIASINDEX_BIN, true);
        indice index = LeerIndex(dis);
        while (index != null && index.year > 0) {
            MostrarAnyo(index);
            index = LeerIndex(dis);
        }

        CerrarFicheroBinario(dis);

    }

    public static indice LeerIndex(DataInputStream dis) {
        indice id = new indice();
        try {
            id.year = dis.readInt();
            id.pos = dis.readLong();

        } catch (IOException ex) {
            id = null;
        }
        return id;
    }

    public static void MostrarAnyo(indice index) {
        System.out.println("Año: " + index.year);
    }

    public static boolean ComprobarValidezAnyo(int anyo) {
        DataInputStream dis = AbrirFicheroLecturaBinario(NOM_FTX_LOTERIASINDEX_BIN, true);
        boolean valido = true;
        indice index = LeerIndice(dis);
        if (index == null) {
            valido = true;
        } else {
            while (valido && index != null) {
                if (index.year == anyo) {
                    valido = false;
                }
                index = LeerIndice(dis);
            }
        }
        CerrarFicheroBinario(dis);
        return valido;

    }

    public static long BuscarPosicionIndice(int anyo) {
        DataInputStream dis = AbrirFicheroLecturaBinario(NOM_FTX_LOTERIASINDEX_BIN, true);
        long posicion = -1;
        indice index = LeerIndice(dis);
        while (index != null) {
            if (index.year == anyo) {
                posicion = index.pos;
            }
            index = LeerIndice(dis);
        }
        CerrarFicheroBinario(dis);
        return posicion;
    }

// <editor-fold defaultstate="collapsed" desc="Escoger Idioma">    
    /**
     * Funcion para mostrar un menu y escoger un idioma
     *
     * @return devuelve el nombre del fitxero del idioma seleccionado
     */
    public static String EscogerIdioma() {
        String resultat = "";
        String[] opcions_menu = {"Catala", "Castella"};
        int seleccio = Utils.Menu(opcions_menu);
        resultat = GestionMenuIdioma(seleccio);
        return resultat;
    }

    /**
     * Función para gestionar la selección del menu de idioma
     *
     * @param seleccio opción seleccionada por el usuario
     * @return devuelve el nombre del idioma en fitxero
     */
    public static String GestionMenuIdioma(int seleccio) {
        String resultat = "";
        switch (seleccio) {
            case 1:
                resultat = "ca.txt";
                break;
            case 2:
                resultat = "es.txt";
                break;
            default:
                resultat = " es.txt";
                break;
        }
        return resultat;
    }

    /**
     * Función para devolver una linia exacta de un fichero txt
     *
     * @param nomfitxer nombre del fichero
     * @param numlinia numero de la linia a devolver
     * @return devuelve la linia escogida de x fichero
     * @throws IOException
     */
    public static String RetornarLinia(String nomfitxer, int numlinia) throws IOException {
        String resultat = "";
        BufferedReader buffer = Utils.AbrirFicheroLectura(nomfitxer, false);
        boolean acabat = false;
        for (int i = 1; !acabat; i++) {
            String liniaactual = buffer.readLine();
            if (i == numlinia) {
                resultat = liniaactual;
                acabat = true;
            }

        }
        buffer.close();
        return resultat;
    }

    // </editor-fold>
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
     * Esta función sirve para devolver el nombre del premio que nos ha tocado,
     * usando un fichero
     *
     * @param nombre numero de premio que nos ha tocado
     * @param nomfitxer nombre dle fichero
     * @return devuelve una lini con el premio que nos ha tocado
     * @throws IOException
     */
    public static String darNombre(int nombre, String nomfitxer) throws IOException {
        final int LINIAREINTEGRO = 13;
        String resultat = "";
        nomfitxer = "p" + nomfitxer;
        if (nombre == 0) {
            resultat = RetornarLinia(nomfitxer, LINIAREINTEGRO);

        } else {
            resultat = RetornarLinia(nomfitxer, nombre);

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
     * @return Devuelve un integer con la cantidad que ha ganado el cupon a
     * comprobar
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
     * @param premioactual El premio acumulado hasta ahora por dicho cupón
     * @return Devuelve el valor del reintegro, o 0, dependiendo de si el cupon
     * és premiado o no
     */
    public static int ComprobarReintegro(int cupon, NumPremiado[] premiados, int premioactual) {
        //Creamos la variable premio
        int premio = 0;
        //Miramos si el útlimo numero coincide con el último numero del gordo
        if (cupon % 10 == premiados[0].numero % 10) {
            //Miramos que el numero no sea el gordo
            if (cupon != premiados[0].numero) {
                //Asignamos el valor de la constante a premio
                premio = REINTEGRO;
                //Si todavía no habia ningún premio ponemos la variable nompremi con reintegro
                if (premioactual == 0) {
                    nompremi = 6;
                }
            }
        }
        //Devolvemos la variable premio
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
        //Creamos la variable premio
        int premio = 0;
        //Creamos una variable con las 2 últimas cifras de cupón
        int ultimas2 = cupon % 100;
        //Miramos si coinciden las 2 últimas cifras con las 2 últimas de un premio mayor
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
     * Esta función comprueba si el cupón està en la misma centena que algún
     * premio mayor
     *
     * @param cupon Numero a comprobar
     * @param premiados Array de los numeros premiados
     * @return Devuelve el premio correspondiente al cupon
     */
    public static int ComprobarCentena(int cupon, NumPremiado[] premiados) {
        //Creamos la variable a devolver
        int premio = 0;
        //Comprobamos si la centena corresponde a alguna de los 4 premios mayores
        for (int i = 0; i <= 4; i++) {
            if ((cupon / 100) % 10 == (premiados[i].numero / 100) % 10) {
                premio = PREMICENTENA;
                nompremi = 8;
            }
        }

        //Devolvemos el valor de premio
        return premio;
    }

    /**
     * Esta función comprueba si el numero del cupon és un numero inferior o
     * superior a uno de los 3 primeros premios
     *
     * @param cupon Numero a comprobar
     * @param premiados Array con los numeros premiados ordenado de mayor a
     * menor
     * @return Devuelve el valor del premio correspondiente al cupon
     */
    public static int ComprobarAproximacion(int cupon, NumPremiado[] premiados) {
        //Creamos la variable a devolver
        int premio = 0;
        //Miramos si el cupon se aproxima al primer premio
        if (cupon == premiados[0].numero - 1 || cupon == premiados[0].numero + 1) {
            premio = PREMIAPROX1;
            nompremi = 9;
            //Miramos si el cupon se aproxima al 2do premio
        } else if (cupon == premiados[1].numero - 1 || cupon == premiados[1].numero + 1) {
            premio = PREMIAPROX2;
            nompremi = 10;
            //Miramos si el cupon se aproxima al 3er premio
        } else if (cupon == premiados[2].numero - 1 || cupon == premiados[2].numero + 1) {
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
        1 tercer premio
        2 cuartos premios
        8 quintos premios
         */
        final int TOTALGUANYADORS = 12;
        //Creamos las variables del programa
        boolean ganador = false;
        int premio = 0;

        //Miramos si el cupon corresponde con alguno de estos premios y le assignamos el premio si és asi
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
// <editor-fold defaultstate="collapsed" desc="GuardarPremios"> 
    public static void GuardarDatos(NumPremiado[] premios, int codigo) throws FileNotFoundException, IOException {
        DataOutputStream dos = AbrirFicheroEscrituraBinario1(NOM_FTX_LOTERIASINDEX_BIN, true, true);
        indice index = new indice();
        RandomAccessFile raf = new RandomAccessFile(NOM_FTX_LOTERIAS_BIN, "rw");
        index.year = codigo;
        index.pos = raf.getFilePointer();
        EscribirIndices(true, index);
        for (int i = 0; i < premios.length; i++) {
            raf.writeInt(premios[i].numero);
            raf.writeInt(premios[i].premio);
        }
        CerrarFicheroBinario(dos);
        raf.close();
    }

    /**
     * Escribir nuevo índice en fichero de índices
     *
     * @param blnAnyadir
     * @param index parámetro del código añadido y su posición
     */
    public static void EscribirIndices(boolean blnAnyadir, indice index) {
        // Creamos el enlace con el fichero en el disco
        DataOutputStream dos = AbrirFicheroEscrituraBinario(NOM_FTX_LOTERIASINDEX_BIN, true, blnAnyadir);
        try {
            dos.writeInt(index.year);
            dos.writeLong(index.pos);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static indice LeerIndice(DataInputStream dis) {
        indice index = new indice();
        try {
            index.year = dis.readInt();
            index.pos = dis.readLong();
        } catch (IOException ex) {
            index = null;
        }
        return index;
    }

    public static NumPremiado[] ExtraerDatos(long posicion) throws IOException {
        NumPremiado[] premios = new NumPremiado[QUANTITATPREMIS];
        RandomAccessFile raf = new RandomAccessFile(NOM_FTX_LOTERIAS_BIN, "r");
        raf.seek(posicion);
        try {
            for (int i = 0; i < QUANTITATPREMIS; i++) {
                premios[i].numero = raf.readInt();
                premios[i].premio = raf.readInt();
            }
        } catch (IOException ex) {
            premios = null;
        }
        raf.close();
        return premios;
    }
//</editor-fold>    

    public static void CrearColla(String idioma, NumPremiado[] premiados) throws FileNotFoundException {
        Colla colla = DadesColla(idioma);
        System.out.print("Vols afegir usuari/s? ");
        int quant = 0;
        boolean sortir = Utils.YesOrNo();
        while (sortir == false) {
            quant += AfegirUsuari(colla, premiados);
            System.out.print("Vols seguir afegint usuaris? ");
            sortir = Utils.YesOrNo();
        }
        colla.quantMembres = quant;
        colla.premis = SumarPremis(colla.index_usuaris);
        //Escriure Colla
    }

    public static int SumarPremis(int index) throws FileNotFoundException {
        int resultat = 0;
        RandomAccessFile raf = new RandomAccessFile(NOM_FTX_USR, "r");
        Usuari usr = LlegirUsuari(raf);
        while (usr != null) {
            if (usr.numcolla == index) {
                resultat += usr.diners;
            }
            usr = LlegirUsuari(raf);
        }
        CerrarRAF(raf);
        return resultat;
    }

    public static Usuari LlegirUsuari(RandomAccessFile raf) {
        Usuari usr = new Usuari();
        try {
            usr.numcolla = raf.readInt();
            usr.nom = raf.readUTF();
            usr.diners = raf.readInt();
            usr.numero = raf.readInt();
            usr.premi = raf.readInt();
        } catch (IOException ex) {
            usr = null;
        }
        return usr;
    }

    public static int AfegirUsuari(Colla colla, NumPremiado[] premiados) {
        Usuari usr = DemanarDadesUsuari(colla, premiados);
        EscriureUsuari(usr);
        return 1;
    }

    public static void EscriureUsuari(Usuari usr) {
        try {
            RandomAccessFile raf = new RandomAccessFile(NOM_FTX_USR, "rw");
            raf.writeInt(usr.numcolla);
            raf.writeUTF(usr.nom);
            raf.writeInt(usr.diners);
            raf.writeInt(usr.numero);
            raf.writeInt(usr.premi);

            CerrarRAF(raf);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void CerrarRAF(RandomAccessFile raf) {
        try {
            raf.close();
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Usuari DemanarDadesUsuari(Colla colla, NumPremiado[] premiados) {
        Usuari usr = new Usuari();
        usr.numcolla = colla.index_usuaris;
        usr.nom = Utils.LlegirString("Digues el nom del usuari");
        usr.diners = ComprobarDiners();
        //Sorteig?
        usr.numero = LlegirInt(scan, "Di el numero que compra el usuario: ", 0, MAXIMBITLLETS);
        usr.premi = ComprobarPremio(usr.numero, premiados);
        return usr;
    }

    public static int ComprobarDiners() {
        int resultat = Utils.LlegirInt("Digues l'import de compra (5€ - 60€): ");
        while (resultat % 5 != 0 && resultat >= 5 && resultat <= 60) {
            resultat = Utils.LlegirInt("Digues l'import de compra (5€ - 60€): ");
        }
        return resultat;
    }

    public static Colla DadesColla(String idioma) {
        Colla colla = new Colla();
        try {
            colla.nom = Utils.LlegirString("DIgues el nom de la colla: ");
            while (!ComprobarNombre(colla.nom)) {
                colla.nom = Utils.LlegirString("Aquesta colla ja exitseix, digues un nou nom: ");
            }
            colla.any = LlegirInt("Digues el any del sorteig: ");
            colla.diners = 0;
            colla.premis = 0;
            colla.quantMembres = 0;
            colla.index_usuaris = ComprovarIndex(colla);
            //Demanr index_usuaris?
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return colla;

    }

    public static int ComprovarIndex(Colla colla) {
        int resultat = Utils.LlegirInt("Digues un nou numero: ");
        try {
            RandomAccessFile raf = new RandomAccessFile(NOM_FTX_COLLAS, "r");
            Colla colla2 = LlegirColla(raf);
            boolean valid = false;
            while (!valid) {
                resultat = Utils.LlegirInt("Digues un nou numero: ");
                valid = true;

                while (colla2 != null) {
                    if (colla2.index_usuaris == resultat) {
                        valid = false;
                    }
                    colla2 = LlegirColla(raf);
                }

            }
        } catch (FileNotFoundException ex) {
            resultat = 0;
        }
        return resultat;
    }

    public static Colla LlegirColla(RandomAccessFile raf) {
        Colla colla = new Colla();
        try {
            colla.nom = raf.readUTF();
            colla.any = raf.readInt();
            colla.diners = raf.readInt();
            colla.premis = raf.readInt();
            colla.quantMembres = raf.readInt();
            colla.index_usuaris = raf.readInt();
        } catch (IOException ex) {
            colla = null;
        }
        return colla;
    }

    public static boolean ComprobarNombre(String nom) throws FileNotFoundException, IOException {
        boolean resultat = true;
        File f = new File(NOM_FTX_COLLAS);
        if (!f.exists()) {
            f.createNewFile();
        }
        RandomAccessFile raf = new RandomAccessFile(NOM_FTX_COLLAS, "r");
        Colla colla = LlegirColla(raf);
        while (colla != null) {
            if (colla.nom.equals(nom)) {
                resultat = false;
            }
        }
        return resultat;
    }

}

class Usuari {

    int numcolla;
    String nom;
    int numero;
    int diners;
    int premi;
}

class Colla {

    String nom;
    int quantMembres;
    int any;
    int diners;
    int premis;
    int index_usuaris;
}

/**
 * Clase de números premiados
 */
class NumPremiado {

    int numero;
    int premio;
}

class indice {

    int year;
    long pos;
}
