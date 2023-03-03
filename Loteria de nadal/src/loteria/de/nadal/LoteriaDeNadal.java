package loteria.de.nadal;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;
import static utils.Utils.*;

/**
 * Simulación lotería de Navidad, comprovación de premios, creación de colles
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
    public static int year = 0;

    public static int arrayByteLength = 8 * QUANTITATPREMIS;
    //Definición de nombres para los ficheros binarios y de texto
    public static final String NOM_FTX_LOTERIAS_BIN = "./loterias.dat";
    public static final String NOM_FTX_LOTERIASINDEX_BIN = "./loteriasindx.dat";
    public static final String NOM_FTX_COLLAS_INDEX = "./collesidx.dat";
    public static final String NOM_FTX_COLLAS = "./colles.dat";
    public static final String NOM_FTX_USR = "./usuariscolles.dat";
    public static long LONG2BYTE = 16;
    public static long LLARGADAUSER = 35;
    //Definimos los colores
    static String rojo = "\033[31m";
    static String verde = "\033[32m";
    static String celeste = "\033[36m";
    static String reset = "\u001B[0m";

    //Numeros de linias del fichero por frase
    public static String IDIOMA;
    static int PRIMERAOPCIONMENU = 1;
    static int SEGUNDAOPCIONMENU = 2;
    static int TERCERAOPCIOMENU = 3;
    static int CUARTAOPCIOMENU = 13;
    static int PREGUNTARCONSULTA = 4;
    static int INTRODUCIRNUMERO = 5;
    static int HASGANADO = 6;
    static int CANTIDADDE = 7;
    static int EUROSALDECIMO = 8;
    static int TITULOLOTERIA = 9;
    static int LINIANUMERO = 10;
    static int PREMIOTEXTO = 11;
    static int GRACIASXPARTICIPAR = 12;
    static int NUEVOSORTEO = 14;
    static int SORTEOANTERIOR = 15;
    static int OPCIONSELECCIONADA = 16;
    static int VOLEUCONSULTAR = 17;
    static int ANY = 18;
    static int CONSULTAANY = 19;
    static int SEGUIRAFEGINT = 20;
    static int NUMCOMPRAUSUARIO = 21;
    static int NOMDELUSUARI = 22;
    static int IMPORTDECOMPRA = 23;
    static int NOMDECOLLA = 24;
    static int NUMDECOLLA = 25;
    static int CREARCOLLA = 26;
    static int MOSTRARCOLLA = 27;
    static int MOSTRARCOLLES = 28;
    static int AFEGIRUNUSUARI = 29;
    static int MODIFCARUNUSUARI = 30;
    static int ESBORRARUNUSUARI = 31;
    static int RECUPERARUNUSUARI = 32;
    static int TEXTNOM = 33;
    static int TEXTNUMERO = 34;
    static int TEXTDINERS = 35;
    static int TEXTTOT = 36;
    static int TEXTPREMIS = 37;
    static int TEXTCODI = 38;
    static int TEXTNUMCOLLA = 39;
    static int TEXTBORRAT = 40;
    static int TEXTMEMBRES = 41;
    static int NOCOLLA = 42;
    static int TEXTSURTIR = 43;
    static int TEXTUSUARIESPECIFIC = 44;

    /**
     * Variable per al nom del premi 1 = Primer premi 2 = Segon premi 3 = Tercer
     * premi 4 = 4t premi 5 = Cinqué premi 6 = Reintegro 7 = 2últimas 8 =
     * Centena 9 = Aprox Gordo 10 = Aprox 2ndo 11 = Aprox 3r 12 = Pedrea
     */
    static int nompremi = 0;

    public static void main(String[] args) throws IOException {
        IDIOMA = EscogerIdioma();
        //Creamos un string con las opciones del menu
        String[] loteriaTipo = {RetornarLinia(IDIOMA, NUEVOSORTEO), RetornarLinia(IDIOMA, SORTEOANTERIOR)};
        String[] menu = {RetornarLinia(IDIOMA, PRIMERAOPCIONMENU), RetornarLinia(IDIOMA, SEGUNDAOPCIONMENU), RetornarLinia(IDIOMA, TERCERAOPCIOMENU)};
        //Creamos la variable para salir del programa
        boolean sortir = false;
        //Creamos el resultado del sorteo en una variable de tipo NumPremiado llamando a la funcion Sorteo()
        NumPremiado[] numeros_premiados = tipoLoteria(loteriaTipo);
        //Creamos un bucle para ejecutar el programa hasta que el usuario quiera salir
        while (!sortir) {
            sortir = BucleOpciones(menu, numeros_premiados);
        }
    }

    /**
     * Funció per a imprimir un menu en bucle
     *
     * @param menu Array amb les opcions del menu a imprimir;
     * @param premiados
     * @return Retorna si l'usuari vol sortir o no
     * @throws IOException
     */
    public static boolean BucleOpciones(String[] menu, NumPremiado[] premiados) throws IOException {
        boolean sortir = false;
        int menusurtida;
        System.out.println(RetornarLinia(IDIOMA, PREGUNTARCONSULTA));
        menusurtida = Menu(menu, RetornarLinia(IDIOMA, OPCIONSELECCIONADA), TEXTSURTIR, IDIOMA);
        sortir = ExecutarMenuBucleOpciones(menusurtida, premiados, sortir);
        return sortir;
    }

    /**
     * Funció per a Executar la selecció del bucle anterior
     *
     * @param menusurtida seleccio del menu anterior
     * @param premiados Numeros premiats
     * @param sortir Variable per sortir
     * @return retorna si elusuari vol sortir o no
     * @throws IOException
     */
    public static boolean ExecutarMenuBucleOpciones(int menusurtida, NumPremiado[] premiados, boolean sortir) throws IOException {
        //Hacemos lo que pide dependiendo de la entrada del usuario
        switch (menusurtida) {
            //Comprovamos un numero
            case 1:
                ComprobarUnNumero(premiados);
                break;
            //Mostramos los premios y su numero correspondiente
            case 2:
                System.out.println(verde + RetornarLinia(IDIOMA, TITULOLOTERIA) + reset);
                System.out.println(celeste + "****************" + reset);
                MostrarPremios(premiados, RetornarLinia(IDIOMA, LINIANUMERO), RetornarLinia(IDIOMA, PREMIOTEXTO));
                break;
            //Salimos del programa
            case 3:
                SubmenuColles(premiados);
                break;
            case 4:
                sortir = true;
                System.out.println(verde + RetornarLinia(IDIOMA, GRACIASXPARTICIPAR) + reset);
                break;

        }
        return sortir;
    }

    /**
     * Procediment per a comprovar un numero amb el sorteig actual
     *
     * @param premiados Sorteig actual
     * @throws IOException
     */
    public static void ComprobarUnNumero(NumPremiado[] premiados) throws IOException {
        int numcupon;
        int premio;
        numcupon = LlegirInt(scan, RetornarLinia(IDIOMA, INTRODUCIRNUMERO), 0, MAXIMBITLLETS);
        premio = ComprobarPremio(numcupon, premiados);
        String nomDelPremi = darNombre(nompremi, IDIOMA);
        nompremi = 0;
        System.out.println(verde + RetornarLinia(IDIOMA, HASGANADO) + nomDelPremi + RetornarLinia(IDIOMA, CANTIDADDE) + premio / 10 + RetornarLinia(IDIOMA, EUROSALDECIMO) + reset);
    }

    /**
     * Comprobar si el fichero índice está vacío
     *
     * @return true si está vacío / false si está lleno
     */
    public static boolean ComprobarIndexVacio() {
        DataInputStream dis = AbrirFicheroLecturaBinario(NOM_FTX_LOTERIASINDEX_BIN, true);
        indice index = LeerIndex(dis);
        boolean noArray = false;
        if (index == null) {
            noArray = true;
        }
        return noArray;
    }

    /**
     * Leer fichro índice en posición "dis"
     *
     * @param dis
     * @return clase de tipo índice con los datos leidos
     */
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

