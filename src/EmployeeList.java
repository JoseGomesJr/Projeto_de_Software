import java.util.*;
public class EmployeeList {
    private List<Employee> employeelist= new ArrayList<>();
    private List<Syndicate> syndicatelist= new ArrayList<>();
    private Employee employee;
    private PayFunction payFunction= new PayFunction();
    private AuxEmployee AuxEmployee= new AuxEmployee();
    private ChangeEmployee change= new ChangeEmployee();
    public void novo(){
        Scanner input= new Scanner(System.in);

        System.out.println("Employer's name");
        String name= input.nextLine();

        System.out.println("Employer's Adress");
        String adress= input.nextLine();
        System.out.println("Union fee to be charged.");
        Double taxSyndicate= input.nextDouble();
        int id= employeelist.size();

       System.out.printf("Type Employee:1-Hourly\n2-Salaried\n3-Comissioned\nSelect Number:");
       int num= input.nextInt();

       switch (num) {
           case 1:
                System.out.println("Hourly wage");
                Double hours= input.nextDouble();
                employee= new Hourly(name,adress,id,hours, taxSyndicate);
             
            break;

            case 2:
                System.out.println("Monthly salary");
                Double salary= input.nextDouble();
                employee= new Salaried(name, adress, id, salary, taxSyndicate);
               
            break;

            case 3:
                System.out.println("Monthly salary");
                Double salary_commission= input.nextDouble();
                System.out.println("Commission");
                Double commission= input.nextDouble();
                employee= new Commissioned(name, adress, id, salary_commission, commission, taxSyndicate);
            break;

           default:
           System.out.println("None of the options were selected, you will return to the start menu");
            break;
       }
       AuxEmployee.AddSyndicate(employee, syndicatelist);
       employee.setPayment(payFunction.AddMethod());
       payFunction.Schedule(employee.getPayment(), employee.typeEmployee());
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
        int id= AuxEmployee.SearchEmployeeList(employeelist);
        int idsyn;
        // remover o empregado da lista de sindicatos
        if(id!=-1) {
            if(employeelist.get(id).getSyndicate()==true){
                idsyn=AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(id));
                syndicatelist.remove(idsyn);
            }
            employeelist.remove(id);

        }
        else{
            System.out.println("The data entered is not associated with any employee");
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
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            AuxEmployee.AddTimecard(employeelist, idname);
        }
        else{
            System.out.println("The data entered is not associated with any employee");
        }
    }
    public void Infotime(){
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            AuxEmployee.Printcard(employeelist, idname);
        }
        else{
            System.out.println("The data entered is not associated with any employee");
        }
    }
    public void Salles(){
        Scanner input= new Scanner(System.in);
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        System.out.println("Informs the sales result:");
        if(idname!=-1){
            if(employeelist.get(idname).typeEmployee().equals("Commssioned")){
                System.out.println("Sale value:");
                Double valor= input.nextDouble();
                System.out.println("Date:");
                System.out.println("The date must be informed in the following format : dd/mm/yyyy HH:mm");
                String date= input.nextLine();
                ((Commissioned) employeelist.get(idname)).setComissionTotal(valor, date);
            }
            else{
                System.out.println("The employee informed is not a commissioner");
            }
        }
        else{
            System.out.println("The data entered is not associated with any employee");
        }
        

    }
    public void TaxService(){
        Scanner input= new Scanner(System.in);
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            System.out.println("Inform the percentage that should be charged to the employee");
            Double tax= input.nextDouble();
            employeelist.get(idname).setTaxService(tax);

        }
        else{
            System.out.println("The data entered is not associated with any employee");
        }
    }
    public void ChangerEmployee(){
        Scanner input= new Scanner(System.in);
        System.out.println("Select the employee you want to change");
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            System.out.println("1-Change employee name");
            System.out.println("2-Change employee address");
            System.out.println("3-Change employee type");
            System.out.println("4-Change employee payment method");
            System.out.println("5-Remove a union member / Make an employee part of the union");
            System.out.println("6-Change union ID");
            System.out.println("7-Change the union fee");
            int nSelect= input.nextInt();
            switch (nSelect) {
                case 1:
                    change.ChangeName(employeelist.get(idname));
                    break;
                case 2:
                    change.ChangeAdress(employeelist.get(idname));
                    break;
                case 3:
                   if(employeelist.get(idname).getSyndicate()==false){
                       employee=employeelist.get(idname);
                       employeelist.remove(idname);
                       employeelist.add(idname, change.ChangerTypeEmployee(employee));
                   }
                   else{
                        int id= AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(idname));
                        employee=employeelist.get(idname);
                        employeelist.remove(idname);
                        employeelist.add(idname, change.ChangerTypeEmployee(employee));
                        if(id!=-1) {
                            int idsyndicate=syndicatelist.get(id).getId();
                            syndicatelist.remove(id);
                            AuxEmployee.AddSyndicate(employeelist.get(idname), syndicatelist, idsyndicate, id);
                        }
                   }
                    break;
                case 4:
                    System.out.println("Choose new payment method");
                    employeelist.get(idname).setPayment(payFunction.AddMethod());
                   break;
                case 5:
                
                   if(!employeelist.get(idname).getSyndicate()){

                        System.out.println("Employee is not part of the union, would you like to add?\n1-Yes\n2-No");
                        int select= input.nextInt();
                        if(select==1){
                            AuxEmployee.AddSyndicate(employeelist.get(idname), syndicatelist);
                        }
                   }
                   else
                   {
                        System.out.println("Does the employee part of the union wish to remove?\n1-Yes\n2-No");
                        int select= input.nextInt();
                        if(select==1){
                            AuxEmployee.RemoveSyndicate(employeelist.get(idname), syndicatelist);
                        }
                        
                   }
                   
                   break;
                case 6:
                    int id= AuxEmployee.SeachSyndicate(syndicatelist, employeelist.get(idname));
                    if(id!=-1){
                        System.out.println("Enter the new ID");
                        int newid= input.nextInt();
                        syndicatelist.get(id).setId(newid);
                    }
                    else{
                        System.out.println("The data entered is not associated with any unionist");
                    }
                   break;
                case 7:
                    System.out.println("Inform the new union fee");
                    Double taxSyndicate= input.nextDouble();
                    employeelist.get(idname).setTaxSyndicate(taxSyndicate);
                    break;
                default:
                    System.out.println("None of the options were selected, you will return to the start menu");
                    break;
            }
           
        }
        else{
            System.out.println("The data entered is not associated with any employee");
        }
    }
    public void pay(){
        payFunction.payment(employeelist);
    }
    public void scheduleoptions(){
        Scanner input= new Scanner(System.in);
        System.out.println("Select the employee you want to change");
        int idname= AuxEmployee.SearchEmployeeList(employeelist);
        if(idname!=-1){
            payFunction.PaymentSchedule(employeelist.get(idname).getPayment()); 
        }
        else{
            System.out.println("The data entered is not associated with any employee");
        }
    }
    public void addSchedule(){
        Scanner input= new Scanner(System.in);
        String schedule= input.nextLine();
        payFunction.addSchedule(schedule);
    }

}
