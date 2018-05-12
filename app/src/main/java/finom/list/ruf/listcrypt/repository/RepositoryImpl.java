package finom.list.ruf.listcrypt.repository;

import java.util.ArrayList;
import java.util.List;

import finom.list.ruf.listcrypt.busines.entity.CryptoCurrencyEntity;
import finom.list.ruf.listcrypt.data_base.NetworkService;
import finom.list.ruf.listcrypt.repository.dto.CryptoCurrencyDTO;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;

public class RepositoryImpl implements Repository {

    private NetworkService service;

    public RepositoryImpl() {
        service = new NetworkService();
    }

    @Override
    public Single<List<CryptoCurrencyEntity>> getListCryptoCurrency() {
        Single<List<CryptoCurrencyDTO>> single = Single.create(
                emitter -> service.getCryptoApi()
                        .getTicker().enqueue(new Callback<List<CryptoCurrencyDTO>>() {
                            @Override
                            public void onResponse(Call<List<CryptoCurrencyDTO>> call, retrofit2.Response<List<CryptoCurrencyDTO>> response) {
                                emitter.onSuccess(response.body());
                            }

                            @Override
                            public void onFailure(Call<List<CryptoCurrencyDTO>> call, Throwable t) {
                                emitter.onError(t);
                            }
                        }));
        return single
                .subscribeOn(Schedulers.io())
                .map(this::castListCrypto);
    }

    private List<CryptoCurrencyEntity> castListCrypto(List<CryptoCurrencyDTO> cryptoCurrencies) {
        List<CryptoCurrencyEntity> result = new ArrayList<>();
        for (CryptoCurrencyDTO cryptoCurrencyDTO : cryptoCurrencies) {
            result.add(CryptoCurrencyEntity.castDTO(cryptoCurrencyDTO));
        }
        return result;
    }
}
