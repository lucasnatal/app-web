package com.betha.daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.betha.business.Pessoa;
import com.betha.util.FabricaSessao;

public class PessoaDAO {
	private Session session;
	private List<Pessoa> lista;

	public PessoaDAO() {
		this.setSession(FabricaSessao.abrirSessao());
	}

	public Pessoa buscarPorCodigo(Integer codigo) {

		for (int i = 0; i < this.lista.size(); i++) {
			if (this.lista.get(i).getCodigo() == codigo)
				return this.lista.get(i);
		}
		return null;
	}

	public List<Pessoa> listarTodas() {
		return this.session.createCriteria(Pessoa.class).list();
	}
	

	public List<Pessoa> listarOrdenadoPor(Boolean asc, String por) {
		return (asc ? session.createCriteria(Pessoa.class)
				.addOrder(Order.asc(por)).list() : session
				.createCriteria(Pessoa.class).addOrder(Order.desc(por)).list());
	}

	public List<Pessoa> listarOrdenadoPor(Boolean asc, String por, String filtro) {
		return (asc ? session.createCriteria(Pessoa.class)
				.add(Restrictions.like(por, filtro, MatchMode.ANYWHERE))
				.addOrder(Order.asc(por)).list() : session
				.createCriteria(Pessoa.class)
				.add(Restrictions.like(por, filtro, MatchMode.ANYWHERE))
				.addOrder(Order.desc(por)).list());
	}

	public List<Pessoa> buscar(String column, String value, Boolean asc) {

		return (asc ? session.createCriteria(Pessoa.class)
				.add(Restrictions.like(column, value, MatchMode.ANYWHERE))
				.addOrder(Order.asc(column)).list() 
				: 
				session.createCriteria(Pessoa.class)
				.add(Restrictions.like(column, value, MatchMode.ANYWHERE))
				.addOrder(Order.desc(column)).list());
	}

	public void update(Pessoa pessoa) {
		Transaction t = session.beginTransaction();
		session.merge(pessoa);
		t.commit();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
