/**
 * 
 */
package com.betha.util;

import java.util.ArrayList;

import org.hibernate.Session;

import com.betha.business.Pessoa;

/**
 * @author Lucas Natal
 *
 */
public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = FabricaSessao.abrirSessao();
		
		Pessoa p =(Pessoa) session.get(Pessoa.class, 1);
		System.out.println(p.getNome());
		
		ArrayList<Pessoa> p2 = (ArrayList<Pessoa>) session.createCriteria(Pessoa.class).list();
		
		for (Pessoa pessoa : p2) {
			System.out.println(pessoa.getNome());
		}
	}

}
