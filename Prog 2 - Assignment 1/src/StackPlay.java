public class StackPlay {

	private static long moves;
   /**
    * Returns the current number of times an element has been moved
    * @return the number of moves
    */   
   public static long getNumberOfMoves() {
	   return moves;
   }
   
   /**
    * Moves numbers from one stack to another using
    * the push and pop methods and a third help stack.
    * @param from the source stack
    * @param to the destination stack
    * @param help the help stack
    * @param n the number of integers to be moved
    */   
   public static void move(SpecialStack from, SpecialStack to, SpecialStack help, int n) {
	   if(n == 1) {
		   to.push(from.pop());
	   }
	   else {
		   move(from, help, to, n - 1);
		   to.push(from.pop());
		   move(help, to, from, n - 1);
	   }
	   moves++;
//        to.push(from.pop());
//        help.push(from.pop());
//        help.push(to.pop());
//        to.push(from.pop());
//        from.push(help.pop());
//        to.push(help.pop());
//        to.push(from.pop());
   }
   
   public static void main(String[] args) {
	  int size = 10;
      SpecialStack from = new SpecialStack(size);
      SpecialStack to = new SpecialStack();
      SpecialStack help = new SpecialStack();
      System.out.println("Start state");
      System.out.println("   From: " + from);
      System.out.println("   To:   " + to);
      System.out.println("   Help: " + help);
      System.out.println("   Number of moves: " + getNumberOfMoves());
      long startTime = System.currentTimeMillis();
      move(from, to, help, size);
      long endTime = System.currentTimeMillis();
      System.out.println("End state");
      System.out.println("   From: " + from);
      System.out.println("   To:   " + to);
      System.out.println("   Help: " + help);
      System.out.println("   Number of moves: " + getNumberOfMoves());
      
      
      System.out.println("Time: " + (endTime - startTime) / 1000f);
   }
}
