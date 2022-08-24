package pedido;

import ingredientes.Adicional;

import java.util.ArrayList;

public class Pedido{

    private int id;
    private  ArrayList<ItemPedido> itens;
    private Cliente cliente;

    public Pedido(int id, ArrayList<ItemPedido> itens,Cliente cliente){
        this.id = id;
        this.itens=itens;
        this.cliente=cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public int getId(){
        return this.id;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public double calcularTotal(Cardapio cardapio){
        double total= 0;
        for(ItemPedido itemPedido : itens ){
            double itemPreco = cardapio.buscarPreco(itemPedido.getShake().getBase())* itemPedido.getShake().getTipoTamanho().getMultiplicador();
            for (Adicional adicional : itemPedido.getShake().getAdicionais()) {
                itemPreco += cardapio.buscarPreco(adicional);
            }
            total+=itemPreco*itemPedido.getQuantidade();

        }

        return total;
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado){

        boolean duaplicado = false;

        for(ItemPedido item : itens){
            if(item.getShake().equals(itemPedidoAdicionado.getShake())){
                item.setQuantidade(item.getQuantidade() + itemPedidoAdicionado.getQuantidade());
                duaplicado = true;
                break;
            }
        }

        if(!duaplicado){
            itens.add(itemPedidoAdicionado);
        }

    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        //substitua o true por uma condição
        boolean existe = false;
        for(ItemPedido item : itens){
            if(item.getShake().toString().equals(itemPedidoRemovido.getShake().toString())){
                item.setQuantidade(item.getQuantidade() - 1);

                existe = true;

                if(item.getQuantidade() == 0){
                    itens.remove(item);
                }
                break;
            }
        }

        if(!existe) throw new IllegalArgumentException("Item nao existe no pedido.");
        return false;
    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
