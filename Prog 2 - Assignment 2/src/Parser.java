import java.util.Map;
import java.util.TreeSet;

/**
 * A template for the parser class. Most of the methods are included
 * but they do nothing but a call to the next metod.
 * 
 * How to use:
 * 1. Copy the class to your own map
 * 2. Rename the file and the class to Parser (don't forget the constructor!)
 * 3. Create the exception classes SyntaxException and EvaluationException
 * 4. Compile!
 * 5. Run and see that the calcultar is just able to handle numbers.
 * 6. Add addition handling in the method expression.
 * 7. Add handling of subtractions also in the method expression
 * 8. Add everything else...
 * 
 * @version 2016-08-11
 */
public class Parser {
  private Stokenizer tokenizer;
  private Map<String, Double> variables; 
  private TreeSet<String> functions;
  
  public Parser(Stokenizer tokenizer, Map<String,Double> variables) {
    this.tokenizer = tokenizer;
    this.variables = variables;
    functions = new TreeSet<String>();  
    functions.add("sin");
    functions.add("cos");
    functions.add("exp");
    functions.add("log");   
  }
 
  public double assignment() {
    double result = expression(); 
    while(tokenizer.getToken().equals("=")) {
    	tokenizer.nextToken();
    	if(tokenizer.isWord()) {
    		this.variables.put(tokenizer.getToken(), result);	
    		tokenizer.nextToken();
    	}
    	else {
    		throw new SyntaxException("Excepted word");
    	}
    }
    return result;  
  }
  
  public double expression() {
    double sum = term();
    while(tokenizer.getToken().equals("+") || tokenizer.getToken().equals("-")) {
    	if(tokenizer.getToken().equals("+")) {
	    	tokenizer.nextToken();
	        sum += term();
    	}
    	if(tokenizer.getToken().equals("-")) {
        	tokenizer.nextToken();
        	sum -= term();
        }
    }
    return sum;
  }
  
  public double term() {
    double prod = factor();
    while(tokenizer.getToken().equals("*") || tokenizer.getToken().equals(("/"))) {
    	if(tokenizer.getToken().equals("*")) {
	    	tokenizer.nextToken();
	        prod *= factor();
    	}
        if(tokenizer.getToken().equals("/")) {
        	tokenizer.nextToken();
        	prod /= factor();
        }
    }
    return prod;
  } 
  
  public double factor() {
    return primary();
  }
   
  public double primary() {
    double result = 99999;
    if(tokenizer.getToken().equals("(")) {
    	tokenizer.nextToken();
    	result = assignment();
    	if(tokenizer.getToken().equals(")")) {
        	tokenizer.nextToken();
    	}
    }
    else if(functions.contains(tokenizer.getToken())) {
    	String functionName = tokenizer.getToken();
    	tokenizer.nextToken();
    	result = functionHandler(functionName);
    }
    else if (tokenizer.isNumber()) {
      result = tokenizer.getNumber();
      tokenizer.nextToken();
    }
    else if(tokenizer.getToken().equals("-")) {
    	tokenizer.nextToken();
    	result = -primary();
    }
    else if(tokenizer.isWord()) {
    	if(this.variables.containsKey(tokenizer.getToken())) {
	    	result = this.variables.get(tokenizer.getToken());
	    	tokenizer.nextToken();
    	}
    	else {
    		tokenizer.nextToken();
    		throw new EvaluationException("Variable " + tokenizer.getToken() + " does not exist in memory");
    	}
    }
    else { 
      throw new SyntaxException("Check for double signs");
    }
    return result;
  }
  
  double functionHandler(String functionName) {
	  double result = primary();
	  if(functionName.equals("sin")) {
		  result = Math.sin(result);
	  }
	  else if(functionName.equals("cos")) {
		  result = Math.cos(result);
	  }
	  else if(functionName.equals("exp")) {
		  result = Math.exp(result);
	  }
	  else if(functionName.equals("log")) {
		  result = Math.log(result);
	  }
	  return result;
  }

} 