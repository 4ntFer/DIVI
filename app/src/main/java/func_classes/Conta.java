package func_classes;

public class Conta {
    private float valor;
    private Pessoa relacionado;
    private Produto produto;

    public Conta(float valor, Pessoa rel, Produto prod){
        relacionado=rel;
        this.valor = valor;
        produto = prod;
    }

    public float getValor(){
        return valor;
    }

    public Produto getProduto(){
        return produto;
    }
}