// <editor-fold defaultstate="collapsed" desc="Escoger Idioma">    
    /**
     * Funcion para mostrar un menu y escoger un idioma
     *
     * @return devuelve el nombre del fitxero del idioma seleccionado
     */
    public static String EscogerIdioma() {
        String resultat = "";
        String[] opcions_menu = {"Català", "Castellano", "English", "Deustch", "Português", "Italiano", "Chino"};
        int seleccio = Utils.MenuBucle(opcions_menu, "Opció/Opción/Option/Opção/Opzione/選項: ");
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
            case 3:
                resultat = "en.txt";
                break;
            case 4:
                resultat = "ger.txt";
                break;
            case 5:
                resultat = "pt.txt";
                break;
            case 6:
                resultat = "it.txt";
                break;
            case 7:
                resultat = "ch.txt";
                break;
            default:
                resultat = "es.txt";
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
        int[] bomboPremios = CrearBomboPremios();
        NumPremiado[] numeros_premiats = new NumPremiado[QUANTITATPREMIS];
        int num_afegir = 0;
        int premi_afegir, posP;
        for (int i = 0; i < numeros_premiats.length; i++) { //Crear todos los premios
            boolean repeatnum = true;
            while (repeatnum) {
                num_afegir = rnd.nextInt(MAXIMBITLLETS + 1); //Generar número aleatorio
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
                premi_afegir = PEDREADA;
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
        final int NUMPOSICIONSCOMPROBAR = 2;
        //Creamos la variable premio
        int premio = 0;
        //Creamos una variable con las 2 últimas cifras de cupón
        int ultimas2 = cupon % 100;
        //Miramos si coinciden las 2 últimas cifras con las 2 últimas de un premio mayor
        for (int i = 0; i <= NUMPOSICIONSCOMPROBAR; i++) {
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
        final int QUANTPREMIS = 4;
        //Comprobamos si la centena corresponde a alguna de los 4 premios mayores
        for (int i = 0; i <= QUANTPREMIS; i++) {
            if (cupon / 100 == premiados[i].numero / 100) {
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
        Aproximacio aprox = CalcularAproximacio(premiados[0].numero);
        Aproximacio aprox2 = CalcularAproximacio(premiados[1].numero);
        Aproximacio aprox3 = CalcularAproximacio(premiados[2].numero);
        //Miramos si el cupon se aproxima al primer premio
        if (cupon == aprox.dalt || cupon == aprox.baix) {
            premio = PREMIAPROX1;
            nompremi = 9;
            //Miramos si el cupon se aproxima al 2do premio
        } else if (cupon == aprox2.dalt || cupon == aprox2.baix) {
            premio = PREMIAPROX2;
            nompremi = 10;
            //Miramos si el cupon se aproxima al 3er premio
        } else if (cupon == aprox3.dalt || cupon == aprox3.baix) {
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

    /**
     * Funció per a calcular les aproximacions de un numero
     *
     * @param numero numero a calcular les aproximacions
     * @return retorna un objecte Aproximacio amb el calcul ja fet
     */
    public static Aproximacio CalcularAproximacio(int numero) {
        Aproximacio aprox = new Aproximacio();
        switch (numero) {
            case MAXIMBITLLETS:
                aprox.dalt = 0;
                aprox.baix = numero - 1;
                break;
            case 0:
                aprox.dalt = numero + 1;
                aprox.baix = MAXIMBITLLETS;
                break;
            default:
                aprox.dalt = numero + 1;
                aprox.baix = numero - 1;
                break;
        }
        return aprox;
    }

    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="GuardarPremios"> 
    /**
     * Procediment per a fuardar les dades de un nou sorteig a un fitxer
     *
     * @param premios Sorteig a guardar
     * @param codigo coid del sorteig (any)
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void GuardarDatos(NumPremiado[] premios, int codigo) throws FileNotFoundException, IOException {
        DataOutputStream dos = AbrirFicheroEscrituraBinario1(NOM_FTX_LOTERIASINDEX_BIN, true, true);
        indice index = new indice();
        RandomAccessFile raf = CrearFitxer(NOM_FTX_LOTERIAS_BIN, "rw");
        index.year = codigo;
        index.pos = raf.length();
        raf.seek(index.pos);
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

    /**
     * Función para comprobar si un año no está repetido
     *
     * @param anyo
     * @return
     */
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

    /**
     * Consultar valor de variable año
     *
     * @return
     * @throws IOException
     */
    public static int IntroducirAnyo() throws IOException {
        int year = LlegirInt(RetornarLinia(IDIOMA, CONSULTAANY));
        return year;
    }

    /**
     * Imprimir los sorteos registrados
     *
     * @throws IOException
     */
    public static void MostrarAnyos() throws IOException {
        DataInputStream dis = AbrirFicheroLecturaBinario(NOM_FTX_LOTERIASINDEX_BIN, true);
        indice index = LeerIndex(dis);
        while (index != null && index.year > 0) {
            MostrarAnyo(index);
            index = LeerIndex(dis);
        }

        CerrarFicheroBinario(dis);

    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Abrir Premios">

    /**
     * Escoger entre una loteria nueva o una guardada
     *
     * @param tipos array con los tipos de lotería
     * @return array de premios
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static NumPremiado[] tipoLoteria(String[] tipos) throws FileNotFoundException, IOException {
        boolean noArray = true;
        NumPremiado[] arrayPremios = new NumPremiado[QUANTITATPREMIS];

        while (noArray) {
            System.out.println(RetornarLinia(IDIOMA, VOLEUCONSULTAR));
            int opcion = MenuBucle(tipos, RetornarLinia(IDIOMA, OPCIONSELECCIONADA));
            noArray = ComprobarIndexVacio();
            if (opcion == 1) {

                MostrarAnyos(); //Muestra los años guardados
                arrayPremios = Sorteo();
                BubbleSortPremis(arrayPremios);
                year = IntroducirAnyo(); //Añades una nuevo sorteo al fichero, requerido un año no repetido
                while (!ComprobarValidezAnyo(year)) {
                    year = IntroducirAnyo();
                }
                ComprobarValidezAnyo(year);
                GuardarDatos(arrayPremios, year);
                noArray = false;
            } else if (opcion == 2 && !noArray) {
                MostrarAnyos(); //Muestra los años guardados
                year = IntroducirAnyo();
                while (ComprobarValidezAnyo(year)) {
                    year = IntroducirAnyo();
                }
                long posicion = BuscarPosicionIndice(year);
                if (posicion != -1) {
                    arrayPremios = ExtraerDatos(posicion);
                }
            }
        }

        return arrayPremios;
    }

    /**
     * Funció que llegeix un objecte de tipus Indice
     *
     * @param dis Objecte DataInputStream per a llegir el fitxer
     * @return
     */
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

    /**
     * Funció per a Llegir un sorteig ja creat, en base al codi
     *
     * @param posicion posicio on comença el sorteig
     * @return Retorna un array de NumPremiado[] del sorteig buscat
     * @throws IOException
     */
    public static NumPremiado[] ExtraerDatos(long posicion) throws IOException {
        NumPremiado[] premios = new NumPremiado[QUANTITATPREMIS];

        RandomAccessFile raf = CrearFitxer(NOM_FTX_LOTERIAS_BIN, "r");
        raf.seek(posicion);
        try {
            for (int i = 0; i < QUANTITATPREMIS; i++) {
                NumPremiado premio = new NumPremiado();
                premio.numero = raf.readInt();
                premio.premio = raf.readInt();
                premios[i] = premio;
            }
        } catch (IOException ex) {
            premios = null;
        }
        raf.close();
        return premios;
    }

    /**
     * Imprimir un año formateado
     *
     * @param index clase índice con los valores de un registro
     * @throws IOException
     */
    public static void MostrarAnyo(indice index) throws IOException {
        System.out.println(RetornarLinia(IDIOMA, ANY) + ": " + index.year);
    }

    /**
     * Encontrar posición en fichero de un registro con código "anyo"
     *
     * @param anyo año del sorteo
     * @return posición del registro
     */
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

// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Creacion Colla">    
    /**
     * Procediment per a Crear una colla
     *
     * @param premiados sorteig de l'any seleccionat
     * @throws FileNotFoundException
     */
    public static void CrearColla(NumPremiado[] premiados) throws FileNotFoundException, IOException {
        Colla colla = DadesColla();
        int quant = 0;
        boolean sortir = true;
        while (sortir) {
            quant += AfegirUsuari(colla, premiados);
            sortir = Utils.YesOrNo(RetornarLinia(IDIOMA, SEGUIRAFEGINT));
        }
        colla.quantMembres = quant;
        EscribirColla(colla);
    }

    /**
     * Procediment per a determinar el numero de colla i la posicio al fitxer
     * index, també serveix per a Escriure les dades als fitxers colla index i
     * colla
     *
     * @param colla Colla a escriure
     */
    public static void EscribirColla(Colla colla) {
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS_INDEX, "rw");
            RandomAccessFile raf2 = CrearFitxer(NOM_FTX_COLLAS, "rw");
            indice2 id = new indice2();
            id.pos = raf2.length();
            id.codiusuari = colla.numcolla;
            EscriureDades(colla, raf2, raf2.length());
            EscriureIndex(id, raf);
            CerrarRAF(raf);
            CerrarRAF(raf2);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Procediment per escriure al fitxer index les dades de la colla que
     * s'escriura al fitxer collas
     *
     * @param id Objecte index amb les dades de la colla seleccionada
     * @param raf Objecte RandomAccessFile per accedir al fitxer index
     */
    public static void EscriureIndex(indice2 id, RandomAccessFile raf) {
        try {
            raf.seek(raf.length());
            raf.writeLong(id.codiusuari);
            raf.writeLong(id.pos);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Procediment per a escriure les dades de la colla al fitxer de collas
     *
     * @param colla Objecte colla a escriure al fitxer
     * @param raf Objecte RandomAccessFile per a escriure al fitxer collas
     * @param codi posicio on s'escriuran les dades
     */
    public static void EscriureDades(Colla colla, RandomAccessFile raf, long codi) {
        try {
            raf.seek(codi);
            raf.writeLong(colla.numcolla);
            raf.writeUTF(colla.nom);
            raf.writeInt(colla.any);
            raf.writeInt(colla.diners);
            raf.writeFloat(colla.premis);
            raf.writeInt(colla.quantMembres);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Funcio que retorna un Usuari que s'ha llegit del fitxer usuaris
     *
     * @param raf Objecte RandomAccessFile per accedir al fitxer usuaris
     * @return Retorna un objecte Usuari que ha llegit del fitxer
     */
    public static Usuari LlegirUsuari(RandomAccessFile raf) {
        Usuari usr = new Usuari();
        try {
            usr.numcolla = raf.readLong();
            usr.nom = raf.readUTF();
            usr.diners = raf.readInt();
            usr.numero = raf.readInt();
            usr.premiPersonal = raf.readFloat();
            usr.premiTotal = raf.readInt();
            usr.premiRepartit = raf.readFloat();
            usr.any = raf.readInt();
            usr.borrat = raf.readBoolean();
        } catch (IOException ex) {
            usr = null;
        }
        return usr;
    }

    /**
     * Funció per a afegir un usuari a una colla
     *
     * @param colla Objecte colla on s'afegirá l'usuari
     * @param premiados Array de NumPremiado[] del sorteig anual actual
     * @return Retorna un 1, per al contador de usuaris afegits
     */
    public static int AfegirUsuari(Colla colla, NumPremiado[] premiados) throws IOException {
        Usuari usr = DemanarDadesUsuari(colla, premiados);
        EscriureUsuari(usr);
        return 1;
    }

    /**
     * Procediment que serveix per a escriure un usuair al punt que se s'ha
     * situat el raf abans de cridar la funció
     *
     * @param usr Usuari a escriure
     * @param raf Objecte RandomAccessFile amb un raf.seek() ja fet del fitxer
     * usuaris
     */
    public static void EscriureUsuariP(Usuari usr, RandomAccessFile raf) {
        try {
            raf.writeLong(usr.numcolla);
            raf.writeUTF(usr.nom);
            raf.writeInt(usr.diners);
            raf.writeInt(usr.numero);
            raf.writeFloat(usr.premiPersonal);
            raf.writeInt(usr.premiTotal);
            raf.writeFloat(usr.premiRepartit);
            raf.writeInt(usr.any);
            raf.writeBoolean(usr.borrat);

        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Procediment per a escriure un usuari al final del fitxer
     *
     * @param usr Usuari a escriure
     */
    public static void EscriureUsuari(Usuari usr) {
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_USR, "rw");
            raf.seek(raf.length());
            raf.writeLong(usr.numcolla);
            raf.writeUTF(usr.nom);
            raf.writeInt(usr.diners);
            raf.writeInt(usr.numero);
            raf.writeFloat(usr.premiPersonal);
            raf.writeInt(usr.premiTotal);
            raf.writeFloat(usr.premiRepartit);
            raf.writeInt(usr.any);
            raf.writeBoolean(usr.borrat);

            CerrarRAF(raf);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Funció per a tancar el RandomAccessFile
     *
     * @param raf Objecte RandomAccessFile a tancar
     */
    public static void CerrarRAF(RandomAccessFile raf) {
        try {
            raf.close();
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Funció que demana al client les dades corresponents per a crear un usuari
     * complet
     *
     * @param colla Colla on s'afegeix l'usuari
     * @param premiados array de NumPremiado[] del sorteig anual actual
     * @return Retorna un objecte usuari amb les dades que ha donat el client
     */
    public static Usuari DemanarDadesUsuari(Colla colla, NumPremiado[] premiados) throws IOException {
        Usuari usr = new Usuari();
        usr.numcolla = colla.numcolla;
        usr.nom = ComprobarNom(colla);
        usr.diners = ComprobarDiners();
        //Sorteig?
        usr.numero = LlegirInt(scan, RetornarLinia(IDIOMA, NUMCOMPRAUSUARIO), 0, MAXIMBITLLETS);
        usr.premiTotal = ComprobarPremio(usr.numero, premiados) / 10;
        usr.premiPersonal = CalcularPremioPersonal(usr.premiTotal, usr.diners);
        usr.premiRepartit = 0;
        usr.any = colla.any;
        usr.borrat = false;
        return usr;
    }

    /**
     * Funció per a comprovar que un nom no existeixi a la colla corresponent
     *
     * @param colla Objecte Colla on comprovar el nom
     * @return Retorna un nom que no existeixi encara
     */
    public static String ComprobarNom(Colla colla) throws IOException {
        String resultat = Utils.LlegirString(RetornarLinia(IDIOMA, NOMDELUSUARI));
        resultat = OmplirNomAmbEspais(resultat);
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_USR, "r");
            Usuari usr = LlegirUsuari(raf);
            while (usr != null) {
                if (usr.numcolla == colla.numcolla && usr.any == colla.any) {
                    if (usr.nom.equals(resultat) || resultat.length() > 20) {
                        resultat = ComprobarNom(colla);
                    }
                }
                usr = LlegirUsuari(raf);
            }
            resultat = OmplirNomAmbEspais(resultat);
            CerrarRAF(raf);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultat;
    }

    /**
     * Funció per omplir un string amb espais fins que el .length sigui 20
     *
     * @param resultat String a alterar
     * @return Retorna el string ocupant 20 espais
     */
    public static String OmplirNomAmbEspais(String resultat) {
        final int LLARGADASTRING = 20;
        for (int i = 0; i < LLARGADASTRING; i++) {
            if (resultat.length() < LLARGADASTRING) {
                resultat = " " + resultat;
            }
        }
        return resultat;
    }

    /**
     * Funció per a calcular el premi personal corresponent a la aportació de
     * cada usuari
     *
     * @param total total guanyat per el seu numero
     * @param aportacio Aportació de l'usuari en diners
     * @return Retorna el premiPersonal corresponent
     */
    public static float CalcularPremioPersonal(int total, int aportacio) {
        float resultat = 0;
        float aportat = aportacio / 20f;
        resultat = total * aportat;
        return resultat;
    }

    /**
     * Funció per a comprobar els diners que aporta un usuari, entre 5 i 60, i
     * múltiple de 5
     *
     * @return Retorna els diners que aporta l'usuari
     */
    public static int ComprobarDiners() {
        int resultat = 0;
        final int MAXIMDINERS = 60;
        final int MINIMDINERS = 5;
        final int MUTIPLEDE = 5;
        try {
            resultat = Utils.LlegirInt(RetornarLinia(IDIOMA, IMPORTDECOMPRA));
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (resultat % MUTIPLEDE != 0 || resultat < MINIMDINERS || resultat > MAXIMDINERS) {
            try {
                resultat = Utils.LlegirInt(RetornarLinia(IDIOMA, IMPORTDECOMPRA));
            } catch (IOException ex) {
                Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resultat;
    }

    /**
     * Funció per a demanar les dades de una colla a l'hora de crearla
     *
     * @return retorna la colla amb les Dades que ha posat l'usuari
     */
    public static Colla DadesColla() {
        Colla colla = new Colla();
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_USR, "r");
            colla.nom = Utils.LlegirString(RetornarLinia(IDIOMA, NOMDECOLLA));
            colla.any = year;
            colla.premis = 0;
            colla.diners = 0;
            colla.quantMembres = 0;
            colla.numcolla = ComprobarNumeroColla();
            //Demanr index_usuaris?
            CerrarRAF(raf);
        } catch (FileNotFoundException ex) {
            colla = null;
        } catch (IOException ex) {
            colla = null;
        }
        return colla;

    }

    /**
     * Funció per a comprobar si el numero de una colla en aquell any existeix
     *
     * @return Retorna el numero de la colla correcte
     */
    public static long ComprobarNumeroColla() {
        long resultat = 0;
        while (resultat == 0) {
            try {
                RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS_INDEX, "r");
                RandomAccessFile raf2 = CrearFitxer(NOM_FTX_COLLAS, "r");
                indice2 id = LlegirIndice2(raf);
                resultat = Utils.LlegirInt(RetornarLinia(IDIOMA, NUMDECOLLA));
                resultat = ComprobarNumeroAno(id, raf2, resultat, raf);
                CerrarRAF(raf);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultat;
    }

    /**
     * Funció per a comprovar el numero de colla i l'any
     *
     * @param id Objecte indice on comença la primera colla
     * @param raf2 Objecte RandomAccessFile del fitxer Collas
     * @param resultat Resultat actual
     * @param raf Objecte RandomAccessFile del fitxer d'indexos
     * @return retorna un codi de colla disponible
     * @throws IOException
     */
    public static long ComprobarNumeroAno(indice2 id, RandomAccessFile raf2, long resultat, RandomAccessFile raf) throws IOException {
        while (id != null) {
            raf2.seek(id.pos);
            Colla colla = LlegirColla(raf2);
            if (id.codiusuari == resultat && colla.any == year) {
                resultat = 0;
                id = null;
            }
            id = LlegirIndice2(raf);
        }
        return resultat;
    }

    /**
     * Procediment per a crear un fitxer si no existeix
     *
     * @param nom nom del fitxer a crear
     */
    public static RandomAccessFile CrearFitxer(String nom, String mode) throws FileNotFoundException {
        File f = new File(nom);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        RandomAccessFile raf = new RandomAccessFile(nom, mode);
        return raf;
    }

    /**
     * Funció per a llegir un objecte de tipus indice2 d'un fitxer
     *
     * @param raf Objecte RandomAccessFile del fitxer del cual llegir l''objecte
     * @return retorna el objecte indice2
     */
    public static indice2 LlegirIndice2(RandomAccessFile raf) {
        indice2 id = new indice2();
        try {
            id.codiusuari = raf.readLong();
            id.pos = raf.readLong();
        } catch (IOException ex) {
            id = null;
        }
        return id;

    }

    /**
     * Funció per a llegir un objecte de tipus indice d'un fitxer
     *
     * @param raf Objecte RandomAccessFile del fitxer del cual llegir l''objecte
     * @return retorna el objecte indice
     */
    public static indice LlegirIndice(RandomAccessFile raf) {
        indice id = new indice();
        try {
            id.year = raf.readInt();
            id.pos = raf.readLong();
        } catch (IOException ex) {
            id = null;
        }
        return id;
    }

    /**
     * Funció per a llegir l'objecte colla d'un fitxer
     *
     * @param raf Objecte RandomAccessFile del fitxer d'on llegir la colla
     * @return retorna l'objecte colla llegit
     */
    public static Colla LlegirColla(RandomAccessFile raf) {
        Colla colla = new Colla();
        try {
            colla.numcolla = raf.readLong();
            colla.nom = raf.readUTF();
            colla.any = raf.readInt();
            colla.diners = raf.readInt();
            colla.premis = raf.readFloat();
            colla.quantMembres = raf.readInt();
        } catch (IOException ex) {
            colla = null;
        }
        return colla;
    }

    /**
     * Funció per a comprovar que el nom de la colla no es repeteixi
     *
     * @param nom nom a comprovar
     * @return retorna si el nom ja existeix o no en un boolean
     * @throws IOException
     */
    public static boolean ComprobarNombre(String nom) throws IOException {
        boolean resultat = true;
        RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS, "r");
        Colla colla = LlegirColla(raf);
        while (colla != null) {
            if (colla.nom.equals(nom)) {
                resultat = false;
            }
        }
        CerrarRAF(raf);
        return resultat;
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Modifiacio Colla">    

    /**
     * Procediment per a imprimir el submenu de les colles
     *
     * @param premiados Array de NumPremiado[] del sorteig anual actual
     * @throws FileNotFoundException
     */
    public static void SubmenuColles(NumPremiado[] premiados) throws FileNotFoundException, IOException {
        String[] opcions_menu = {RetornarLinia(IDIOMA, CREARCOLLA), RetornarLinia(IDIOMA, MOSTRARCOLLA), RetornarLinia(IDIOMA, MOSTRARCOLLES), RetornarLinia(IDIOMA, TEXTUSUARIESPECIFIC), RetornarLinia(IDIOMA, AFEGIRUNUSUARI), RetornarLinia(IDIOMA, MODIFCARUNUSUARI), RetornarLinia(IDIOMA, ESBORRARUNUSUARI), RetornarLinia(IDIOMA, RECUPERARUNUSUARI)};
        int seleccio = Utils.Menu(opcions_menu, RetornarLinia(IDIOMA, OPCIONSELECCIONADA), TEXTSURTIR, IDIOMA);
        switch (seleccio) {
            case 1:
                CrearColla(premiados);
                break;
            case 2:
                long codiColla = Utils.LlegirLong(RetornarLinia(IDIOMA, NUMDECOLLA));
                MostrarColla(codiColla);
                break;
            case 3:
                MostrarTotesLesColles();
                break;
            case 4:
                long codiColla6 = Utils.LlegirLong(RetornarLinia(IDIOMA, NUMDECOLLA));
                String nomUser6 = Utils.LlegirString(RetornarLinia(IDIOMA, NOMDELUSUARI));
                ImprimirUsuariEspecific(codiColla6, nomUser6);
                break;
            case 5:

                long codiColla2 = Utils.LlegirLong(RetornarLinia(IDIOMA, NUMDECOLLA));
                AfegirUsuaris(codiColla2, premiados);
                break;
            case 6:
                long codiColla3 = Utils.LlegirLong(RetornarLinia(IDIOMA, NUMDECOLLA));
                String nomUser = Utils.LlegirString(RetornarLinia(IDIOMA, NOMDELUSUARI));
                ModificarUsuaris(codiColla3, premiados, nomUser);
                break;
            case 7:
                long codiColla4 = Utils.LlegirLong(RetornarLinia(IDIOMA, NUMDECOLLA));
                String nomUser2 = Utils.LlegirString(RetornarLinia(IDIOMA, NOMDELUSUARI));
                EsborrarUsuari(codiColla4, nomUser2);
                break;
            case 8:
                long codiColla5 = Utils.LlegirLong(RetornarLinia(IDIOMA, NUMDECOLLA));
                MostrarBorrados(codiColla5);
                String nomUser3 = Utils.LlegirString(RetornarLinia(IDIOMA, NOMDELUSUARI));
                RecuperarUsuaris(nomUser3, codiColla5);
                break;

            case 9:
                break;
        }
    }

    public static void ImprimirUsuariEspecific(long codi, String nom) throws FileNotFoundException, IOException {
        RandomAccessFile raf = CrearFitxer(NOM_FTX_USR, "r");
        Usuari usr = LlegirUsuari(raf);
        nom = OmplirNomAmbEspais(nom);
        while (usr != null) {
            if (usr.numcolla == codi && usr.nom.equals(nom) && usr.any == year) {
                EscribirDatosUsr(usr);
            }
            usr = LlegirUsuari(raf);
        }
    }

    /**
     * Imprimir por pantalla los datos del parámetro u
     *
     * @param u clase usuari
     * @throws IOException
     */
    public static void EscribirDatosUsr(Usuari u) throws IOException {
        u.nom = u.nom.replace(" ","");
        System.out.println("------------------------------");
        System.out.println(RetornarLinia(IDIOMA, TEXTNUMCOLLA) + ": " + u.numcolla);
        System.out.println(RetornarLinia(IDIOMA, TEXTNOM) + ": " + u.nom);
        System.out.println(RetornarLinia(IDIOMA, TEXTDINERS) + ": " + u.diners);
        System.out.println(RetornarLinia(IDIOMA, TEXTNUMERO) + ": " + u.numero);
        System.out.println(RetornarLinia(IDIOMA, ANY) + ": " + u.any);
        System.out.println("------------------------------");
        
    }

    /**
     * Mostrar Usuarios borrados de una peña específica
     *
     * @param codi codigo de la peña
     */
    public static void MostrarBorrados(long codi) {
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS_INDEX, "r");
            RandomAccessFile raf2 = CrearFitxer(NOM_FTX_COLLAS, "r");
            RandomAccessFile raf3 = CrearFitxer(NOM_FTX_USR, "r");
            indice2 id = LlegirIndice2(raf);
            Colla colla = null;
            while (id != null) {
                if (id.codiusuari == codi) {
                    raf2.seek(id.pos);
                    colla = LlegirColla(raf2);
                    if (colla.any == year) {
                        Usuari usr = LlegirUsuari(raf3);
                        while (usr != null) {
                            if (usr.borrat && usr.numcolla == colla.numcolla && usr.any == year) {

                                EscribirDatosUsr(usr);
                            }
                            usr = LlegirUsuari(raf3);

                        }
                    }

                }
                id = LlegirIndice2(raf);

            }
            CerrarRAF(raf);
            CerrarRAF(raf2);
            CerrarRAF(raf3);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Procediment per a esborrar un usuari d'una colla
     *
     * @param codi codi de la colla on es troba l'usuari
     * @param nom nom de l'usuari a esborrar
     */
    public static void EsborrarUsuari(long codi, String nom) {
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS_INDEX, "r");
            RandomAccessFile raf2 = CrearFitxer(NOM_FTX_COLLAS, "rw");
            RandomAccessFile raf3 = CrearFitxer(NOM_FTX_USR, "rw");
            indice2 id = LlegirIndice2(raf);
            nom = OmplirNomAmbEspais(nom);
            long posiciocolla = 0;
            Colla colla = null;
            while (id != null) {
                if (id.codiusuari == codi) {
                    raf2.seek(id.pos);
                    colla = LlegirColla(raf2);
                    if (colla.any == year) {
                        long posicio = raf3.getFilePointer();
                        posiciocolla = EsborrarUsuariEspecific(raf3, nom, colla, posicio, posiciocolla, id, raf);
                    }
                }
                id = LlegirIndice2(raf);
            }
            if (colla != null) {
                EscriureDades(colla, raf2, posiciocolla);
            }
            CerrarRAF(raf);
            CerrarRAF(raf2);
            CerrarRAF(raf3);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Funció per a esborrar un usuari específic
     *
     * @param raf3 Objecte RandomAccessFile del fitxer de usuaris
     * @param nom nom del usuari a esborrar
     * @param colla Objecte colla de on es troba l'usuari a esborrar
     * @param posicio long amb la posicio on es troba l'usuari que es comença a
     * llegir
     * @param posiciocolla oisicio on es troba la colla de l'usuari
     * @param id Objecte Indice2 de la colla
     * @param raf Objecte RandomAccessFile del fitxer d'indexos
     * @return retorna la poscio on comença la colla que s'ha modifcat
     * @throws IOException
     */
    public static long EsborrarUsuariEspecific(RandomAccessFile raf3, String nom, Colla colla, long posicio, long posiciocolla, indice2 id, RandomAccessFile raf) throws IOException {
        Usuari usr = LlegirUsuari(raf3);
        while (usr != null) {
            if (usr.nom.equals(nom) && usr.numcolla == colla.numcolla && usr.any == year) {
                usr.borrat = true;
                raf3.seek(posicio);
                EscriureUsuariP(usr, raf3);
                posiciocolla = id.pos;
                colla.quantMembres--;
                usr = null;
                raf.seek(raf.length());
            } else {
                posicio = raf3.getFilePointer();
                usr = LlegirUsuari(raf3);
            }

        }
        return posiciocolla;
    }
    /**
     * Recuperar usuarios borrados
     * @param nom nombre del usuario
     * @param codi código de la peña
     */
    public static void RecuperarUsuaris(String nom, long codi) {
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS_INDEX, "r");
            RandomAccessFile raf2 = CrearFitxer(NOM_FTX_COLLAS, "rw");
            RandomAccessFile raf3 = CrearFitxer(NOM_FTX_USR, "rw");
            indice2 id = LlegirIndice2(raf);
            nom = OmplirNomAmbEspais(nom);
            long posiciocolla = 0;
            Colla colla = null;
            while (id != null) {
                if (id.codiusuari == codi) {
                    raf2.seek(id.pos);
                    colla = LlegirColla(raf2);
                    if (colla.any == year) {
                        long posicio = raf3.getFilePointer();
                        Usuari usr = LlegirUsuari(raf3);
                        posiciocolla = RecuperarUsuarioEspecifico(usr, nom, colla, raf3, posicio, posiciocolla, id, raf);

                    }
                }
                id = LlegirIndice2(raf);
            }
            if (colla != null) {
                EscriureDades(colla, raf2, posiciocolla);
            }
            CerrarRAF(raf);
            CerrarRAF(raf2);
            CerrarRAF(raf3);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Buscar usuario para su recuperación
     * @param usr clase tipo usuario
     * @param nom nombre del usuario
     * @param colla clase del tipo colla
     * @param raf3
     * @param posicio posición del usuario
     * @param posiciocolla posición de la colla
     * @param id índice donde se situa la colla
     * @param raf
     * @return posición en la colla
     * @throws IOException 
     */
    public static long RecuperarUsuarioEspecifico(Usuari usr, String nom, Colla colla, RandomAccessFile raf3, long posicio, long posiciocolla, indice2 id, RandomAccessFile raf) throws IOException {
        while (usr != null) {
            if (usr.nom.equals(nom) && usr.numcolla == colla.numcolla && usr.any == year) {
                usr.borrat = false;
                raf3.seek(posicio);
                EscriureUsuariP(usr, raf3);
                posiciocolla = id.pos;
                colla.quantMembres++;
                usr = null;
                raf.seek(raf.length());
            } else {
                posicio = raf3.getFilePointer();
                usr = LlegirUsuari(raf3);
            }
        }
        return posiciocolla;
    }

    /**
     * Procediment per a modificar un usuari especific
     *
     * @param codi codi de la colla on es troba l'usuari a modifcar
     * @param premiados Array de NumPremiado[] amb el sorteig actual
     * @param nom nom de l'usuari a modificar
     */
    public static void ModificarUsuaris(long codi, NumPremiado[] premiados, String nom) {
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS_INDEX, "r");
            RandomAccessFile raf2 = CrearFitxer(NOM_FTX_COLLAS, "r");
            RandomAccessFile raf3 = CrearFitxer(NOM_FTX_USR, "rw");
            indice2 id = LlegirIndice2(raf);
            nom = OmplirNomAmbEspais(nom);
            IterarId(id, codi, raf2, raf3, nom, premiados, raf);
            CerrarRAF(raf);
            CerrarRAF(raf2);
            CerrarRAF(raf3);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Procediment per a iterar per diferents Indexos de colles
     *
     * @param id Objecte Indice2 per a iterar amb ell
     * @param codi codi de la colla a troabr
     * @param raf2 Objecte RandomAccessFile del fitxer de collas
     * @param raf3 Objecte RandomAccessFile del fitxer de usuaris
     * @param nom nom de l'usuari a trobar
     * @param premiados Array de NumPremiado[] amb el sorteig actual
     * @param raf Objecte RandomAccessFile del fitxer de index collas
     * @throws IOException
     */
    public static void IterarId(indice2 id, long codi, RandomAccessFile raf2, RandomAccessFile raf3, String nom, NumPremiado[] premiados, RandomAccessFile raf) throws IOException {
        while (id != null) {
            if (id.codiusuari == codi) {
                raf2.seek(id.pos);
                Colla colla = LlegirColla(raf2);
                if (colla.any == year) {
                    long posicio = raf3.getFilePointer();
                    Usuari usr = LlegirUsuari(raf3);
                    IteracioUsuari(usr, nom, colla, premiados, raf3, posicio);
                }
            }
            id = LlegirIndice2(raf);

        }
    }

    /**
     * Procediment per a iterar usuaris fins trobar el que s'ha de esborrar
     *
     * @param usr usuari a iterar
     * @param nom nom de l'usuari a trobar
     * @param colla Objecte Colla on buscar l'usuari
     * @param premiados Array de NumPremiado[] amb el sorteig actual
     * @param raf3 Objecte RandomAccessFile del fitxer de usuaris
     * @param posicio posicio on comença l'usuari que s'ha llegit
     * @throws IOException
     */
    public static void IteracioUsuari(Usuari usr, String nom, Colla colla, NumPremiado[] premiados, RandomAccessFile raf3, long posicio) throws IOException {
        while (usr != null) {
            if (usr.nom.equals(nom) && usr.any == colla.any) {
                String[] opcions_menu = {RetornarLinia(IDIOMA, TEXTNOM), RetornarLinia(IDIOMA, TEXTNUMERO), RetornarLinia(IDIOMA, TEXTDINERS), RetornarLinia(IDIOMA, TEXTTOT)};
                int seleccio = Utils.MenuBucle(opcions_menu, RetornarLinia(IDIOMA, OPCIONSELECCIONADA));
                usr = ExecutarMenuModificacio(seleccio, usr, colla, premiados);
                raf3.seek(posicio);
                EscriureUsuariP(usr, raf3);
                usr = null;
            } else {
                posicio = raf3.getFilePointer();
                usr = LlegirUsuari(raf3);
            }
        }
    }

    /**
     * Funció que executa la selecció del menu i retorna el usuari modifcat
     *
     * @param seleccio seleccio feta del menu
     * @param usr Usuari a modificar
     * @param colla Colla de l0usuari a modificar
     * @param premiados Array de NumPremiado[] amb el sorteig actual
     * @return Retorna el usuari modificat
     * @throws IOException
     */
    public static Usuari ExecutarMenuModificacio(int seleccio, Usuari usr, Colla colla, NumPremiado[] premiados) throws IOException {
        switch (seleccio) {
            case 1:
                usr.nom = ComprobarNom(colla);
                break;
            case 2:
                usr.numero = LlegirInt(scan, RetornarLinia(IDIOMA, NUMCOMPRAUSUARIO), 0, MAXIMBITLLETS);
                break;
            case 3:
                usr.diners = ComprobarDiners();
                break;
            case 4:
                usr = DemanarDadesUsuari(colla, premiados);
                break;
        }
        return usr;
    }

    /**
     * Procediment per a afegir un usuari a una colla
     *
     * @param codi Codi de la colla a on afegir l'usuari
     * @param premiados Array de NumPremiado[] amb el sorteig actual
     */
    public static void AfegirUsuaris(long codi, NumPremiado[] premiados) {
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS_INDEX, "r");
            RandomAccessFile raf2 = CrearFitxer(NOM_FTX_COLLAS, "rw");
            indice2 id = LlegirIndice2(raf);
            Colla colla = null;
            long posicioColla = 0;
            while (id != null) {
                if (id.codiusuari == codi) {
                    raf2.seek(id.pos);
                    colla = LlegirColla(raf2);
                    if (colla.any == year) {
                        colla.quantMembres += AfegirUsuari(colla, premiados);
                        posicioColla = id.pos;
                        raf.seek(raf.length());
                    }
                }
                id = LlegirIndice2(raf);
            }
            if (colla != null) {
                EscriureDades(colla, raf2, posicioColla);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Procediment per mostarr totes les colles de l'any actual
     */
    public static void MostrarTotesLesColles() {
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS_INDEX, "r");
            indice2 id = LlegirIndice2(raf);
            while (id != null) {
                Colla colla = ActualitzarColla(id.pos);
                if (colla.any == year) {
                    System.out.println(RetornarLinia(IDIOMA, TEXTCODI) + ":" + id.codiusuari);
                    ActualitzarUsuari(colla);
                    ImprimirTaula(colla);
                    Imprimir4Linies();
                }
                id = LlegirIndice2(raf);

            }
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Procediment per a imprimir un espai de 4 linies
     */
    public static void Imprimir4Linies() {
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    /**
     * Procediment per a mostrar una colla específica de aquest any
     *
     * @param numero colla a imprimir
     */
    public static void MostrarColla(long numero) {
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS_INDEX, "r");
            RandomAccessFile raf2 = CrearFitxer(NOM_FTX_COLLAS, "r");
            indice2 id = LlegirIndice2(raf);
            long posicion = -1;
            while (id != null) {
                if (id.codiusuari == numero) {
                    raf2.seek(id.pos);
                    Colla colla = LlegirColla(raf2);
                    if (colla.any == year) {
                        posicion = id.pos;
                    }
                }
                id = LlegirIndice2(raf);

            }
            CerrarRAF(raf);
            if (posicion != -1) {
                Colla colla = ActualitzarColla(posicion);
                ActualitzarUsuari(colla);
                ImprimirTaula(colla);
            } else {
                System.out.println(RetornarLinia(IDIOMA, NOCOLLA));
            }
        } catch (IOException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Procediment per a actualitzar un usuari
     *
     * @param colla Objecte colla de on modificar l'usuari
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void ActualitzarUsuari(Colla colla) throws FileNotFoundException, IOException {
        RandomAccessFile raf = CrearFitxer(NOM_FTX_USR, "r");
        RandomAccessFile raf2 = CrearFitxer(NOM_FTX_USR, "rw");
        Usuari usr = LlegirUsuari(raf);
        while (usr != null) {
            if (usr.numcolla == colla.numcolla) {
                usr.premiRepartit = CalcularPremiRepartit(colla, usr);
            }
            EscriureUsuariP(usr, raf2);
            usr = LlegirUsuari(raf);
        }
        CerrarRAF(raf);
    }

    /**
     * Funció per a calcular el premi repartit
     *
     * @param colla Objecte Colla on calcular els premis
     * @param usr Usuari on actualitzar el premi repartit
     * @return Retorna el premi repartit
     */
    public static float CalcularPremiRepartit(Colla colla, Usuari usr) {
        float divisor = colla.premis / (float) colla.diners;
        return usr.diners * divisor;
    }

    /**
     * Procediment per a imprimir les taules de colles
     *
     * @param colla Colla a imprimir
     * @throws IOException
     */
    public static void ImprimirTaula(Colla colla) throws IOException {
        ImprimirCollaCamps();
        ImprimirCollaDades(colla);
        ImprimirUsuarisCamps();
        ImprimirUsuaris(colla.numcolla);
        ImprimirUltimaLinia();
    }

    /**
     * Procediment per a imprimir les dades de la colla
     *
     * @param colla Objecte colla a imprimir
     */
    public static void ImprimirCollaDades(Colla colla) {
        System.out.print("| ");
        System.out.printf("%20s", colla.any);
        System.out.print(" | ");
        System.out.printf("%9s", colla.quantMembres);
        System.out.print(" | ");
        System.out.printf("%12s", colla.diners);
        System.out.print(" | ");
        System.out.printf("%15s", colla.premis);
        System.out.println("| ");
        System.out.println("+==================================================================+");
    }

    /**
     * Procediment per a imprimir els camps de la taula de colla
     *
     * @throws IOException
     */
    public static void ImprimirCollaCamps() throws IOException {
        System.out.println("+==================================================================+");
        System.out.print("| ");
        System.out.printf("%20s", RetornarLinia(IDIOMA, ANY).toUpperCase());
        System.out.print(" | ");
        System.out.printf("%9s", RetornarLinia(IDIOMA, TEXTMEMBRES).toUpperCase());
        System.out.print(" | ");
        System.out.printf("%12s", RetornarLinia(IDIOMA, TEXTDINERS).toUpperCase());
        System.out.print(" | ");
        System.out.printf("%15s", RetornarLinia(IDIOMA, TEXTPREMIS).toUpperCase());
        System.out.println("| ");
        System.out.println("+------------------------------------------------------------------+");
    }

    /**
     * Procediment per a imprimir la ultima linia
     */
    public static void ImprimirUltimaLinia() {
        System.out.println("+==================================================================+");
    }

    /**
     * Procediment per a imprimir els camps de lña taula de usuaris
     *
     * @throws IOException
     */
    public static void ImprimirUsuarisCamps() throws IOException {
        System.out.println("+==================================================================+");
        System.out.print("| ");
        System.out.printf("%20s", RetornarLinia(IDIOMA, TEXTNOM).toUpperCase());
        System.out.print(" | ");
        System.out.printf("%9s", RetornarLinia(IDIOMA, TEXTNUMERO).toUpperCase());
        System.out.print(" | ");
        System.out.printf("%12s", RetornarLinia(IDIOMA, TEXTDINERS).toUpperCase());
        System.out.print(" | ");
        System.out.printf("%15s", RetornarLinia(IDIOMA, TEXTPREMIS).toUpperCase());
        System.out.println("| ");
        System.out.println("+------------------------------------------------------------------+");
    }

    /**
     * Procediment per a imprimir els usuaris de la colla i any seleccionats
     *
     * @param codi codi de la colla
     */
    public static void ImprimirUsuaris(long codi) {
        try {
            RandomAccessFile raf = CrearFitxer(NOM_FTX_USR, "r");
            Usuari usr = LlegirUsuari(raf);
            while (usr != null) {
                if (usr.numcolla == codi && !usr.borrat && usr.any == year) {
                    System.out.print("| ");
                    System.out.printf("%20s", usr.nom);
                    System.out.print(" | ");
                    System.out.printf("%9s", usr.numero);
                    System.out.print(" | ");
                    System.out.printf("%12s", usr.diners);
                    System.out.print(" | ");
                    System.out.printf("%15s", usr.premiRepartit);
                    System.out.println("| ");
                }
                usr = LlegirUsuari(raf);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoteriaDeNadal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Funció per a actualitzar una colla especifica
     *
     * @param codi posicio al fitxer de la colla a atualitzar
     * @return Retorna el Objecte colla actualitzat
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Colla ActualitzarColla(long codi) throws FileNotFoundException, IOException {
        RandomAccessFile raf = CrearFitxer(NOM_FTX_COLLAS, "rw");
        raf.seek(codi);
        Colla colla = LlegirColla(raf);
        colla.premis = SumarPremis(colla);
        colla.diners = SumarDiners(colla);
        EscriureDades(colla, raf, codi);
        CerrarRAF(raf);
        return colla;
    }

    /**
     * Funció per a sumar els premis de tots els usuaris a un objecte colla
     *
     * @param colla Objecte a on sumar els premis
     * @return Retorna la suma total dels premis
     * @throws FileNotFoundException
     */
    public static float SumarPremis(Colla colla) throws FileNotFoundException {
        float resultat = 0;
        RandomAccessFile raf = CrearFitxer(NOM_FTX_USR, "r");
        Usuari usr = LlegirUsuari(raf);
        while (usr != null) {
            if (usr.numcolla == colla.numcolla && !usr.borrat && usr.any == year) {
                resultat += usr.premiPersonal;
            }
            usr = LlegirUsuari(raf);
        }
        CerrarRAF(raf);
        return resultat;
    }

    /**
     * Funció per a sumar els diners de tots els usuaris a un objecte colla
     *
     * @param colla Objecte cola on sumar els diners
     * @return Retorna la suma total dels diners
     * @throws FileNotFoundException
     */
    public static int SumarDiners(Colla colla) throws FileNotFoundException {
        int resultat = 0;
        RandomAccessFile raf = CrearFitxer(NOM_FTX_USR, "r");
        Usuari usr = LlegirUsuari(raf);
        while (usr != null) {
            if (usr.numcolla == colla.numcolla && !usr.borrat && usr.any == year) {
                resultat += usr.diners;
            }
            usr = LlegirUsuari(raf);
        }
        CerrarRAF(raf);
        return resultat;
    }

// </editor-fold>
}

/**
 * Clase de dades que conté un usuari
 *
 * @author palom
 */
class Usuari {

    long numcolla;
    String nom;
    int numero;
    int diners;
    float premiPersonal;
    int premiTotal;
    float premiRepartit;
    int any;
    boolean borrat;
}

/**
 * Clase de dades que conté una colla
 *
 * @author palom
 */
class Colla {

    String nom;
    int quantMembres;
    int any;
    int diners;
    float premis;
    long numcolla;
}

/**
 * Classe per a calcular les aproximacions d'un numero
 *
 * @author palom
 */
class Aproximacio {

    int dalt;
    int baix;
}

/**
 * Clase de números premiados
 */
class NumPremiado {

    int numero;
    int premio;
}

/**
 * Clase per a guardar els indexos dels sortejos
 *
 * @author palom
 */
class indice {

    int year;
    long pos;
}

/**
 * Classe per a guardar els indexos de les colles
 *
 * @author palom
 */
class indice2 {

    long codiusuari;
    long pos;
}
