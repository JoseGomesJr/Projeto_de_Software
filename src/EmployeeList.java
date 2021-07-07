import java.util.*;
public class EmployeeList {
    private List<Employee> employeelist= new ArrayList<>();
    private List<Syndicate> syndicatelist= new ArrayList<>();
    private Employee employee;
    private PayFunction payFunction= new PayFunction();
    private AuxFunction auxFunction= new AuxFunction();
    public void novo(){
        Scanner input= new Scanner(System.in);

        System.out.println("Employer's name");
        String name= input.nextLine();

        System.out.println("Employer's Adress");
        String adress= input.nextLine();

        int id= employeelist.size();

       System.out.printf("Type Employee:1-Hourly\n2-Salaried\n3-Comissioned\nSelect Number:");
       int num= input.nextInt();

       switch (num) {
           case 1:
                System.out.println("Hourly wage");
                int hours= input.nextInt();
                employee= new Hourly(name,adress,id,hours);
             
            break;

            case 2:
                System.out.println("Monthly salary");
                Double salary= input.nextDouble();
                employee= new Salaried(name, adress, id, salary);
               
            break;

            case 3:
                System.out.println("Monthly salary");
                Double salary_commission= input.nextDouble();
                System.out.println("Commission");
                Double commission= input.nextDouble();
                employee= new Commissioned(name, adress, id, salary_commission, commission);
            break;

           default:
            break;
       }
       auxFunction.AddSyndicate(employee, syndicatelist);
       employee.setPayment(payFunction.AddMethod());
       employeelist.add(employee);
    }
    public void printList()
    {
        for(Employee employees: employeelist)
        {
            System.out.println("----EMPLOYEE----");
            System.out.println(employees.printInfo());
            System.out.println(employees.getPayment().InfoPay()+"\nEmployee belongs to the union: "+employees.getSyndicate());
            
        }
    }
    public void removeEmployee()
    {
        Scanner input= new Scanner(System.in);
        System.out.println("Select a form of identification:\n1-id\n2-Name"); // generalize a forma de seleção 
        int nSelect= input.nextInt();
        switch (nSelect) {
            case 1:
                System.out.println("Enter ID");
                int id= input.nextInt();
                id= auxFunction.CheckEmployees(employeelist, id);
                if(id!=-1)  employeelist.remove(id);
                break;
            case 2:
                System.out.println("Enter the name\nCaution!The employee's name must be entered in the same way it was registered");
                String name= input.nextLine();
                int idname= auxFunction.CheckEmployees(employeelist, name);
                if(idname!=-1) employeelist.remove(idname);
                break;
            default:
                break;
        }
        
        
    }
    public void getSyndicatelist() {
        for(Syndicate syndicate: syndicatelist)
        {
            System.out.println("----SYNDICATE----");
            System.out.println(syndicate.printInfo());  
        }
    }
    public void Timecard(){
        Scanner input= new Scanner(System.in);
        System.out.println("Enter the name\nCaution!The employee's name must be entered in the same way it was registered");
        String name= input.nextLine();
        int idname= auxFunction.CheckEmployees(employeelist, name);
        if(idname!=-1){
            auxFunction.AddTimecard(employeelist, idname);
        }
    }
    public void Infotime(){
        Scanner input= new Scanner(System.in);
        System.out.println("Enter the name\nCaution!The employee's name must be entered in the same way it was registered");
        String name= input.nextLine();
        int idname= auxFunction.CheckEmployees(employeelist, name);
        if(idname!=-1){
            auxFunction.Printcard(employeelist, idname);
        }
    }
}
