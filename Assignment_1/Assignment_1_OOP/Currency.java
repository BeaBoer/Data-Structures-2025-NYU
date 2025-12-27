public abstract class Currency implements Exchangeable{

    private String currencyName;
    private double totalFunds;

    // constructor
    public Currency(String currencyName, double totalFunds) {
        this.currencyName = currencyName;
        this.totalFunds = totalFunds;
    }

    public double getTotalFunds(){
        return totalFunds;
    }
    
    public String getCurrencyName(){
        return currencyName; 
    }
    
    public void setTotalFunds(double amount){
        this.totalFunds = amount;  
    }
    // abstract methods
    public abstract double toEarthDollars(double amount);

    public abstract double fromEarthDollars(double earthDollars);

    /**
     * @Override
     *           public void exchange(Currency other, double amount) {
     * 
     *           if (amount)
     *           System.out.println("Converting from " + this.currencyName + " to "
     *           + other.currencyName
     *           + " and initiating transfer...");
     *           };
     */

    public static void main(String[] args) {

        Mars mars = new Mars(100.00);
        Jupiter jupiter = new Jupiter(100.00);
        Saturn saturn = new Saturn(100.00);

        System.out.println("<-- Exchanges -->");

        mars.exchange(saturn, 25.00);
        jupiter.exchange(saturn, 10.00);
        saturn.exchange(mars, 122.00); 
        jupiter.exchange(mars, 50.00);
    }
}