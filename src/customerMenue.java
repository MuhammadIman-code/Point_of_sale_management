import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class customerMenue {
    public Scanner scanner = new Scanner(System.in);
    public ArrayList<Customer> customers;
    public customerMenue() {
        customers=new ArrayList<>();
        loadData();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void customer_menue()
    {
        screenCleaner.clearScreen();
        int choice = 0;

        while (choice != 5) {
            screenCleaner.clearScreen();
            System.out.println("customers Menu:");
            System.out.println("1. Add new customer");
            System.out.println("2. Update customer details");
            System.out.println("3. Find customer");
            System.out.println("4. Remove Existing customer");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Add new customer selected");
                    addNewCustomer();
                    break;
                case 2:
                    System.out.println("Update customer details selected");
                    updateCustomerDetails();
                    break;
                case 3:
                    System.out.println("Find customer selected");
                    search_customer();
                    break;
                case 4:
                    System.out.println("Remove Existing customer selected");
                    delete();
                    break;
                case 5:
                    System.out.println("Back to Main Menu");
                    saveData();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }//end of while loop
    }//end of function
    // add Method add the item in array list
    void addNewCustomer()
    {

        int id=0;
        System.out.print("Name: ");
        String na = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Phone: ");
        String phone = scanner.next();
        System.out.println("Enter sale limit");
        double sale_limit = scanner.nextDouble();
        System.out.println("press 1 to confirm item  other wise 0");
        int choise=scanner.nextInt();
        switch(choise)
        {
            case 1:
                id=uniqueId.c_id();
                for (int i=0;i<customers.size();i++)
                {
                    Customer cu = (Customer) customers.get(i);
                    if(id==cu.Customer_id)
                    {
                        id=uniqueId.c_id();
                    }
                }
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String Date = dateFormat.format(new Date());
                Customer cu=new Customer(id,na,email,phone,sale_limit,Date);
                customers.add(cu);
                break;
            case 0:
                break;
            default:
                System.out.println("please Enter correct choice.");
                break;
        }


    }
    public void search_customer()
    {
        System.out.println("Enter Customer name");
        String n=scanner.next();
        for(int i=0;i<customers.size();i++) {
            Customer cu = (Customer) customers.get(i);
            if(n.equals(cu.name))
            {
                cu.display();
            }
            else {
                System.out.println("Customer not found");
                break;
            }
        }
    }//end of function
    public void updateCustomerDetails()
    {
        System.out.println("Enter Name of the Customer");
        String n=scanner.next();
        int choice=0;
        while(choice!=4) {
            for (int i = 0; i < customers.size(); i++) {
                Customer cu = (Customer) customers.get(i);
                if (n.equals(cu.name)) {
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.printf("%-10s%-25s%-15s%-10s%-15s%n", "Customer ID", "Name", "Email", "Phone", "Date");
                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.printf("%-10d%-25s%-15.2f%-10d%-15s%n", cu.Customer_id, cu.name, cu.Email, cu.phone, cu.date);
                    System.out.println("Witch field you want to update");
                    System.out.println("(1) Name  (2) Email (3) Phone (4) Sale limit  (5)exit ");
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Enter updated Customer name");
                            n = scanner.next();
                            cu.setName(n);
                            break;
                        case 2:
                            System.out.println("Enter Updated Customer email");
                            String s = scanner.next();
                            cu.setEmail(s);
                            break;
                        case 3:
                            System.out.println("Enter updated Phone number");
                            String q = scanner.next();
                            cu.setPhone(q);
                            break;
                        case 4:
                            System.out.println("Enter Updated Customer sale_limit");
                            Double sl = scanner.nextDouble();
                            cu.setSale_limit(sl);
                            break;
                            case 5:
                                break;
                        default:
                            System.out.println("invalid choice. Please try again.");
                    }
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String Date = dateFormat.format(new Date());
                    cu.setDate(Date);
                }
                else {
                    System.out.println("Item not found");
                }
            }
        }//while loop  end

    }
    public void all()
    {
        for(int i=0;i<customers.size();i++) {
            Customer cu = (Customer) customers.get(i);

            cu.display();

        }
    }
    public void delete()
    {
        System.out.println("Enter Customer Name");
        String id=scanner.next();
        for(int i=0;i<customers.size();i++) {
            Customer cu = (Customer) customers.get(i);
            if (id.equals(cu.name) ) {
                customers.remove(i);
            }
        }
    }
    public void saveData()
    {
        try {
            FileWriter fr = new FileWriter("Customer_database.txt");
            PrintWriter pw = new PrintWriter(fr);
            String line = null;
            for(int i=0;i<customers.size();i++) {
                Customer cu = (Customer) customers.get(i);
                line = cu.Customer_id+";"+cu.name+";"+cu.Email+";"+cu.phone+";"+cu.sale_limit+";"+cu.date;
                pw.println(line);
            }
            pw.flush();
            pw.close();
            fr.close();
        }
        catch(Exception e)
        {
            System.out.println("There is a problem"+e);
        }

    }//end of save data method
    public void loadData()
    {
        int id;
        double s;
        String i,n,e,p,sa,d;
        String[] token;
        String line = null;
        try {

            FileReader fr = new FileReader("customer_database.txt");
            BufferedReader br  = new BufferedReader(fr);
            line=br.readLine();
            while (line!=null)
            {
                uniqueId.c_id();
                token=line.split(";");
                i=token[0];
                n=token[1];
                e=token[2];
                p=token[3];
                sa=token[4];
                d=token[5];
                id=Integer.parseInt(i);

                s=Double.parseDouble(sa);
                Customer cu= new Customer(id,n,e,p,s,d);
                customers.add(cu);
                line=br.readLine();
            }
            br.close();
            fr.close();
        }
        catch (Exception ex)
        {
            System.out.println("There is a problem "+ex);
        }
    }//end of load method
}
