package com.betha.business;

import java.util.Comparator;

public class PessoaComparator implements Comparator<Pessoa>{
	public boolean asc;
	
	public PessoaComparator(boolean asc){
		this.asc = asc;
	}
	@Override
	public int compare(Pessoa o1, Pessoa o2) {
		if(this.asc)
			return o1.getNome().compareTo(o2.getNome());
		return o2.getNome().compareTo(o1.getNome());
	}

}
