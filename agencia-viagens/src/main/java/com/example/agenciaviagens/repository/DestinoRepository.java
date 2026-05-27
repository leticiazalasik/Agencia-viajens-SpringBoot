package com.example.agenciaviagens.repository;

import java.util.List;
import java.util.Optional;

import com.example.agenciaviagens.entity.DestinoResumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.agenciaviagens.entity.Destino;

public interface DestinoRepository extends JpaRepository<Destino, Long> {

    @Query("SELECT d FROM Destino d WHERE 1=1 " +
        "AND (:nome = '' OR LOWER(d.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) " +
        "AND (:localizacao = '' OR LOWER(d.localizacao) LIKE LOWER(CONCAT('%', :localizacao, '%'))) " +
        "AND (:nota = -1 OR d.nota >= :nota) " +
        "AND (:filtrarDisponivel = false or d.disponivel = :disponivel)"
    )
    public List<DestinoResumo> buscarComFiltrosDinamicos(
            @Param("nome") String nome,
            @Param("localizacao") String localizacao,
            @Param("nota") Double nota,
            @Param("disponivel") Boolean disponivel,
            @Param("filtrarDisponivel") Boolean filtrarDisponivel
        );

}