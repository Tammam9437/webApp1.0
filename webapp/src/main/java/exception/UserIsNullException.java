package exception;

public class UserIsNullException extends RuntimeException{
    
	private static final long serialVersionUID = 1L;

		public UserIsNullException(){
                super();
        }
        
        public UserIsNullException(String message){
                super(message);
        }
}