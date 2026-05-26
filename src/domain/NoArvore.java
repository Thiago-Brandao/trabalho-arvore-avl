package domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoArvore {

    private Produto produto;
    private NoArvore esquerda;
    private NoArvore direita;

    private int altura;

    public NoArvore(Produto produto) {
        this.produto = produto;
        this.altura = 1;
    }

}