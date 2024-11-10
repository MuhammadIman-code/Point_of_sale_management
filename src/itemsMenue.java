import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class itemsMenue {
    public Scanner scanner = new Scanner(System.in);
    itemdao in;
    public ArrayList<Item> items;
    public itemsMenue() {
        items=new ArrayList<>();
        //loadData();
    }
    public void menue()
    {
        screenCleaner.clearScreen();
        int choice = 0;

        while (choice != 5) {
            screenCleaner.clearScreen();
            System.out.println("Items Menu:");
            System.out.println("1. Add new Item");
            System.out.println("2. Update Item details");
            System.out.println("3. Find Items");
            System.out.println("4. Remove Existing Item");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Add new Item selected");
                    addNewItem();
                    break;
                case 2:
                    System.out.println("Update Item details selected");
                    updateItemDetails();
                    break;
                case 3:
                    System.out.println("Find Items selected");
                    search_item();
                    break;
                case 4:
                    System.out.println("Remove Existing Item selected");
                    delete();
                    break;
                case 5:
                    System.out.println("Back to Main Menu");
                    //saveData();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }//end of while loop
    }//end of function
    // add Method add the item in array list
    void addNewItem()
    {
         int id=0;int quan=0;
        String des=null;
        double pri=0.0;
        System.out.print("Description: ");
        String description = scanner.next();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        System.out.println("press 1 to confirm item  other wise 0");
        int choise=scanner.nextInt();
        switch(choise)
        {
            case 1:

                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String Date = dateFormat.format(new Date());
                Item i=new Item(id,description,price,quantity,Date);
                in=new itemdao();
                in.addItem(i);
                break;
            case 0:
                break;
            default:
                System.out.println("please Enter correct choice.");
                break;
        }


    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void search_item()
    {
        System.out.println("Enter item name");
        String n=scanner.next();
        in=new itemdao();
        ResultSet set =in.search_record(n);
        try {
            while(set.next())
            {
                int id =set.getInt(1);
                String nam=set.getString(2);
                double price=set.getDouble(3);
                int qty=set.getInt(4);
                String date=set.getString(5);
                System.out.println("----------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10s%-25s%-15s%-10s%-15s%n", "Item ID", "Description", "Price", "Quantity", "Date");
                System.out.println("----------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10d%-25s%-15.2f%-10d%-15s%n",id, nam, price, qty,date);
            }
            System.in.read(); // Wait for any input from the user
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//end of function
    public void updateItemDetails()
    {
        System.out.println("Enter Name of the Item");
        String n=scanner.next();
        int choice=0;
        boolean found = false;
     while(choice!=4) {
         for (int i = 0; i < items.size(); i++) {
             Item it = (Item) items.get(i);
             if (n.equals(it.discription)) {
                 found = true;
                 System.out.println("----------------------------------------------------------------------------------------------------------");
                 System.out.printf("%-10s%-25s%-15s%-10s%-15s%n", "Item ID", "Description", "Price", "Quantity", "Date");
                 System.out.println("----------------------------------------------------------------------------------------------------------");
                 System.out.printf("%-10d%-25s%-15.2f%-10d%-15s%n", it.Item_id, it.discription, it.price, it.quantity, it.date);
                 System.out.println("Witch field you want to update");
                 System.out.println("(1) Description  (2) Price (3) Quantity (4) exit ");
                 choice = scanner.nextInt();
                 switch (choice) {
                     case 1:
                         System.out.println("Enter updated item name");
                         n = scanner.next();
                         it.setDiscription(n);
                         break;
                     case 2:
                         System.out.println("Enter updated item price");
                         int p = scanner.nextInt();
                         it.setPrice(p);
                         break;
                     case 3:
                         System.out.println("Enter updated item quantity");
                         int q = scanner.nextInt();
                         it.setQuantity(q);
                         break;
                     case 4:
                         break;
                     default:
                         System.out.println("invalid choice. Please try again.");
                 }
             }

         }
         if (!found) {
             System.out.println("Item not found");
         }
     }//while loop  end

    }
    public void all()
    {
        for(int i=0;i<items.size();i++) {
            Item it = (Item) items.get(i);

                it.display();

        }
    }
    public void delete()
    {
        System.out.println("Enter item description");
        String id=scanner.next();
        for(int i=0;i<items.size();i++) {
            Item it = (Item) items.get(i);
            if (id.equals(it.discription) ) {
                items.remove(i);
            }
        }
    }
    /*public void saveData()
    {
        try {
            FileWriter fr = new FileWriter("Item_database.txt");
            PrintWriter pw = new PrintWriter(fr);
            String line = null;
            for(int i=0;i<items.size();i++) {
            Item it = (Item) items.get(i);
            line = it.Item_id+";"+it.discription+";"+it.quantity+";"+it.price+";"+it.date;
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
        int id ,qua;
        double price;
        String i,q,name,date,p;
        String[] token;
       String line = null;
        try {

            FileReader fr = new FileReader("Item_database.txt");
            BufferedReader br  = new BufferedReader(fr);
            line=br.readLine();
            while (line!=null)
            {
                uniqueId.itemId();
                token=line.split(";");
                i=token[0];
                name=token[1];
                q=token[2];
                p=token[3];
                date=token[4];
               id=Integer.parseInt(i);
               qua = Integer.parseInt(q);
               price=Double.parseDouble(p);
               Item it = new Item(id,name,price,qua,date);
               items.add(it);
               line=br.readLine();
            }
            br.close();
            fr.close();
        }
        catch (Exception e)
        {
            System.out.println("There is a problem "+e);
        }
    }//end of load method*/
    }

