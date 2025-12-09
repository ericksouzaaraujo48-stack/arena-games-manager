package com.erick.arena_games.controller;

import com.erick.arena_games.model.Jogador;
import com.erick.arena_games.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JogadorController {

    @Autowired
    private JogadorRepository repository;

    // --- 1. LISTAR ---
    @GetMapping("/jogadores")
    public String listarJogadores(Model model) {
        model.addAttribute("listaJogadores", repository.findAll());
        return "pagina-jogadores";
    }

    // --- 2. SALVAR NOVO ---
    @PostMapping("/jogadores/salvar")
    public String salvarJogador(Jogador jogador, Model model) {
        try {
            repository.save(jogador);
            return "redirect:/jogadores";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("erro", "Ops! Esse email já está cadastrado no sistema.");
            model.addAttribute("listaJogadores", repository.findAll());
            return "pagina-jogadores";
        }
    }

    // --- 3, 4, 6, 7. PLACAR (Vitórias e Derrotas) ---
    @PostMapping("/jogadores/{id}/vitoria")
    public String adicionarVitoria(@PathVariable Long id) {
        atualizarPlacar(id, "vitoria", 1);
        return "redirect:/jogadores";
    }

    @PostMapping("/jogadores/{id}/vitoria/remover")
    public String removerVitoria(@PathVariable Long id) {
        atualizarPlacar(id, "vitoria", -1);
        return "redirect:/jogadores";
    }

    @PostMapping("/jogadores/{id}/derrota")
    public String adicionarDerrota(@PathVariable Long id) {
        atualizarPlacar(id, "derrota", 1);
        return "redirect:/jogadores";
    }

    @PostMapping("/jogadores/{id}/derrota/remover")
    public String removerDerrota(@PathVariable Long id) {
        atualizarPlacar(id, "derrota", -1);
        return "redirect:/jogadores";
    }

    // Método auxiliar para evitar repetição de código no placar
    private void atualizarPlacar(Long id, String tipo, int quantidade) {
        Jogador jogador = repository.findById(id).orElse(null);
        if (jogador != null) {
            if (tipo.equals("vitoria")) {
                int novaVitoria = jogador.getVitorias() + quantidade;
                if (novaVitoria >= 0) jogador.setVitorias(novaVitoria);
            } else {
                int novaDerrota = jogador.getDerrotas() + quantidade;
                if (novaDerrota >= 0) jogador.setDerrotas(novaDerrota);
            }
            repository.save(jogador);
        }
    }

    // --- 5. DELETAR ---
    @PostMapping("/jogadores/{id}/deletar")
    public String deletarJogador(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/jogadores";
    }

    // --- 8. ATUALIZAR (CORRIGIDO) ---
    @PostMapping("/jogadores/atualizar")
    public String atualizarJogador(Jogador jogador, Model model) {
        try {
            Jogador jogadorBanco = repository.findById(jogador.getId()).orElse(null);
            if (jogadorBanco != null) {
                jogadorBanco.setNickname(jogador.getNickname());
                jogadorBanco.setNomeReal(jogador.getNomeReal());
                jogadorBanco.setEmail(jogador.getEmail());
                repository.save(jogadorBanco);
            }
            return "redirect:/jogadores";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("erro", "Erro ao atualizar: Esse email já pertence a outro guerreiro!");
            model.addAttribute("listaJogadores", repository.findAll());
            return "pagina-jogadores";
        }
    }
}