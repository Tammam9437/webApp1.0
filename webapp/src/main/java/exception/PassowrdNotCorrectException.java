package exception;

public class PassowrdNotCorrectException extends RuntimeException{
    
	private static final long serialVersionUID = 1L;

		public PassowrdNotCorrectException(){
                super();
        }
        
        public PassowrdNotCorrectException(String message){
                super(message);
        }
}