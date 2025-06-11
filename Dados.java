package br.com.maisunifacisa;


import java.util.ArrayList;
import java.util.List;

public class Dados {
	private List<Tarefa> atividades = new ArrayList<>();
	private List<Usuario> usuarios = new ArrayList<>();

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Tarefa> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Tarefa> atividades) {
		this.atividades = atividades;
	}
	public void adicionarAtividade(Tarefa atividade) {
		atividades.add(atividade);
	}
	public void adicionarUsuario(Usuario usuario) {
		usuarios.add(usuario);

	}
	public Boolean excluirTarefa(String titulo) {
		for(Tarefa tarefa : tarefas) {
			if(tarefa.getTitulo().equals(titulo)) {
				atividades.remove(tarefa);
				return true;
			}
		}
		return false;
	}
	
	public void exibirAtividades() {
		if (atividades.isEmpty()) {
			System.out.println("Nenhuma atividade cadastrada.");
		} else {
			for (Tarefa atividade : atividades) {
				atividade.exibirDetalhes();
			}
		}
		}
	public void buscarPorUsuario(String nomeUsuario) {
		boolean encontrado = false;
		for (Tarefa atividade : atividades) {
			if (atividade.getUsuarioResponsavel().getNome().equalsIgnoreCase(nomeUsuario)) {
				atividade.exibirDetalhes();
				encontrado = true;
			}
		}
		if (!encontrado) {
			System.out.println("Nenhuma atividade encontrada para o usuário: " + nomeUsuario);
		}
	}
	public Boolean buscarPeloTitulo(String titulo) {
		for(Tarefa tarefa : tarefas) {
			if(tarefa.getTitulo().equals(titulo)) {
				return true;
				}
			}
				return false;
		}

}
