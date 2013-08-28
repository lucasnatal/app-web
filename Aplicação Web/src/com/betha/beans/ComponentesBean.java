package com.betha.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import com.betha.business.Pessoa;
import com.betha.business.PessoaComparator;
import com.betha.daos.PessoaDAO;
import com.betha.util.FabricaSessao;
import com.betha.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ComponentesBean {
	private List<Pessoa> lista;
	private List<Pessoa> filtrados;
	private List<Pessoa> selecionados;
	private Pessoa pessoa;
	private boolean checkboxSelecionado;
	private boolean sorted;
	private boolean asc;
	private String filtro;
	private PessoaDAO pessoaDao;

	
	private Pessoa pessoaSelecionada;

	public ComponentesBean() {
		this.pessoaDao = new PessoaDAO();
		this.lista = pessoaDao.listarTodas();
		this.selecionados = new ArrayList<Pessoa>();
		this.filtrados = new ArrayList<Pessoa>();
	}

	public List<Pessoa> getLista() {
		return lista;
	}

	public void setLista(List<Pessoa> lista) {
		this.lista = lista;
	}

	public List<Pessoa> getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(List<Pessoa> selecionados) {
		this.selecionados = selecionados;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public boolean isCheckboxSelecionado() {
		return checkboxSelecionado;
	}

	public void setCheckboxSelecionado(boolean checkboxSelecionado) {
		this.checkboxSelecionado = checkboxSelecionado;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public boolean isSorted() {
		return sorted;
	}

	public void setSorted(boolean sorted) {
		this.sorted = sorted;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Pessoa> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Pessoa> filtrados) {
		this.filtrados = filtrados;
	}

	public void selecionar() {
		for (int i = 0; i < selecionados.size(); i++) {
			System.out.println(selecionados.get(i).getNome());
		}
	}

	public void marcarTodos() {
		for (int i = 0; i < this.lista.size(); i++) {
			this.lista.get(i).selecionado =(this.checkboxSelecionado);
		}
	}

	public void sort() {
		this.setSorted(true);
		this.setAsc(!this.asc);
		if (this.filtro != null && this.filtro.length() > 0) {
			this.filtrados = pessoaDao.listarOrdenadoPor(asc, "nome", this.filtro);
		} else {
			this.lista = pessoaDao.listarOrdenadoPor(asc, "nome");
		}
	}

	public void buscar() {
		
			this.filtrados = this.pessoaDao.buscar("nome", this.filtro, this.asc);
	}
	
	public void editar(Pessoa pessoa)
	{
		if(this.pessoaSelecionada == null || this.pessoaSelecionada != pessoa){
			this.pessoaSelecionada = pessoa;
		}else{
			this.pessoaSelecionada = null;
			this.pessoaDao.update(pessoa);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro salvo com sucesso!", ""));
		}
	}
	
	public void excluir(Pessoa pessoa){
		this.lista.remove(pessoa);
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa excluída com sucesso!", ""));
	}
	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

}
