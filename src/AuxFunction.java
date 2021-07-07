import java.util.*;
public class AuxFunction {
    public int SeachEmployee (String name, List<Employee> employees){
        int posi=0, aux=-1;
        for(Employee employee: employees)
        {
            if(name.equals(employee.getName()))
            {
                aux=posi;
                break;
            }
            posi++;
        } 
        return aux;
    }
    public int SeachEmployee (int id, List<Employee> employees){
        int posi=0, aux=-1;
        for(Employee employee: employees)
        {
            if(id==employee.getId())
            {
                aux=posi;
                break;
            }
            posi++;
        } 
        return aux;
    }
    public int CheckEmployees( List<Employee> employees, String name){
        Scanner input= new Scanner(System.in);
        int aux=SeachEmployee(name, employees);
        if(aux==-1){
            System.out.println("Employee name does not exist");
            System.out.println("Would you like to enter a new name\n1-Yes\n2-No");
            int nSelect= input.nextInt();
            switch (nSelect) {
                case 1:
                    System.out.println("Enter the new name\nCaution!The employee's name must be entered in the same way it was registered");
                    String newName= input.nextLine();
                    return CheckEmployees(employees, newName);
                default:
                    break;
            }
        }
        else{
            return aux;
        }
        return -1;

    }
    public int CheckEmployees( List<Employee> employees, int id){
        Scanner input= new Scanner(System.in);
        int aux=SeachEmployee(id, employees);
        if(aux==-1){
            System.out.println("Employee name does not exist");
            System.out.println("Would you like to enter a new id\n1-Yes\n2-No");
            int nSelect= input.nextInt();
            switch (nSelect) {
                case 1:
                    System.out.println("Enter the new ID");
                    int newID= input.nextInt();
                    return CheckEmployees(employees, newID);
                default:
                    break;
            }
        }
        else{
            return aux;
        }
        return -1;

    }
    public void AddSyndicate(Employee employee, List<Syndicate> syndicates){
        Scanner input= new Scanner(System.in);
        Syndicate unionlist;
        System.out.println("Is the new employee part of the union?");
        int nSelect= input.nextInt();
        if(nSelect==1){
            System.out.println("What is the employee union id?");
            int id= input.nextInt();
            employee.setSyndicate(true);
            unionlist= new Syndicate(employee, id);
           syndicates.add(unionlist);
        }
    }
    public void AddTimecard(List<Employee> employees, int id){
        Scanner input= new Scanner(System.in);
        if(employees.get(id).typeEmployee().equals("Hourly"))
        {
            Hourly hourly= (Hourly) employees.get(id);
            System.out.println("Choose the option:\n1-Entry time\n2-Exit time");
            int nSelect= input.nextInt();
            switch (nSelect) {
                case 1:
                    hourly.setEntryCard();
                    break;
                case 2:
                    hourly.setExitCard();;
                    break;
                default:
                    break;
            }
            employees.add(id, hourly);
        }
        return;
    }
    public void Printcard(List<Employee> employees, int id){
        Scanner input= new Scanner(System.in);
        if(employees.get(id).typeEmployee().equals("Hourly"))
        {
            Hourly hourly= (Hourly) employees.get(id);
           hourly.Cardinf();
        }
        return;
    }
}
