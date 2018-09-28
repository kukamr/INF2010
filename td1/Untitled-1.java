public class Main{

    public int factorielle(int n) {
        if(n == 1){
            return 1;
        }
        else {
            return n*factorielle(n-1);
        }
    }

    public static void main(String[] args){
        int resultat = factorielle(5);
        
        System.out.println(resultat);
    }
}