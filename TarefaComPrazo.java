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
	public String verificarSituacaoPrazo() {
	    LocalDate hoje = LocalDate.now();

	    if (getStatus() == StatusTarefa.CONCLUIDA && hoje.isBefore(prazo)) {
	        return "ADIANTADA";
	    } else if (hoje.isAfter(prazo)) {
	        return "VENCIDA";
	    } else if (hoje.isEqual(prazo)) {
	        return "ENTREGA HOJE";
	    } else {
	        return "NO PRAZO";
	    }
	}
	
	@Override
	public void exibirDetalhes() {
		System.out.println("Detalhes da Atividade com Prazo:");
		System.out.println("Título: " + getTitulo());
		System.out.println("Descrição: " + getDescricao());
		System.out.println("Responsável: " + getUsuarioResponsavel());
		System.out.println("Status: " + getStatus());
		System.out.println("Prazo: " + prazo);
		System.out.println("-----------------------------");
		
	}
}
