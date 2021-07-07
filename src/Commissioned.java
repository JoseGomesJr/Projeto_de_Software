public class Commissioned extends Employee {
    private Double monthly_salary;
    private Double commission;
    Commissioned(String name, String adress, int id, Double salary, Double commission)
    {
        super(name, adress, id);
        this.monthly_salary= salary;
        this.commission= commission;
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
    @Override
    public String typeEmployee(){
        return "Commssioned";
    }
}
