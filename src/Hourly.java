public class Hourly extends Employee {
    private int hours;
    private int taxhours;
    private Double hoursDay[];
    private TimeCard card= new TimeCard();
    public Hourly(String name, String adress, int id, int hours, Double taxSyndicate){
        super(name, adress, id, taxSyndicate);
        this.hours=hours;
        this.taxhours=0;
    }
    public void setHours(int hours){
        this.hours = hours;
    }
    public int getHours() {
        return hours;
    }
    public int getTaxhours() {
        return taxhours;
    }
    public void setEntryCard( ) {
        this.card.EntTimeCard();
    }
    public void setExitCard( ) {
        this.card.ExiTimeCard();
    }
    public void Cardinf(){
        card.getDateInfo();
    }
    public void setHoursDay(int day) {
        if(day>1 && day<7){
            this.hoursDay[day]=card.InforHoras();
        }
    }
    @Override
    public String typeEmployee(){
        return "Hourly";
    }

}
