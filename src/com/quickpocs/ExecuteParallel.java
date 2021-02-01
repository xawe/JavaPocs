package com.quickpocs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.Foo;

public class ExecuteParallel {

	/* 
	 * Exemplo de chamada paralela usando o parallelStream
	 * Na minha maquina, média de 25 segundos para executar usando sleep
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long start = System.currentTimeMillis();
		
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			ids.add(i);
		}		
		
		List<Foo> items = ids
			.parallelStream()
			.map( p -> new SimpleWork(p).doWork())
			.collect(Collectors.toList());							
		
		long end = System.currentTimeMillis();
		System.out.println("Total de itens " + items.size());
		System.out.println("Tempo decorrido " + (end - start));
		

	}

}
