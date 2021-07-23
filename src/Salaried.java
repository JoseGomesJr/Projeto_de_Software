public class Salaried extends Employee {
    private Double monthly_salary;
    Salaried(String name, String adress, int id, Double salary, Double taxSyndicate){
        super(name, adress, id, taxSyndicate);
        monthly_salary=salary;
    }
    public void setMonthly_salary(Double monthly_salary) {
        this.monthly_salary = monthly_salary;
    }
    public Double getMonthly_salary() {
        return monthly_salary;
    }
    public String typeEmployee(){
        return "Salaried";
    }
    public Double payMent() {
        Double paytotal= monthly_salary-(monthly_salary*(this.getTaxSyndicate()/100));
       if(this.getTaxService()!=0){
        paytotal= paytotal-(paytotal*(this.getTaxService()/100));
       }
       return paytotal;
    }

}
