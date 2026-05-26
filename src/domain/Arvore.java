package domain;

import java.util.ArrayList;
import java.util.List;

public class Arvore {

    NoArvore raiz;

    // INSERIR

    public void inserir(Produto produto) {
        raiz = inserirRecursivo(raiz, produto);
    }

    // BUSCAR
    public Produto buscarProduto(Integer codigo) {

        NoArvore resultado =
                buscarRecursivo(raiz, codigo);

        if (resultado != null) {
            return resultado.getProduto();
        }

        return null;
    }

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

    public List<Produto> listarEmOrdemCrescente() {

        List<Produto> produtos =
                new ArrayList<>();

        emOrdem(raiz, produtos);

        return produtos;
    }

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


    private int getAltura(NoArvore no) {
        return (no == null)
                ? 0
                : no.getAltura();
    }

    private int getFatorBalanceamento(NoArvore no) {

        if (no == null) {
            return 0;
        }

        return getAltura(no.getEsquerda())
                - getAltura(no.getDireita());
    }

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

        // LL
        if (fator > 1 &&
                produto.getCodigo()
                        < no.getEsquerda()
                        .getProduto()
                        .getCodigo()) {

            return rotacaoSimplesDireita(no);
        }

        // RR
        if (fator < -1 &&
                produto.getCodigo()
                        > no.getDireita()
                        .getProduto()
                        .getCodigo()) {

            return rotacaoSimplesEsquerda(no);
        }

        // LR
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

        // RL
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

        // LL
        if (fator > 1 &&
                getFatorBalanceamento(no.getEsquerda()) >= 0) {

            return rotacaoSimplesDireita(no);
        }

        // LR
        if (fator > 1 &&
                getFatorBalanceamento(no.getEsquerda()) < 0) {

            no.setEsquerda(
                    rotacaoSimplesEsquerda(
                            no.getEsquerda()
                    )
            );

            return rotacaoSimplesDireita(no);
        }

        // RR
        if (fator < -1 &&
                getFatorBalanceamento(no.getDireita()) <= 0) {

            return rotacaoSimplesEsquerda(no);
        }

        // RL
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

    public void excluirProduto(Integer codigo) {
        raiz = excluirRecursivo(raiz, codigo);
    }

    public void atualizarQuantidade(
            Integer codigo,
            Integer novaQuantidade
    ) {

        Produto produto = buscarProduto(codigo);

        if (produto != null) {
            produto.setQuantidadeEmEstoque(novaQuantidade);
        }
    }

    private NoArvore menorValor(NoArvore no) {

        NoArvore atual = no;

        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }

        return atual;
    }
}
