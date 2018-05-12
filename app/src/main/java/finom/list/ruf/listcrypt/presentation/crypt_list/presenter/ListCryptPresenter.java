package finom.list.ruf.listcrypt.presentation.crypt_list.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import finom.list.ruf.listcrypt.busines.interactor.Interactor;
import finom.list.ruf.listcrypt.presentation.crypt_list.view.ListCryptView;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class ListCryptPresenter extends MvpPresenter<ListCryptView.View> {
    private static final String TAG = "ListCryptPresenter";
    private final Interactor interactor;
    private List<CryptoCurrency> cryptoCurrencies;

    public ListCryptPresenter() {
        interactor = Interactor.INTERACTOR_IMPLEMENTATION;
    }

    public void start() {
        if (cryptoCurrencies != null && !cryptoCurrencies.isEmpty())
            getViewState().updateListCryptoCurrency(cryptoCurrencies);
        else loadListCryptoCurrency();
    }

    @SuppressLint("CheckResult")
    private void loadListCryptoCurrency() {
        interactor.getListCryptoCurrency()
                .map(cryptoCurrencies -> this.cryptoCurrencies = cryptoCurrencies)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess(cryptoCurrencies -> getViewState().showListCryptoCurrency())
                .doFinally(getViewState()::hideLoading)
                .subscribe(getViewState()::updateListCryptoCurrency, this::handleError);
    }

    public void onSwipeToRefresh() {
        loadListCryptoCurrency();
    }

    public void onClickCryptoCurrency(CryptoCurrency cryptoCurrency) {
        //TODO показать экран деталей
    }

    private void handleError(Throwable throwable) {
        //TODO обработка ошибок
        Log.e(TAG, "handleError: " + throwable.getMessage(), throwable);
    }
}
