@SuppressWarnings("SpellCheckingInspection")
public class Batalha {
    /*
        Defina 4 atributos privados:
        - treinador1, treinador2 do tipo Treinador (isto é, objetos já instanciados da classe Treinador);
        - vencedor, do tipo int;
        - iniciada, do tipo boolean.
    */
    private final Treinador treinador1;
    private final Treinador treinador2;
    private int vencedor;
    private boolean iniciada;

    public Batalha(Treinador treinador1, Treinador treinador2) {
        /*
        Construtor da classe
        Inicialize os atributos privados com:
        - treinador1 recebe o mesmo endereço do parâmetro treinador1 (dica: é só fazer uma atribuição simples);
        - treinador2 recebe o mesmo endereço do parâmetro treinador2
        - vencedor deve ser zero (indica quem venceu a partida: 0 = ninguém, 1 = o treinador1, 2 = o treinador2);
        - iniciada deve ser false (indica se a batalha já ocorreu ou não).
        */

        this.treinador1 = treinador1;
        this.treinador2 = treinador2;
        this.vencedor = 0; // 0 indica que ninguém venceu ainda
        this.iniciada = false; // A batalha ainda não começou
    }

    public Treinador getTreinador1() {
        /*
        Retorna o objeto (atributo privado) treinador1.
        */
        return treinador1;
    }

    public Treinador getTreinador2() {
        /*
        Retorna o objeto (atributo privado) treinador2.
        */
        return treinador2;
    }

    public int getVencedor() {
        /*
        Retorna o valor do atributo privado vencedor.
        */
        return vencedor;
    }

    public int batalhar(int i1, int i2, int c, int k) {
        /*
        Recebe 4 parâmetros:
        - i1, um índice de listaPokemons do treinador1, indicando qual Pokémon será usado por este treinador;
        - i2, um índice de listaPokemons do treinador2, indicando qual Pokémon será usado por este treinador;
        - c: pode ser 1 ou 2, indicando qual Pokémon começará atacando (se o do treinador1, ou o do treinador2);
        - k: inteiro >= 1, indica de quantas em quantas iterações devem ser usados os ataques com habilidade, ao invés de ataques simples.

        Dica 1: ao iniciar a batalha, restaure os hit points de ambos os Pokémons. A batalha só termina quando os hit points de um
        dos Pokémons for igual a zero. A partir deste momento, não deve haver mais ataques. Ou seja, a cada chamada do método atacar(),
        independente do Pokémon que o chamou, esta condição deve ser checada.

        Dica 2: crie uma variável contadora (por exemplo, iter), começando de 1 para contar quantas iterações foram necessárias para
        encerrar o jogo. Toda vez que (iter % k == 0), ou seja, iter for múltiplo de k, ambos os Pokémons devem atacar usando suas
        habilidades especiais.

        Dica 3: a cada iteração, cada um dos Pokémons se ataca. Exemplo:
        Quando c == 2, primeiro o Pokémon do treinador2 ataca o Pokémon do treinador1.
        Em seguida, o Pokémon do treinador1 ataca o Pokémon do treinador2. Só então a variável contadora (iter) é incrementada.

        Ao final da batalha:
        - incremente as vitórias e derrotas de cada treinador;
        - retorne o valor da variável contadora (iter), indicando quantas iterações foram necessárias para finalizar a batalha.

        Uma batalha não pode ocorrer quando:
        - o atributo privado iniciada já for true;
        - i1 estiver fora do intervalo válido da listaPokemons do treinador1;
        - i2 estiver fora do intervalo válido da listaPokemons do treinador2;
        Neste caso, o método deve retornar 0.

        */
        if (iniciada || i1 < 0 || i1 >= treinador1.getListaPokemons().size() || i2 < 0 || i2 >= treinador2.getListaPokemons().size()) {
            return 0; // Verifica se a batalha pode ocorrer
        }

        iniciada = true; // A batalha começa
        Pokemon p1 = treinador1.getListaPokemons().get(i1);
        Pokemon p2 = treinador2.getListaPokemons().get(i2);
        p1.restaurarHp();
        p2.restaurarHp();

        int iter = 0; // Contador de iterações

        while (p1.getHpAtual() > 0 && p2.getHpAtual() > 0) {
            iter++;
            if (iter % k == 0) { // Usa habilidades especiais
                if (c == 1) {
                    p1.atacarComHabilidade(p2);
                    if (p2.getHpAtual() > 0) p2.atacarComHabilidade(p1);
                } else {
                    p2.atacarComHabilidade(p1);
                    if (p1.getHpAtual() > 0) p1.atacarComHabilidade(p2);
                }
            } else { // Ataques normais
                if (c == 1) {
                    p1.atacar(p2);
                    if (p2.getHpAtual() > 0) p2.atacar(p1);
                } else {
                    p2.atacar(p1);
                    if (p1.getHpAtual() > 0) p1.atacar(p2);
                }
            }

            // Alterna quem começa atacando a cada rodada
            c = (c == 1) ? 2 : 1;
        }

        // Determina o vencedor e atualiza os contadores de vitórias/derrotas
        if (p1.getHpAtual() > 0) {
            vencedor = 1;
            treinador1.incVitorias();
            treinador2.incDerrotas();
        } else {
            vencedor = 2;
            treinador2.incVitorias();
            treinador1.incDerrotas();
        }

        return iter; // Retorna o número de iterações necessárias para finalizar a batalha

    }

    public static void main(String[] args) {
        /* Método main para testes
           Não é necessário implementá-lo, mas você pode testar as funcionalidades da classe Batalha aqui.
        */
        // Criando dois treinadores
        Treinador treinador1 = new Treinador("Ash", 10);
        Treinador treinador2 = new Treinador("Antônio", 10);

        // Adicionando Pokémons aos treinadores
        treinador1.adicionarPokemon(new Pokemon("Pikachu", 100, 55, 40));
        treinador2.adicionarPokemon(new Pokemon("Squirtle", 100, 59, 63));

        // Iniciando uma batalha entre os treinadores
        Batalha batalha = new Batalha(treinador1, treinador2);

        // Executando a batalha
        int numeroDeIteracoes = batalha.batalhar(0, 0, 1, 3);
        int vencedor = batalha.getVencedor();

        // Imprimindo os resultados da batalha
        System.out.println("A batalha terminou após " + numeroDeIteracoes + " iterações.");
        if (vencedor == 1) {
            System.out.println("O vencedor é o treinador: " + treinador1.getNome());
        } else if (vencedor == 2) {
            System.out.println("O brabo mesmo é o: " + treinador2.getNome());
        } else {
            System.out.println("A batalha terminou em empate.");
        }
    }

}
