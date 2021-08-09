import java.util.*;

public class Undo {
    //Acões que foram efetuadas
    public static final int NEWEMP=1;
    public static final int REMOVEEMP=2;
    public static final int TIME=3;
    public static final int COMISSION=4;
    public static final int TAXSERVICE=5;
    public static final int CHANGERNAME=61;
    public static final int CHANGERADRESS=62;
    public static final int CHANGERTYPE=63;
    public static final int CHANGERPAY=64;
    public static final int CHANGERSYND=65;
    public static final int CHANGERIDSYN=66;
    public static final int CHANGERTAXSYND=67;
    public static final int PAY=7;
    //Meta dados a serem salvos para possivel retrocesso
    private Employee semployee;
    private String sname;
    private int sobj;
    private int soption;
    private int idsyn;
    private Double spay;
    private Double commission;
    private PaymentMethod spayment;
    private Double taxsynd;
    private List<Double> comission;
    private List<Double> taxservice;
    private List<Double> payhour;
    public void Salve(int option, String name, Employee employee){
        this.soption= option;
        this.sname= name;
        this.semployee= employee;
    }
    public void Salve(int option, Employee nEmployee){
        this.semployee= nEmployee;
        this.soption= option;
    }
    public void Salvesyndi(int option, Employee nEmployee, int idsyn){
        this.semployee= nEmployee;
        this.soption= option;
        this.idsyn= idsyn;
    }
    public void Salvecommission(int option, Double comission, String date, Employee employee){
        this.soption=option;
        this.commission= comission;
        this.sname= date;
        this.semployee= employee;
    }
    public void SalveTaxservi(int option, Double tax, Employee employee){
        this.soption=option;
        this.semployee= employee;
        this.spay= tax;
    }
    public void Salvetime(int option,Double pay, Employee employee, int aux){
        this.sobj=aux;
        this.semployee=employee;
        this.soption=option;
        if(aux == 2){
            this.spay= pay;
        }
    }
    public void Salvepay(int option, PaymentMethod pay, Employee employee){
        this.soption=option;
        this.semployee= employee;
        this.spayment= pay;
    }
    public void Salveidsyn(int option, int idsyn, int sobj){
        this.soption= option;
        this.idsyn= idsyn;
        this.sobj= sobj;
    }
    public void Salvesalary(int option,List<Employee> employeelist){
        this.soption=option;
        this.comission= new ArrayList<>();
        this.payhour= new ArrayList<>();
        this.taxservice= new ArrayList<>();
        for(Employee employee: employeelist){
            switch (employee.typeEmployee()) {
                case "Hourly":
                    Double aux_pay= ((Hourly) employee).getPay();
                    this.payhour.add(aux_pay);
                    this.taxservice.add(employee.getTaxService());
                    break;
                case "Commssioned":
                    Double aux_commission= ((Commissioned) employee).getComissionTotal();
                    this.comission.add(aux_commission);
                    this.taxservice.add(employee.getTaxService());
                    break;
                case "Salaried":
                    this.taxservice.add(employee.getTaxService());
                    break;
                default:
                    break;
            }
        }
    }
    public void undoTime(){
        if(this.sobj==1){
            if(semployee.typeEmployee().equals("Hourly")){
                ((Hourly)semployee).resetEntry();
            }else{
                System.out.println("Something went wrong!");
            }
        }
        else if(this.sobj==2){
            if(semployee.typeEmployee().equals("Hourly")){
                ((Hourly)semployee).resetExit();
                ((Hourly)semployee).setPay(this.spay);
                this.spay=0d;
            }else{
                System.out.println("Something went wrong!");
            }
        }
    }
    public void undocomission(){
        if(semployee.typeEmployee().equals("Commssioned")){
            ((Commissioned) semployee).setComissionTotal(commission);
            ((Commissioned) semployee).removdate(sname);
            
        }else{
            System.out.println("Something went wrong!");
        }
    }
    public void undotaxService(){
        semployee.setTaxService(spay);
    }
    public void undoname(){
        semployee.setName(sname);
    }
    public void undoadress(){
        semployee.setAdress(sname);
    }
    public void undotype( List<Employee> employees, List<Syndicate> syndicates){
        int posi=0;
        for(Employee employee: employees){
            if(semployee.getName().equals(employee.getName()) && semployee.getId()==employee.getId()){
                employees.remove(posi);
                employees.add(posi, semployee);
            }
            posi++;
        }
        posi=0;
        if(semployee.getSyndicate()){
            for(Syndicate syndicate: syndicates){
                if(semployee.getName().equals(syndicate.getUnionlist().getName()) && semployee.getId()==syndicate.getUnionlist().getId()){
                    syndicates.get(posi).setUnionlist(semployee);
                }
                posi++;
            }
        }
    }
    public void undopayment(){
        this.semployee.setPayment(this.spayment);
    }
    public void undoidsyn(List<Syndicate> syndicatelist){
        syndicatelist.get(this.sobj).setId(this.idsyn);
    }
    public void undotaxsynd(){
        this.semployee.setTaxSyndicate(this.taxsynd);
    }
    public void undosalary(List<Employee> employeelist){
        int posipay=0;
        int positax=0;
        int posicomission=0;
        for(Employee employee: employeelist){
            switch (employee.typeEmployee()){
                case "Hourly":
                    Double aux_pay=this.payhour.get(posipay);
                    ((Hourly) employee).setPay(aux_pay);
                    employee.setTaxService(this.taxservice.get(positax));
                    posipay++;
                    positax++;
                    break;
                case "Commssioned":
                    Double aux_commission=this.comission.get(posicomission); 
                    ((Commissioned) employee).setComissionTotal(aux_commission);
                    employee.setTaxService(this.taxservice.get(positax));
                    posicomission++;
                    positax++;
                    break;
                case "Salaried":
                    employee.setTaxService(this.taxservice.get(positax));
                    positax++;
                    break;
                default:
                    break;
            }
        }
    }
    public Employee getSemployee() {
        return semployee;
    }
    public int getSoption() {
        return soption;
    }
    public int getIdsyn() {
        return idsyn;
    }
    public Double getSpay() {
        return spay;
    }
    public int getSobj() {
        return sobj;
    }
    public void setSoption(int soption) {
        this.soption = soption;
    }
}
