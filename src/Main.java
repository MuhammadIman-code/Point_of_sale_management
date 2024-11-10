
import java.util.Scanner;
public class Main {

    public static void main(String[] a2rgs) {
        itemsMenue item = new itemsMenue();

        customerMenue customer = new customerMenue();
        Sale sale = new Sale();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.out.println("----------Point of Sale Terminal----------");
        while (choice != 6) {

            System.out.println("Main Menu:");
            System.out.println("1. Manage Items");
            System.out.println("2. Manage Customers");
            System.out.println("3. Make New Sale");
            System.out.println("4. Make Payment");
            System.out.println("5. Print Reports");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Manage Items selected");
                    item.menue();
                    break;
                case 2:
                    System.out.println("Manage Customers selected");
                    customer.customer_menue();
                    break;
                case 3:
                    System.out.println("Make New Sale selected");
                    //sale.destroy();
                    sale.MadeSale();
                    break;
                case 4:
                    System.out.println("Make Payment selected");
                    // Add your code for making a payment here
                    break;
                case 5:
                    System.out.println("Print Reports selected");
                    // Add your code for printing reports here
                    break;
                case 6:

                    System.out.println("Exiting...");

                    sale.save_sales();
                    screenCleaner.clearScreen();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }
}