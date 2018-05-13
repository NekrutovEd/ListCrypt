package finom.list.ruf.listcrypt.presentation.crypt_details.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.text.NumberFormat;
import java.util.Locale;

import finom.list.ruf.listcrypt.R;
import finom.list.ruf.listcrypt.presentation.crypt_details.presenter.CryptDetailsPresenter;
import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;

public class CryptDetailsFragment extends MvpAppCompatFragment implements CryptDetailsView.View {

    @InjectPresenter
    public CryptDetailsPresenter presenter;

    @ProvidePresenter
    public CryptDetailsPresenter providePresenter() {
        return new CryptDetailsPresenter(cryptoCurrency);
    }

    private CryptoCurrency cryptoCurrency;

    public static CryptDetailsFragment newInstance(CryptoCurrency cryptoCurrency) {
        Bundle args = new Bundle();
        CryptDetailsFragment fragment = new CryptDetailsFragment();
        fragment.setArguments(args);
        fragment.cryptoCurrency = cryptoCurrency;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_crypt_details, container, false);
        initToolbar(root);

        TextView symbol = root.findViewById(R.id.fragment_crypt_details_symbol);
        TextView name = root.findViewById(R.id.fragment_crypt_details_name);
        TextView priceUsd = root.findViewById(R.id.fragment_crypt_details_price_usd);
        TextView priceBtc = root.findViewById(R.id.fragment_crypt_details_price_btc);
        TextView percentChange_1h = root.findViewById(R.id.fragment_crypt_details_percent_change_1h);
        TextView percentChange_24h = root.findViewById(R.id.fragment_crypt_details_percent_change_24h);
        TextView percentChange_7d = root.findViewById(R.id.fragment_crypt_details_percent_change_7d);
        TextView marketCapUsd = root.findViewById(R.id.fragment_crypt_details_market_cap_usd);
        TextView volumeUsd_24h = root.findViewById(R.id.fragment_crypt_details_volume_usd_24h);
        TextView availableSupply = root.findViewById(R.id.fragment_crypt_details_available_supply);
        TextView totalSupply = root.findViewById(R.id.fragment_crypt_details_total_supply);

        CryptoCurrency cryptoCurrency = presenter.getCryptoCurrency();
        NumberFormat formatCurrency = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat format = NumberFormat.getInstance(Locale.US);

        symbol.setText(cryptoCurrency.getSymbol());
        name.setText(cryptoCurrency.getName());
        priceUsd.setText(formatCurrency.format(cryptoCurrency.getPriceUsd()));
        priceBtc.setText(String.format("%s BTC", format.format(cryptoCurrency.getPriceBtc())));
        initTextPercent(percentChange_1h, cryptoCurrency.getPercentChange_1h());
        initTextPercent(percentChange_24h, cryptoCurrency.getPercentChange_24h());
        initTextPercent(percentChange_7d, cryptoCurrency.getPercentChange_7d());
        marketCapUsd.setText(formatCurrency.format(cryptoCurrency.getMarketCapUsd()));
        volumeUsd_24h.setText(formatCurrency.format(cryptoCurrency.getVolumeUsd_24h()));
        availableSupply.setText(String.format("%s %s", format.format(cryptoCurrency.getAvailableSupply()), cryptoCurrency.getSymbol()));
        totalSupply.setText(String.format("%s %s", format.format(cryptoCurrency.getTotalSupply()), cryptoCurrency.getSymbol()));

        TextView maxSupply = root.findViewById(R.id.fragment_crypt_details_max_supply);
        View maxSupplyLine = root.findViewById(R.id.fragment_crypt_details_max_supply_line);
        TextView maxSupplyTitle = root.findViewById(R.id.fragment_crypt_details_max_supply_title);
        if (cryptoCurrency.getMaxSupply() == null) {
            maxSupply.setVisibility(View.GONE);
            maxSupplyLine.setVisibility(View.GONE);
            maxSupplyTitle.setVisibility(View.GONE);
        } else {
            maxSupply.setVisibility(View.VISIBLE);
            maxSupplyLine.setVisibility(View.VISIBLE);
            maxSupplyTitle.setVisibility(View.VISIBLE);
            maxSupply.setText(String.format("%s %s", format.format(cryptoCurrency.getMaxSupply()), cryptoCurrency.getSymbol()));
        }

        return root;
    }

    private void initToolbar(View root) {
        Toolbar toolbar = root.findViewById(R.id.fragment_crypt_details_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_white_24px);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        setHasOptionsMenu(true);
    }

    private void initTextPercent(TextView textViewPercent, Float value) {
        String formatPercent = "%s%%";
        if (value > 0) {
            textViewPercent.setTextColor(
                    textViewPercent.getResources().getColor(R.color.colorGreen));
            formatPercent = "+%s%%";
        } else if (value < 0) {
            textViewPercent.setTextColor(
                    textViewPercent.getResources().getColor(R.color.colorRed));
        } else {
            textViewPercent.setTextColor(
                    textViewPercent.getResources().getColor(R.color.colorGreen));
        }
        textViewPercent.setText(String.format(formatPercent, value));
    }

    private void onBackPressed() {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }
}
