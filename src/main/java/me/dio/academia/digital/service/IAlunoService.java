package me.dio.academia.digital.service;

import me.dio.academia.digital.dto.MessageResponseDTO;
import me.dio.academia.digital.entity.Aluno;
import me.dio.academia.digital.entity.AvaliacaoFisica;
import me.dio.academia.digital.entity.form.AlunoForm;
import me.dio.academia.digital.entity.form.AlunoUpdateForm;
import me.dio.academia.digital.exceptions.AlunoNotFound;

import java.util.List;

public interface IAlunoService {
  /**
   * Cria um Aluno e salva no banco de dados.
   * @param form - formulário referente aos dados para criação de um Aluno no banco de dados.
   * @return - Aluno recém-criado.
   */
  Aluno create(AlunoForm form);

  /**
   * Retorna um Aluno que está no banco de dados de acordo com seu Id.
   * @param id - id do Aluno que será exibido.
   * @return - Aluno de acordo com o Id fornecido.
   */
  Aluno get(Long id) throws AlunoNotFound;

  /**
   * Retorna todos os Alunos que estão no banco de dados.
   * @return - Uma lista os Alunos que estão salvas no DB.
   */
  List<Aluno> getAll(String dataDeNascimento);

  /**
   * Atualiza o Aluno.
   * @param id - id do Aluno que será atualizado.
   * @param formUpdate - formulário referente aos dados necessários para atualização do Aluno
   * no banco de dados.
   * @return - Aluno recém-atualizado.
   */
  MessageResponseDTO update(Long id, AlunoUpdateForm formUpdate) throws AlunoNotFound;

  /**
   * Deleta um Aluno específico.
   * @param id - id do Aluno que será removido.
   * @return
   */
  String delete(Long id) throws AlunoNotFound;

    List<AvaliacaoFisica> getAllAvaliacaoFisica(Long id) throws AlunoNotFound;
}
