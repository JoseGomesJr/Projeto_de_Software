public class Salaried extends Employee {
    private Double monthly_salary;
    Salaried(String name, String adress, int id, Double salary){
        super(name, adress, id);
        monthly_salary=salary;
    }
    public void setMonthly_salary(Double monthly_salary) {
        this.monthly_salary = monthly_salary;
    }
    public Double getMonthly_salary() {
        return monthly_salary;
    }
    @Override
    public String typeEmployee(){
        return "Salaried";
    }


}
