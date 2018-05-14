package finom.list.ruf.listcrypt.data_base;

import java.util.List;

import finom.list.ruf.listcrypt.repository.dto.CryptoCurrencyDTO;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface CryptoApi {
    @GET("ticker")
    Single<List<CryptoCurrencyDTO>> getTicker();
}
