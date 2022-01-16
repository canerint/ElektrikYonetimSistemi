package elektrikprojesi;


import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminIslemler {
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private Connection con = null;
    
    
    public void kurumsalEkle( String odenecekTutar){
        
        String sorgu = "Insert into kfatura (kurumsalFatura) VALUES(?)";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            

            preparedStatement.setString(1, odenecekTutar);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminIslemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void bireyselEkle( String odenecekTutar){
        
        String sorgu = "Insert into fatura (bireyselFatura) VALUES(?)";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            

            preparedStatement.setString(1, odenecekTutar);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminIslemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void bireyselSil(int id){
        String sorgu = "Delete From fatura where id =?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setInt(1, id);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminIslemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void kurumsalSil(int id){
        String sorgu = "Delete From kfatura where id =?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setInt(1, id);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdminIslemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void kurumsalGuncelle(int id,String yeni_odenecekTutar){
        String sorgu = "Update kfatura set kurumsalFatura =? where id =?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            

            preparedStatement.setString(1, yeni_odenecekTutar);
            preparedStatement.setInt(2, id);
            
            preparedStatement.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminIslemler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void bireyselGuncelle(int id,String yeni_odenecekTutar){
        String sorgu = "Update fatura set bireyselFatura =? where id =?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);

            preparedStatement.setString(1, yeni_odenecekTutar);
            preparedStatement.setInt(2, id);
            
            preparedStatement.executeUpdate();
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminIslemler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public ArrayList<BireyselMusteri> bireyselMusterileriGetir(){
        ArrayList<BireyselMusteri> cikti1 = new ArrayList<>();
        try {
            statement = con.createStatement();
            String sorgu = "Select * From fatura";
            
            ResultSet rs = statement.executeQuery(sorgu);
             
            
            while (rs.next()){
                
                int id = rs.getInt("id");

                String odenecekTutar = rs.getString("bireyselFatura");
                
                
                cikti1.add(new BireyselMusteri(id,odenecekTutar));
            }
            return cikti1;
           
        } catch (SQLException ex) {
            Logger.getLogger(AdminIslemler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        public ArrayList<KurumsalMusteri> kurumsalMusterileriGetir(){
        ArrayList<KurumsalMusteri> cikti2 = new ArrayList<>();
        try {
            statement = con.createStatement();
            String sorgu = "Select * From kfatura";
            
            ResultSet rs = statement.executeQuery(sorgu);
             
            
            while (rs.next()){
                
                int id = rs.getInt("id");

                
                String odenecekTutar = rs.getString("kurumsalFatura");
                
                
                cikti2.add(new KurumsalMusteri(id,odenecekTutar));
            }
            return cikti2;
           
        } catch (SQLException ex) {
            Logger.getLogger(KurumsaLIslemler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
        
    }
    
    public boolean girisYap(String kullaniciAdi, String parola){
        
        String sorgu = "Select * From adminler where username = ? and password = ?";
        
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, kullaniciAdi);
            preparedStatement.setString(2, parola);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            return rs.next();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminIslemler.class.getName()).log(Level.SEVERE, null, ex);
        
            return false;
        }
        
        
        
    }


    public AdminIslemler(){


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
        AdminIslemler adminIslemler = new AdminIslemler();
        
        
    }

    
}
