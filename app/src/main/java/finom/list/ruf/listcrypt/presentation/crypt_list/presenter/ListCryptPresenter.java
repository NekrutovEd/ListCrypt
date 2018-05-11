package finom.list.ruf.listcrypt.presentation.crypt_list.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import finom.list.ruf.listcrypt.busines.interactor.Interactor;
import finom.list.ruf.listcrypt.presentation.crypt_list.view.ListCryptView;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;
import finom.list.ruf.listcrypt.repository.dto.CryptoCurrencyDTO;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class ListCryptPresenter extends MvpPresenter<ListCryptView.View> {

    private final Interactor interactor;

    public ListCryptPresenter() {
        interactor = Interactor.INTERACTOR_IMPLEMENTATION;
    }

    @SuppressLint("CheckResult")
    public void start() {
        interactor.getListCryptoCurrency()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getViewState()::updateListCryptoCurrency, this::handleError);
    }

    public void onClickCryptoCurrency(CryptoCurrency cryptoCurrency) {
        //TODO показать экран деталей
    }

    private void handleError(Throwable throwable) {
        //TODO обработка ошибок
    }
}
