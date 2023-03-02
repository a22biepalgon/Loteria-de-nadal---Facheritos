/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package loteria.de.nadal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ausias
 */
public class LoteriaDeNadalTest {

    public LoteriaDeNadalTest() {
    }
    static NumPremiado[] premiados = LoteriaDeNadal.Sorteo();

    @BeforeClass
    public static void setUpClass() {
        LoteriaDeNadal.BubbleSortPremis(premiados);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void testLecturaUsuari() {
        Usuari usr = null;
        boolean expResult = true;
        boolean result = false;
        File f = new File(LoteriaDeNadal.NOM_FTX_USR);
        if (f.exists()) {

            try {
                RandomAccessFile raf = new RandomAccessFile(LoteriaDeNadal.NOM_FTX_USR, "r");
                usr = LoteriaDeNadal.LlegirUsuari(raf);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoteriaDeNadalTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (usr != null) {
                result = true;
            }
        }
        //Si dona error probablement no exiteixi el fitxer
        assertEquals(expResult, result);
    }

    @Test
    public void testComprobarIdiomaCastella() {
        final int NUMEROPROVA = 1;
        System.out.println("Comprobar menu idioma castella");
        String expResult = "ca.txt";
        String result = LoteriaDeNadal.GestionMenuIdioma(NUMEROPROVA);
        assertEquals(expResult, result);
    }

    @Test
    public void testComprobarIdiomaCatala() {
        final int NUMEROPROVA = 1;
        System.out.println("Comprobar menu idioma catala");
        String expResult = "ca.txt";
        String result = LoteriaDeNadal.GestionMenuIdioma(NUMEROPROVA);
        assertEquals(expResult, result);
    }

    @Test
    public void testComprobarIdiomadefault() {
        final int NUMEROPROVA = 80;
        System.out.println("Comprobar menu idioma default");
        String expResult = "es.txt";
        String result = LoteriaDeNadal.GestionMenuIdioma(NUMEROPROVA);
        assertEquals(expResult, result);
    }

    @Test
    public void testComprobarSorteOrdenado() {
        boolean result = false;
        System.out.println("Comprobar Ordenación del sorteo");
        if (premiados[0].premio == LoteriaDeNadal.GORDO) {
            if (premiados[1].premio == LoteriaDeNadal.SEGONPREMI) {
                if (premiados[2].premio == LoteriaDeNadal.TERCERPREMI) {
                    if (premiados[3].premio == LoteriaDeNadal.QUARTPREMI && premiados[4].premio == LoteriaDeNadal.QUARTPREMI) {
                        if (premiados[5].premio == LoteriaDeNadal.QUINTOPREMI && premiados[6].premio == LoteriaDeNadal.QUINTOPREMI && premiados[7].premio == LoteriaDeNadal.QUINTOPREMI && premiados[8].premio == LoteriaDeNadal.QUINTOPREMI && premiados[9].premio == LoteriaDeNadal.QUINTOPREMI && premiados[10].premio == LoteriaDeNadal.QUINTOPREMI && premiados[11].premio == LoteriaDeNadal.QUINTOPREMI && premiados[12].premio == LoteriaDeNadal.QUINTOPREMI) {
                            if (premiados[198].premio == LoteriaDeNadal.PEDREADA) {
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    @Test
    public void testComprobarCantidadPremios() {
        System.out.println("Comprobar quantitat de premis");
        boolean result = false;
        int[] temp = new int[6];

        for (int i = 0; i < premiados.length; i++) {
            switch (premiados[i].premio) {
                case LoteriaDeNadal.GORDO:
                    temp[0]++;
                    break;
                case LoteriaDeNadal.SEGONPREMI:
                    temp[1]++;
                    break;
                case LoteriaDeNadal.TERCERPREMI:
                    temp[2]++;
                    break;
                case LoteriaDeNadal.QUARTPREMI:
                    temp[3]++;
                    break;
                case LoteriaDeNadal.QUINTOPREMI:
                    temp[4]++;
                    break;
                case LoteriaDeNadal.PEDREADA:
                    temp[5]++;
                    break;
                default:
                    break;
            }
        }
        if (temp[0] == LoteriaDeNadal.quantGordo) {
            if (temp[1] == LoteriaDeNadal.quantSegund) {
                if (temp[2] == LoteriaDeNadal.quantTercer) {
                    if (temp[3] == LoteriaDeNadal.quantCuart) {
                        if (temp[4] == LoteriaDeNadal.quantQuint) {
                            if (temp[5] == LoteriaDeNadal.QUANTITATPREMIS - LoteriaDeNadal.quantPremis) {
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    @Test
    public void testComprobarNumeroRepetidos() {
        System.out.println("");
        boolean result = true;
        int[] temp = new int[99999];
        for (int i = 0; i < premiados.length; i++) {
            temp[premiados[i].numero]++;
            if (temp[premiados[i].numero] > 1) {
                result = false;
            }
        }
        boolean expResult = true;
        assertEquals(expResult, result);

    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio Gordo
     */
    @Test
    public void testComprobarPremioGordo() {
        System.out.println("ComprobarPremio Gordo");
        int cupon = premiados[0].numero;
        int expResult = LoteriaDeNadal.GORDO;
        int result = LoteriaDeNadal.ComprobarBombo(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarBombo method, of class LoteriaDeNadal. Premio segundo
     */
    @Test
    public void testComprobarPremioSegundo() {
        int cupon = premiados[1].numero;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.SEGONPREMI) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.SEGONPREMI + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Segundo con Reintegro");
        } else {
            expResult = LoteriaDeNadal.SEGONPREMI;
            System.out.println("ComprobarPremio Segundo");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio tercero
     */
    @Test
    public void testComprobarPremioTercero() {
        int cupon = premiados[2].numero;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.TERCERPREMI) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.TERCERPREMI + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Tercero con Reintegro");
        } else {
            expResult = LoteriaDeNadal.TERCERPREMI;
            System.out.println("ComprobarPremio Tercero");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio cuarto
     */
    @Test
    public void testComprobarPremioCuarto() {
        int cupon = premiados[3].numero;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.QUARTPREMI) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.QUARTPREMI + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Cuarto con Reintegro");
        } else {
            expResult = LoteriaDeNadal.QUARTPREMI;
            System.out.println("ComprobarPremio Cuarto");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio quinto
     */
    @Test
    public void testComprobarPremioQuinto() {
        int cupon = premiados[5].numero;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.QUINTOPREMI) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.QUINTOPREMI + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Quinto con Reintegro");
        } else {
            expResult = LoteriaDeNadal.QUINTOPREMI;
            System.out.println("ComprobarPremio Quinto");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio pedrea
     */
    @Test
    public void testComprobarPremioPedreada() {
        int cupon = premiados[20].numero;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.PEDREADA) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.PEDREADA + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Pedreada con Reintegro");
        } else {
            expResult = LoteriaDeNadal.PEDREADA;
            System.out.println("ComprobarPremio Pedreada");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio
     * Aproximación primer premio -1
     */
    @Test
    public void testComprobarPremioAproximacionPrimero() {
        int cupon = premiados[0].numero - 1;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.PREMIAPROX1) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.PREMIAPROX1 + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Aproximación primero -1 con Reintegro");
        } else {
            expResult = LoteriaDeNadal.PREMIAPROX1;
            System.out.println("ComprobarPremio Aproximación Primero -1");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio
     * Aproximación primer premio +1
     */
    @Test
    public void testComprobarPremioAproximacionPrimero2() {
        int cupon = premiados[0].numero + 1;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.PREMIAPROX1) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.PREMIAPROX1 + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Aproximación Primero +1 con Reintegro");
        } else {
            expResult = LoteriaDeNadal.PREMIAPROX1;
            System.out.println("ComprobarPremio Aproximación Primero +1");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio
     * Aproximación segundo premio -1
     */
    @Test
    public void testComprobarPremioAproximacionSegundo() {
        int cupon = premiados[1].numero - 1;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.PREMIAPROX2) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.PREMIAPROX2 + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Aproximación Segundo -1 con Reintegro");
        } else {
            expResult = LoteriaDeNadal.PREMIAPROX2;
            System.out.println("ComprobarPremio Aproximación Segundo -1");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio
     * Aproximación segundo premio +1
     */
    @Test
    public void testComprobarPremioAproximacionSegundo2() {
        int cupon = premiados[1].numero + 1;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.PREMIAPROX2) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.PREMIAPROX2 + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Aproximación Segundo +1 con Reintegro");
        } else {
            expResult = LoteriaDeNadal.PREMIAPROX2;
            System.out.println("ComprobarPremio Aproximación Segundo +1");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio
     * Aproximación tercer premio -1
     */
    @Test
    public void testComprobarPremioAproximacionTercero() {
        int cupon = premiados[2].numero - 1;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.PREMIAPROX3) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.PREMIAPROX3 + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Aproximación Tercero -1 con Reintegro");
        } else {
            expResult = LoteriaDeNadal.PREMIAPROX3;
            System.out.println("ComprobarPremio Aproximación Tercero -1");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio
     * Aproximación tercrer premio +1
     */
    @Test
    public void testComprobarPremioAproximacionTercero2() {
        int cupon = premiados[2].numero + 1;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.PREMIAPROX3) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.PREMIAPROX3 + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Aproximación Tercero +1 con Reintegro");
        } else {
            expResult = LoteriaDeNadal.PREMIAPROX3;
            System.out.println("ComprobarPremio Aproximación Tercero +1");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio Centena
     * de un 4to premio
     */
    @Test
    public void testComprobarPremioCentena() {

        int cupon = premiados[0].numero;
        if (cupon % 100 > 50) {
            cupon = cupon - 50;
        } else if (cupon % 100 <= 50) {
            cupon = cupon + 49;
        }
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.PREMICENTENA) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.PREMICENTENA + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Centena con Reintegro");
        } else {
            expResult = LoteriaDeNadal.PREMICENTENA;
            System.out.println("ComprobarPremio Centena");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio 2 últimas
     * cifras
     */
    @Test
    public void testComprobarPremio2Ultimas() {
        int cupon = premiados[0].numero % 100;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.PREMI2ULTIMAS) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.PREMI2ULTIMAS + LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio 2 últimas con Reintegro");
        } else {
            expResult = LoteriaDeNadal.PREMI2ULTIMAS;
            System.out.println("ComprobarPremio 2 últimas");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }
}
