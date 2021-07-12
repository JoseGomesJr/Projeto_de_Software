import java.util.*;
public class AuxEmployee {
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
    public int CheckEmployees(List<Employee> employees, int id){
        Scanner input= new Scanner(System.in);
        int aux=SeachEmployee(id, employees);
        if(aux==-1){
            System.out.println("Employee id does not exist");
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
        System.out.println("Is the new employee part of the union?1-Yes\n2-No");
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
            System.out.println("Choose the option:\n1-Entry time\n2-Exit time");
            int nSelect= input.nextInt();
            switch (nSelect) {
                case 1:
                    ((Hourly)employees.get(id)).setEntryCard();
                    break;
                case 2:
                    ((Hourly)employees.get(id)).setExitCard();
                    ((Hourly)employees.get(id)).setHoursDay(day());
                    break;
                default:
                    System.out.println("None of the options were selected, you will return to the start menu");
                    break;
            }
        }
        else{
            System.out.println("The employee informed is not an hourly");
        }
        return;
    }
    public int day(){
        Calendar calendar= Calendar.getInstance();
        int day=calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }
    public void Printcard(List<Employee> employees, int id){
        if(employees.get(id).typeEmployee().equals("Hourly"))
        {
           ((Hourly)employees.get(id)).Cardinf();
        }
        return;
    }
    public int SearchEmployeeList(List<Employee> employeelist){
        Scanner input= new Scanner(System.in);
        int id=-1;
        System.out.println("Select a form of identification:\n1-id\n2-Name"); // generalize a forma de seleção 
        int nSelect= input.nextInt();
        input.nextLine();
        switch (nSelect){
            case 1:
                System.out.println("Enter ID");
                id= input.nextInt();
                id= CheckEmployees(employeelist, id);
                if(id!=-1)  return id;
                break;
            case 2:
                System.out.println("Enter the name\nCaution!The employee's name must be entered in the same way it was registered");
                String name= input.nextLine();
                id= CheckEmployees(employeelist, name);
                if(id!=-1) return id;
                break;
            default:
                System.out.println("None of the options were selected, you will return to the start menu");
                break;
        }
        return id;
    }
    public int SeachSyndicate(List<Syndicate> syndicates, Employee employee){
        int posi=0, aux=-1;
        for(Syndicate syndicate : syndicates)
        {
            if(syndicate.getUnionlist().equals(employee))
            {
                aux=posi;
                break;
            }
            posi++;
        } 
        return aux;
    }
    public void AddSyndicate(Employee employee, List<Syndicate> syndicates, int id, int local){
        Syndicate unionlist;
        employee.setSyndicate(true);
        unionlist= new Syndicate(employee, id);
        syndicates.add(local,unionlist);
    }
    public void RemoveSyndicate(Employee employee, List<Syndicate> syndicates){
        employee.setSyndicate(false);
        int aux= SeachSyndicate(syndicates, employee);
        if(aux!=-1){
            syndicates.remove(aux);
        }
        else{
            System.out.println("The data entered is not associated with any unionist");
        }
    }
    
}
