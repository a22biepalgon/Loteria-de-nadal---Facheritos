/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package loteria.de.nadal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author ausias
 */
public final class LoteriaDeNadalTest {

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal.
     */
    @Test
    public void testComprobarPremio() {
        System.out.println("Comprovant el cupon");
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        int cupon = -1 ;
        int expResult = 0;
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class LoteriaDeNadal.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        LoteriaDeNadal.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of BubbleSortPremis method, of class LoteriaDeNadal.
     */
    @Test
    public void testBubbleSortPremis() {
        System.out.println("BubbleSortPremis");
        LoteriaDeNadal.NumPremiado[] array = null;
        LoteriaDeNadal.BubbleSortPremis(array);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of MostrarPremios method, of class LoteriaDeNadal.
     */
    @Test
    public void testMostrarPremios() {
        System.out.println("MostrarPremios");
        LoteriaDeNadal.NumPremiado[] num_premi = null;
        String paraulaNumero = "";
        String paraulaPremio = "";
        LoteriaDeNadal.MostrarPremios(num_premi, paraulaNumero, paraulaPremio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of CrearBomboPremios method, of class LoteriaDeNadal.
     */
    @Test
    public void testCrearBomboPremios() {
        System.out.println("CrearBomboPremios");
        int[] expResult = null;
        int[] result = LoteriaDeNadal.CrearBomboPremios();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ActualizarBomboPremios method, of class LoteriaDeNadal.
     */
    @Test
    public void testActualizarBomboPremios() {
        System.out.println("ActualizarBomboPremios");
        int position = 0;
        int[] bomboPremios = null;
        LoteriaDeNadal.ActualizarBomboPremios(position, bomboPremios);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Sorteo method, of class LoteriaDeNadal.
     */
    @Test
    public void testSorteo() {
        System.out.println("Sorteo");
        LoteriaDeNadal.NumPremiado[] expResult = null;
        LoteriaDeNadal.NumPremiado[] result = LoteriaDeNadal.Sorteo();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of darNombre method, of class LoteriaDeNadal.
     */
    @Test
    public void testDarNombre() {
        System.out.println("darNombre");
        int nombre = 0;
        String expResult = "";
        String result = LoteriaDeNadal.darNombre(nombre);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ComprobarReintegro method, of class LoteriaDeNadal.
     */
    @Test
    public void testComprobarReintegro() {
        System.out.println("ComprobarReintegro");
        int cupon = 0;
        LoteriaDeNadal.NumPremiado[] premiados = null;
        int premioactual = 0;
        int expResult = 0;
        int result = LoteriaDeNadal.ComprobarReintegro(cupon, premiados, premioactual);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ComprobarUltimas method, of class LoteriaDeNadal.
     */
    @Test
    public void testComprobarUltimas() {
        System.out.println("ComprobarUltimas");
        int cupon = 0;
        LoteriaDeNadal.NumPremiado[] premiados = null;
        int expResult = 0;
        int result = LoteriaDeNadal.ComprobarUltimas(cupon, premiados);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ComprobarCentena method, of class LoteriaDeNadal.
     */
    @Test
    public void testComprobarCentena() {
        System.out.println("ComprobarCentena");
        int cupon = 0;
        LoteriaDeNadal.NumPremiado[] premiados = null;
        int expResult = 0;
        int result = LoteriaDeNadal.ComprobarCentena(cupon, premiados);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ComprobarAproximacion method, of class LoteriaDeNadal.
     */
    @Test
    public void testComprobarAproximacion() {
        System.out.println("ComprobarAproximacion");
        int cupon = 0;
        LoteriaDeNadal.NumPremiado[] premiados = null;
        int expResult = 0;
        int result = LoteriaDeNadal.ComprobarAproximacion(cupon, premiados);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ComprobarBombo method, of class LoteriaDeNadal.
     */
    @Test
    public void testComprobarBombo() {
        System.out.println("ComprobarBombo");
        int cupon = 0;
        LoteriaDeNadal.NumPremiado[] premiados = null;
        int expResult = 0;
        int result = LoteriaDeNadal.ComprobarBombo(cupon, premiados);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ComprobarBombo2 method, of class LoteriaDeNadal.
     */
    @Test
    public void testComprobarBombo2() {
        System.out.println("ComprobarBombo2");
        int cupon = 0;
        LoteriaDeNadal.NumPremiado[] premiados = null;
        int expResult = 0;
        int result = LoteriaDeNadal.ComprobarBombo2(cupon, premiados);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nombrePremiado method, of class LoteriaDeNadal.
     */
    @Test
    public void testNombrePremiado() {
        System.out.println("nombrePremiado");
        int premio = 0;
        LoteriaDeNadal.NumPremiado[] premiados = null;
        int expResult = 0;
        int result = LoteriaDeNadal.nombrePremiado(premio, premiados);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
