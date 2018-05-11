package finom.list.ruf.listcrypt.data_base;

import java.util.List;

import finom.list.ruf.listcrypt.repository.dto.CryptoCurrencyDTO;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoApi {
    @GET("ticker")
    Call<List<CryptoCurrencyDTO>> getTicker();
}
