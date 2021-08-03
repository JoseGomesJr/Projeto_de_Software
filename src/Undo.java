import java.util.List;

public class Undo {
    public static int emp=1;
    //Meta dados a serem salvos para possivel retrocesso
    private Employee semployee;
    private String sname;
    private int sobj;
    private int soption;
    private int idsyn;
    private Double spay;
    private Double commission;
    public void Salve(int option, String name){

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
    public void Time(){
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
    public void comission(){
        if(semployee.typeEmployee().equals("Commssioned")){
            ((Commissioned) semployee).setComissionTotal(commission);
            ((Commissioned) semployee).removdate(sname);
            
        }else{
            System.out.println("Something went wrong!");
        }
    }
    public void taxService(){
        
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
    public static int getEmp() {
        return emp;
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
