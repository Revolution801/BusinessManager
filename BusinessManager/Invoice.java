/***************************************
 * Created by Kent Allen and Drew Adams
 * 4/01/2015
 **************************************/

// Static class
public class Invoice {
   private static double totalPrice, seniorDiscount;
   
   public static void printInvoice(Client client, String currentDate) {
      // Prints header
      System.out.printf("%-32s %26s%n", "Bill To: ", "Invoice Date:");
      System.out.printf("%-32s %26s%n",client.getFName() + " " + client.getLName(), currentDate);
      System.out.printf("%s%n", client.getPhone());
      System.out.printf("%s%n", client.getAddress());
      System.out.printf("%s, %s, %s%n", client.getCity(), client.getState(), client.getZip());
      System.out.println();
      System.out.printf("%-10s %-32s %-8s %-8s%n", "Date", "Services", "Hours", "Amount");
      System.out.println("-----------------------------------------------------------");
      
      // Checks if jobs for client are hourly or not and prints job info
      for(Job el : client.getJobList()) {
         if(el.getHours() > 0)
            System.out.printf("%-10s %-32s %-8.2f %6.2f%n", el.getDate(), 
               el.getTitle(), el.getHours(), totalPrice(el));
         else
            System.out.printf("%-10s %-32s %15.2f%n", el.getDate(), el.getTitle(), totalPrice(el));
      }
      System.out.println();
      
      // Checks if client recieves senior discount and prints if they do
      if(client.getSeniorDiscount())
         System.out.printf("%n%21s %37s%n", "Senior Discount (10%)", 
            "-"+String.format("%.2f",seniorDiscount(client)));
      
      System.out.println("-----------------------------------------------------------");
      System.out.printf("%50s %8.2f%n", "Total Amount Due: ", grandTotal(client));
   }
   
   // Calculates total price of a job, hourly or not
   public static double totalPrice(Job job) {
      if(job.getHours() > 0)
         return job.getPrice()*job.getHours();
      
      return job.getPrice();
   }
   
   // Calculates total amount of invoice
   public static double grandTotal(Client client) {
      totalPrice = 0;
      
      for(Job el : client.getJobList()) {
         if(el.getHours() > 0.0) {
            totalPrice += el.getPrice()*el.getHours();
         }else {
            totalPrice += el.getPrice();
         }
      }
      
      // If client gets senior discount the price is reduced
      if (client.getSeniorDiscount())
         return totalPrice - seniorDiscount;
      else
         return totalPrice;
   }
   
   // Calculates amount of discount
   public static double seniorDiscount(Client client) {
      seniorDiscount = grandTotal(client)*.1;
      return seniorDiscount;
   }
}