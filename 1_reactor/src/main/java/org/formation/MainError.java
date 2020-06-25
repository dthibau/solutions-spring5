package org.formation;



import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

public class MainError {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hooks.onOperatorDebug();
		
		MainError.methode3();
		
		MainError.methode2();
		
		MainError.methode1();
		
	}
	
	public static void methode1() {
	 
		Flux.range(1, 10)
		  .log()
		  .subscribe(MainError::badUncheckedMethod);
	}
	
	public static void methode2() {
		 
		Flux.range(1, 10)
		  .log()
		  .subscribe(MainError::badUncheckedMethod, e -> System.err.println(e) );
	}
	
	public static void methode3() {
		 
		Flux.range(1, 10)
		  .log()
		  .map(MainError::badUncheckedMethod)
		  .onErrorResume(e -> Flux.range(5, 5))
		  .subscribe(i -> System.out.println(i) );
	}
	
	
	public static int badUncheckedMethod(int i) {
		if ( i == 5 )
			throw new RuntimeException("5");
		return i;
	}
}
