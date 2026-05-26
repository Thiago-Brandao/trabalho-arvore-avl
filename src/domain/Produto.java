package domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Produto {

    private Integer codigo;
    private String nome;
    private String categoria;
    private Integer quantidadeEmEstoque;
    private Double preco;

}

