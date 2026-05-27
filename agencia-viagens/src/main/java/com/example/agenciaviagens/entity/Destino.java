package com.example.agenciaviagens.entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;

@Entity
@Table(name="destinos")
public class Destino {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Localização é obrigatória")
    private String localizacao;

    @NotNull(message = "Nota é obrigatória")
    private Double nota;

    @Embedded
    private Detalhes detalhes;

    @NotNull(message = "Disponibilidade é obrigatória")
    private Boolean disponivel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Detalhes getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(Detalhes detalhes) {
        this.detalhes = detalhes;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
