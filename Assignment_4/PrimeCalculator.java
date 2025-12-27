public class PrimeCalculator{

	public void primesTo(int n) {
		if(n<2) {
			throw new IllegalArgumentException("Error: Input must be a number greater than or equal to 2.");
		}
		Queue<Integer> numbers = new ArrayQueue<Integer>(); 
		for (int i = 2; i < n+1; i++) {
			numbers.enqueue(i);
		}
		Queue<Integer> primes = new ArrayQueue<Integer>(); 
		
		while(!numbers.isEmpty()) {
			int p = numbers.first(); 
			primes.enqueue(p);
		    numbers.dequeue();
		    
		    if(numbers.size()==0) {
		    	break; 
		    }
		    //if numbers is empty
			if(numbers.size()==1){
				primes.enqueue(numbers.first());
				break; 
			}
			//if size is 1
			int checker = numbers.first();
			int i = numbers.first(); 
			do {
				if(i%p==0){
					numbers.dequeue(); 
				}
				else{
					numbers.enqueue(numbers.first());
					numbers.dequeue(); 
				} 
				i = numbers.first(); 
			}while(i != checker); //all numbers compared to p
		}//all prime numbers added to primes

		System.out.println("Printing primes up to " + n + ":");
		while(primes.size() != 1){
			System.out.print(primes.dequeue() + ", "); 
		}
		System.out.println(primes.dequeue() + ".");	
	} //end of primesTo

	public static void main (String [] args) {
		new PrimeCalculator().primesTo(20);
		new PrimeCalculator().primesTo(2); 
		new PrimeCalculator().primesTo(0); 
	}//end of main method
} //end of PrimeCalculator
	