public class Item {

     int Item_id = 0;
     String discription = null;
     double price  = 0.0;
     int quantity = 0;
     String date = null;
    public Item(int item_id, String discription, double price, int quantity,String date) {
        Item_id = item_id;
        this.discription = discription;
        this.price = price;
        this.quantity = quantity;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setItem_id(int item_id) {
        Item_id = item_id;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItem_id() {
        return Item_id;
    }

    public String getDiscription() {
        return discription;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    public void display()
    {
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-25s%-15s%-10s%-15s%n", "Item ID", "Description", "Price", "Quantity", "Date");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10d%-25s%-15.2f%-10d%-15s%n",Item_id, discription, price, quantity,date);

    }
    public void SetQuantity(int qua)
    {
        quantity -= qua;
    }

}//end of class
