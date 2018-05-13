package finom.list.ruf.listcrypt.presentation.crypt_list.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;

public interface ListCryptView {

    interface View extends MvpView {

        void updateListCryptoCurrency(@NonNull List<CryptoCurrency> cryptoCurrencies);

        void hideLoading();

        void showListCryptoCurrency();

        @StateStrategyType(SkipStrategy.class)
        void showCryptDetails(CryptoCurrency cryptoCurrency);
    }
}
