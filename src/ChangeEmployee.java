import java.util.*;
public class ChangeEmployee {
    public void ChangeName(Employee employee){
        Scanner input= new Scanner(System.in);
        System.out.println("Write the new name:");
        String name= input.nextLine();
        employee.setName(name);
    }
    public void ChangeAdress(Employee employee){
        Scanner input= new Scanner(System.in);
        System.out.println("Write the new address:");
        String adress= input.nextLine();
        employee.setAdress(adress);
    }
    public Employee ChangerTypeEmployee(Employee employee){
        Scanner input= new Scanner(System.in);
        PaymentMethod auxPay= employee.getPayment();
        System.out.println("Select the new employee type:\n1-Hourly\n2-Salaried\n3-Commissioned");
        int nSelect= input.nextInt();
        switch (nSelect) {
            case 1:
                if(employee.typeEmployee().equals("Hourly")){
                    System.out.println("Selected employee is hourly type.");
                    break;
                }
                System.out.println("Hourly wage");
                int hours= input.nextInt();
                employee= new Hourly(employee.getName(), employee.getAdress(), employee.getId(), hours, employee.getTaxSyndicate());
                employee.setPayment(auxPay);
                break;
            case 2:
            if(employee.typeEmployee().equals("Salaried")){
                System.out.println("Selected employee is salaried type.");
                break;
            }
                System.out.println("Monthly salary");
                Double salary= input.nextDouble();
                employee= new Salaried(employee.getName(), employee.getAdress(), employee.getId(), salary, employee.getTaxSyndicate());
                employee.setPayment(auxPay);
            break;

            case 3:
            if(employee.typeEmployee().equals("Commssioned")){
                System.out.println("Selected employee is commssioned type.");
                break;
            }
                System.out.println("Monthly salary");
                Double salary_commission= input.nextDouble();
                System.out.println("Commission");
                Double commission= input.nextDouble();
                employee= new Commissioned(employee.getName(), employee.getAdress(), employee.getId(), salary_commission, commission, employee.getTaxSyndicate());
                employee.setPayment(auxPay);
            break;
            default:
                break;
        }
        return employee;
    }
    
}
