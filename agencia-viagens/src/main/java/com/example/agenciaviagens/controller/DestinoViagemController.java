package com.example.agenciaviagens.controller;

import com.example.agenciaviagens.entity.Destino;
import com.example.agenciaviagens.entity.DestinoResumo;
import com.example.agenciaviagens.services.DestinoViagemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/destino-viagem")
public class DestinoViagemController {

    @Autowired
    private DestinoViagemService destinoService;

    //Listar (com e sem filtro)
    @GetMapping
    public ResponseEntity<List<DestinoResumo>> listarDestinos(@RequestParam Map<String, String> filtros) {
        if (filtros.size() > 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(destinoService.listarComFiltro(filtros));
    }

    @PostMapping
    public ResponseEntity<Destino> criarDestino(@RequestBody Destino destino) {
        return new ResponseEntity<>(destinoService.criarDestino(destino), HttpStatus.CREATED);
    }

    //Para visualizar detalhes de um destino
    @GetMapping("/{id}")
    public ResponseEntity<Destino> buscarDestinoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(destinoService.buscarDestinoPorId(id));
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Destino> atualizarDestino(@PathVariable Long id, @RequestBody Destino destino) {
        destinoService.buscarDestinoPorId(id);
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
