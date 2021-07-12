import java.util.*;

public class Commissioned extends Employee {
    private Double monthly_salary;
    private Double commission;
    private Double comissionTotal;
    private List<String> dates= new ArrayList<>();
    Commissioned(String name, String adress, int id, Double salary, Double commission, Double taxSyndicate)
    {
        super(name, adress, id, taxSyndicate);
        this.monthly_salary= salary;
        this.commission= commission;
        this.comissionTotal=0d;
    }
    public void setMonthly_salary(Double monthly_salary) {
        this.monthly_salary = monthly_salary;
    }
    public void setCommission(Double commission) {
        this.commission = commission;
    }
    public Double getMonthly_salary() {
        return monthly_salary;
    }
    public Double getCommission() {
        return commission;
    }
    public void setComissionTotal(Double valor, String date) {
       comissionTotal+=valor*(commission/100);
       dates.add(date);
    }
    @Override
    public String typeEmployee(){
        return "Commssioned";
    }
}
