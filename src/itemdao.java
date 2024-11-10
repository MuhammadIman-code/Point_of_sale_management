import java.sql.*;

public class itemdao  extends dataBaseConnection {
    public itemdao() {
        con = GetConnection();

    }

    public void addItem(Item i) {
        PreparedStatement ps = null;
        try {
            String query = "insert into item(description,price,quantity,date) values(?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, i.discription);
            String p = (i.price + "");
            ps.setString(2, p);
            String q1 = (i.quantity + "");
            ps.setString(3, q1);
            ps.setString(4, i.date);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error in addItem");
        } finally {
            // Close the PreparedStatement
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Error closing PreparedStatement: " + e.getMessage());
                }
            }
        }

    }

    public ResultSet search_record(String name) {
       PreparedStatement ps = null;
        ResultSet set=null;
        try {
            String query = "select * from item where description = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            set = ps.executeQuery();

        }
        catch (Exception e){
            System.out.println("Error in search_record"+e.getMessage());
        }
        return set;
    }
}
