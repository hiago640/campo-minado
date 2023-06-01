package br.com.hiago640.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.hiago640.cm.excecao.ExplosaoException;

class CampoTest {

    private Campo campo = new Campo(3, 3);

    @Test
    void vizinhoRealTest() {
        Campo vizinho = new Campo(3, 2);
        boolean res = campo.adicionarVizinho(vizinho);

        assertTrue(res);
    }

    @Test
    void vizinhoReal1Distancia1DireitaTest() {
        Campo vizinho = new Campo(3, 4);
        boolean res = campo.adicionarVizinho(vizinho);

        assertTrue(res);
    }

    @Test
    void vizinhoReal1Distancia1EmCimaTest() {
        Campo vizinho = new Campo(2, 3);
        boolean res = campo.adicionarVizinho(vizinho);

        assertTrue(res);
    }

    @Test
    void vizinhoReal1Distancia2EsquerdaTest() {
        Campo vizinho = new Campo(2, 2);
        boolean res = campo.adicionarVizinho(vizinho);

        assertTrue(res);
    }

    @Test
    void vizinhoNaoIncluidoTest() {
        Campo vizinho = new Campo(1, 2);
        boolean res = campo.adicionarVizinho(vizinho);

        assertFalse(res);
    }
    
    @Test
    void valorPadraoAtributoMarcadoTest() {
    	assertFalse(campo.isMarcado());
    }
    
    @Test
    void valorAlternarMarcacaoTest() {
    	campo.alternarMarcacao();
    	
    	assertTrue(campo.isMarcado());
    }
    
    @Test
    void valorAlternarMarcacaoDuasChamadasTest() {
    	campo.alternarMarcacao();
    	campo.alternarMarcacao();
    	
    	assertFalse(campo.isMarcado());
    }
    
    @Test
    void abrirCampoNaoMinadoTest() {
    	assertTrue(campo.abrir());
    }
    
    @Test
    void abrirCampoNaoMinadoMarcadoTest() {
    	campo.alternarMarcacao();
    	
    	assertFalse(false);
    }
    
    @Test
    void abrirCampoMinadoMarcadoTest() {
    	campo.alternarMarcacao();
    	campo.minar();
    	
    	assertFalse(campo.abrir());
    }
    
    @Test
    void abrirCampoMinadoNaoMarcadoTest() {
    	campo.minar();
    	
    	assertThrows(ExplosaoException.class, () -> campo.abrir());
    }
 
    @Test
    void abrirCampoComVizinhosMinadoTest() {

    	Campo campo11 = new Campo(1, 1);
    	Campo campo12 = new Campo(1, 2);
    	
    	campo12.minar();
    	
    	Campo campo22 = new Campo(2, 2);
    	
    	campo22.adicionarVizinho(campo11);
    	campo22.adicionarVizinho(campo12);
    	
    	campo.adicionarVizinho(campo22);
    	campo.abrir(); 
    	
    	assertTrue(campo22.isAberto() && !campo11.isAberto());
    }
    
}
