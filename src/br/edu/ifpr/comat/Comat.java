package br.edu.ifpr.comat;

import br.edu.ifpr.comat.dao.ClienteDAO;
import br.edu.ifpr.comat.dao.ContatoDAO;
import br.edu.ifpr.comat.model.Cidade;
import br.edu.ifpr.comat.model.Cliente;
import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.model.Contato;
import br.edu.ifpr.comat.model.Endereco;
import br.edu.ifpr.comat.model.Estado;
import java.util.Date;

/**
 * @project Comat
 * @class Comat
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class Comat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Estado e1 = new Estado("PR", "Paraná");
        Estado e2 = new Estado("SP", "São Paulo");
        Estado e3 = new Estado("SC", "Santa Catarina");

        Cidade c1 = new Cidade(null, "Curitiba", e1);
        Cidade c2 = new Cidade(null, "Londrina", e2);
        Cidade c3 = new Cidade(null, "Campinas", e3);

        Endereco n1 = new Endereco(null, "Rua", "XV de Novembo", 1200, null, "Centro", 80010210, c1);
        Endereco n2 = new Endereco(null, "Av", "Brasil", 230, null, "Centro", 80010320, c2);
        Endereco n3 = new Endereco(null, "Rodovia", "PR 445", 233, null, null, 80010210, c3);

        Contato ct1 = new Contato(null, "Joao", "4333443247", "438880000", "joao@joao.com", "Pedreiro", null);
        new ContatoDAO().insert(ct1);       

//        Set contatos = new HashSet();
//        contatos.add(ct1);

        Cliente f = new ClienteFisica(80238807991L, 8991084, "Cristhiano", new Date(), "84361902", null, 1, "cristhiano@c3info.com.br", "www.c3info.com.br", "33443247", "none", n1, null);
        new ClienteDAO().insert(f);


// TESTE INSERT        
//        new EstadoDAO().insert(e1);
//        new EstadoDAO().insert(e2);
//        new EstadoDAO().insert(e3);
//
//        new CidadeDAO().insert(c1);
//        new CidadeDAO().insert(c2);
//        new CidadeDAO().insert(c3);
//
//        new EnderecoDAO().insert(n1);
//        new EnderecoDAO().insert(n2);
//        new EnderecoDAO().insert(n3);

// TESTE DELETE
//        new EnderecoDAO().delete(1);
//        new CidadeDAO().delete(1);
//        new EstadoDAO().delete("PR");

// TESTE UPDATE
//        e1.setNome("Teste");
//        new EstadoDAO().update(e1);
//
//        c1.setNome("Teste");
//        new CidadeDAO().update(c1);
//
//        n1.setLogradouro("Teste");
//        new EnderecoDAO().update(n1);

//// TESTE SELECT ALL
//        for (Estado item : new EstadoDAO().select()) {
//            System.out.println(item.getNome().toString());
//        }
//
//        for (Cidade item : new CidadeDAO().select()) {
//            System.out.println(item.getNome().toString());
//
//        }
//        for (Endereco item : new EnderecoDAO().select()) {
//            System.out.println(item.getLogradouro().toString());
//        }

// TESTE SELECT ONE
//        System.out.println(new EstadoDAO().select("SP"));
//        System.out.println(new CidadeDAO().select(2).getNome());
//        System.out.println(new EnderecoDAO().select(2).getLogradouro());

    }
}
