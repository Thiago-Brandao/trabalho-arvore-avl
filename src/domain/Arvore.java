package domain;

import java.util.ArrayList;
import java.util.List;

public class Arvore {

    NoArvore raiz;

    // INSERIR

    /**
     * Insere um produto na árvore AVL utilizando
     * o código do produto como chave.
     *
     * @param produto produto a ser inserido
     */
    public void inserir(Produto produto) {
        raiz = inserirRecursivo(raiz, produto);
    }

    // BUSCAR

    /**
     * Busca um produto na árvore AVL a partir
     * do código informado.
     *
     * @param codigo código do produto
     * @return produto encontrado ou null
     */
    public Produto buscarProduto(Integer codigo) {

        NoArvore resultado =
                buscarRecursivo(raiz, codigo);

        if (resultado != null) {
            return resultado.getProduto();
        }

        return null;
    }

    /**
     * Realiza a busca recursiva de um nó
     * na árvore AVL utilizando o código
     * do produto como chave.
     *
     * @param no nó atual da árvore
     * @param codigo código do produto
     * @return nó encontrado ou null
     */
    private NoArvore buscarRecursivo(
            NoArvore no,
            Integer codigo
    ) {

        if (no == null) {
            return null;
        }

        if (codigo.equals(
                no.getProduto().getCodigo())) {

            return no;
        }

        if (codigo <
                no.getProduto().getCodigo()) {

            return buscarRecursivo(
                    no.getEsquerda(),
                    codigo
            );
        }

        return buscarRecursivo(
                no.getDireita(),
                codigo
        );
    }

    // ATUALIZAR

    /**
     * Atualiza os dados de um produto existente
     * a partir do código informado.
     *
     * @param produtoAtualizado produto com os novos dados
     */
    public void atualizarProduto(Produto produtoAtualizado) {

        Produto produto =
                buscarProduto(
                        produtoAtualizado.getCodigo()
                );

        if (produto != null) {

            produto.setNome(
                    produtoAtualizado.getNome()
            );

            produto.setCategoria(
                    produtoAtualizado.getCategoria()
            );

            produto.setQuantidadeEmEstoque(
                    produtoAtualizado.getQuantidadeEmEstoque()
            );

            produto.setPreco(
                    produtoAtualizado.getPreco()
            );
        }
    }

    // LISTAR EM ORDEM

    /**
     * Método para retornar a lista de produtos em ordem crescente
     * @return lista de produtos em ordem crescente
     */
    public List<Produto> listarEmOrdemCrescente() {

        List<Produto> produtos =
                new ArrayList<>();

        emOrdem(raiz, produtos);

        return produtos;
    }

    /**
     * Percorre a árvore em ordem crescente e adiciona
     * os produtos encontrados na lista informada.
     *
     * @param no nó atual da árvore
     * @param produtos lista de produtos
     */
    private void emOrdem(
            NoArvore no,
            List<Produto> produtos
    ) {

        if (no != null) {

            emOrdem(
                    no.getEsquerda(),
                    produtos
            );

            produtos.add(
                    no.getProduto()
            );

            emOrdem(
                    no.getDireita(),
                    produtos
            );
        }
    }

    // Árvore AVL

    /**
     * Método para retornar altura da árvore AVL
     * caso o nó for nullo retorna nulo
     * @param no nó que será consultado
     * @return retornado a altura ou valor nulo caso o nó for nulo.
     */
    private int getAltura(NoArvore no) {
        return (no == null)
                ? 0
                : no.getAltura();
    }


    /**
     * Calcula o fator de balanceamento de um nó da árvore AVL.
     * O fator é obtido pela diferença entre a altura da subárvore
     * esquerda e da subárvore direita.
     *
     * @param no nó da árvore
     * @return fator de balanceamento do nó
     */
    private int getFatorBalanceamento(NoArvore no) {

        if (no == null) {
            return 0;
        }

        return getAltura(no.getEsquerda())
                - getAltura(no.getDireita());
    }

    /**
     * Realiza uma rotação simples à direita em um nó da árvore AVL
     * rotação utilizada para corrigir desbalanceamentos do tipo LL (Esquerda Esquerda)
     *
     * @param y nó desbalanceado
     * @return nova raiz da subárvore após a rotação
     */
    private NoArvore rotacaoSimplesDireita(NoArvore y) {

        NoArvore x = y.getEsquerda();
        NoArvore t2 = x.getDireita();

        x.setDireita(y);
        y.setEsquerda(t2);

        y.setAltura(
                Math.max(
                        getAltura(y.getEsquerda()),
                        getAltura(y.getDireita())
                ) + 1
        );

        x.setAltura(
                Math.max(
                        getAltura(x.getEsquerda()),
                        getAltura(x.getDireita())
                ) + 1
        );

        return x;
    }

    /**
     * Realiza uma rotação simples à esquerda em um nó da árvore AVL.
     * Essa rotação é utilizada para corrigir desbalanceamentos
     * do tipo RR (Direita Direita).
     *
     * @param x nó desbalanceado
     * @return nova raiz da subárvore após a rotação
     */
    private NoArvore rotacaoSimplesEsquerda(NoArvore x) {

        NoArvore y = x.getDireita();
        NoArvore t2 = y.getEsquerda();

        y.setEsquerda(x);
        x.setDireita(t2);

        x.setAltura(
                Math.max(
                        getAltura(x.getEsquerda()),
                        getAltura(x.getDireita())
                ) + 1
        );

        y.setAltura(
                Math.max(
                        getAltura(y.getEsquerda()),
                        getAltura(y.getDireita())
                ) + 1
        );

        return y;
    }

