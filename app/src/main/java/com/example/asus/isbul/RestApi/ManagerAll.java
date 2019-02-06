package com.example.asus.isbul.RestApi;


import com.example.asus.isbul.Models.BasvuruListModel;
import com.example.asus.isbul.Models.BasvuruModel;
import com.example.asus.isbul.Models.BasvuruSonucModel;
import com.example.asus.isbul.Models.DeneyimEkleModel;
import com.example.asus.isbul.Models.DeneyimModel;
import com.example.asus.isbul.Models.DetayModel;
import com.example.asus.isbul.Models.EgitimEkleModel;
import com.example.asus.isbul.Models.EgitimModel;
import com.example.asus.isbul.Models.GirisYapModel;
import com.example.asus.isbul.Models.IlanDetayModel;
import com.example.asus.isbul.Models.IlanDetayNitelikModel;
import com.example.asus.isbul.Models.IlanModel;
import com.example.asus.isbul.Models.IlanPaylasimiModel;
import com.example.asus.isbul.Models.KayitOlModel;
import com.example.asus.isbul.Models.KullaniciBilgiModel;
import com.example.asus.isbul.Models.SilModel;
import com.example.asus.isbul.Models.YetenekEkleModel;
import com.example.asus.isbul.Models.YetenekModel;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {
        return ourInstance;
    }

    public Call<KayitOlModel> kayitOl(String mail, String kadi, String parola) {
        Call<KayitOlModel> x = getRestApi().registerUser(mail, kadi, parola);
        return x;
    }

    public Call<GirisYapModel> girisYap(String mail, String parola) {
        Call<GirisYapModel> x = getRestApi().girisYap(mail, parola);
        return x;
    }

    public Call<YetenekEkleModel> yetenekEkle(String id, String yetenek) {
        Call<YetenekEkleModel> x = getRestApi().yetenekekle(id, yetenek);
        return x;
    }

    public Call<DeneyimEkleModel> deneyimEkle(String id, String sirket, String title, String yil) {
        Call<DeneyimEkleModel> x = getRestApi().deneyimEkle(id, sirket, title, yil);
        return x;
    }

    public Call<List<DeneyimModel>> deneyimGetir(String id) {
        Call<List<DeneyimModel>> x = getRestApi().deneyimGetir(id);
        return x;
    }

    public Call<List<EgitimModel>> egitimGetir(String id) {
        Call<List<EgitimModel>> x = getRestApi().egitimGetir(id);
        return x;
    }

    public Call<List<YetenekModel>> yetenekGetir(String id) {
        Call<List<YetenekModel>> x = getRestApi().yetenekGetir(id);
        return x;
    }

    public Call<List<IlanModel>> ilanListele(String id) {
        Call<List<IlanModel>> x = getRestApi().ilanListele(id);
        return x;
    }

    public Call<List<BasvuruListModel>> basvuruListele(String id) {
        Call<List<BasvuruListModel>> x = getRestApi().basvuruListele(id);
        return x;
    }

    public Call<List<IlanModel>> ilanlarim(String id) {
        Call<List<IlanModel>> x = getRestApi().ilanlarim(id);
        return x;
    }

    public Call<List<IlanDetayModel>> ilanDetay(String id) {
        Call<List<IlanDetayModel>> x = getRestApi().ilanDetay(id);
        return x;
    }

    public Call<List<IlanDetayNitelikModel>> ilanDetayNitelik(String id) {
        Call<List<IlanDetayNitelikModel>> x = getRestApi().ilanDetayNitelik(id);
        return x;
    }

    public Call<SilModel> deneyimSil(String id) {
        Call<SilModel> x = getRestApi().deneyimSil(id);
        return x;
    }

    public Call<SilModel> egitimSil(String id) {
        Call<SilModel> x = getRestApi().egitimSil(id);
        return x;
    }

    public Call<SilModel> yetenekSil(String id) {
        Call<SilModel> x = getRestApi().yetenekSil(id);
        return x;
    }

    public Call<List<KullaniciBilgiModel>> bilgiGetir(String id) {
        Call<List<KullaniciBilgiModel>> x = getRestApi().kullaniciBilgiGetir(id);
        return x;
    }

    public Call<EgitimEkleModel> egitimEkle(String universite, String bolum, String baslangic, String bitis, String id) {
        Call<EgitimEkleModel> x = getRestApi().egitimEkle(universite, bolum, baslangic, bitis, id);
        return x;
    }

    public Call<BasvuruModel> basvuruYap(String userid, String sahipid, String ilanid) {
        Call<BasvuruModel> x = getRestApi().basvuruYap(userid, sahipid, ilanid);
        return x;
    }

    public Call<BasvuruSonucModel> basvuruOnay(String kadi, String mailadres, String basvuruid, String baslik) {
        Call<BasvuruSonucModel> x = getRestApi().basvuruOnay(kadi, mailadres, basvuruid, baslik);
        return x;
    }

    public Call<BasvuruSonucModel> basvuruRed(String kadi, String mailadres, String basvuruid, String baslik) {
        Call<BasvuruSonucModel> x = getRestApi().basvuruRed(kadi, mailadres, basvuruid, baslik);
        return x;
    }

    public Call<IlanPaylasimiModel> ilanPaylas(String kid, String baslik, String aciklama, String adres) {
        Call<IlanPaylasimiModel> x = getRestApi().ilanPaylas(kid, baslik, aciklama, adres);
        return x;
    }

    public Call<DetayModel> kriterEkle(String ilanid, String text, String tecrube, String egitimbilgisi) {
        Call<DetayModel> x = getRestApi().kriterEkle(ilanid, text, tecrube, egitimbilgisi);
        return x;
    }

    public Call<DetayModel> nitelikEkle(String ilanid, String text, String nitelik) {
        Call<DetayModel> x = getRestApi().nitelikEkle(ilanid, text, nitelik);
        return x;
    }

    public Call<DetayModel> tanimEkle(String ilanid, String text, String tanim) {
        Call<DetayModel> x = getRestApi().tanimEkle(ilanid, text, tanim);
        return x;
    }

    public Call<DetayModel> pozisyonBilgiEkle(String firmasektoru, String text, String calismasekli, String departman, String pozisyonseviyesi, String ilanid) {
        Call<DetayModel> x = getRestApi().pozisyonBilgiEkle(firmasektoru, text, calismasekli, departman, pozisyonseviyesi, ilanid);
        return x;
    }

}
