package finom.list.ruf.listcrypt.presentation.crypt_details.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import finom.list.ruf.listcrypt.presentation.crypt_details.view.CryptDetailsView;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;

@InjectViewState
public class CryptDetailsPresenter extends MvpPresenter<CryptDetailsView.View> {

    private CryptoCurrency cryptoCurrency;

    public CryptDetailsPresenter(CryptoCurrency cryptoCurrency) {
        this.cryptoCurrency = cryptoCurrency;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }
}
