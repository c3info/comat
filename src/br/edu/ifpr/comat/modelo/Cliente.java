package br.edu.ifpr.comat.modelo;

import java.util.List;

/**
 * @project Comat
 * @class Cliente
 * @author Cristhiano Konczak Cardoso <cristhiano@c3info.com.br>
 * @date 17/09/2013
 */
public class Cliente {

    byte status;
    String email;
    String site;
    String telefone;
    String observacoes;
    List<Contato> contatos;
    List<Endereco> enderecos;
}
