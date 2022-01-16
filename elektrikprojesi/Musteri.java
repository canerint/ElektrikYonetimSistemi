
package elektrikprojesi;


public class Musteri {
    
    private int id;
    private String odenecekTutar;

   
    
    private String tarih;

    public Musteri(int id, String odenecekTutar) {
        this.id = id;
        this.odenecekTutar = odenecekTutar;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOdenecekTutar() {
        return odenecekTutar;
    }

    public void setOdenecekTutar(String odenecekTutar) {
        this.odenecekTutar = odenecekTutar;
    }
    
    
}
