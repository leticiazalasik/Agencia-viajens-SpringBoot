package com.example.agenciaviagens.controller;

import com.example.agenciaviagens.entity.Destino;
import com.example.agenciaviagens.entity.DestinoResumo;
import com.example.agenciaviagens.services.DestinoViagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/destino-viagem")
public class DestinoViagemController {

    @Autowired
    private DestinoViagemService destinoService;

    //Listar (com e sem filtro)
    @GetMapping
    public ResponseEntity<List<DestinoResumo>> listarDestinos(
            @RequestParam Map<String, String> params
    ) {

        Set<String> filtrosPermitidos = Set.of(
                "p_nome",
                "p_localizacao",
                "p_nota",
                "p_disponivel"
        );

        // valida filtros inexistentes
        for (String param : params.keySet()) {

            if (!filtrosPermitidos.contains(param)) {
                return ResponseEntity.badRequest().build();
            }

            // valida parâmetros vazios
            if (params.get(param).isBlank()) {
                return ResponseEntity.badRequest().build();
            }
        }

        String nome = params.getOrDefault("p_nome", "");
        String localizacao = params.getOrDefault("p_localizacao", "");

        Double nota = params.containsKey("p_nota")
                ? Double.valueOf(params.get("p_nota"))
                : null;

        Boolean disponivel = params.containsKey("p_disponivel")
                ? Boolean.valueOf(params.get("p_disponivel"))
                : false;

        Boolean filtrarDisponivel = params.containsKey("p_disponivel");

        return ResponseEntity.ok(
                destinoService.listarComFiltro(
                        nome,
                        localizacao,
                        nota,
                        disponivel,
                        filtrarDisponivel
                )
        );
    }

    @PostMapping
    public ResponseEntity<Destino> criarDestino(@Valid @RequestBody Destino destino) {
        return new ResponseEntity<>(destinoService.criarDestino(destino), HttpStatus.CREATED);
    }

    //Para visualizar detalhes de um destino
    @GetMapping("/{id}")
    public ResponseEntity<Destino> buscarDestinoPorId(@PathVariable Long id) {
        Destino destino = destinoService.buscarDestinoPorId(id);
            return ResponseEntity.ok(destino);
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Destino> atualizarDestino(@PathVariable Long id, @RequestBody Destino destino) {
        return ResponseEntity.ok(destinoService.atualizarDestino(id, destino));

    }

    @PatchMapping("/{id}/avaliacao")
    public ResponseEntity<Destino> avaliarDestino(
            @PathVariable Long id,
            @RequestParam Double nota) {

        return ResponseEntity.ok(destinoService.avaliarDestino(id, nota));
    }

    //Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        destinoService.excluirDestino(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
