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
     * Test of ComprobarPremio method, of class LoteriaDeNadal.
     */
    @Test
    public void testComprobarPremio() {
        System.out.println("ComprobarPremio");
        
        LoteriaDeNadal.NumPremiado[] premiados = LoteriaDeNadal.Sorteo();
        int cupon = premiados[0].numero;
        int expResult = premiados[0].premio;
        int result = LoteriaDeNadal.ComprobarPremio(cupon, premiados);
        assertEquals(expResult, result);
    }

    
}
