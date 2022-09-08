package armazem;

import ingredientes.Fruta;
import ingredientes.Ingrediente;
import ingredientes.TipoFruta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArmazemTest {

    private Armazem armazem;

    @BeforeEach
    void setUp(){
        this.armazem = new Armazem();
    }

    @Test
    void catasrarIngredienteEmEstoque_property(){

        Ingrediente ingredienteTest = new Fruta(TipoFruta.Morango);
        armazem.cadastarIngredienteEmEstoque(ingredienteTest);
        Assertions.assertTrue(armazem.getEstoque().containsKey(ingredienteTest));
        Assertions.assertEquals(0, armazem.consultarQuantidadeDoIngredienteEmEstoque(ingredienteTest));

    }

    @Test
    void catasrarIngredienteEmEstoque_IngredienteJaCadastrado(){

        Ingrediente ingrediente = new Fruta(TipoFruta.Morango);
        try{
            armazem.cadastarIngredienteEmEstoque(ingrediente);
            armazem.cadastarIngredienteEmEstoque(ingrediente);
        }catch (Throwable e ){
            Assertions.assertEquals("Ingrediente já cadastrado", e.getMessage());
            Assertions.assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void descadastrarIngredienteEmEstoque() {

            Ingrediente ingredienteTest = new Fruta(TipoFruta.Morango);
            armazem.cadastarIngredienteEmEstoque(ingredienteTest);
            armazem.descadastrarIngredienteDoEstoque(ingredienteTest);
            Assertions.assertFalse(armazem.getEstoque().containsKey(ingredienteTest));
    }

    @ParameterizedTest
    @CsvSource(value = "9, 9")
    void adicionarQuantidadeDoIngredienteEmEstoque(int quatidadeParaAdicionar, int total){

        Ingrediente ingredienteTest = new Fruta(TipoFruta.Morango);
        armazem.cadastarIngredienteEmEstoque(ingredienteTest);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingredienteTest, quatidadeParaAdicionar);

        Assertions.assertTrue(armazem.getEstoque().containsKey(ingredienteTest));
        Assertions.assertEquals(total, armazem.consultarQuantidadeDoIngredienteEmEstoque(ingredienteTest));
    }

    @Test
    void deveRetornarExcecaoAoAdicionarQuantidadeAIngredienteNaoEncontrado(){

        Ingrediente ingredienteTest = new Fruta(TipoFruta.Morango);

        try {
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingredienteTest, 9);
        }catch (Throwable e ){
            Assertions.assertEquals("Ingrediente não encontrado", e.getMessage());
            Assertions.assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void deveRetornarExcecaoAoAdicionarQuantidadeSomadaMenorOuIgualZero() {
        Ingrediente ingredienteTest = new Fruta(TipoFruta.Morango);
        try {
            armazem.cadastarIngredienteEmEstoque(ingredienteTest);
            armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingredienteTest, 0);
        }catch (Throwable e ) {
            Assertions.assertEquals("Quantidade Inválida", e.getMessage());
            Assertions.assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void reduzirQuantidadeDoIngredienteEmEstoque() {

         Ingrediente ingrediente = new Fruta(TipoFruta.Morango);
         armazem.cadastarIngredienteEmEstoque(ingrediente);
         armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingrediente, 3);
         armazem.reduzirQuantidadeDoIngredienteEmEstoque(ingrediente, 2);
         Assertions.assertEquals(1,armazem.consultarQuantidadeDoIngredienteEmEstoque(ingrediente));
    }

    @Test
    void reduzirQuantidadeDeveRetornarExececaoCasoIngredienteNaoExista() {

        Ingrediente ingredienteTest = new Fruta(TipoFruta.Morango);

        try {
            armazem.reduzirQuantidadeDoIngredienteEmEstoque(ingredienteTest, 2);
        }catch (Throwable e) {
            Assertions.assertEquals("Ingrediente não encontrado", e.getMessage());
            Assertions.assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void reduzirQuantidadeDeveRetornarExcecaoCasoQuantidadeForMenorOuIgualAZero() {

        Ingrediente ingredienteTest = new Fruta(TipoFruta.Morango);
        armazem.cadastarIngredienteEmEstoque(ingredienteTest);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingredienteTest, 1);

        try {
            armazem.reduzirQuantidadeDoIngredienteEmEstoque(ingredienteTest, 2);
        }catch (Throwable e) {
            Assertions.assertEquals("Quantidade Inválida", e.getMessage());
            Assertions.assertEquals(IllegalArgumentException.class, e.getClass());
        }
    }

    @Test
    void consultarQuantidadeDoIngredienteEmEstoque(){

        Ingrediente ingredienteTest = new Fruta(TipoFruta.Morango);
        armazem.cadastarIngredienteEmEstoque(ingredienteTest);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(ingredienteTest, 2);

        armazem.consultarQuantidadeDoIngredienteEmEstoque(ingredienteTest);

        Assertions.assertEquals(2,armazem.consultarQuantidadeDoIngredienteEmEstoque(ingredienteTest));
    }

    @Test
    void consultarQuantidadeDoIngredienteEmEstoqueCasoIngredienteNaoExista(){

        Ingrediente ingredienteTest = new Fruta(TipoFruta.Morango);
        armazem.cadastarIngredienteEmEstoque(ingredienteTest);

        try {
            armazem.consultarQuantidadeDoIngredienteEmEstoque(ingredienteTest);
        } catch (Throwable e){
            Assertions.assertEquals("Ingrediente não encontrado", e.getMessage());
            Assertions.assertEquals(IllegalArgumentException.class, e.getClass());
        }


    }

}
