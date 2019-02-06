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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("/isbulservis/kayitol.php")
    Call<KayitOlModel> registerUser(@Field("mail") String mailAdres, @Field("kadi") String kadi, @Field("parola") String pass);

    @FormUrlEncoded
    @POST("/isbulservis/girisyap.php")
    Call<GirisYapModel> girisYap(@Field("mailadres") String mailAdres, @Field("parola") String parola);

    @FormUrlEncoded
    @POST("/isbulservis/yetenekekle.php")
    Call<YetenekEkleModel> yetenekekle(@Field("id") String id, @Field("yetenek") String yetenek);

    @FormUrlEncoded
    @POST("/isbulservis/deneyimekle.php")
    Call<DeneyimEkleModel> deneyimEkle(@Field("id") String id, @Field("sirket") String sirket,
                                       @Field("title") String title, @Field("yil") String yil);

    @FormUrlEncoded
    @POST("/isbulservis/egitimekle.php")
    Call<EgitimEkleModel> egitimEkle(@Field("universite") String universite, @Field("bolum") String bolum, @Field("baslangic") String baslangic,
                                     @Field("bitis") String bitis, @Field("kid") String kid);

    @FormUrlEncoded
    @POST("/isbulservis/deneyimlistele.php")
    Call<List<DeneyimModel>> deneyimGetir(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbulservis/egitimlistele.php")
    Call<List<EgitimModel>> egitimGetir(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbulservis/yeteneklistele.php")
    Call<List<YetenekModel>> yetenekGetir(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbulservis/ilanlistele.php")
    Call<List<IlanModel>> ilanListele(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbulservis/basvurular.php")
    Call<List<BasvuruListModel>> basvuruListele(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbulservis/ilanlarim.php")
    Call<List<IlanModel>> ilanlarim(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbulservis/ilandetay.php")
    Call<List<IlanDetayModel>> ilanDetay(@Field("ilanid") String ilanid);

    @FormUrlEncoded
    @POST("/isbulservis/ilandetaynitelikler.php")
    Call<List<IlanDetayNitelikModel>> ilanDetayNitelik(@Field("ilanid") String ilanid);

    @FormUrlEncoded
    @POST("/isbulservis/deneyimsil.php")
    Call<SilModel> deneyimSil(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbulservis/egitimsil.php")
    Call<SilModel> egitimSil(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbulservis/yeteneksil.php")
    Call<SilModel> yetenekSil(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbulservis/kullanicibilgileri.php")
    Call<List<KullaniciBilgiModel>> kullaniciBilgiGetir(@Field("id") String id);

    @FormUrlEncoded
    @POST("/isbulservis/basvuruyap.php")
    Call<BasvuruModel> basvuruYap(@Field("userid") String userid, @Field("sahipid") String sahipid, @Field("ilanid") String ilanid);

    @FormUrlEncoded
    @POST("/isbulservis/basvuruonay.php")
    Call<BasvuruSonucModel> basvuruOnay(@Field("kadi") String kadi, @Field("mailadres") String mailadres, @Field("basvuruid") String basvuruid, @Field("baslik") String baslik);

    @FormUrlEncoded
    @POST("/isbulservis/basvurured.php")
    Call<BasvuruSonucModel> basvuruRed(@Field("kadi") String kadi, @Field("mailadres") String mailadres, @Field("basvuruid") String basvuruid, @Field("baslik") String baslik);

    @FormUrlEncoded
    @POST("/isbulservis/ilanpaylas.php")
    Call<IlanPaylasimiModel> ilanPaylas(@Field("kid") String kid, @Field("baslik") String baslik, @Field("aciklama") String aciklama, @Field("adres") String adres);


    @FormUrlEncoded
    @POST("/isbulservis/ilandetaygir.php")
    Call<DetayModel> kriterEkle(@Field("ilanid") String ilanid, @Field("text") String text, @Field("tecrube") String tecrube, @Field("egitimbilgisi") String egitimbilgisi);

    @FormUrlEncoded
    @POST("/isbulservis/ilandetaygir.php")
    Call<DetayModel> pozisyonBilgiEkle(@Field("firmasektoru") String firmasektoru, @Field("text") String text, @Field("calismasekli") String calismasekli, @Field("departman") String departman,
                                       @Field("pozisyonseviyesi") String pozisyonseviyesi, @Field("ilanid") String ilanid  );


    @FormUrlEncoded
    @POST("/isbulservis/ilandetaygir.php")
    Call<DetayModel> nitelikEkle(@Field("ilanid") String ilanid, @Field("text") String text,@Field("nitelik") String nitelik);

    @FormUrlEncoded
    @POST("/isbulservis/ilandetaygir.php")
    Call<DetayModel> tanimEkle(@Field("ilanid") String ilanid, @Field("text") String text,@Field("tanim") String tanim);

}
