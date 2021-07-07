public class Hourly extends Employee {
    private int hours;
    private int taxhours;
    private TimeCard card= new TimeCard();
    public Hourly(String name, String adress, int id, int hours){
        super(name, adress, id);
        this.hours=hours;
        this.taxhours=0;
    }
    public void setHours(int hours) {
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
    @Override
    public String typeEmployee(){
        return "Hourly";
    }

}
