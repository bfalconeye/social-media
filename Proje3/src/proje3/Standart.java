package proje3;

import java.util.Scanner;
import sun.security.krb5.internal.crypto.EType;

/**
 * Proje3 Kullanicilarin metin tabanli paylasim yapabilmesi, diger kullanicilari
 * arkadas ekleyebilmesi onlara mesaj atabilmesi. 3.odev 30.01.2020
 *
 * @author beyza sahingoz - beyza.sahingoz@stu.fsm.edu.tr
 */
public class Standart extends Kullanici implements IKullaniciIslemler, IPaylasimIslemleri {

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    private Kullanici kullanici;
    private int paylasimSayisi;
    private String paylasimIcerigi;
    private Kullanici[] istekListesi;
    private Kullanici[] arkadasListesi;
    private String[] yorumListesi;
    private String[] paylasimListesi;
    private int begeniSayisi;
    private int yorumSayisi;

    public Standart(String adSoyad, String kullaniciAdi, int ID, int arkadasSayisi) {
        super(adSoyad, kullaniciAdi, ID, arkadasSayisi);
        this.istekListesi = new Kullanici[100];
        this.arkadasListesi = new Kullanici[100];
        this.yorumListesi = new String[100];
        this.paylasimListesi = new String[100];
    }

    //Arkadas listesi üzerinde gezerek ad soyad ve idleri yazdırıyorum.
    @Override
    public void arkadasListele() {
        for (int i = 0; i < getArkadasListesi().length; i++) {
            if (getArkadasListesi()[i] != null) {
                System.out.println("Arkadas ad soyadi: " + getArkadasListesi()[i].getAdSoyad());
                System.out.println("Arkadas ID'si: " + getArkadasListesi()[i].getID());
            }
        }
    }

    //Arkadas listesi üzerinde gezerek kullaniciyi listeden çıkarıyorum.
    @Override
    public void sil(Kullanici kullanici) {
        for (int i = 0; i < getArkadasListesi().length; i++) {
            if (getArkadasListesi()[i] != null && getArkadasListesi()[i].equals(kullanici)) {
                getArkadasListesi()[i] = null;
                System.out.println("Arkadas silindi..");
            }
        }
    }

    //Kullanicinin istek listesi üzerinde gezerek parametre olarak girilen diğer kullanıcıyı listeye ekledim.
    @Override
    public void istekGonder(Kullanici k, Kullanici k1) {
        for (int i = 0; i < k.getIstekListesi().length; i++) {
            if (k.getIstekListesi()[i] == null) {
                getIstekListesi()[i] = k1;
                System.out.println("Istek gonderildi.");
                break;
            }
        }
    }

    //Istek listesi üzerinde gezerek listeden istek yollayan kullanıcının ad soyadı yazdırıyorumç
    @Override
    public void istekListele() {
        for (int i = 0; i < getIstekListesi().length; i++) {
            if (getIstekListesi()[i] != null) {
                System.out.println(i + 1 + ".istek: " + getIstekListesi()[i].getAdSoyad());
            }
        }
    }

    //Arkadas listesi üstünde gezerek kullaniciyi listeye ekliyorum.
    @Override
    public void arkadasEkle(Kullanici kullanici) {
        for (int i = 0; i < getArkadasListesi().length; i++) {
            if (getArkadasListesi()[i] == null) {
                getArkadasListesi()[i] = kullanici;
                System.out.println("Arkadas eklendi..");
                break;
            }
        }
    }

    //Paylasim listesi uzerinde gezerek begeni sayisini arttırıp ardından yazdırıyorum.
    @Override
    public void begen(String paylasim) {
        for (int i = 0; i < getPaylasimListesi().length; i++) {
            if (getPaylasimListesi()[i] != null && getPaylasimListesi()[i].equals(paylasim)) {
                begeniSayisi++;
                System.out.println("Begeni sayisi: " + begeniSayisi);
            }
        }
    }

    //Standart kullanici oldugu icin oncelikle yorum sayisi konusunda uyarip yorum listesine yorumu ekliyorum.
    @Override
    public void yorumYap(String yorum) {
        System.out.println("En fazla 5 yorum yapabilirsiniz.");
        for (int i = 0; i < getYorumListesi().length; i++) {
            if (getYorumListesi()[i] == null) {
                getYorumListesi()[i] = yorum;
                //Standart kullanici oldugu icin 5ten fazla yorum yapmasini engelliyorum.
                if (yorumSayisi > 4) {
                    System.out.println("5'ten fazla yorum yapamazsiniz.");
                    getYorumListesi()[i] = null;
                    yorumSayisi--;
                }
                yorumSayisi++;
                break;
            }
        }
        //Yorum telefon numarasi gibiyse uzunluğunun 11 ve karakterlerin sayı olmasini dikkate alarak uyari veriyorum.
        for (int i = 0; i < getYorumListesi().length; i++) {
            if (getYorumListesi()[i] != null) {
                if (getYorumListesi()[i].length() == 11 && getYorumListesi()[i].charAt(i) < 57 && getYorumListesi()[i].charAt(i) > 48) {
                    System.out.println("Bu yorum telefon numarasi olabilir!");
                }
            }
        }

        System.out.println("Yorum sayisi: " + yorumSayisi);
    }

