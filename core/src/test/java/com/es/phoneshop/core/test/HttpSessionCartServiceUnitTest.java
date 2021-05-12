package com.es.phoneshop.core.test;


import com.es.phoneshop.core.cart.HttpSessionCartServiceBahtinova;
import com.es.phoneshop.core.model.phone.PhoneBahtinova;
import com.es.phoneshop.core.model.phone.PhoneDaoBahtinova;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class HttpSessionCartServiceUnitTest {
    private static final long ID1 = 1003L;
    private static final long ID2 = 1006L;
    private static final long ID3 = 1011L;
    private static final int PRICE1 = 249;
    private static final int PRICE2 = 270;

    @Mock
    private PhoneDaoBahtinova mockDao;

    @InjectMocks
    private HttpSessionCartServiceBahtinova cartService;

    @Test
    public void checkItemsNum() {
        cartService.addPhone(ID1, 3L);
        cartService.addPhone(ID1, 5L);
        cartService.addPhone(ID2, 7L);
        cartService.addPhone(ID3, 1L);
        cartService.addPhone(ID3, 1L);
        cartService.addPhone(ID3, 1L);

        long itemsNum = cartService.getItemsNum();

        Assert.assertEquals(18L, itemsNum);
    }

    @Test
    public void checkOverallPrice() {
        cartService.addPhone(ID1, 1L);
        cartService.addPhone(ID1, 1L);
        cartService.addPhone(ID2, 2L);
        PhoneBahtinova phone1 = new PhoneBahtinova();
        phone1.setPrice(new BigDecimal(PRICE1));
        PhoneBahtinova phone2 = new PhoneBahtinova();
        phone2.setPrice(new BigDecimal(PRICE2));
        Mockito.when(mockDao.get(ID1)).thenReturn(Optional.of(phone1));
        Mockito.when(mockDao.get(ID2)).thenReturn(Optional.of(phone2));

        int overallPrice = cartService.getOverallPrice();

        Assert.assertEquals(1038, overallPrice);
    }
}
