package finom.list.ruf.listcrypt.busines.interactor;

import java.util.List;

import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;
import io.reactivex.Single;

public interface Interactor {

    Interactor INTERACTOR_IMPLEMENTATION = InteractorImpl.getInstance();

    Single<List<CryptoCurrency>> getListCryptoCurrency();
}
