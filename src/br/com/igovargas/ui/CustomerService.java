package br.com.igovargas.ui;

import javax.swing.JOptionPane;
import br.com.igovargas.dao.ICustomerDAO;
import br.com.igovargas.domain.Customer;

public class CustomerService {

    /** > Abaixo é declarado uma variável que irá armazenar o objeto responsável por interagir
     *  com a interface ICustomerDAO.*/
    private ICustomerDAO iCustomerDAO;

    /** > Aqui, definimos o construtor da classe CustomerService. Ele vai receber da interface ICustomerDAO
     *  um objeto (informação), vai implementar essa interface e vai atribuir a variável iCustomerDAO. Isso vai
     *  permitir que a nossa classe CustomerService consiga, utilizando essa interface, acessar e manipular
     *  os dados escritos dentro dela.*/
    public CustomerService(ICustomerDAO iCustomerDAO) {
        this.iCustomerDAO = iCustomerDAO;
    }

    public void start() {
        
        /** > JOptionPare mexe com o framework Swing, já um pouco defasado. Apesar disso, essa linha de
         *  comandos Jxxxxx é responsável por "criar" interfaces gráficas no Java. Não esqueça que sempre
         *  irá pedir informações para cada tipo delas.*/
        String option = JOptionPane.showInputDialog(null,
        "Digite 1-Cadastro, 2-Consultar, 3-Exclusão, 4-Alteração e 5-Sair.",
        "Início", JOptionPane.INFORMATION_MESSAGE);

        /** > Utilizar esse start(); e return(); em sequência, irá gerar um StackOverFlowError no futuro,
         *  porém é uma mandeira de resolver o problema sem fazer grandes alterações no código.*/
        while (!isValidOption(option)) {
            if (null == option) {
                JOptionPane.showMessageDialog(null,
                "Não habilitado para campos vazios", "Erro", JOptionPane.INFORMATION_MESSAGE);
                start();
                return;
                
            } else {
                JOptionPane.showMessageDialog(null,
                "Insira um número corretamente",
                "Erro", JOptionPane.ERROR_MESSAGE);
                start();
                return;
            }
        }

        /** > "Enquanto a opção for válida", faça isso... Utilizando o While, podemos criar um loop
         *  no qual lê os códigos dentro dele até que algo faça o loop parar.*/
        while (isValidOption(option)) {
            /** > Se (a opção de sair (variável 'option" representando a opção digitada)) > saia (exit()).*/
            if (isExitOption(option)) {
                exit();   
            /** > Então, se (a opção de registrar (variável "option" representando a opção digitada)).*/    
            } else if (isRegister(option)) {
                /** > A String "variável" = vai aparecer na tela um pop-up para preencher com informações
                 *  solicitadas.*/
                String infos = JOptionPane.showInputDialog(null,
                "Digite os dados do cliente, separados por vírgulas (NOM/CPF/CEL/END/NUM/CID/EST).",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                /** > "Variável" cadastra as informações da variável "infos".*/
                register(infos);

            } else if (isConsult(option)) {
                String infos = JOptionPane.showInputDialog(null,
                "Digite o CPF do cliente",
                "Consulta de Cliente", JOptionPane.INFORMATION_MESSAGE);
                consult(infos);

            } else if (isChanged(option)) {
                String infos = JOptionPane.showInputDialog(null,
                "Para alterar informações, digite os dados do cliente, separados por vírgulas (NOM/CPF/CEL/END/NUM/CID/EST).",
                "Alteração de dados", JOptionPane.INFORMATION_MESSAGE);
                change(infos);
                
            } else if (isExclude(option)) {
                String infos = JOptionPane.showInputDialog(null,
                "Digite o CPF do cliente",
                "Exclusão de Cliente", JOptionPane.INFORMATION_MESSAGE);
                exclude(infos);
            }
            /** > Após realizar o que precisava ser feito dentro desse While, voltamos ao início.*/
            option = JOptionPane.showInputDialog(null,
                "Digite 1-Cadastro, 2-Consultar, 3-Exclusão, 4-Alteração e 5-Sair.",
                "Início", JOptionPane.INFORMATION_MESSAGE);
        }  
    }
    /** > Aqui declaramos o que é o método "isValidOption" como Boolean (verdadeiro ou falso), utilizado
     *  nas opções acima. Nele, declaramos que se 1 = opção escolhida || (ou) 2 = opção escolhida, etc...
     *  É verdadeiro, e então entramos no loop (While) escrito acima. Caso contrário, retora flaso,
     *  o que significa retornar ao "!isValidOption" que também é um loop, mas que informa erros.*/
    private boolean isValidOption(String option) {
        if ("1".equals(option) || "2".equals(option) || "3".equals(option) || "4".equals(option) || "5".equals(option)) {
            return true;
        }
        return false;
    }

    /** > Void é vazio, então espera receber uma informação. Se atendermos a condição do "exit" em nosso
     *  código, receberemos como retorno o que está escrito nesse método, junto ao seu comando final.*/
    private void exit() {
        JOptionPane.showMessageDialog(null,
        "Até logo!", "Processo finalizado", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    /** > Aqui começamos a receber variáveis no estilo Boolean, onde irão nos retornar verdadeiro ou falso
     *  e, dependendo do que for retornado, executarão uma ação no código onde elas estão inseridas.*/
    private boolean isRegister(String option) {
        if ("1".equals(option)) {
            return true;
        }
        return false;
    }

    /** > A variável "register" (recebe "variável infos" em formato de String) realiza um comando onde
     *  através de um array, armazena as informações digitadas dentro da variável "splittedInfos", onde
     *  essa variável é o resultado das informações digitadas "infos" divididas por vírgulas (comando
     * .split(regex: separados por virgula))*/
    private void register(String infos) {
        String[] splittedInfos = infos.split(",");
        /** > Aqui, estamos declarando uma variável chamada "hasNull" do tipo Boolean (verdadeiro ou falso)
         *  e declarando ela como sendo falsa. Mas por que? Inicialmente eu assumo que não há campos vazios
         *  dentro do sistema para registrar um cliente (false). Depois, no "for", se o sistema encontrar
         *  um campo vazio, ele vai mudar a variável para "true" e registrar as informações do cliente.*/
        Boolean hasNull = false;

        /** > Para (cada String chamada "info" dentro do array splittedInfos)
         *  se ("info" for igual a null (ausente) || (ou) "info.trim()" (remove os espaços em branco extras
         *  no início e no fim da String "info") ".isEmpty" (verifica se não sobrou nenhum caractere). Se
         *  essas condições se confirmarem, alteramos a variável "hasNull" para "true" e registramos
         *  o cliente. Após isso, paramos o "for".*/
        for (String info : splittedInfos) {
            if (info == null || info.trim().isEmpty()) {
                hasNull = true;
                break;
            }
        }

        /** > Se o hasNull for falso, entrará na condição abaixo, onde nem todos os campos foram
         *  devidamente preenchidos.*/
        if (hasNull) {
            JOptionPane.showMessageDialog(null,
            "Todos os campos devem ser preenchidos",
            "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
            return;
        
        /** > Se a variável "splittedInfos" receber menos de 7 valores (valores baseados na quantidade
         *  de informações necessárias para preencher todas as "casas" do array), informará o
         *  erro abaixo.*/    
        } else if (splittedInfos.length < 7) {
            JOptionPane.showMessageDialog(null,
            "Você deve informar 7 dados separados por vírgula.",
            "Erro de entrada", JOptionPane.WARNING_MESSAGE);
            return;
        }

        /** > Abaixo, o código está criando o novo cliente (customer) no banco de dados, armazenando cada uma das
         *  informações passadas em cada uma das variáveis "splittedInfos", ordenando as mesmas
         *  através do seu número pré-definido dentro do array []. O resultado é armazenado na variável
         *  isRegistered.
         * 
         *  > Boolean isRegistered = iCustomerDAO.register(customer) está dizendo que:
         *  A variável isRegistered é booleana (verdaeira ou falsa) e é igual a iCustomer, que vai
         *  manipular a informação register, que se encontra dentro da interface ICustomer. Baseado se
         *  for verdadeiro ou falso, o código fara algo.*/
        Customer customer = new Customer
        (splittedInfos[0],
        splittedInfos[1],
        splittedInfos[2],
        splittedInfos[3],
        splittedInfos[4],
        splittedInfos[5],
        splittedInfos[6]);
        Boolean isRegistered = iCustomerDAO.register(customer);
        
        /** Se (isRegistered) é verdadeira, o cliente foi cadastrado no banco de dados com sucesso.*/
        if (isRegistered) {
        JOptionPane.showMessageDialog(null,
        "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        /** Se não, mostra a mensagem de erro abaixo.*/
        } else {
        JOptionPane.showMessageDialog(null,
        "Cliente já cadastrado.", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean isConsult(String option) {
        if ("2".equals(option)) {
            return true;
        }
        return false;
    }

    private void consult(String cpfString) {
        /** > Com o Try, "tentamos executar este código" e com o catch, "se der erro, pegue
         *  o erro e me deixe lidar com ele sem quebrar o programa".*/
        try {
            /** > Aqui podemos ler como: tipo da variável "variável" = tipo da variável.valor de(variável.trim),
             *  o que quer dizer que estamos dizendo ao programa que o valor do formato Long (números extensos)
             *  da variável "cpf" é o resultado de transformarmos a variavel "cpfString", que é declarada como
             *  String.*/
            Long cpf = Long.valueOf(cpfString.trim());
            /** > Crie um objeto customer e coloque nele o resultado da consulta pelo CPF no banco de dados.*/
            Customer customer = iCustomerDAO.consult(cpf);
        
            if (customer != null) {
                JOptionPane.showMessageDialog(null, "Nome: " + customer.getName() + "\n" + "CPF: " + customer.getCpf() + "\n" + "Celular: " + customer.getCell() + "\n" + "Endereço: " + customer.getAddress() + ", " + customer.getNumber() + "\n" + "Cidade: " + customer.getCity() + " - " + customer.getState(),
                "Cliente encontrado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                "Cliente não encontrado.",
                "Consulta", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null,
            "CPF inválido. Digite apenas números.",
            "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isExclude(String option) {
        if ("3".equals(option)) {
            return true;
        }
        return false;
    }

    private void exclude(String infos) {

        if (infos == null || infos.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                "Você deve inserior um CPF.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {

            Long cpf = Long.valueOf(infos.trim());
            Customer customer = iCustomerDAO.consult(cpf);
           
            if (customer != null) {
                iCustomerDAO.exclude(cpf);
                JOptionPane.showMessageDialog(null,
                "Cliente excluido com sucesso!",
                "Concluído", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                "CPF não encontrado.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
            "CPF inválido. Digite apenas números.",
            "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isChanged(String option) {
        if ("4".equals(option)) {
            return true;
        }
        return false;
    }
 
    private void change(String infos) {
        /** > Para este método, vamos realizar algumas validações antes de realizar a
            substituição das informações do cliente.*/
        String[] splittedInfos = infos.split(",");

        /** > Vamos validar se serão informados as 7 informações necessárias para preencher
            o cadastro do cliente (lembrando que o Array começa sua contagem em 0, logo: 0/1/2/3/4/5/6).*/
        if (splittedInfos.length < 7) {
            JOptionPane.showMessageDialog(null,
            "Você deve informar 7 dados separados por vírgula (NOM/CPF/CEL/END/NUM/CID/EST).",
            "Erro de entrada", JOptionPane.WARNING_MESSAGE);
            return;
        }

        /** > Vamos validar se todos os campos de informação serão preenchidos.*/
        for (String info : splittedInfos) {
            if (info == null || info.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                "Todos os campos devem ser preenchidos",
                "Erro de cadastro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        /** > Vamos validar se o CPF digitado é valido ou não. Declaramos a variável com o tipo dela.*/
        Long cpf;

        /** > Com o Try, "tentamos executar este código" e com o catch, "se der erro, pegue
         *  o erro e me deixe lidar com ele sem quebrar o programa".*/
        try {
            cpf = Long.valueOf(splittedInfos[1].trim());
        
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
            "CPF inválido. Digite apenas números.",
            "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        /** > Vamos validar se o CPF digitado existe no banco de dados ou não. Para isso, vamos
         * chamar a função de iCustomerDAO relacionada ao CPF e atribuindo ela a uma nova variável,
         * chamada de existingCustomer.*/
        Customer existingCustomer = iCustomerDAO.consult(cpf);

        if (existingCustomer == null) {
            JOptionPane.showMessageDialog(null,
            "CPF não encontrado.",
            "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        /** > Passando por todas as validações anteriores, caso nenhuma delas alerte nada tudo esteja
         * de acordo, iniciamos o processo de atualizar os dados daquele cliente.*/
        Customer updatedCustomer = new Customer(
        splittedInfos[0].trim(), //nome
        splittedInfos[1].trim(), //cpf
        splittedInfos[2].trim(), //celular
        splittedInfos[3].trim(), //endereço
        splittedInfos[4].trim(), //número
        splittedInfos[5].trim(), //cidade
        splittedInfos[6].trim());//estado

        /** > Aqui atualizamos os dados chamando o método iCustomerDAO, que origina da classe
         *  ICustomerDAO (private ICustomerDAO iCustomerDAO;) modificada no início do código.*/
        iCustomerDAO.change(updatedCustomer);

        /** > Após alterados os dados, confirmamos em tela para o usuário.*/
        JOptionPane.showMessageDialog(null,
        "Cliente atualizado com sucesso!",
        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean isExitOption(String option) {
        if ("5".equals(option)) {
            return true;
        }
        return false;
    }
}