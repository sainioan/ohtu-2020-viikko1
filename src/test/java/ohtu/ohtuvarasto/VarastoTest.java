package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto storage2;
    Varasto storage3;
    Varasto storage4;
    Varasto storage5;
    double delta = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        storage2 = new Varasto(-5);
        
        storage3 = new Varasto(10,20.0);
        storage4 = new Varasto(-5,-5.0);
        storage5 = new Varasto(100,20.0);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), delta);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), delta);
    }
    @Test
    public void volumeToZero(){
        assertEquals(0, storage2.getTilavuus(), delta);
    }
     @Test
    public void balanceSetCorrectly() {
        assertEquals(10.0, storage3.getSaldo(), delta);
    }
     @Test
    public void balanceSetCorrectly2() {
        assertEquals(20.0, storage5.getSaldo(), delta);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), delta);
    }
    @Test 
    public void stringWorks() {
        String s =  varasto.toString();
        String res = "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu();
        assertEquals(res, "aaa");
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), delta);
    }
       @Test
    public void addInValid() {
        varasto.lisaaVarastoon(-1);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(10, varasto.getTilavuus(), delta);
    }
          @Test
    public void addInWasted() {
        varasto.lisaaVarastoon(10000);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(10, varasto.getTilavuus(), delta);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, delta);
    }
    @Test
    public void inValidTake() {
        double res = varasto.otaVarastosta(-8);


        assertEquals(0.0, res, delta);
    }
    @Test
    public void takeExceedsBalance() {
        double res = varasto.otaVarastosta(800000);


        assertEquals(0, res, delta);
    }
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), delta);
    }

}