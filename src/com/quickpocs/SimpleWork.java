package com.quickpocs;

import java.util.Random;

import model.Foo;

public class SimpleWork {

	private Integer _id;
	
	public SimpleWork(Integer id) {
		_id = id;
	}
	
	public Foo doWork() {
		
		Random r = new Random();		
		Foo f = new Foo();
		f.setActive(true);
		f.setId( _id);				
		f.setName(String.valueOf( r.nextInt()));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return f;
	}
}
