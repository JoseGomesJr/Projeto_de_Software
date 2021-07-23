import java.time.LocalDate;
import java.util.*;

public class PayFunction {
    private PaymentMethod pMethod;
    private PayDay pDay= new PayDay();
    
    public PaymentMethod AddMethod(){
        Scanner input= new Scanner(System.in);
        System.out.printf("Select a payment method:\n1-Check by post\n2-Check in hand\n3-Bank Account\n");
        int nSelect= input.nextInt();
        switch (nSelect) {
            case 1:
                this.pMethod= new PaymentMethod("Check by post");
                break;
            case 2:
                this.pMethod= new PaymentMethod("Check in hand");
                break;
            case 3:
                PayBanck();
                break;    
            default:
                break;
        }
       return this.pMethod;
    }
    private void PayBanck(){
        Scanner input= new Scanner(System.in);
        System.out.println("Inform the bank account where the amount will be deposited:");
        System.out.println("Bank name:");
        String nameBank= input.nextLine();
        System.out.println("Type Account:\nEnter the type of account, example: checking account");
        String typeAccount = input.nextLine();
        System.out.println("Number Account:\nAccount number must be a number");
        int numbeAccount= input.nextInt();
        this.pMethod= new PaymentMethod("Bank Account", numbeAccount, nameBank, typeAccount);
    }
    public void Schedule(PaymentMethod payment, String type){
        PayDay pay= new PayDay();
        switch (type) {
            case "Hourly":
                pay.setOption(0);
                break;
            case "Salaried":
                pay.setOption(2);
                break;
            case "Commssioned":
                pay.setOption(1);
                break;
            default:
                break;
        }
        payment.setpDay(pay);
    }
    private void printPayEmployee(Employee employee){
        System.out.println("----EMPLOYEE----");
        System.out.println(employee.getName()+"\n"+employee.getId());
        System.out.println("The salary amount based on the calculations made is"+employee.payMent());
        System.out.println(employee.getPayment().InfoPay());
    }
    private void payString(String day, int week, Employee employee){
        LocalDate date= LocalDate.now();
        String daynow= date.getDayOfWeek().name();
        Calendar calendar= Calendar.getInstance();
        int weeknow = calendar.get(Calendar.WEEK_OF_MONTH);
        if(day.equals(daynow) && week==weeknow){
            printPayEmployee(employee);
        }
        else if(day.equals(daynow) && week==1){
            printPayEmployee(employee);
        }
    }
    private void payMONTH( int day, Employee employee){
        Calendar calendar= Calendar.getInstance();
        int daynow = calendar.get(Calendar.DAY_OF_MONTH);
       if(day==daynow){
            printPayEmployee(employee);
       }
    }
    public void payment(List<Employee> employees)
    {
       for(Employee employee: employees){
            String payString= employee.getPayment().getpDay().opitionString();
            String aux[]= payString.split(" ");
            int week;
            switch (aux[0]) {
                case "WEEKLY":
                    week= Integer.parseInt(aux[1]);
                    payString(aux[2], week, employee);
                    break;
                case "MONTLHY":
                    if(aux[1].equals("$")){
                        monthly(employee);
                    }
                    else{
                        week= Integer.parseInt(aux[1]);
                        payMONTH(week, employee);
                    }
                    break;
                default:
                    break;
            }
       }
    }
    private void monthly(Employee employee){
        Calendar calendar= Calendar.getInstance();
        if(calendar.getTime().equals(getUltimoDiaUtilDoMes(calendar).getTime())){
            printPayEmployee(employee);
        }
    }
    private static Calendar getUltimoDiaUtilDoMes(Calendar calendar) {
        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.DATE, -1);
        while(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -1);            
        }
        //System.out.println(calendar.getTime());
        return calendar;
    }
    public void PaymentSchedule(PaymentMethod payment){
        Scanner input= new Scanner(System.in);
        PayDay pay= payment.getpDay();
        int option=0;
        System.out.println("Options:");
        for(String schedule: pay.getSchedule()){
            System.out.println(option+"- "+schedule);
            option++;
        }
        System.out.println("Choose a date option:");
        int selectoption= input.nextInt();
        if(selectoption>option-1){
            System.out.println("None of the options were selected, you will return to the start menu");
        }
        else{
            payment.getpDay().setOption(selectoption);
        }

    }
}
