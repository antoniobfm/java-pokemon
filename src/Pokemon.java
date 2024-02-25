import java.util.Arrays;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
public class Pokemon {
    /*
        Defina 6 atributos privados:
        - nome, habilidade, do tipo String;
        - hpOriginal, hpAtual, ataque, defesa, todos do tipo int.
    */
    private String nome;
    private String habilidade;
    private int hpOriginal;
    private int hpAtual;
    private int ataque;
    private int defesa;

    public Pokemon(String nome, int hp, int ataque, int defesa) {
        /*
        Construtor da classe
        Recebe os 4 parâmetros indicados acima, e copia seus valores nos respectivos atributos privados
        usando os métodos: setNome(), setHpOriginal(), setAtaque(), setDefesa().

        É importante usar os setters, porque cada um desses valores precisa ser validado por eles.
        */
        setNome(nome); // Usa o setter para validar e atribuir o nome
        setHpOriginal(hp); // Usa o setter para validar e atribuir hpOriginal e hpAtual
        setAtaque(ataque); // Usa o setter para validar e atribuir ataque
        setDefesa(defesa); // Usa o setter para validar e atribuir defesa
    }

    public static Pokemon gerarPokemonAleatorio() {
        /* Método estático já implementado que retorna um pokemon aleatório.
        Pode ser usado para os seus testes. Fique à vontade para modificá-lo de acordo
         com suas necessidades.

         OBS: este método não é utilizado em nenhum outro lugar, e não será testado durante a avaliação da atividade.
         */
        List<Pokemon> listaPokemons = Arrays.asList(
                new Pokemon("Bulbasaur", 100, 49, 49),
                new Pokemon("Charmander", 100, 65, 58),
                new Pokemon("Pikachu", 100, 55, 40),
                new Pokemon("Squirtle", 100, 59, 63)
        );
        int indiceAleatorio = (int) (Math.random() * listaPokemons.size());
        return listaPokemons.get(indiceAleatorio);
    }

    public void setNome(String nome) {
        /*
        Recebe o nome do Pokémon por parâmetro e verifica se ele encontra-se na seguinte lista:
        "Pikachu", "Charmander", "Bulbasaur", "Squirtle", "Jigglypuff", "Meowth", "Psyduck", "Eevee", "Vulpix", "Growlithe"

        Se o nome existe na lista, o valor do parâmetro deve ser copiado no atributo privado nome. Além disso,
        atualize o valor do atributo privado habilidade, de acordo com a habilidade deste Pokémon. As respectivas habilidades
        dos Pokémons da lista acima são as seguintes:
        "elétrico", "fogo", "grama", "água", "normal", "normal", "água", "normal", "fogo", "fogo"

        Caso o nome não exista na lista, o atributo privado nome deve ser atualizado para "Pikachu", e o atributo privado
        habilidade deve ser atualizado para "elétrico".
        */
        List<String> nomesValidos = Arrays.asList(
                "Pikachu", "Charmander", "Bulbasaur", "Squirtle",
                "Jigglypuff", "Meowth", "Psyduck", "Eevee", "Vulpix", "Growlithe");
        List<String> habilidades = Arrays.asList(
                "elétrico", "fogo", "grama", "água", "normal", "normal",
                "água", "normal", "fogo", "fogo");

        int indice = nomesValidos.indexOf(nome);
        if (indice != -1) {
            this.nome = nome;
            this.habilidade = habilidades.get(indice);
        } else {
            this.nome = "Pikachu";
            this.habilidade = "elétrico";
        }
    }

    public String getNome() {
        /*
        Retorna o valor do atributo privado nome.
        */
        return nome;
    }

    public String getHabilidade() {
        /*
        Retorna o valor do atributo privado habilidade.
        */
        return habilidade;
    }

    public int getHpAtual() {
        /*
        Retorna o valor do atributo privado hpAtual.
        */
        return hpAtual;
    }

    public void setHpOriginal(int hp) {
        /*
        Atualiza o atributo privado hpOriginal. Se for menor que 1, o hpOriginal deve ser 1.
        Já se for maior que 500, o hpOriginal deve ser 500.
        Ou seja, o hpOriginal sempre é um valor entre 1 e 500.
        Sempre que hpOriginal for atualizado, faça com que o atributo hpAtual seja igual a hpOriginal.
        */
        hpOriginal = Math.max(1, Math.min(hp, 500));
        hpAtual = hpOriginal;
    }

