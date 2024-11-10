public class Customer {
    int Customer_id = 0;
    String name = null;
    String Email  = null;
    String phone= null;
    Double sale_limit = 0.0;
    String date=null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Customer(int customer_id, String name, String email, String phone, Double sale_limit,String date) {
        Customer_id = customer_id;
        this.name = name;
        Email = email;
        this.phone = phone;
        this.sale_limit = sale_limit;
        this.date = date;
    }

    public int getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(int customer_id) {
        Customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getSale_limit() {
        return sale_limit;
    }

    public void setSale_limit(Double sale_limit) {
        this.sale_limit = sale_limit;
    }

    public void display()
    {
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-25s%-15s%-10s%-15s%-15s%n", "Customer ID", "Name", "Email", "Phone", "Sales Limit", "Date");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10d%-25s%-15s%-10s%-10.2f%-15s%n", Customer_id, name, Email, phone, sale_limit, date);

    }

}
