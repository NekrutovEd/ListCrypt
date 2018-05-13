package finom.list.ruf.listcrypt.presentation.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class CryptoCurrency {

    private String id;
    private String name;
    private String symbol;
    private Integer rank;
    private Float priceUsd;
    private Float priceBtc;
    private Long volumeUsd_24h;
    private Long marketCapUsd;
    private Long availableSupply;
    private Long totalSupply;
    private Long maxSupply;
    private Float percentChange_1h;
    private Float percentChange_24h;
    private Float percentChange_7d;
    private Long lastUpdated;


    /**
     * Returns true if and only if {@code CryptoCurrency.name} or {@code CryptoCurrency.symbol} contains string.
     * Without the register
     *
     * @param textSearch the string to search for
     */
    public boolean contains(String textSearch) {
        return name.toLowerCase().contains(textSearch.toLowerCase())
                || symbol.toLowerCase().contains(textSearch.toLowerCase());
    }

    public int compareRank(CryptoCurrency cryptoCurrency) {
        Integer rank = cryptoCurrency.getRank();
        if (this.rank > rank) return 1;
        else if (this.rank < rank) return -1;
        else return 0;
    }

    public int comparePriceUsd(CryptoCurrency cryptoCurrency) {
        Float priceUsd = cryptoCurrency.getPriceUsd();
        if (this.priceUsd > priceUsd) return 1;
        else if (this.priceUsd < priceUsd) return -1;
        else return 0;
    }

    public int compareMarketCapUsd(CryptoCurrency cryptoCurrency) {
        Long marketCapUsd = cryptoCurrency.getMarketCapUsd();
        if (this.marketCapUsd > marketCapUsd) return 1;
        else if (this.marketCapUsd < marketCapUsd) return -1;
        else return 0;
    }

    public int compareVolumeUsd(CryptoCurrency cryptoCurrency) {
        Long volumeUsd_24h = cryptoCurrency.getVolumeUsd_24h();
        if (this.volumeUsd_24h > volumeUsd_24h) return 1;
        else if (this.volumeUsd_24h < volumeUsd_24h) return -1;
        else return 0;
    }

    public int comparePercentChange_24h(CryptoCurrency cryptoCurrency) {
        Float percentChange_24h = cryptoCurrency.getPercentChange_24h();
        if (this.percentChange_24h > percentChange_24h) return 1;
        else if (this.percentChange_24h < percentChange_24h) return -1;
        else return 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Float getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Float priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Float getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(Float priceBtc) {
        this.priceBtc = priceBtc;
    }

    public Long getVolumeUsd_24h() {
        return volumeUsd_24h;
    }

    public void setVolumeUsd_24h(Long volumeUsd_24h) {
        this.volumeUsd_24h = volumeUsd_24h;
    }

    public Long getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(Long marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Long getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(Long availableSupply) {
        this.availableSupply = availableSupply;
    }

    public Long getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Long totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Long getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Long maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Float getPercentChange_1h() {
        return percentChange_1h;
    }

    public void setPercentChange_1h(Float percentChange_1h) {
        this.percentChange_1h = percentChange_1h;
    }

    public Float getPercentChange_24h() {
        return percentChange_24h;
    }

    public void setPercentChange_24h(Float percentChange_24h) {
        this.percentChange_24h = percentChange_24h;
    }

    public Float getPercentChange_7d() {
        return percentChange_7d;
    }

    public void setPercentChange_7d(Float percentChange_7d) {
        this.percentChange_7d = percentChange_7d;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
