package mbeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;

import control.ControleDisciplina;
import entidades.Disciplina;



/*
 * 
 * TODO Disciplina (ver arquivo) 
 *  BEAN:
 *   Revisar, arrumar e deletar métodos desnecessários/ruins/inúteis.
 *   
 *  JSF: 
 *   Implementar avisos (ao deletar) e validações dos dados. 
 * 
 * 
 */







@ManagedBean(name = "disciplinaMBean")
@ViewScoped
public class DisciplinaMBean {

	private ControleDisciplina controleDisciplina;

	// Lista de todas as disciplinas
	private static ArrayList<Disciplina> lista;

	// Lista de disciplinas pesquisadas (fiz isso para evitar muitas consultas
	// ao banco de dados)
	private static ArrayList<Disciplina> listaPesquisa;
	private Disciplina selecionado;

	private int id;
	private String nome;

	public DisciplinaMBean() {

		if (lista == null) {
			lista = new ArrayList<Disciplina>();
		}

		if (this.getNome() == null) {
			this.setNome("");
		}

		controleDisciplina = ControleDisciplina.getInstance();

		atualizarListagem();
	}

	public String cadastrar() {

		System.out.println("metodo cadastrar Ok");

		if (!(this.getNome().isEmpty() || this.getNome() == " " || this
				.getNome() == "  ")) {
			Disciplina disciplina = new Disciplina();
			disciplina.setNome(this.getNome());

			controleDisciplina.adicionar(disciplina);
		} else {
			System.out.println("Disciplina não inserida.");
		}

		limparCampos();
		atualizarListagem();

		return null;
	}

	public String editar() {

		if (this.getDisciplinaSelecionada() != null){
			// Pegando ID da disciplina selecionada e o nome editado pelo usuário (no edit.xhtml)
			String nome = this.getDisciplinaSelecionada().getNome();
			int id = this.getDisciplinaSelecionada().getId();
			
			// Verificando dados
			System.out.println("---------------------");
			System.out.println("nome editado: " + this.getDisciplinaSelecionada().getNome());
			System.out.println("---------------------");
			
			// Atualizando disciplina
			Disciplina disciplinaAtualizar = new Disciplina(id, nome);
			controleDisciplina.atualizar(disciplinaAtualizar);
			
			// Atualizando dados da disciplinaSelecionada e das listas
			Disciplina disciplina = controleDisciplina.consultaDisciplina(id);
			selecionado = disciplina;
			
			limparCampos();
			atualizarListagem();
		}

		return null;
	}

	public String deletar() {
		
		// Prevenindo para ID da disciplina selecionada não ser null
		if(this.getDisciplinaSelecionada() != null){
			
			int id = this.getDisciplinaSelecionada().getId();
			
			// Limpando a disciplina selecionada antes de excluir do banco/lista (se não dá erro)
			setDisciplinaSelecionada(null);
			
			// Removando da banco/lista
			controleDisciplina.remover(id);
			System.out.println("Disciplina excluída!");

			// Atualizando lista
			limparCampos();
			atualizarListagem();
		}

		 return null;
	}

	/**
	 * Atualizar a listagem de disciplinas (chamado do próprio Bean, ou seja, na
	 * primeira vez que executa) e a lista de disciplinasPesquisadas - agora listaDisciplinasPesquisadas 
	 * tem todas as disciplinas.
	 */
	public void atualizarListagem() {
		this.setListaDisciplinas(controleDisciplina.consulta());
		listaPesquisa = (ArrayList<Disciplina>) this.getListaDisciplinas().clone();
	}

	/**
	 * Atualiza a listagem de disciplinas (chamado da View) TODO: Futuramente
	 * implementar de forma que não seja case-sensitive?
	 * 
	 * @param e
	 */
	public void atualizarListagemPesquisa(ValueChangeEvent e) {
		if (e.getNewValue().toString().length() > 0) {
			this.pegarDisciplinaLista(e.getNewValue().toString());
			System.out.println("Disciplina pesquisada com nome: "
					+ e.getNewValue().toString());
		} else {
			atualizarListagem();
			System.out.println("Deveria mostrar todas as disciplinas agora.");
		}
	}
	
	/**
	 * Limpa todos os objetos selecionados na tabela.
	 */
	public void limparSelecionadosDataTable() {
		RequestContext.getCurrentInstance().execute(
				"resultadosDisciplina.unselectAllRows()");
	}
	
	/**
	 * Limpa o campo de Input.
	 */
	private void limparCampos() {
		this.setNome("");
	}

	/**
	 * Adiciona na listaDisciplinasPesquisadas as disciplinas que contém no seu
	 * nome o termo pesquisado pelo usuário. Criei este novo arrayList pq é
	 * melhor usar um ArrayList ao invés de ficar fazendo consultas toda hora no
	 * banco.
	 * 
	 * @param termo
	 */
	private void pegarDisciplinaLista(String termo) {

		for (int i = 0; i < this.getListaDisciplinas().size(); i++) {

			Disciplina estaDisciplina = this.getListaDisciplinas().get(i);
			boolean disciplinaTemEsseTermo = estaDisciplina.getNome().contains(
					termo);

			if (disciplinaTemEsseTermo) {
				if (!containsListaDisciplina(estaDisciplina)) {
					this.getListaDisciplinasPesquisadas().add(estaDisciplina);
				} else {
					// não faz nada, pois a disciplina já está na lista
				}
			} else { // disciplina não tem esse termo
				if (!containsListaDisciplina(estaDisciplina)) {
					// não faz nada, pois a disciplina sem o termo não está na
					// lista
				} else {
					this.getListaDisciplinasPesquisadas()
							.remove(estaDisciplina);
				}
			}
		}
	}

	private boolean containsListaDisciplina(Disciplina estaDisciplina) {

		for (Disciplina d : this.getListaDisciplinasPesquisadas())
			if (d.getNome().equals(estaDisciplina.getNome()))
				return true;

		return false;
	}

	
	
	
	
	
	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ControleDisciplina getControleDisciplina() {
		return controleDisciplina;
	}

	public void setControleDisciplina(ControleDisciplina controleDisciplina) {
		this.controleDisciplina = controleDisciplina;
	}

	public Disciplina getDisciplinaSelecionada() {
		System.out.println("Get Disciplina selecionada: "
				+ selecionado);
		return selecionado;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.selecionado = disciplinaSelecionada;
		System.out.println("Set Disciplina selecionada: "
				+ disciplinaSelecionada);
	}

	public ArrayList<Disciplina> getListaDisciplinas() {
		return lista;
	}

	public void setListaDisciplinas(ArrayList<Disciplina> listaDisciplinas) {
		DisciplinaMBean.lista = listaDisciplinas;
	}

	public ArrayList<Disciplina> getListaDisciplinasPesquisadas() {
		return listaPesquisa;
	}

	public void setListaDisciplinasPesquisadas(ArrayList<Disciplina> lista) {
		DisciplinaMBean.listaPesquisa = lista;
	}
}