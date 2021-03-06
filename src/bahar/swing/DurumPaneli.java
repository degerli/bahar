package bahar.swing;

import bahar.bilgi.DersBilgisi;
import bahar.bilgi.DersOturumu;
import bahar.bilgi.OturumDinleyici;
import net.miginfocom.swing.MigLayout;
import org.bushe.swing.event.annotation.EventSubscriber;

import javax.swing.*;

public class DurumPaneli extends JPanel implements OturumDinleyici {

    JLabel sureLbl;
    JLabel yazilanSayisiLbl;
    JLabel hataSayisiLbl;
    JLabel hizLbl;

    public DurumPaneli(DersBilgisi dersBilgisi) {

        // this.setBackground(Color.white);
        this.setLayout(new MigLayout("wrap 2"));

        // ad
        this.add(ComponentFactory.fixedLengthLabel(dersBilgisi.kullaniciAdi, 20), "span 2");

        // sureLbl
        this.add(ComponentFactory.fixedLengthLabel("Sure ", 16));
        sureLbl = ComponentFactory.fixedLengthLabel("00:00", 16);

        this.add(sureLbl);

        // toplam
        this.add(ComponentFactory.fixedLengthLabel("Kelime:", 16));
        this.add(ComponentFactory.fixedLengthLabel(String.valueOf(dersBilgisi.kelimeSayisi()), 16));


        // yazilan
        this.add(ComponentFactory.fixedLengthLabel("Yazilan harf:", 16));
        yazilanSayisiLbl = ComponentFactory.fixedLengthLabel("0", 16);
        this.add(yazilanSayisiLbl);

        // hata sayisi
        this.add(ComponentFactory.fixedLengthLabel("Hata:", 16));
        hataSayisiLbl = ComponentFactory.fixedLengthLabel("0", 16);
        this.add(hataSayisiLbl);

        // hizLbl
        this.add(ComponentFactory.fixedLengthLabel("Hiz (harf/dk):", 16));
        hizLbl = ComponentFactory.fixedLengthLabel("--", 16);
        this.add(hizLbl);
    }
    

    @EventSubscriber(eventClass = OturumEvent.class)
    public void onEvent(DersEvent event) {
    }



    public DurumPaneli(DersOturumu oturum) {
        this(oturum.dersBilgisi);
        this.sureLbl.setText(sureFormatla(oturum.sure()));
        this.yazilanSayisiLbl.setText(String.valueOf(oturum.yazilanHarfSayisi));
        this.hataSayisiLbl.setText(String.valueOf(oturum.gorunenHataSayisi));
        this.hizLbl.setText(oturum.harfHizHasapla());
    }

    public void saniyeArtti(int sn, String hiz) {
        sureLbl.setText(sureFormatla(sn));
        this.hizLbl.setText(hiz);
        validate();
    }

    public void harfYazildi(int harfSayisi, int hataSayisi, String hiz) {
        this.hataSayisiLbl.setText(String.valueOf(hataSayisi));
        this.yazilanSayisiLbl.setText(String.valueOf(harfSayisi));
        this.hizLbl.setText(hiz);
        validate();
    }

    private String sureFormatla(int sn) {
        StringBuilder sb = new StringBuilder();
        int saniyeler = sn % 60;
        int dakikalar = sn / 60;

        if (dakikalar < 10) {
            sb.append("0").append(dakikalar);
        } else sb.append(dakikalar);
        sb.append(":");
        if (saniyeler < 10) {
            sb.append("0").append(saniyeler);
        } else sb.append(saniyeler);
        return sb.toString();
    }
}
