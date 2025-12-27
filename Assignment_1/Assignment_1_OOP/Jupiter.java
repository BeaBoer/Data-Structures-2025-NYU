public class Jupiter extends Currency{

    //counts transaction number
    private int transactionCount = 0; 

    //constructor
    public Jupiter(double startingBalance){
        super("JupiterJingles", startingBalance); 
    }

    //implementing abstract methods from Currency
    @Override
    public double toEarthDollars(double amount){
        return amount / Exchangeable.JJ;
    }

    @Override
    public double fromEarthDollars(double earthDollars){
        return earthDollars * Exchangeable.JJ; 
    }

    //exchange method with transaction requirement
    @Override
    public void exchange(Currency other, double amount){
        double fee; 

        if (transactionCount == 0)
        {
            fee = 0; 
        }
        else
        {
            fee = (double) Math.round(amount * 0.1 *100) / 100; 
        }

        double minimumFunds = amount + fee; 

        if (minimumFunds > this.getTotalFunds()){
            System.out.println("Uh oh — " + this.getCurrencyName() + " only has an available balance of " + getTotalFunds() + " which is less than " + amount + "!\n");
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
        System.out.println("Jupiter exchange fee is " + String.format("%.2f",fee) + " " + this.getCurrencyName());
        System.out.println("Jupiter has a total of " + String.format("%.2f",getTotalFunds()) + " " + this.getCurrencyName());
        System.out.println(other.getClass().getSimpleName() + " has a total of " + String.format("%.2f",other.getTotalFunds()) + " " + other.getCurrencyName() + "\n");

        //incriment after successful transaction
        transactionCount++; 
    } 
}