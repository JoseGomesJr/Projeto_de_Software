import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        EmployeeList employees= new EmployeeList();
        Scanner input= new Scanner(System.in);
        int nSelect=0;
        do
        {
            System.out.println("Selection Option:");
            System.out.println("1-New employee");
            System.out.println("2-Remove employee");
            System.out.println("3-Report time card");
            System.out.println("0-Exit");
            nSelect= input.nextInt();
           switch (nSelect) {
               case 1:
                    employees.novo();
                    continue;
                case 2:
                    employees.removeEmployee();
                    continue;
                case 3:
                    employees.Timecard();
                    continue;
                

               default:
                   break;
           }
           
        }while(nSelect!=0);

        employees.printList();
        employees.Infotime();
        employees.getSyndicatelist();
        input.close();
    }
}
