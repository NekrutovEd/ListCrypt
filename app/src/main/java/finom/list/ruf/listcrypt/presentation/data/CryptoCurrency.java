package finom.list.ruf.listcrypt.presentation.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CryptoCurrency {

    private String id;
    private String name;
    private String symbol;
    private Integer rank;
    private Double priceUsd;
    private Double priceBtc;
    private Double volumeUsd_24h;
    private Long marketCapUsd;
    private Double availableSupply;
    private Double totalSupply;
    private Double maxSupply;
    private Double percentChange_1h;
    private Double percentChange_24h;
    private Double percentChange_7d;
    private Long lastUpdated;


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

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Double getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(Double priceBtc) {
        this.priceBtc = priceBtc;
    }

    public Double getVolumeUsd_24h() {
        return volumeUsd_24h;
    }

    public void setVolumeUsd_24h(Double volumeUsd_24h) {
        this.volumeUsd_24h = volumeUsd_24h;
    }

    public Long getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(Long marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public Double getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(Double availableSupply) {
        this.availableSupply = availableSupply;
    }

    public Double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Double getMaxSupply() {
        return maxSupply;
    }

    public void setMaxSupply(Double maxSupply) {
        this.maxSupply = maxSupply;
    }

    public Double getPercentChange_1h() {
        return percentChange_1h;
    }

    public void setPercentChange_1h(Double percentChange_1h) {
        this.percentChange_1h = percentChange_1h;
    }

    public Double getPercentChange_24h() {
        return percentChange_24h;
    }

    public void setPercentChange_24h(Double percentChange_24h) {
        this.percentChange_24h = percentChange_24h;
    }

    public Double getPercentChange_7d() {
        return percentChange_7d;
    }

    public void setPercentChange_7d(Double percentChange_7d) {
        this.percentChange_7d = percentChange_7d;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