    public void decHpAtual(int qtd) {
        /*
        Decrementa o atributo privado hpAtual de acordo com o valor de qtd.
        Jamais pode ser menor que zero.
        */
        hpAtual = Math.max(0, hpAtual - qtd); // Garante que hpAtual não seja menor que 0
    }

    public void restaurarHp() {
        /*
        Copia o valor do atributo hpOriginal no atributo hpAtual.
        */
        hpAtual = hpOriginal;
    }

    public int getAtaque() {
        /*
        Retorna o valor do atributo privado ataque.
        */
        return ataque;
    }

    public void setAtaque(int ataque) {
        /*
        Atualiza o atributo privado ataque. Se for menor que 10, o ataque deve ser 10.
        Já se for maior que 100, o ataque deve ser 100.
        Ou seja, o ataque sempre é um valor entre 10 e 100.
        */
        this.ataque = Math.max(10, Math.min(ataque, 100)); // Garante que ataque esteja entre 10 e 100
    }

    public int getDefesa() {
        /*
        Retorna o valor do atributo privado defesa.
        */
        return defesa;
    }

    public void setDefesa(int defesa) {
        /*
        Atualiza o atributo privado defesa. Se for menor que 10, a defesa deve ser 10.
        Já se for maior que 100, a defesa deve ser 100.
        Ou seja, a defesa sempre é um valor entre 10 e 100.
        */
        this.defesa = Math.max(10, Math.min(defesa, 100)); // Garante que defesa esteja entre 10 e 100
    }

    public void atacar(Pokemon outroPokemon) {
        /*
        Ataca outro Pokémon, causando um dano que é o ataque do Pokémon atacante menos a defesa do seu inimigo.

         Se o dano for maior que zero, decrementa dano hit points do seu inimigo. Caso contrário, não faz nada.
        */

        int dano = this.ataque - outroPokemon.getDefesa();
        if (dano > 0) {
            outroPokemon.decHpAtual(dano);
        }
    }

    public void atacarComHabilidade(Pokemon outroPokemon) {
        /*
         Ataca outro Pokémon com uma habilidade específica, causando um dano pré-definido com base na habilidade.
         Se o Pokémon é de água, causa um dano de 5 no inimigo;
         Se o Pokémon é de elétrico, causa um dano de 10 no inimigo;
         Se o Pokémon é de fogo, causa um dano de 15 no inimigo;
         Se o Pokémon é de grama, causa um dano de 4 no inimigo;
         Se o Pokémon é possui outra habilidade (normal, por exemplo), causa um dano padrão de 2 no inimigo.
         Após calcular o dano, decrementa este valor dos hit points do Pokémon inimigo.

         O Pokémon que está atacando (this) também sobre um dano fixo de 1, devido ao seu esforço com a habilidade especial.
         Ou seja, decrementa 1 hit point do Pokémon atacante.
        */

        int danoBase;
        switch (this.habilidade) {
            case "água":
                danoBase = 5;
                break;
            case "elétrico":
                danoBase = 10;
                break;
            case "fogo":
                danoBase = 15;
                break;
            case "grama":
                danoBase = 4;
                break;
            default: // "normal" e outras habilidades
                danoBase = 2;
                break;
        }
        outroPokemon.decHpAtual(danoBase);
        this.decHpAtual(1); // O Pokémon atacante sofre 1 ponto de dano pelo esforço
    }

    public static void main(String[] args) {
        /* Método main para testes
           Não é necessário implementá-lo, mas você pode testar as funcionalidades da classe Pokemon aqui.
        */
        Pokemon pikachu = new Pokemon("Pikachu", 100, 55, 40);
        Pokemon squirtle = new Pokemon("Squirtle", 100, 59, 63);

        System.out.println("Antes do ataque:");
        System.out.println(pikachu.getNome() + " HP: " + pikachu.getHpAtual());
        System.out.println(squirtle.getNome() + " HP: " + squirtle.getHpAtual());

        pikachu.atacarComHabilidade(squirtle);

        System.out.println("Depois do ataque:");
        System.out.println(pikachu.getNome() + " HP: " + pikachu.getHpAtual());
        System.out.println(squirtle.getNome() + " HP: " + squirtle.getHpAtual());
    }

}
