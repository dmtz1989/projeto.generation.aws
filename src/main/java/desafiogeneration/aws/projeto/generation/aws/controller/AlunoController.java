package desafiogeneration.aws.projeto.generation.aws.controller;

import desafiogeneration.aws.projeto.generation.aws.model.AlunoModel;
import desafiogeneration.aws.projeto.generation.aws.repository.AlunoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @Operation(description = "Busca o Aluno pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o Aluno"),
            @ApiResponse(responseCode = "400", description = "Aluno não Encontrado")
    })
    @GetMapping(path = "/api/aluno/{id}")
    public ResponseEntity<AlunoModel> buscarAluno(@PathVariable("id") Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(description = "Busca todos os Alunos Cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos os Alunos Cadastrados")
    })
    @GetMapping(path = "/api/alunos")
    public Iterable<AlunoModel> listarAlunos(){
        return repository.findAll();
    }

    @Operation(description = "Cadastra o Aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o Aluno e Realiza o Cadastro"),
            @ApiResponse(responseCode = "201", description = "Cadastro Realizado"),
    })
    @PostMapping(path = "/api/aluno/salvar")
    public AlunoModel salvarAluno(@RequestBody AlunoModel aluno){
        return repository.save(aluno);
    }

    @Operation(description = "Busca o Aluno pelo ID e faz alterações no Cadastro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o Aluno e Atualiza o Cadastro"),
            @ApiResponse(responseCode = "201", description = "Atualização Realizada"),
            @ApiResponse(responseCode = "400", description = "Aluno não Encontrado")
    })
    @PutMapping(path = "/api/aluno/{id}")
    public ResponseEntity<AlunoModel> atualizarAluno(@PathVariable("id") Integer id, @RequestBody AlunoModel aluno) {
        AlunoModel alterarAluno = repository.findById(id)
                .orElse(ResponseEntity.notFound().<AlunoModel>build().getBody());

        alterarAluno.setId();
        alterarAluno.setNome();
        alterarAluno.setNotaPrimeiroSemestre();
        alterarAluno.setNotaSegundoSemestre();
        alterarAluno.setNomeProfessor();
        alterarAluno.setSala();

        AlunoModel atualizadoAluno = repository.save(aluno);
        return ResponseEntity.ok(atualizadoAluno);
    }

    @Operation(description = "Busca o Aluno pelo ID e faz alterações no Cadastro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o Aluno e Atualiza o Cadastro"),
            @ApiResponse(responseCode = "201", description = "Atualização Realizada"),
            @ApiResponse(responseCode = "400", description = "Aluno não Encontrado")
    })
    @PatchMapping(path = "/api/aluno/{id}")
    public ResponseEntity<AlunoModel> atualizarInfoAluno(@PathVariable("id") Integer id, @RequestBody AlunoModel aluno) {
        AlunoModel alterarAluno = repository.findById(id)
                .orElse(ResponseEntity.notFound().<AlunoModel>build().getBody());

        alterarAluno.setId();
        alterarAluno.setNome();
        alterarAluno.setNotaPrimeiroSemestre();
        alterarAluno.setNotaSegundoSemestre();
        alterarAluno.setNomeProfessor();
        alterarAluno.setSala();

        AlunoModel atualizadoAluno = repository.save(aluno);
        return ResponseEntity.ok(atualizadoAluno);
    }

    
    @Operation(description = "Busca o Aluno pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleta o Aluno"),
            @ApiResponse(responseCode = "400", description = "Aluno não Encontrado")
    })
    @DeleteMapping(path = "/api/aluno/{id}")
    public ResponseEntity<AlunoModel> deletarAluno(@PathVariable("id") Integer id){
        repository.deleteById(id);
        return null;
    }
}
