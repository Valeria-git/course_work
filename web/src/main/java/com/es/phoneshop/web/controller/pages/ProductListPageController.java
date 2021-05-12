package com.es.phoneshop.web.controller.pages;

import com.es.phoneshop.core.model.phone.PhoneBahtinova;
import com.es.phoneshop.core.model.phone.PhoneDaoBahtinova;
import com.es.phoneshop.core.order.OrderServiceImplBahtinova;
import com.es.phoneshop.core.page.PagingService;
import com.es.phoneshop.web.controller.exceptions.PageNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/productList")
public class ProductListPageController {
    @Resource
    private PhoneDaoBahtinova phoneDao;

    @Resource
    private PagingService pagingService;

    @Resource
    private OrderServiceImplBahtinova orderService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showProductList(ModelAndView model,
                                  @RequestParam(required = false) String search,
                                  @RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "id asc") String order) {
        List<PhoneBahtinova> phoneList;
        int total = pagingService.getTotalPages(search);

        if(page < 1 || page > total) {
            throw new PageNotFoundException();
        }

        int offset = pagingService.getOffset(page);

        if(search != null) {
            phoneList = phoneDao.search(search, order, offset, 10);
        } else {
            phoneList = phoneDao.findAll(order, offset, 10);
        }

        model.addObject("search", search);
        model.addObject("order", order);
        model.addObject("page", page);
        model.addObject("total", total);
        model.addObject("pagesNum", pagingService.calculatePagesNum(page, total));
        model.addObject("pageUrl", pagingService.getPageURL(search, order));
        model.addObject("searchParameter", pagingService.addSearchToOrder(search));
        model.addObject("phones", setColors(phoneList));
        return model;
    }

    private List<PhoneBahtinova> setColors(List<PhoneBahtinova> phoneList) {
        for (PhoneBahtinova phone : phoneList) {
            phone.setColors(new HashSet<>(phoneDao.getPhoneColors(phone.getId())));
        }
        return phoneList;
    }
}
