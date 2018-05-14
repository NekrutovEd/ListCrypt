package finom.list.ruf.listcrypt.repository;

import java.util.ArrayList;
import java.util.List;

import finom.list.ruf.listcrypt.busines.entity.CryptoCurrencyEntity;
import finom.list.ruf.listcrypt.data_base.NetworkService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImpl implements Repository {

    private NetworkService service;

    public RepositoryImpl() {
        service = new NetworkService();
    }

    @Override
    public Single<List<CryptoCurrencyEntity>> getListCryptoCurrency() {
        return service.getCryptoApi()
                .getTicker()
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMapIterable(cryptoCurrencyDTOList -> cryptoCurrencyDTOList)
                .reduce(new ArrayList<CryptoCurrencyEntity>(), (cryptoCurrencyEntities, cryptoCurrencyDTO) -> {
                    cryptoCurrencyEntities.add(CryptoCurrencyEntity.castDTO(cryptoCurrencyDTO));
                    return cryptoCurrencyEntities;
                });
    }
}
