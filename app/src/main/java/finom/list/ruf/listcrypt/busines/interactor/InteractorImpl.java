package finom.list.ruf.listcrypt.busines.interactor;

import java.util.ArrayList;
import java.util.List;

import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;
import finom.list.ruf.listcrypt.repository.Repository;
import finom.list.ruf.listcrypt.repository.RepositoryImpl;
import io.reactivex.Single;

public class InteractorImpl implements Interactor {

    private static volatile InteractorImpl INSTANCE;

    private Repository repository;

    private InteractorImpl() {
        repository = new RepositoryImpl();
    }

    public static InteractorImpl getInstance() {
        InteractorImpl localInstance = INSTANCE;
        if (localInstance == null)
            synchronized (InteractorImpl.class) {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    INSTANCE = localInstance = new InteractorImpl();
                }
            }
        return localInstance;
    }

    @Override
    public Single<List<CryptoCurrency>> getListCryptoCurrency() {
        return repository.getListCryptoCurrency()
                .toObservable()
                .flatMapIterable(cryptoCurrencyEntities -> cryptoCurrencyEntities)
                .reduce(new ArrayList<CryptoCurrency>(), (cryptoCurrencies, cryptoCurrencyEntity) -> {
                    cryptoCurrencies.add(cryptoCurrencyEntity.copyTo());
                    return cryptoCurrencies;
                });
    }
}
