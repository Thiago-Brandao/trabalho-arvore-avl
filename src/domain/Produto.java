package domain;

public class Produto {

    private Integer codigo;
    private String nome;
    private String categoria;
    private Integer quantidadeEmEstoque;
    private Double preco;


    public Produto() {
    }

    // Contrutor
    public Produto(Integer codigo, String nome, String categoria, Integer quantidadeEmEstoque, Double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.preco = preco;
    }

    // Geters e Seters


    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(Integer quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
