import com.sun.jdi.IntegerValue;
import domain.Arvore;
import domain.Produto;

import java.util.List;
import java.util.Scanner;

public class Estoque {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Arvore arvore = new Arvore();

        int opcao;

        do {

            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Buscar produto");
            System.out.println("3 - Atualizar produto");
            System.out.println("4 - Atualizar quantidade");
            System.out.println("5 - Excluir produto");
            System.out.println("6 - Listar produtos");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    Produto produto = new Produto();

                    System.out.println("Código:");
                    produto.setCodigo(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println("Nome:");
                    produto.setNome(scanner.nextLine());

                    System.out.println("Categoria:");
                    produto.setCategoria(scanner.nextLine());

                    System.out.println("Quantidade:");
                    produto.setQuantidadeEmEstoque(scanner.nextInt());

                    System.out.println("Preço:");
                    produto.setPreco(scanner.nextDouble());

                    arvore.inserir(produto);

                    System.out.println("Produto cadastrado.");

                    break;

                case 2:

                    System.out.println("Digite o código:");

                    int codigoBusca = scanner.nextInt();

                    Produto encontrado =
                            arvore.buscarProduto(codigoBusca);

                    if (encontrado != null) {
                        System.out.println(encontrado);
                    } else {
                        System.out.println("Produto não encontrado.");
                    }

                    break;

                case 3:

                    Produto atualizado = new Produto();

                    System.out.println("Código:");
                    atualizado.setCodigo(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println("Novo nome:");
                    atualizado.setNome(scanner.nextLine());

                    System.out.println("Nova categoria:");
                    atualizado.setCategoria(scanner.nextLine());

                    System.out.println("Nova quantidade:");
                    atualizado.setQuantidadeEmEstoque(scanner.nextInt());

                    System.out.println("Novo preço:");
                    atualizado.setPreco(scanner.nextDouble());

                    arvore.atualizarProduto(atualizado);

                    System.out.println("Produto atualizado.");

                    break;

                case 4:

                    System.out.println("Código:");
                    int codigoQuantidade = scanner.nextInt();

                    System.out.println("Nova quantidade:");
                    int quantidade = scanner.nextInt();

                    arvore.atualizarQuantidade(
                            codigoQuantidade,
                            quantidade
                    );

                    System.out.println("Quantidade atualizada.");

                    break;

                case 5:

                    System.out.println("Código:");
                    int codigoExcluir = scanner.nextInt();

                    arvore.excluirProduto(codigoExcluir);

                    System.out.println("Produto removido.");

                    break;

                case 6:

                    List<Produto> produtos =
                            arvore.listarEmOrdemCrescente();

                    for (Produto p : produtos) {
                        System.out.println(p);
                        System.out.println("----------------");
                    }

                    break;

                case 0:

                    System.out.println("Encerrando...");

                    break;

                default:

                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}

