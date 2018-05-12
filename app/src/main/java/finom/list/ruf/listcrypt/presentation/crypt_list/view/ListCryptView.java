package finom.list.ruf.listcrypt.presentation.crypt_list.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;

public interface ListCryptView {
    interface View extends MvpView {
        void updateListCryptoCurrency(List<CryptoCurrency> cryptoCurrencies);

        void hideLoading();

        void showListCryptoCurrency();
    }
}
