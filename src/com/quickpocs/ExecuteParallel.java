package com.quickpocs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
		Collection<Foo> sCollection = Collections.synchronizedCollection(new ArrayList<Foo>());
		ids
			.parallelStream()
			.forEach(p->{
				sCollection.add(new SimpleWork(p).doWork());
			});					
		
		long end = System.currentTimeMillis();		
		System.out.println("Tempo decorrido " + (end - start));

	}

}
