import domain.Arvore;
import domain.Produto;

import java.util.Scanner;

public class Estoque {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Arvore arvore = new Arvore();
        Produto produto = new Produto();
        /**
         * Cadastro de produto
         */

        System.out.println("Digite o codigo do produto: ");
        produto.setCodigo(scanner.nextInt());

        System.out.println("Digite o nome do produto: ");
        produto.setNome(scanner.nextLine());

        System.out.println("Digite a categoria do produto:");
        produto.setCategoria(scanner.nextLine());

        System.out.println("Digite a quantidade em estoque: ");
        produto.setQuantidadeEmEstoque(scanner.nextInt());

        System.out.println("Digite o preço do produto: ");
        produto.setPreco(scanner.nextDouble());


        arvore.inserir(produto);

    }
}
