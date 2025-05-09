package br.com.igovargas.domain;
import java.util.Objects;

public class Customer {

    private String name;
    private Long cpf;
    private Long cell;
    private String address;
    private Integer number;
    private String city;
    private String state;

    public Customer(String name, String cpf, String cell, String address, String number, String city, String state) {
        this.name = name;
        this.cpf = Long.valueOf(cpf.trim()); 
        this.cell = Long.valueOf(cell.trim());
        this.address = address;
        this.number = Integer.valueOf(number.trim());
        this.city = city;
        this.state = state;

        /**EXPLICAÇÃO DESTE CONSTRUTOR
         * > As variáveis privadas são declaradas nas linhas 5 a 11.
         * > Após isso, eu crio um construtor chamado public Customer();
         * > Então, passo os parâmetros que eu quero que ele receba dentro dos ();
         * > Como isso é um formulário, o ideal é o construtor receber informações no formato String;
         * > Dentro dos () então, devo escrever "String + variável" que servirá de parâmetro;
         * > Como as variáveis são private, eu posso pegar elas nesse arquivo "Customer.java"
         *   através do "this.";
         * > A variáveis que já são String é certo manter assim, exemplo: this.name = name
         *   (lê-se esta.variavel =recebe= valor da variável);
         * > Agora o contrutor vai receber os parâmetros, porém nem todos podem ser String;
         * > Faço então, exemplo: this.cpf = Long.valueOf(cpf.trim()) (lê-se esta.variavel = O
         *   valor dela é do tipo Long(variável.remove qualquer espaço em branco que possa existir
         *   antes ou depois do valor))
         */
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getCell() {
        return cell;
    }

    public void setCell(Long cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**EXPLICAÇÃO DOS GETTERS & SETTERS
     * > Acima, chamamos os "getters" e os "setters";
     * > Eles servem, basicamente, para que possamos acessar e modificar os valores atribuídos às
     *   variáveis privadas em outras classes do nosso projeto, como exemplo: utilizamos
     *   getName() em outra classe para pegar o valor da variável privada "name" que se encontra nessa classe;
     */

    @Override
    public int hashCode() {
        /*int hash = 17;
        hash = 31 * hash + Objects.hashCode(this.cpf);
        return hash;*/
        return Objects.hash(cpf);
    }

    /**EXPLICAÇÃO DO HASHCODE
     * > O hashCode acima está é utilizado para localizar rápidamente onde se encontra um determinado objeto;
     * > Você irá buscar por esse objeto através do uso de HashSet ou HashMap;
     * > Para fazer isso, existem dois modos de se escrever um hashCode: o modo manual e o modo automático;
     * > O modo manual esá comentado em verde, abaixo de "public int hashCode()", onde utilizamos números
     *   primos para poder realizar um cálculo e retornar o valor para a variável "hash";
     * > O modo automático, que é o que está sendo utilizado, faz todo esse processo "por baixo dos panos";
     * > Para que o hashCode funcione de modo correto, deve-se sempre utilizar a função equals() abaixo.
     */

    public boolean equals(Object obj) {
        if (this == obj) { //Se o objeto comparado for o mesmo, retorna true.
            return true;
        }

        if (obj == null) { //Se o objeto passado for null, retorna false (não pode ser igual).
            return false;
        }

        if (getClass() != obj.getClass()) { //Se os objetos não forem da mesma classe, retorna false
            return false;
        }

        final Customer other = (Customer) obj; //Faz o cast para Customer.
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false; //Se os CPFs não forem iguais, retorna false.
        }

        return true; //Se todas as comparações passarem, os objetos são iguais.
    }

    /**EXPLICAÇÃO EQUALS
     * > O método "equals" compara dois objetos para verificar se eles são iguais;
     * > Ao retornar FALSE, o programa quer dizer à você: NÃO, OS CPFS NÃO SÃO IGUAIS"
     * > Ao retornar TRUE, o programa quer dizer à você: SIM, ELES SÃO IGUAIS"
     * > Baseado nessa informação, você pode escolher o que fazer com o CPF;
     * > Em nosso código, precisamos que sempre seja FALSE para que possamos adicionar o CPF ao sistema;
     * > Em caso de TRUE, o CPF não será adicionado, pois já existe no sistema;
     * > Esse método é util para evitarmos duplicidade de dados no sistema.
     */

    @Override
    public String toString() {
        return "Customer{" + "nome=" + name + '\'' + ", cpf=" + cpf + '}';
    }

    /**EXPLICAÇÃO TOSTRING
    * > O método toString() é sobrescrito para fornecer uma representação legível e útil 
    *   do objeto Customer, exibindo os dados importantes como nome e CPF.
    * > Quando o método toString() é chamado, por exemplo, ao imprimir o objeto com 
    *   System.out.println(customer), ele retorna uma string no formato:
    *   "Customer{nome=João, cpf=12345678900}".
     */
}
