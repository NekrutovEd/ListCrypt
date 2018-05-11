package finom.list.ruf.listcrypt.busines.interactor.entity;

import finom.list.ruf.listcrypt.presentation.data.CryptoCurrency;
import finom.list.ruf.listcrypt.repository.dto.CryptoCurrencyDTO;

public class CryptoCurrencyEntity {

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

    public static CryptoCurrencyEntity castDTO(CryptoCurrencyDTO cryptoCurrencyDTO) {
        CryptoCurrencyEntity entity = new CryptoCurrencyEntity();
        entity.id = cryptoCurrencyDTO.getId();
        entity.name = cryptoCurrencyDTO.getName();
        entity.symbol = cryptoCurrencyDTO.getSymbol();
        entity.rank = cryptoCurrencyDTO.getRank();
        entity.priceUsd = cryptoCurrencyDTO.getPriceUsd();
        entity.priceBtc = cryptoCurrencyDTO.getPriceBtc();
        entity.volumeUsd_24h = cryptoCurrencyDTO.getVolumeUsd_24h();
        entity.marketCapUsd = cryptoCurrencyDTO.getMarketCapUsd();
        entity.availableSupply = cryptoCurrencyDTO.getAvailableSupply();
        entity.totalSupply = cryptoCurrencyDTO.getTotalSupply();
        entity.maxSupply = cryptoCurrencyDTO.getMaxSupply();
        entity.percentChange_1h = cryptoCurrencyDTO.getPercentChange_1h();
        entity.percentChange_24h = cryptoCurrencyDTO.getPercentChange_24h();
        entity.percentChange_7d = cryptoCurrencyDTO.getPercentChange_7d();
        entity.lastUpdated = cryptoCurrencyDTO.getLastUpdated();
        return entity;
    }

    public CryptoCurrency copyTo() {
        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setId(this.id);
        cryptoCurrency.setName(this.name);
        cryptoCurrency.setSymbol(this.symbol);
        cryptoCurrency.setRank(this.rank);
        cryptoCurrency.setPriceUsd(this.priceUsd);
        cryptoCurrency.setPriceBtc(this.priceBtc);
        cryptoCurrency.setVolumeUsd_24h(this.volumeUsd_24h);
        cryptoCurrency.setMarketCapUsd(this.marketCapUsd);
        cryptoCurrency.setAvailableSupply(this.availableSupply);
        cryptoCurrency.setTotalSupply(this.totalSupply);
        cryptoCurrency.setMaxSupply(this.maxSupply);
        cryptoCurrency.setPercentChange_1h(this.percentChange_1h);
        cryptoCurrency.setPercentChange_24h(this.percentChange_24h);
        cryptoCurrency.setPercentChange_7d(this.percentChange_7d);
        cryptoCurrency.setLastUpdated(this.lastUpdated);
        return cryptoCurrency;
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
