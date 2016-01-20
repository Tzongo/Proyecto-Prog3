package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.PlantasVsZombies;
public class Test1 {

	public PlantasVsZombies plantasVsZombies;
	  
    @Before  
    public void antesDelTest() {  
        /** 
         * El metodo precedido por la etiqueta @Before 
         * es para indicar a JUnit que debe ejecutarlo 
         * antes de ejecutar los Tests que figuran en 
         * esta clase. 
         */  
        this.plantasVsZombies.main(null);
    }  
  
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
