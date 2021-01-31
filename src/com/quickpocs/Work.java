package com.quickpocs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;

import model.Foo;

public class Work  implements Callable<Foo>{
	
	private int _id ;
	public Work(Integer in) {
		_id = in;
	}
	
	@Override
	public Foo call() throws Exception {
		long start = System.currentTimeMillis();


		Random r = new Random();		
		Foo f = new Foo();
		f.setActive(true);
		f.setId( _id);				
		f.setName(String.valueOf( r.nextInt()));
		try {
			if(_id == 10) {				
				f.setActive(false);
				//throw new IllegalStateException();
			}			
			Thread.sleep(5000);
					
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		long end = System.currentTimeMillis();		
		f.setElapsedTime(end - start);
		return f;
	}

}
