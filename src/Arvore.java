public class Arvore {

    private NoArvore raiz;

    public Arvore() {
        this.raiz = null;
    }

    // 1. MÉTODO AUXILIAR: Altura
    private int getAltura(NoArvore no) {
        return (no == null) ? 0 : no.altura;
    }

    // 2. MÉTODO AUXILIAR: Fator de Balanceamento
    private int getFatorBalanceamento(NoArvore no) {
        return (no == null) ? 0 : getAltura(no.esquerda) - getAltura(no.direita);
    }

    // 3. ROTAÇÃO SIMPLES DIREITA (Você já começou esta!)
    private NoArvore rotacaoSimplesDireita(NoArvore y) {
        NoArvore x = y.esquerda;
        NoArvore T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

// Atualiza alturas (Y primeiro pois agora é filho de X)
        y.altura = Math.max(getAltura(y.esquerda), getAltura(y.direita)) + 1;
        x.altura = Math.max(getAltura(x.esquerda), getAltura(x.direita)) + 1;

        return x;
    }

    // 4. ROTAÇÃO SIMPLES ESQUERDA (Faltava esta)
    private NoArvore rotacaoSimplesEsquerda(NoArvore x) {
        NoArvore y = x.direita;
        NoArvore T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(getAltura(x.esquerda), getAltura(x.direita)) + 1;
        y.altura = Math.max(getAltura(y.esquerda), getAltura(y.direita)) + 1;

        return y;
    }

    // 5. INSERÇÃO (O coração da AVL)
    public void inserir(int valor) {
        this.raiz = inserirRecursivo(this.raiz, valor);
    }

    private NoArvore inserirRecursivo(NoArvore no, int valor) {
// Passo 1: Inserção normal de BST
        if (no == null) return new NoArvore(valor);

        if (valor < no.valor) {
            no.esquerda = inserirRecursivo(no.esquerda, valor);
        } else if (valor > no.valor) {
            no.direita = inserirRecursivo(no.direita, valor);
        } else {
            return no; // Não permite valores duplicados
        }

// Passo 2: Atualizar altura do nó ancestral
        no.altura = 1 + Math.max(getAltura(no.esquerda), getAltura(no.direita));

// Passo 3: Obter o fator para verificar balanceamento
        int fator = getFatorBalanceamento(no);

// --- Casos de Rotação ---

// Caso Direita (LL)
        if (fator > 1 && valor < no.esquerda.valor) {
            return rotacaoSimplesDireita(no);
        }

// Caso Esquerda (RR)
        if (fator < -1 && valor > no.direita.valor) {
            return rotacaoSimplesEsquerda(no);
        }

// Caso Esquerda-Direita (LR)
        if (fator > 1 && valor > no.esquerda.valor) {
            no.esquerda = rotacaoSimplesEsquerda(no.esquerda);
            return rotacaoSimplesDireita(no);
        }

// Caso Direita-Esquerda (RL)
        if (fator < -1 && valor < no.direita.valor) {
            no.direita = rotacaoSimplesDireita(no.direita);
            return rotacaoSimplesEsquerda(no);
        }

        return no;
    }
}