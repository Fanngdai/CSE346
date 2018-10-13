package cse346proj1;

/**
 * Network of VLSI Switching Elements
 * 
 * Language Used: Java
 * No packages used
 * 
 * This program generates the probability that there is a throughput at A or C.
 * With the calculated value, I compare it with random values from 0-100 while
 * multiplying the probability by 100. I compare the probability there is a packet
 * at A twice with 2 random numbers because there are 2 inputs. For probability of
 * an output of C, I have to compare the number I generated with 4 random values.
 * I do this for ten-thousand times and get the average since the more times I do
 * this, the closer the value I get is to the actual probability.
 * 
 * For the extra credit, I implemented the model equation which is the probability
 * there is an output at a certain percentage. For both A and C.
 * 
 * Project 1
 * Spring 2018
 * Due: Monday, Feb 12th, 2018
 * @author Fanng Dai
 */
public class CSE346Proj1 {
    static int timesToCalculate = 10000;
    static double[] a = new double[101];
    static double[] c = new double[101];
    
    /*
    * @brief Prints the probability of throughput A from 0.01 to 1 incrementing by 0.01.
    *
    * @param prob The probability
    * @return The prob to 
    */
    public static double extraCreditA(double prob){
        if(prob < 0 || prob > 1) {
            System.out.println("Prob not right");
            return 0;
        }
        double percent = throughputA(prob);
//        System.out.print("Prob:\t");
//        System.out.printf("%.2f",prob);
//        System.out.println("\tPercentage:\t" + percent);
        System.out.println(percent);
        return extraCreditA(prob + 0.01);
    }
   
    public static double extraCreditC(double prob){ 
        if(prob < 0 || prob > 1) {
            System.out.println("Prob not right");
            return 0;
        }
        double percent = throughputC(prob);
//        System.out.print("Prob:\t");
//        System.out.printf("%.2f",prob);
//        System.out.println("\tPercentage:\t" + percent);
        System.out.println(percent);
        return extraCreditC(prob + 0.01);
    }
    
    /**
     * Calculate the prob of throughput given a certain prob
     * 
     * @param prob
     *  Calculate the prob
     * 
     * @return 
     *  The prob of the throughput
     */
    public static double throughputA(double prob) {
        return 1 - (1 - prob) * (1 - prob);
    }
    
    public static double throughputC(double prob) {
        double q = 1 - (1 - prob) * (1 - prob);
        return 1- ((1-q)*(1-q));
    }
    
    public static boolean compareRand(double num) {
        if(Math.random()*100 <= num*100)
            return true;
        return false;
    }
    
    /**
     * Simulate
     * 
     * Actual project
     * 
     * @param prob
     */
    public static void simulate(int num){
        double probability = num/100.0;
        if(probability < 0 || probability > 1) {
            System.out.println("Prob not right");
        } else {
            double totalA = 0;
            double totalC = 0;
            for(int i=0; i < timesToCalculate; i++) {
                if(Math.random() < probability || Math.random() < probability){
                    totalA ++;
                    totalC ++;
                } else if(Math.random() < probability || Math.random() < probability) {
                    totalC ++;
                }
            }
            a[num] = totalA/timesToCalculate;
            c[num] = totalC/timesToCalculate;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Extra Credit
//        System.out.println("Extra Credit A");
//        extraCreditA(0);
//        System.out.println("\n\nExtra Credit C");
//        extraCreditC(0);
        
        for(int i=0; i<=100; i++){
//            System.out.println("Prob: " + (i/100) + "\tPercent: " + simulateA(i/100));
            simulate(i);
        }
        
        System.out.println("Simulate A");
        for(int i=0; i<=100; i++)
            System.out.println(a[i]);
        System.out.println("\n\nSimulate C");
        for(int i=0; i<=100; i++)
            System.out.println(c[i]);
    }
}
