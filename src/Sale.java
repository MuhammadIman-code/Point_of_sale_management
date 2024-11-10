import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.text.SimpleDateFormat;

public class Sale {
    String status ;
    int customer_id = 0;
    double totalPrice = 0.0;
    int sale_id = 0;
    String name = null;
    HashMap<Integer, Integer> sales;
    String date = null;
    Scanner cin = new Scanner(System.in);
    ArrayList<Item> item;
    itemsMenue it = new itemsMenue();
    customerMenue cu =new customerMenue();
    ArrayList<Customer> c;
    ArrayList<SingleSaleInfo> singleSale;
    ArrayList<Sale> Sales;

    public Sale() {

        sales = new HashMap<>();
        item = it.getItems();
        c = cu.getCustomers();
        //it.loadData();
        singleSale = new ArrayList<>();
        Sales = new ArrayList<>();
        load_sale();

    }

   /* public Sale( double totalPrice,int sale_id, String name,String date) {
        this.totalPrice = totalPrice;
        this.sale_id = sale_id;
        this.name = name;
        this.date = date;
    }*/

    public Sale(int sale_id,int customer_id,String date,double totalPrice,String status) {
        this.sale_id = sale_id;
        this.customer_id=customer_id;
        this.date=date;
        this.totalPrice = totalPrice;
        this.status=status;
    }


    public void GetCustomer_name()
{
    for(int i=0;i<c.size();i++)
    {
       Customer customer = (Customer) c.get(i);
       if(customer.Customer_id==customer_id)
       {
           name=customer.getName();
       }
    }
}
    public void MadeSale() {
        sales.clear();
        customer_id = 0;
        totalPrice = 0.0;
        sale_id = 0;
        date = null;
        name = null;
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date = dateFormat.format(new Date());
        System.out.println("Sales date:" + date);
        System.out.println("Enter  Customer Id:");
        customer_id=cin.nextInt();
        boolean customerFound = false;
        for(int i = 0; i < c.size(); i++) {
            Customer cu = (Customer) c.get(i);
            if(customer_id == cu.Customer_id) {
                customerFound = true;
                break;
            }
        }

        if (!customerFound) {
            System.out.println("Customer not found!!!!!");
            return;
        }
        int choice = 0;
        while (choice != 2) {
            // Print menu options
            System.out.println("Press 1 to Enter New Item");
            System.out.println("Press 2 to End Sale");
            System.out.println("Press 3 to Remove an existing Item from the current sale");
            System.out.println("Press 4 to Cancel Sale");
            System.out.println("Choose from option 1 – 4:");

            // Read user choice
            choice = cin.nextInt();

            // Process user choice
            switch (choice) {
                case 1:
                    System.out.println("You chose option 1: Enter New Item");
                    ItemSale();
                    break;
                case 2:
                    System.out.println("You chose option 2: End Sale");
                    EndSale();

                    break;
                case 3:
                    System.out.println("You chose option 3: Remove an existing Item from the current sale");
                    RemoveInSale();
                    break;
                case 4:
                    System.out.println("You chose option 4: Cancel Sale");
                    // Add code to handle option 4
                    break;
                default:
                    System.out.println("Invalid choice. Please choose from options 1 - 4.");
                    break;
            }
        }//end of while loop
    }

