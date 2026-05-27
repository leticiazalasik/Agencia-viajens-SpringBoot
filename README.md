# ✈️ Agência de Viagens API

API RESTful desenvolvida com **Java** e **Spring Boot** para auxiliar clientes e parceiros a planejar e gerenciar destinos de viagem.

Desenvolvido como parte do desafio prático do curso **SENAI**.

A API estará disponível em `http://localhost:8080`

---

## 🚀 Tecnologias

- Java 17+
- Spring Boot
- Maven
---

## 📋 Endpoints

| Método | Endpoint | Descrição                                                 |
|--------|----------|-----------------------------------------------------------|
| `GET` | `/destino-viagem` | Lista de resumo todos os destinos (com filtros opcionais) |
| `GET` | `/destino-viagem/{id}` | Retorna detalhes de um destino específico                 |
| `POST` | `/destino-viagem` | Cadastra um novo destino                                  |
| `PUT` | `/destino-viagem/{id}` | Atualiza um destino existente                             |
| `PATCH` | `/destino-viagem/{id}/avaliacao` | Avalia um destino com nota de 1 a 10                      |
| `DELETE` | `/destino-viagem/{id}` | Exclui um destino                                         |

---

## 🔍 Filtros na listagem

O endpoint `GET /destino-viagem` aceita os seguintes parâmetros opcionais:

| Parâmetro | Tipo | Descrição |
|-----------|------|-----------|
| `p_nome` | `String` | Filtra por nome do destino |
| `p_localizacao` | `String` | Filtra por localização |
| `p_nota` | `Double` | Filtra destinos com nota maior ou igual ao valor |
| `p_disponivel` | `Boolean` | Filtra por disponibilidade |

**Exemplo:**
```
GET /destino-viagem?p_nome=praia&p_disponivel=true
```

---

## 📝 Exemplos de uso

### Cadastrar um destino

`POST /destino-viagem`

```json
{
  "nome": "Praia de Jurerê Internacional",
  "localizacao": "Florianópolis, Santa Catarina, Brasil",
  "nota": 9.5,
  "disponivel": true,
  "detalhes": {
    "descricao": "Uma das praias mais sofisticadas do Brasil, com águas calmas e infraestrutura completa.",
    "hotel": "Jurerê Beach Village - resort 5 estrelas com acesso direto à praia.",
    "atividadeTuristica": "Passeio de escuna, stand-up paddle, kitesurf e trilhas pela ilha."
  }
}
```

### Avaliar um destino

`PATCH /destino-viagem/1/avaliacao?nota=9.0`

A nova média é calculada automaticamente com base na nota já existente.

---

## 👨‍💻 Autores
ANA LÍGIA VINCENZI BORTOLOTTI 

LETÍCIA ZALASIK

SÁVIO EDUARDO ZOBOLI

