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
        int result = LoteriaDeNadal.ComprobarBombo(cupon, premiados);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of ComprobarBombo method, of class LoteriaDeNadal. Premio segundo
     */
    @Test
    public void testComprobarPremioSegundo() {
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[1].numero;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.SEGONPREMI) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.SEGONPREMI +LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Segundo con Reintegro");
        }
        else {
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
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[2].numero;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.TERCERPREMI) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.TERCERPREMI +LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Tercero con Reintegro");
        }
        else {
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
        
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[3].numero;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.QUARTPREMI) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.QUARTPREMI +LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Cuarto con Reintegro");
        }
        else {
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
        
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[5].numero;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.QUINTOPREMI) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.QUINTOPREMI +LoteriaDeNadal.REINTEGRO;
            System.out.println("ComprobarPremio Quinto con Reintegro");
        }
        else {
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
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        LoteriaDeNadal.BubbleSortPremis(premiados);
        int cupon = premiados[20].numero;
        int expResult;
        if (LoteriaDeNadal.ComprobarReintegro(cupon, premiados, LoteriaDeNadal.PEDREADA) == LoteriaDeNadal.REINTEGRO) {
            expResult = LoteriaDeNadal.PEDREADA +200;
            System.out.println("ComprobarPremio Pedreada con Reintegro");
        }
        else {
            expResult = LoteriaDeNadal.PEDREADA;
            System.out.println("ComprobarPremio Pedreada");
        }
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }
    

    
    

    
}
