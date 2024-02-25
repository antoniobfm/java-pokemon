import java.util.ArrayList;

@SuppressWarnings("SpellCheckingInspection")
public class Treinador extends Pessoa {
    /*
        Defina 3 atributos privados:
        - listaPokemons, do tipo ArrayList, que guarda objetos da classe Pokemon;
        - vitorias, derrotas, do tipo int.

        Além disso, Treinador herde da classe Pessoa. Ou seja, a classe Treinador
        guanhará os métodos getNome(), setNome(), getIdade() e setIdade() por herança.
    */
    private final ArrayList<Pokemon> listaPokemons;
    private int vitorias;
    private int derrotas;

    public Treinador(String nome, int idade) {
        /*
        Construtor da classe
        Recebe os 2 parâmetros indicados acima. Como esses valores são manipulados pela
        classe Pessoa, da qual Treinador deve herdar, utilize a função super() para passar
        esses valores para que o construtor de Pessoa os inicialize.

        Além disso, faça com que:
        - o atributo privado listaPokemons seja um ArrayList vazio;
        - o atributo privado vitorias seja zero;
        - o atributo privado derrotas seja zero.
        */
        // super("", -1);
        super(nome, idade); //  Passa os valores para o construtor da superclasse Pessoa
        this.listaPokemons = new ArrayList<>(); // Inicializa como ArrayList vazio
        this.vitorias = 0; // Inicializa vitorias como 0
        this.derrotas = 0; // Inicializa derrotas como 0
    }

    public boolean adicionarPokemon(Pokemon pokemon) {
        /*
        Recebe um objeto Pokemon por parâmetro e o adiciona no fim da listaPokemons.

        OBS: o método não deve permitir que um treinador tenha mais de um Pokémon com
        o mesmo nome.

        Deve retornar true se inseriu o Pokémon com sucesso, e false caso contrário (caso
        já exista um Pokémon com o mesmo nome).
        */
        // return false;
        // Verifica se já existe um Pokémon com o mesmo nome na lista
        for (Pokemon p : listaPokemons) {
            if (p.getNome().equalsIgnoreCase(pokemon.getNome())) {
                return false; // Retorna false se encontrar um Pokémon com o mesmo nome
            }
        }
        listaPokemons.add(pokemon); // Adiciona o novo Pokémon
        return true; // Retorna true após adicionar com sucesso
    }

    public ArrayList<Pokemon> getListaPokemons() {
        /*
        Retorna o objeto que representa um ArrayList de Pokémons (atributo privado listaPokemons).
        */
        return listaPokemons;
    }

    public int totalPokemons() {
        /*
        Retorna o número total de Pokémons na listaPokemons.
        */
        return listaPokemons.size();
    }

    public int ataqueTotal() {
        /*
        Retorna a soma dos ataques de todos os Pokémons na listaPokemons.
        */
        int totalAtaque = 0;
        for (Pokemon p : listaPokemons) {
            totalAtaque += p.getAtaque(); // Soma o ataque de cada Pokémon
        }
        return totalAtaque;
    }

    public int getVitorias() {
        /*
        Retorna o valor do atributo privado vitorias.
        */
        return vitorias;
    }

    public void incVitorias() {
        /*
        Incrementa o atributo vitorias em 1 unidade.
        */
        vitorias++;
    }

    public int getDerrotas() {
        /*
        Retorna o valor do atributo privado derrotas.
        */
        return derrotas;
    }

    public void incDerrotas() {
        /*
        Incrementa o atributo derrotas em 1 unidade.
        */

        derrotas++;
    }

    public static void main(String[] args) {
        /* Método main para testes
           Não é necessário implementá-lo, mas você pode testar as funcionalidades da classe Treinador aqui.
        */
        Treinador ash = new Treinador("Bressa", 10);

        Pokemon pikachu = new Pokemon("Pikachu", 50, 15, 15);
        ash.adicionarPokemon(pikachu);

        Pokemon charmander = new Pokemon("Charmander", 40, 15, 15);
        ash.adicionarPokemon(charmander);

        System.out.println("Nome do treinador: " + ash.getNome());
        System.out.println("Idade do treinador: " + ash.getIdade());
        System.out.println("Total de Pokémons: " + ash.totalPokemons());
        System.out.println("Ataque total: " + ash.ataqueTotal());

        // Teste de adicionar Pokémon com nome repetido
        Pokemon outroPikachu = new Pokemon("Pikachu", 50, 15, 15);
        if (!ash.adicionarPokemon(outroPikachu)) {
            System.out.println("ERRO: Não foi possível adicionar outro Pikachu!");
        }
    }

}
