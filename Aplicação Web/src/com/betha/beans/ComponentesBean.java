package com.betha.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.betha.business.Pessoa;
import com.betha.business.PessoaComparator;
import com.betha.daos.PessoaDAO;

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

	public ComponentesBean() {
		PessoaDAO pessoaDao = new PessoaDAO();
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
			this.lista.get(i).setSelecionado(this.checkboxSelecionado);
		}
	}

	public void sort() {
		this.setSorted(true);
		this.setAsc(!this.asc);
		if (this.filtro != null && this.filtro.length() > 0) {
			Collections.sort(this.filtrados, new PessoaComparator(this.asc));
		} else {
			Collections.sort(this.lista, new PessoaComparator(this.asc));
		}
	}

	public void buscar() {
		this.filtrados = new ArrayList<Pessoa>();
		for (int i = 0; i < this.lista.size(); i++) {
			if (this.lista.get(i).getNome().toLowerCase()
					.contains(this.filtro.toLowerCase())) {
				this.filtrados.add(this.lista.get(i));
			}
		}
	}
}