    //Yorum listesi uzerinde gezerek yorumu siliyorum ve sayiyi azaltiyorum.
    @Override
    public void yorumSil(String yorum) {
        for (int i = 0; i < getYorumListesi().length; i++) {
            if (getYorumListesi()[i] != null) {
                getYorumListesi()[i] = null;
            }
        }
        yorumSayisi--;
    }

    //Kullanicinin yorum listesi uzerinde gezerek kullanici bilgilerini yazdiriyorum.
    @Override
    public void yorumListele(Kullanici k) {
        for (int i = 0; i < k.getYorumListesi().length; i++) {
            if (k.getYorumListesi()[i] != null) {
                System.out.println("Yorumlar: ");
                System.out.println(i + 1 + ".yorum sahibi: " + k.getAdSoyad() + " nin yorumu: " + k.getYorumListesi()[i]);
            }
        }
    }

    //Paylasim listesi uzerinde gezerek paylasimlari yazdiriyorum.
    @Override
    public void goruntule() {
        for (int i = 0; i < getPaylasimListesi().length; i++) {
            if (getPaylasimListesi()[i] != null) {
                System.out.println(i + 1 + ": " + getPaylasimListesi()[i]);
            }
        }
    }

    //Paylasim listesi uzerinde gezerek paylasimi siliyorum.
    @Override
    public void paylasimSil(String paylasim) {
        for (int i = 0; i < getPaylasimListesi().length; i++) {
            if (getPaylasimListesi()[i] != null && getPaylasimListesi()[i].equals(paylasim)) {
                getPaylasimListesi()[i] = null;
            }
        }
    }

    //Paylasim listesi uzerinde gezerek paylasimi ekleyip paylasim sayisini arttiriyorum.
    @Override
    public void paylasimiEkle(String paylasim) {
        for (int i = 0; i < getPaylasimListesi().length; i++) {
            if (getPaylasimListesi()[i] == null) {
                getPaylasimListesi()[i] = paylasim;
                setPaylasimSayisi(getPaylasimSayisi() + 1);
                break;
            }
        }
    }

    //Paylasim listesi uzerinde gezerek paylasimi begeniyi yorumu listeliyorum.
    @Override
    public void paylasimBilgileri(Kullanici kullanici) {
        for (int i = 0; i < getPaylasimListesi().length; i++) {
            if (getPaylasimListesi()[i] != null) {
                System.out.println("Paylasim: " + getPaylasimListesi()[i]);
                System.out.println("Paylasimin begeni sayisi: " + begeniSayisi);
                System.out.println("Paylasimin yorum sayisi: " + yorumSayisi);
                for (int j = 0; j < getYorumListesi().length; j++) {
                    if (getYorumListesi()[j] != null) {
                        System.out.println("Yapilan yorum: " + getYorumListesi()[j]);
                    }
                }
            }
        }
    }

    public int getPaylasimSayisi() {
        return paylasimSayisi;
    }

    public void setPaylasimSayisi(int paylasimSayisi) {
        this.paylasimSayisi = paylasimSayisi;
    }

    public String getPaylasimIcerigi() {
        return paylasimIcerigi;
    }

    public void setPaylasimIcerigi(String paylasimIcerigi) {
        this.paylasimIcerigi = paylasimIcerigi;
    }

    //Stringi integera ceviriyorum
    public static int IntegeraDonustur(String temp) {
        int i = 0;
        int sayi = 0;
        boolean negatifMi = false;
        int uzunluk = temp.length();
        if (temp.charAt(0) == '-') {
            negatifMi = true;
            i = 1;
        }
        while (i < uzunluk) {
            sayi = sayi * 10;
            sayi = sayi + (temp.charAt(i++) - '0');
        }
        if (negatifMi) {
            sayi = -sayi;
        }
        return sayi;
    }

    public Kullanici[] getIstekListesi() {
        return istekListesi;
    }

    public void setIstekListesi(Kullanici[] istekListesi) {
        this.setIstekListesi(istekListesi);
    }

    public Kullanici[] getArkadasListesi() {
        return arkadasListesi;
    }

    public void setArkadasListesi(Kullanici[] arkadasListesi) {
        this.setArkadasListesi(arkadasListesi);
    }

    public String[] getYorumListesi() {
        return yorumListesi;
    }

    public void setYorumListesi(String[] yorumListesi) {
        this.setYorumListesi(yorumListesi);
    }

    public String[] getPaylasimListesi() {
        return paylasimListesi;
    }

    public void setPaylasimListesi(String[] paylasimListesi) {
        this.setPaylasimListesi(paylasimListesi);
    }

    public int getBegeniSayisi() {
        return begeniSayisi;
    }

    public void setBegeniSayisi(int begeniSayisi) {
        this.begeniSayisi = begeniSayisi;
    }
}
