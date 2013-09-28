package br.edu.ifpr.comat;

import br.edu.ifpr.comat.dao.CidadeDAO;
import br.edu.ifpr.comat.dao.ClienteDAO;
import br.edu.ifpr.comat.dao.EnderecoDAO;
import br.edu.ifpr.comat.dao.EstadoDAO;
import br.edu.ifpr.comat.model.Cidade;
import br.edu.ifpr.comat.model.Cliente;
import br.edu.ifpr.comat.model.ClienteFisica;
import br.edu.ifpr.comat.model.Contato;
import br.edu.ifpr.comat.model.Endereco;
import br.edu.ifpr.comat.model.Estado;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


        Cliente f = new ClienteFisica(80238807991L, 8991084, "Cristhiano", new Date(), "84361902", null, 1, "cristhiano@c3info.com.br", "www.c3info.com.br", "33443247", "none", n1);
        new ClienteDAO().addCliente(f);
       

// TESTE INSERT        
//        new EstadoDAO().addEstado(e1);
//        new EstadoDAO().addEstado(e2);
//        new EstadoDAO().addEstado(e3);
//
//        new CidadeDAO().addCidade(c1);
//        new CidadeDAO().addCidade(c2);
//        new CidadeDAO().addCidade(c3);
//
//        new EnderecoDAO().addEndereco(n1);
//        new EnderecoDAO().addEndereco(n2);
//        new EnderecoDAO().addEndereco(n3);

// TESTE DELETE
//        new EnderecoDAO().deleteEndereco(1);
//        new CidadeDAO().deleteCidade(1);
//        new EstadoDAO().deleteEstado("PR");

// TESTE UPDATE
//        e1.setNome("Teste");
//        new EstadoDAO().updateEstado(e1);
//        
//        c1.setNome("Teste");
//        new CidadeDAO().updateCidade(c1);
//        
//        n1.setLogradouro("Teste");
//        new EnderecoDAO().updateEndereco(n1);

//// TESTE SELECT ALL
//        for (Estado item : new EstadoDAO().selectEstado()) {
//            System.out.println(item.getNome().toString());
//        }
//
//        for (Cidade item : new CidadeDAO().selectCidade()) {
//            System.out.println(item.getNome().toString());
//
//        }
//        for (Endereco item: new EnderecoDAO().selectEndereco()){
//            System.out.println(item.getLogradouro().toString());
//        }

// TESTE SELECT ONE
//        System.out.println(new EstadoDAO().selectEstado("SP"));
//        System.out.println(new CidadeDAO().selectCidade(2).getNome());
//        System.out.println(new EnderecoDAO().selectEndereco(2).getLogradouro());

    }
}
