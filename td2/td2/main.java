import java.util.ArrayList;
import java.util.Random;

public class main 
{
   static int p = 46337;
   
   public static void main(String[] args) 
   {
      ArrayList<Integer> al = new ArrayList<Integer>(20);
      
      for(int i = 0; i < 20; i ++){
          al.add(i,i);
      }

      QuadraticSpacePerfectHashing qhash = new QuadraticSpacePerfectHashing<>(al);
      System.out.println(qhash);

      LinearSpacePerfectHashing lhash = new LinearSpacePerfectHashing<>(al);
      System.out.println(lhash);
      
      int cle33dansLinear = lhash.getKey(33);
      System.out.println("Cle de 33 dans le linear : " + cle33dansLinear);
      System.out.println("Cle de 33 dans le quadratic : " + lhash.data[cle33dansLinear].getKey(33));
      System.out.println("Est present : " + 33 + " " + lhash.containsValue(33));
    }
}


