package com.erick.arena_games.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity // 1. Diz ao Spring: "Isso aqui é uma tabela do banco!"
@Table(name = "jogadores") // 2. Diz: "O nome exato da tabela no SQL é 'jogadores'"
public class Jogador {

    @Id // 3. Diz: "Essa é a chave primária (Primary Key)"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 4. Diz: "O banco gera o ID sozinho (Auto Increment)"
    private Long id;

    @Column(nullable = false) // Não aceita nulo
    private String nickname;

    @Column(name = "nome_real") // Mapeia para a coluna 'nome_real' (snake_case)
    private String nomeReal;

    private String email;

    private Integer vitorias = 0; // Começa com 0

    private Integer derrotas = 0; // Começa com 0

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro = LocalDateTime.now(); // Pega a hora atual automaticamente

    // --- CONSTRUTORES, GETTERS E SETTERS ---
    // (O IntelliJ pode gerar isso para você, mas vou deixar aqui para facilitar)

    public Jogador() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getNomeReal() { return nomeReal; }
    public void setNomeReal(String nomeReal) { this.nomeReal = nomeReal; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getVitorias() { return vitorias; }
    public void setVitorias(Integer vitorias) { this.vitorias = vitorias; }

    public Integer getDerrotas() { return derrotas; }
    public void setDerrotas(Integer derrotas) { this.derrotas = derrotas; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
}