package finom.list.ruf.listcrypt.presentation.crypt_list.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import finom.list.ruf.listcrypt.busines.interactor.Interactor;
import finom.list.ruf.listcrypt.presentation.crypt_list.view.ListCryptView;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ListCryptPresenter extends MvpPresenter<ListCryptView.View> {
    private static final String TAG = "ListCryptPresenter";
    private final Interactor interactor;
    private List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String textSearch = "";

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
                .subscribe(this::filterListCryptoCurrency, this::handleError);
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

    public void onSearch(String textSearch) {
        this.textSearch = textSearch;
        filterListCryptoCurrency(cryptoCurrencies);
    }

    private void filterListCryptoCurrency(List<CryptoCurrency> cryptoCurrencies) {
        compositeDisposable.clear();
        if (textSearch.isEmpty()) {
            getViewState().updateListCryptoCurrency(cryptoCurrencies);
        } else {
            List<CryptoCurrency> result = new ArrayList<>();
            compositeDisposable.add(Observable.fromIterable(cryptoCurrencies)
                    .subscribeOn(Schedulers.computation())
                    .filter(cryptoCurrency -> cryptoCurrency.contains(textSearch))
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnComplete(() -> getViewState().updateListCryptoCurrency(result))
                    .subscribe(result::add));
        }
    }
}
