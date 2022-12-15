package math;

public class FractionDivisionByZero extends RuntimeException{
    public FractionDivisionByZero(){
        super("Division by zero!");
    }

    public FractionDivisionByZero(String message){
        super(message);
    }

    public FractionDivisionByZero(Throwable cause){
        super("Division by zero!",cause);
    }

    public FractionDivisionByZero(String message, Throwable cause){
        super(message,cause);
    }
}
