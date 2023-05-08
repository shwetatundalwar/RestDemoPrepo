package practice;

public class demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String name1 = "Fizz";
	    String name2 = "Buzz";
	    
	    for(int i=1;i<=15;i++){
	    	
	        if(i%3==0 && i%5==0) {
	        	System.out.println("FizzBuzz");
	        	break;
	        }
	        if(i%3==0){
	            
	            System.out.println(name1);
	            
	        }if(i%5==0){
	            
	            System.out.println(name2);
	        }else{
	            
	            System.out.println(i);
	        }
	        
	    }

	}

}
