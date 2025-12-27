public class Saturn extends Currency {

    //constructor
    public Saturn(double startingBalance){
        super("SaturnSilver", startingBalance); 
    }

    //implementing abstract methods from Currency
    @Override
    public double toEarthDollars(double amount){
        return amount / Exchangeable.SS;
    }

    @Override
    public double fromEarthDollars(double earthDollars){
        return earthDollars * Exchangeable.SS; 
    }

    //exchange method with 7% fee
    @Override
    public void exchange(Currency other, double amount){
        double fee = 5; 
        double minimumFunds = amount + fee; 

        if (minimumFunds > this.getTotalFunds()){
            System.out.println("Uh oh — " + this.getClass().getSimpleName() + " only has an available balance of " + getTotalFunds() + " which is less than " + minimumFunds + "!\n");
            return;
        }

        //Conversions 
        double earthDollars = this.toEarthDollars(amount); 
        double convertedCurrency = other.fromEarthDollars(earthDollars);

        //Updates to balances
        this.setTotalFunds(this.getTotalFunds() - minimumFunds);
        other.setTotalFunds(other.getTotalFunds() + convertedCurrency);

        System.out.println("Converting from " + this.getCurrencyName() + " to " + other.getCurrencyName() + " and initiating transfer...");
        System.out.println(String.format("%.2f",amount) + " " + this.getCurrencyName() + " = " + String.format("%.2f",earthDollars) + " EarthDollars = " + String.format("%.2f",convertedCurrency) + " " + other.getCurrencyName());
        System.out.println("Saturn exchange fee is " + String.format("%.2f",fee) + " " + this.getCurrencyName());
        System.out.println("Saturn has a total of " + String.format("%.2f",getTotalFunds()) + " " + this.getCurrencyName());
        System.out.println(other.getClass().getSimpleName() + " has a total of " + String.format("%.2f",other.getTotalFunds()) + " " + other.getCurrencyName() + "\n");
    } 
}