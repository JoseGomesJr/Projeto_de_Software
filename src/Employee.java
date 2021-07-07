public class Employee {
    private String name;
    private String adress;
    private int id;
    private PaymentMethod payment;
    private boolean syndicate;
    //private int maior=0;

    Employee(String name, String adress, int id){
        this.name=name;
        this.adress= adress;
        this.id= id;
        this.syndicate= false;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setSyndicate(boolean syndicate) {
        this.syndicate = syndicate;
    }
    public String getName() {
        return this.name;
    }
    public String getAdress() {
        return this.adress;
    }
    public int getId() {
        return this.id;
    }
    public boolean getSyndicate(){
        return this.syndicate;
    }
    public void setPayment(PaymentMethod payment) {
        this.payment = payment;
    }
    public PaymentMethod getPayment() {
        return this.payment;
    }
    public String typeEmployee(){
        return "Employee";
    }
    
    public String printInfo(){
        return "Name Employee: "+name+"\nAdress Employee: "+adress+"\nID Employee: "+id+"\nType Employee: "+this.typeEmployee()+"\n";
    }
}