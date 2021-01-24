package com.quickpocs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import model.Foo;

public class Execute {

	public static void main(String[] args) {		
		ExecutorService executor = Executors.newWorkStealingPool();				
		List<Callable<Foo>> callables = new ArrayList<Callable<Foo>>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 2000; i++) {
			callables.add(new Work(i));
		}			
		try {
			executor.invokeAll(callables)
				.stream()
				.map(future -> {
					try {
						return future.get();
					} catch (Exception e) {
						System.out.println("Error -- " + e.getMessage());
						//return new Foo();
						throw new IllegalStateException(e);
					}
				} )
				.forEach((item) -> System.out.println(item.getName() + " -- " + item.getId().toString() + " || " + item.getElapsedTime()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Tempo decorrido " + (end - start));
	}

}
