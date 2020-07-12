package disciotomasyonu.DosyaIslemleri;

import java.io.File;
import java.io.FileInputStream;

public class DosyaOkuma {

    String directory = System.getProperty("user.dir");
    String fileName = "deneme.txt";
    String absolutePath = directory + File.separator + fileName;

    public String TumSatirlariGetir() {
        String str = "";
        try {
            File file = new File(absolutePath);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();

            str = new String(data, "UTF-8");
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        return str;
    }
}
