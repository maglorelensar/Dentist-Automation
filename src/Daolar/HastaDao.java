package Daolar;

import disciotomasyonu.DosyaIslemleri.DosyaOkuma;
import disciotomasyonu.DosyaIslemleri.DosyaYazma;
import disciotomasyonu.Tablolar.Hasta;
import java.util.ArrayList;

public class HastaDao {

    DosyaYazma dosyaYazma;
    DosyaOkuma dosyaOkuma;

    public HastaDao() {
        dosyaYazma = new DosyaYazma();
        dosyaOkuma = new DosyaOkuma();
    }

    public boolean Kaydet(Hasta hasta) {
        dosyaYazma.SatirlaraEkle(hasta.getAdi() + "@"
                + hasta.getSoyadi() + "@"
                + hasta.getTC() + "@"
                + hasta.getTelefon() + "@"
                + hasta.getDogumTarihi() + "#"
        );

        return true;
    }

    public ArrayList<Hasta> ListeGetir() {
        ArrayList<Hasta> hastaListesi = new ArrayList<>();

        String hastaDosyaStr = dosyaOkuma.TumSatirlariGetir();
        if (hastaDosyaStr.length() == 1 || hastaDosyaStr.length() == 0) {
            return hastaListesi;
        }
        String[] hastalarStr = hastaDosyaStr.split("#");

        for (String hastaDStr : hastalarStr) {
            String[] hastaStr = hastaDStr.split("@");
            Hasta hasta = new Hasta(hastaStr[0], hastaStr[1], hastaStr[2], hastaStr[3], hastaStr[4]);
            hastaListesi.add(hasta);
        }

        return hastaListesi;

    }

    public void Sil(Hasta hasta) {

        ArrayList<Hasta> hastaListesi = this.ListeGetir();

        hastaListesi.removeIf(x -> (x.getTC() == null ? hasta.getTC() != null : x.getTC().equals(hasta.getTC())));

        dosyaYazma.SatirlariSil();

        for (Hasta fhasta : hastaListesi) {
            this.Kaydet(fhasta);
        }

    }

}
