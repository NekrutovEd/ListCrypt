package finom.list.ruf.listcrypt.repository;

import java.util.List;

import finom.list.ruf.listcrypt.busines.entity.CryptoCurrencyEntity;
import io.reactivex.Single;

public interface Repository {

    Single<List<CryptoCurrencyEntity>> getListCryptoCurrency();
}
