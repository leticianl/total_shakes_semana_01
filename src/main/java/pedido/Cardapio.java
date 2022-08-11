package pedido;

import ingredientes.Ingrediente;

import java.util.TreeMap;

public class Cardapio {
    private TreeMap<Ingrediente,Double> precos;

    public Cardapio(){
        this.precos= new TreeMap<>();
    }

    public TreeMap<Ingrediente, Double> getPrecos(){
        return this.precos;
    }

    public void adicionarIngrediente(Ingrediente ingrediente,Double preco){
        if(preco > 0.0){
            this.precos.put(ingrediente, preco);

        }else{
            throw new IllegalArgumentException("Preco invalido.");
        }
    }

    public boolean atualizarIngrediente(Ingrediente ingrediente,Double preco){
        if (preco > 0.0) {
            if (this.precos.containsKey(ingrediente)) {
                this.precos.replace(ingrediente, preco);
                return true;
            } else {
                throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
            }
        }else{
            throw new IllegalArgumentException("Preco invalido.");
        }
    }

    public boolean removerIngrediente(Ingrediente ingrediente){
        if(this.precos.containsKey(ingrediente)){
            precos.remove(ingrediente);
            return true;
        }else{
            throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
        }
    }

    public Double buscarPreco(Ingrediente ingrediente){
        if(this.precos.containsKey(ingrediente)) {
            return precos.get(ingrediente);
        }else{
            throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
        }
    }

    @Override
    public String toString() {
        return this.precos.toString();
    }

}
