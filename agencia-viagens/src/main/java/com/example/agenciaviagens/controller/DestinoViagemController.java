package com.example.agenciaviagens.controller;

import com.example.agenciaviagens.entity.Destino;
import com.example.agenciaviagens.services.DestinoViagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/destino-viagem")
public class DestinoViagemController {

    @Autowired
    private DestinoViagemService destinoService;

    //Listar (com e sem filtro)
    @GetMapping
    public ResponseEntity<List<Destino>> listarDestinos(
        @RequestParam(required = false) String p_nome,
        @RequestParam(required = false) String p_localizacao,
        @RequestParam(required = false) Double p_nota,
        @RequestParam(required = false) Boolean p_disponivel
    ) {
        String nome = p_nome==null?"":p_nome;
        String localizacao = p_localizacao==null?"":p_localizacao;
        Double nota = p_nota==null?-1:p_nota;
        Boolean disponivel = p_disponivel==null?false:p_disponivel;
        Boolean filtrarDisponivel = p_disponivel!=null;

        return ResponseEntity.ok(destinoService.listarComFiltro(nome,localizacao,nota,disponivel,filtrarDisponivel));
    }

    @PostMapping
    public ResponseEntity<Destino> criarDestino(@RequestBody Destino destino) {
        return new ResponseEntity<>(destinoService.criarDestino(destino), HttpStatus.CREATED);
    }

    //Para visualizar detalhes de um destino
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Destino>> buscarDestinoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(destinoService.buscarDestinoPorId(id));
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Destino> atualizarDestino(@PathVariable Long id, @RequestBody Destino destino) {
        return ResponseEntity.ok(destinoService.atualizarDestino(id, destino));

    }

    //Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        destinoService.buscarDestinoPorId(id);
        destinoService.excluirDestino(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
