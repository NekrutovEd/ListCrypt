package finom.list.ruf.listcrypt.busines.interactor;

import java.util.ArrayList;
import java.util.List;

import finom.list.ruf.listcrypt.busines.interactor.entity.CryptoCurrencyEntity;
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
                .map(this::castListCrypto);
    }

    private List<CryptoCurrency> castListCrypto(List<CryptoCurrencyEntity> cryptoCurrencies) {
        List<CryptoCurrency> result = new ArrayList<>();
        for (CryptoCurrencyEntity cryptoCurrencyEntity : cryptoCurrencies) {
            result.add(cryptoCurrencyEntity.copyTo());
        }
        return result;
    }
}
