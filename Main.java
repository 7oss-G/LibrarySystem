import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        int maxCapacity=0;
        Scanner input = new Scanner(System.in);
        try{
        System.out.println("Kindly input the maximum libraary capacity to initiate the Library system");
        boolean done=false;
     
        maxCapacity=input.nextInt();
        if(maxCapacity <=0){
            throw new Exception("maxCapacity can not be ess than or equal 0");}
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        LibrarySystem NUlibrary= new LibrarySystem(maxCapacity);
        input.close();
        
    }
    
}