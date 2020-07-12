package disciotomasyonu.Tablolar;

public class Hasta {

    String Adi;
    String Soyadi;
    String TC;
    String Telefon;
    String DogumTarihi;

    public Hasta(String Adi, String Soyadi, String TC, String Telefon, String DogumTarihi) {
        this.Adi = Adi;
        this.Soyadi = Soyadi;
        this.TC = TC;
        this.Telefon = Telefon;
        this.DogumTarihi = DogumTarihi;
    }

    public String getAdi() {
        return Adi;
    }

    public void setAdi(String Adi) {
        this.Adi = Adi;
    }

    public String getSoyadi() {
        return Soyadi;
    }

    public void setSoyadi(String Soyadi) {
        this.Soyadi = Soyadi;
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    public String getDogumTarihi() {
        return DogumTarihi;
    }

    public void setDogumTarihi(String DogumTarihi) {
        this.DogumTarihi = DogumTarihi;
    }
    
    
    
}