    /**
     * Insere um produto na árvore AVL de forma recursiva.
     * Após a inserção, realiza o balanceamento da árvore
     * por meio das rotações necessárias.
     *
     * @param no nó atual da árvore
     * @param produto produto a ser inserido
     * @return raiz atualizada da subárvore
     */
    private NoArvore inserirRecursivo(
            NoArvore no,
            Produto produto
    ) {

        if (no == null) {
            return new NoArvore(produto);
        }

        if (produto.getCodigo()
                < no.getProduto().getCodigo()) {

            no.setEsquerda(
                    inserirRecursivo(
                            no.getEsquerda(),
                            produto
                    )
            );

        } else if (produto.getCodigo()
                > no.getProduto().getCodigo()) {

            no.setDireita(
                    inserirRecursivo(
                            no.getDireita(),
                            produto
                    )
            );

        } else {
            return no;
        }

        no.setAltura(
                1 + Math.max(
                        getAltura(no.getEsquerda()),
                        getAltura(no.getDireita())
                )
        );

        int fator = getFatorBalanceamento(no);

        // ESQUERDA ESQUERDA
        if (fator > 1 &&
                produto.getCodigo()
                        < no.getEsquerda()
                        .getProduto()
                        .getCodigo()) {

            return rotacaoSimplesDireita(no);
        }

        // DIREITA DIREITA
        if (fator < -1 &&
                produto.getCodigo()
                        > no.getDireita()
                        .getProduto()
                        .getCodigo()) {

            return rotacaoSimplesEsquerda(no);
        }

        // ESQUERDA DIREITA
        if (fator > 1 &&
                produto.getCodigo()
                        > no.getEsquerda()
                        .getProduto()
                        .getCodigo()) {

            no.setEsquerda(
                    rotacaoSimplesEsquerda(
                            no.getEsquerda()
                    )
            );

            return rotacaoSimplesDireita(no);
        }

        // DIREITA ESQUERDA
        if (fator < -1 &&
                produto.getCodigo()
                        < no.getDireita()
                        .getProduto()
                        .getCodigo()) {

            no.setDireita(
                    rotacaoSimplesDireita(
                            no.getDireita()
                    )
            );

            return rotacaoSimplesEsquerda(no);
        }

        return no;
    }

    /**
     * Remove um produto da árvore AVL de forma recursiva.
     * Após a remoção, realiza o balanceamento da árvore
     * por meio das rotações necessárias.
     *
     * @param no nó atual da árvore
     * @param codigo código do produto a ser removido
     * @return raiz atualizada da subárvore
     */
    private NoArvore excluirRecursivo(
            NoArvore no,
            Integer codigo
    ) {

        if (no == null) {
            return null;
        }

        if (codigo < no.getProduto().getCodigo()) {

            no.setEsquerda(
                    excluirRecursivo(
                            no.getEsquerda(),
                            codigo
                    )
            );

        } else if (codigo > no.getProduto().getCodigo()) {

            no.setDireita(
                    excluirRecursivo(
                            no.getDireita(),
                            codigo
                    )
            );

        } else {

            // Nó com 1 filho ou nenhum
            if (no.getEsquerda() == null) {
                return no.getDireita();
            }

            if (no.getDireita() == null) {
                return no.getEsquerda();
            }

            // Nó com 2 filhos
            NoArvore menor =
                    menorValor(no.getDireita());

            no.setProduto(menor.getProduto());

            no.setDireita(
                    excluirRecursivo(
                            no.getDireita(),
                            menor.getProduto().getCodigo()
                    )
            );
        }

        // Atualiza altura
        no.setAltura(
                1 + Math.max(
                        getAltura(no.getEsquerda()),
                        getAltura(no.getDireita())
                )
        );

        int fator = getFatorBalanceamento(no);

        // ESQUERDA ESQUERDA
        if (fator > 1 &&
                getFatorBalanceamento(no.getEsquerda()) >= 0) {

            return rotacaoSimplesDireita(no);
        }

        // ESQUERDA DIREITA
        if (fator > 1 &&
                getFatorBalanceamento(no.getEsquerda()) < 0) {

            no.setEsquerda(
                    rotacaoSimplesEsquerda(
                            no.getEsquerda()
                    )
            );

            return rotacaoSimplesDireita(no);
        }

        // DIREITA DIREITA
        if (fator < -1 &&
                getFatorBalanceamento(no.getDireita()) <= 0) {

            return rotacaoSimplesEsquerda(no);
        }

        // DIREITA ESQUERDA
        if (fator < -1 &&
                getFatorBalanceamento(no.getDireita()) > 0) {

            no.setDireita(
                    rotacaoSimplesDireita(
                            no.getDireita()
                    )
            );

            return rotacaoSimplesEsquerda(no);
        }

        return no;
    }

    /**
     * método para exclusão do produto da árvoreAVL
     * utilizando o código do produto como a chave
     * de busca.
     *
     * @param codigo código do produto a ser removido
     */
    public void excluirProduto(Integer codigo) {
        raiz = excluirRecursivo(raiz, codigo);
    }

    /**
     * Atualiza a quantidade em estoque de um produto
     * a partir do código informado.
     *
     * @param codigo código do produto
     * @param novaQuantidade nova quantidade em estoque
     */
    public void atualizarQuantidade(
            Integer codigo,
            Integer novaQuantidade
    ) {

        Produto produto = buscarProduto(codigo);

        if (produto != null) {
            produto.setQuantidadeEmEstoque(novaQuantidade);
        }
    }

    /**
     * Retorna o nó com o menor valor de uma subárvore.
     * Esse método é utilizado no processo de remoção
     * de nós com dois filhos.
     *
     * @param no raiz da subárvore
     * @return nó com o menor valor encontrado
     */
    private NoArvore menorValor(NoArvore no) {

        NoArvore atual = no;

        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }

        return atual;
    }
}