    public void ItemSale() {
        sale_id=uniqueId.SalesId();
        int choice = 1;
        int qua=0;
        while (choice != 0) {
            System.out.println("Enter Item id:");
            int i_id = cin.nextInt();

            boolean found = false;
            for (int i = 0; i < item.size(); i++) {
                Item it = (Item) item.get(i);
                if (it.getItem_id() == i_id) {
                    found=true;
                    System.out.println("Description: " + it.getDiscription());
                    System.out.println("Price: " + it.getPrice());
                    System.out.println("Enter Quantity:");
                    qua = cin.nextInt();
                    if (qua > it.getQuantity()) {
                        System.out.println("Not enough quantities. There are only " + it.getQuantity() + " items in stock. Please enter a quantity within range.");
                        System.out.println("Do you want to continue to enter quantity within range (1) or discard (0)?");
                        int choice1 = cin.nextInt();
                        if (choice1 == 1) {
                            System.out.println("Enter Quantity within range:");
                            qua = cin.nextInt();
                            break;
                        } else if (choice1 == 0) {
                            MadeSale();
                        } else {
                            System.out.println("Invalid choice. Please choose from options 1 and 0.");
                        }
                    }
                break;
                }

            }//end of for loop

            if (!found) {
                System.out.println("Item not found!!!");
                MadeSale(); // Move MadeSale() outside the loop to handle the scenario after checking all items
            }

            System.out.println("Confirm Sale press 1 other wise 0");
            choice = cin.nextInt();
            if (choice == 1) {
                if (sales.containsKey(i_id)) {
                    System.out.println("Item is already exist");
                    System.out.println("You want to update! press 1 or not then press 0");
                    int c = cin.nextInt();
                    switch (c) {
                        case 1:
                            sales.put(i_id, qua);
                            break;
                        case 0:
                            break;

                    }
                } else {

                    sales.put(i_id, qua);
                }
            }//end of swith statement
            System.out.println("you want another sale press 1 or not then press 0");
            choice = cin.nextInt();
        }

    }//end of function


    public void RemoveInSale() {

        int choice = 1;
        while (choice == 1) {
            System.out.println("Enter Item Id you want to remove");
            int id = cin.nextInt();
            if (sales.containsKey(id)) {
                sales.remove(id);
                break;
            } else {
                System.out.println("Enter correct choice ");
            }
        }
    }//End of function

    public void EndSale() {
        totalPrice=0;
        for (Map.Entry<Integer, Integer> entry : sales.entrySet()) {
            GetCustomer_name();


           int key = entry.getKey();
            int value = entry.getValue();
            for (int i = 0; i < item.size(); i++) {
                Item it =  item.get(i);
                if(it.Item_id==key)
                {
                    String dis = it.getDiscription();
                    double pri = it.getPrice();
                    totalPrice = totalPrice + (pri*value);
                    SingleSaleInfo ss = new SingleSaleInfo(key,pri,dis,value);
                    singleSale.add(ss);

                    it.SetQuantity(value);
                    break;
                }
            }
        }//end of for loop
        System.out.println("Sales ID: " + sale_id + "\t\t\t\tCustomer ID: " + customer_id);
        System.out.println("Sales Date: " + date + "\t\t\tName: " + name);
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Item Id\t\tDescription\t\t\t\t\tQuantity\t\tAmount");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (SingleSaleInfo sales : singleSale) {
            System.out.printf("%-8d\t%-30s\t%-8d\t%-8.2f\n", sales.id, sales.discription, sales.quantity, sales.pice);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\tTotal Sales: \t\tRs."+totalPrice);
        status="UnPaid";
        Sale sa = new Sale(sale_id,customer_id,date,totalPrice,status);
        Sales.add(sa);
    }

    public void save_sales()
    {
        try{
            String line = null;
            FileWriter fr = new FileWriter("sales.txt");
            PrintWriter pw = new PrintWriter(fr);
            for(int i=0;i<Sales.size();i++) {
                Sale s = (Sale) Sales.get(i);
                line = s.sale_id + ";" + s.customer_id + ";" + s.date+";"+s.totalPrice+";"+s.status;
                pw.println(line);
            }
            pw.flush();
            pw.close();
            fr.close();
        }
        catch (Exception e)
        {
            System.out.println("Error writing file");
        }
    }//end save_sales function



    public void load_sale()
    {
        int salesId ,Customer_id;
        String Date;
        String si,ci,date,stat,tot;
        String[] token;
        String line = null;
        try {

            FileReader fr = new FileReader("sales.txt");
            BufferedReader br  = new BufferedReader(fr);
            line=br.readLine();
            while (line!=null)
            {
                sale_id=uniqueId.SalesId();
                token=line.split(";");
                si=token[0];
                ci=token[1];
                date=token[2];
                tot = token[3];
                status=token[4];
                totalPrice=Double.parseDouble(tot);
                salesId=Integer.parseInt(si);
                Customer_id = Integer.parseInt(ci);

                Sale sa = new Sale(sale_id,Customer_id,date,totalPrice,status);
                Sales.add(sa);
                line=br.readLine();
            }
            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            System.out.println("There is a problem "+e);
        }
    }

}

