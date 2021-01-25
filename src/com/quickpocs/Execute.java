package com.quickpocs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.Foo;

public class Execute {

	/* 
	 * Exemplo de chamada de multiplas funções em paralelo usando Callable e syncronizedCollection 
	 * para armazenamento dos resultados
	 * WIP - pendente implementação e comparação usando CopyOnWriteArrayList
	 */
	public static void main(String[] args) {		
		ExecutorService executor = Executors.newWorkStealingPool();				
		List<Callable<Foo>> callables = new ArrayList<Callable<Foo>>();
		List<Foo> copyOnWriteArray = new CopyOnWriteArrayList<Foo>();
		
		long start = System.currentTimeMillis();
		Collection<Foo> syncCollection = Collections.synchronizedCollection(new ArrayList<Foo>());
		for (int i = 0; i < 50; i++) {
			callables.add(new Work(i));
		}			
		try {
			executor.invokeAll(callables)
				.stream()
				.map(future -> {
					try {
						Foo itemRecebido = future.get();
						syncCollection.add(itemRecebido);
						return itemRecebido;
					} catch (Exception e) {
						System.out.println("Error -- " + e.getMessage());
						throw new IllegalStateException(e);
					}
				} )
				.forEach((item) -> System.out.println(item.getName() + " -- " + item.getId().toString() + " || " + item.getElapsedTime()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		long end = System.currentTimeMillis();		
		System.out.println("Tempo decorrido " + (end - start));
		
		System.out.println("Exibindo não ativos");
		Foo retorno = syncCollection
			.stream()
			.filter(x-> x.getActive().equals(Boolean.FALSE))
			.findFirst()
			.get();
			
		System.out.println(retorno.getId() + " -- " + retorno.getName());
	}

}
