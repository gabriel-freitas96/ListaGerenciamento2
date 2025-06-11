package br.com.maisunifacisa;

import java.time.LocalDate;

public class TarefaComPrazo extends Tarefa {
	
	public LocalDate prazo;
	
	public TarefaComPrazo(String titulo, String descricao, Usuario  usuarioResponsavel, LocalDate prazo) {
		super(titulo, descricao, usuarioResponsavel);
		this.prazo = prazo;
		
	}
	public LocalDate getPrazo() {
		return prazo;
	}
	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}
	@Override
	public boolean estaAtrasada() {
		return getStatus() != StatusTarefa.CONCLUIDA && LocalDate.now().isAfter(prazo);
	}
	public boolean estaConcluida() {
		return getStatus() == StatusTarefa.CONCLUIDA;
	}
	public void exibirDetalhes() {
		System.out.println("Detalhes da Atividade com Prazo:");
		System.out.println("Título: " + getTitulo());
		System.out.println("Descrição: " + getDescricao());
		System.out.println("Responsável: " + getUsuarioResponsavel().getNome());
		System.out.println("Status: " + getStatus());
		System.out.println("Prazo: " + prazo);
		System.out.println("-----------------------------");
		
	}
	@Override
	public String toString() {
		return "TarefaComPrazo [prazo=" + prazo + "]";
	}
}
