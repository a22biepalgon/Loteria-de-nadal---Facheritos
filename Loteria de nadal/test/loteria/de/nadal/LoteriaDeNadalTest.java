/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package loteria.de.nadal;

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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    

    

    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio Gordo
     */
    @Test
    public void testComprobarPremioGordo() {
        System.out.println("ComprobarPremio Gordo");
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[0].numero;
        int expResult = LoteriaDeNadal.GORDO;
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio segundo
     */
    @Test
    public void testComprobarPremioSegundo() {
        System.out.println("ComprobarPremio Segundo");
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[1].numero;
        int expResult;
        if (cupon%10 == premiados[0].numero%10) {
            expResult = LoteriaDeNadal.SEGONPREMI +200;
        }
        else {
            expResult = LoteriaDeNadal.SEGONPREMI;
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio tercero
     */
    @Test
    public void testComprobarPremioTercero() {
        System.out.println("ComprobarPremio Tercero");
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[2].numero;
        int expResult;
        if (cupon%10 == premiados[0].numero%10) {
            expResult = LoteriaDeNadal.TERCERPREMI +200;
        }
        else {
            expResult = LoteriaDeNadal.TERCERPREMI;
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio cuarto
     */
    @Test
    public void testComprobarPremioCuarto() {
        System.out.println("ComprobarPremio Cuarto");
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[3].numero;
        int expResult;
        if (cupon%10 == premiados[0].numero%10) {
            expResult = LoteriaDeNadal.QUARTPREMI +200;
        }
        else {
            expResult = LoteriaDeNadal.QUARTPREMI;
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio quinto
     */
    @Test
    public void testComprobarPremioQuinto() {
        System.out.println("ComprobarPremio Quinto");
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[5].numero;
        int expResult;
        if (cupon%10 == premiados[0].numero%10) {
            expResult = LoteriaDeNadal.QUINTOPREMI +200;
        }
        else {
            expResult = LoteriaDeNadal.QUINTOPREMI;
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }
    /**
     * Test of ComprobarPremio method, of class LoteriaDeNadal. Premio pedrea
     */
    @Test
    public void testComprobarPremioPedreada() {
        System.out.println("ComprobarPremio Pedrea");
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[20].numero;
        int expResult;
        if (cupon%10 == premiados[0].numero%10) {
            expResult = LoteriaDeNadal.PEDREADA +200;
        }
        else {
            expResult = LoteriaDeNadal.PEDREADA;
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }
    
    

    
}
