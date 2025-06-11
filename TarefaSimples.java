package br.com.maisunifacisa;


public class TarefaSimples  extends Tarefa{
	
	public TarefaSimples(String titulo, String descricao, Usuario  usuarioResponsavel) {
		super(titulo, descricao, usuarioResponsavel);
	}
		
	@Override
	public boolean estaAtrasada() {
		return false;
	}
		
	public boolean estaConcluida() {
		return getStatus() == StatusTarefa.CONCLUIDA;
	}
	public void exibirDetalhes() {
		System.out.println("Detalhes da Atividade Simples:");
		System.out.println("Título: " + getTitulo());
		System.out.println("Descrição: " + getDescricao());
		System.out.println("Responsável: " + getUsuarioResponsavel().getNome());
		System.out.println("Status: " + getStatus());
		System.out.println("-----------------------------");
	}
}


