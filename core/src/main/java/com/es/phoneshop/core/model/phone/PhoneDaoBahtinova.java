package com.es.phoneshop.core.model.phone;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PhoneDaoBahtinova {
    Optional<PhoneBahtinova> get(Long key);
    void save(PhoneBahtinova phone);
    List<PhoneBahtinova> findAll(String order, int offset, int limit);
    List<PhoneBahtinova> search (String like, String order, int offset, int limit);
    List<ColorBahtinova> getPhoneColors(Long key);
    void savePhoneColors(Long key, Set<ColorBahtinova> colorSet);
    int phoneCount();
    int searchCount(String like);
    Optional<StockBahtinova> getStock(Long key);
    public void decreaseStock(final Long phoneId, final Long quantity);
}
