public class SingleSaleInfo {
    int id= 0;
    double pice= 0;
    String discription = null;
    int quantity= 0;

    public SingleSaleInfo(int id, double pice, String discription, int quantity) {

        this.id = id;
        this.pice = pice;
        this.discription = discription;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPice() {
        return pice;
    }

    public void setPice(double pice) {
        this.pice = pice;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
