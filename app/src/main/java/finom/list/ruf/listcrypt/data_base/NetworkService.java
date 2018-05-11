package finom.list.ruf.listcrypt.data_base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    private static final String URL = "https://api.coinmarketcap.com/v1/";

    private CryptoApi cryptoApi;

    public NetworkService() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cryptoApi = retrofit.create(CryptoApi.class);
    }

    public CryptoApi getCryptoApi() {
        return cryptoApi;
    }
}
