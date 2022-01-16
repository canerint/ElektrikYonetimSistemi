package elektrikprojesi;


import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KurumsaLIslemler {
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private Connection con = null;
    
    
    
    public void faturaEkle(String toplamFatura){
        String sorgu = "Insert ignore Into kfatura (kurumsalFatura) VALUES(?)";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, toplamFatura);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(BireyseLIslemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void faturaOde(int id, double yeni_odenecekTutar){
        String sorgu = "Update kfatura set kurumsalFatura =? where id =?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setDouble(1, yeni_odenecekTutar);
            preparedStatement.setInt(2, id);
            
            preparedStatement.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(BireyseLIslemler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<KurumsalMusteri> kurumsalMusterileriGetir(){
        ArrayList<KurumsalMusteri> cikti = new ArrayList<>();
        try {
            statement = con.createStatement();
            String sorgu = "Select * From kfatura";
            
            ResultSet rs = statement.executeQuery(sorgu);
             
            
            while (rs.next()){
                
                int id = rs.getInt("id");

                String odenecekTutar = rs.getString("kurumsalFatura");
                
                
                cikti.add(new KurumsalMusteri(id,odenecekTutar));
            }
            return cikti;
           
        } catch (SQLException ex) {
            Logger.getLogger(KurumsaLIslemler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
        
    }
    
    public boolean girisYap(String kullaniciAdi, String parola){
        
        String sorgu = "Select * From kurumsalmusterigirisi where username = ? and password = ?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, kullaniciAdi);
            preparedStatement.setString(2, parola);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            return rs.next();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(KurumsaLIslemler.class.getName()).log(Level.SEVERE, null, ex);
        
            return false;
        }
        
        
        
    }


    public KurumsaLIslemler(){


        String url = "jdbc:mysql://" + Database.host + ":" + Database.port + "/" + Database.db_ismi + "?useUnicode=true&characterEncoding=utf8";

        try{
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver bulunamadı...");
        }


        try {
            con = DriverManager.getConnection(url,Database.kullanici_adi,Database.parola);
            System.out.println("Bağlantı başarılı...");
        } catch (SQLException e) {
            System.out.println("Bağlantı başarısız...");



        }
    }


    public static void main(String[] args) {
        KurumsaLIslemler kurumsaLIslemler = new KurumsaLIslemler();
    }
}
