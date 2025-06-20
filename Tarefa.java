package br.com.maisunifacisa;

public abstract class Tarefa {
	protected String titulo;
	protected String descricao;
	protected Usuario usuarioResponsavel;
	protected StatusTarefa status;
	
	public Tarefa(String titulo, String descricao, Usuario usuarioResponsavel) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuarioResponsavel = usuarioResponsavel;
		this.status = StatusTarefa.PENDENTE;
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setStatus(StatusTarefa status) {
		this.status = status;
}
	public StatusTarefa getStatus() {
		return status;
	}
	
	public String verificarSituacaoPrazo() {
	    return ""; 
	}
	
	public abstract void exibirDetalhes();
	
	
}
			
