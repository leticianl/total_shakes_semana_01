package armazem;

import ingredientes.Ingrediente;

import java.util.TreeMap;

public class Armazem {

    private TreeMap<Ingrediente, Integer> estoque;
    public Armazem(){
        this.estoque = new TreeMap<>();
    }

    public TreeMap<Ingrediente, Integer> getEstoque(){

        return this.estoque;
    }

    private int getQuantidade(Ingrediente ingrediente){

        return this.estoque.get(ingrediente);
    }

    public void cadastarIngredienteEmEstoque(Ingrediente ingrediente) {

        if(!estoque.containsKey(ingrediente)){
            estoque.put(ingrediente, 0);
        }else{
            throw new IllegalArgumentException("Ingrediente já cadastrado");
        }
    }


    public int consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {

        if(estoque.containsKey(ingrediente)){
            return getQuantidade(ingrediente);
        }else{
            throw new IllegalArgumentException("Ingrediente não cadatrado");
        }
    }


    public void descadastrarIngredienteDoEstoque(Ingrediente ingrediente) {

        if(estoque.containsKey(ingrediente)){
            estoque.remove(ingrediente);
        }else{
            throw new IllegalArgumentException("Ingrediente não encontrado");

        }
    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {

        if(quantidade > 0) {
            if(estoque.containsKey(ingrediente)){
                estoque.put(ingrediente, getQuantidade(ingrediente) + quantidade);
            }else {
                throw new IllegalArgumentException("Ingrediente não encontrado");
            }
        }else {
            throw new IllegalArgumentException("Quantidade Inválida");
        }
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade){

        if(estoque.containsKey(ingrediente)){

            if(getQuantidade(ingrediente) == 0){
                descadastrarIngredienteDoEstoque(ingrediente);
            }
            if(getQuantidade(ingrediente) < 0){
                throw new IllegalArgumentException("Quantidade Inválida");
            }
            estoque.put(ingrediente, getQuantidade(ingrediente) - quantidade);
        }else {
          throw new IllegalArgumentException("Ingrediente não encontrado");
        }
    }



}
