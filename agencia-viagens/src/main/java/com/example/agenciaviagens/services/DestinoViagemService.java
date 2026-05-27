package com.example.agenciaviagens.services;

import com.example.agenciaviagens.entity.Destino;
import com.example.agenciaviagens.repository.DestinoRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinoViagemService {

    @Autowired
    private DestinoRepository DestinoRepository;

    public List<Destino> listarComFiltro(String nome, String localizacao, Double nota,
            Boolean disponivel, Boolean filtrarDisponivel) {
        List<Destino> destinos = this.DestinoRepository.buscarComFiltrosDinamicos(nome, localizacao, nota, disponivel,filtrarDisponivel);
        return destinos;
    }

    public Destino criarDestino(Destino destino) {
        Destino destino_salvo = this.DestinoRepository.save(destino);
        return destino_salvo;
    }

    public Optional<Destino> buscarDestinoPorId(Long id) {
        Optional<Destino> destino = this.DestinoRepository.findById(id);

        return destino;
    }

    public Destino atualizarDestino(Long id, Destino d) {
        Optional<Destino> destino_optional = this.buscarDestinoPorId(id);
        Destino destino = destino_optional.orElseThrow(() -> new Error("Destino não encontrado"));

        destino.setNome(d.getNome());
        destino.setLocalizacao(d.getLocalizacao());
        destino.setDetalhes(d.getDetalhes());
        destino.setNota(d.getNota());
        destino.setDisponivel(d.getDisponivel());

        this.DestinoRepository.save(destino);

        return destino;
    }

    public Destino avaliarDestino(Long id, Double novaNota) {

        Optional<Destino> destinoOptional = this.buscarDestinoPorId(id);
        Destino destino = destinoOptional.orElseThrow(
                () -> new RuntimeException("Destino não encontrado"));
        Double media = (destino.getNota() + novaNota) / 2;
        destino.setNota(media);

        this.DestinoRepository.save(destino);

        return destino;
    }

    public void excluirDestino(Long id) {
        if (this.DestinoRepository.existsById(id)) {
            this.DestinoRepository.deleteById(id);
        } else {
            throw new Error("Destino não foi encontrado");
        }
    }

}