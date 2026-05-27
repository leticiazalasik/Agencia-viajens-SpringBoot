package com.example.agenciaviagens.services;

import com.example.agenciaviagens.entity.Destino;
import com.example.agenciaviagens.entity.DestinoResumo;
import com.example.agenciaviagens.repository.DestinoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DestinoViagemService {

    @Autowired
    private DestinoRepository destinoRepository;

    public List<DestinoResumo> listarComFiltro(String nome, String localizacao, Double nota, Boolean disponivel, Boolean filtrarDisponivel) {
        List<DestinoResumo> destinos = this.destinoRepository.buscarComFiltrosDinamicos(nome, localizacao, nota, disponivel,filtrarDisponivel);
        return destinos;
    }

    public Destino criarDestino(Destino destino) {
        return this.destinoRepository.save(destino);
    }

    public Destino buscarDestinoPorId(Long id) {
        return this.destinoRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Destino não encontrado"
                        )
                );
    }

    public Destino atualizarDestino(Long id, Destino d) {
        Destino destino = this.buscarDestinoPorId(id);

        destino.setNome(d.getNome());
        destino.setLocalizacao(d.getLocalizacao());
        destino.setDetalhes(d.getDetalhes());
        destino.setNota(d.getNota());
        destino.setDisponivel(d.getDisponivel());

        this.destinoRepository.save(destino);

        return destino;
    }

    public Destino avaliarDestino(Long id, Double novaNota) {

        Destino destino = this.buscarDestinoPorId(id);

        if (novaNota < 1 || novaNota > 10) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nota deve ser entre 1 e 10"
            );
        }
        Double media = (destino.getNota() + novaNota) / 2;
        destino.setNota(media);

        this.destinoRepository.save(destino);

        return destino;
    }

    public void excluirDestino(Long id) {
        Destino destino = this.buscarDestinoPorId(id);
            this.destinoRepository.deleteById(id);
    }

}