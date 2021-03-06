package com.es.phoneshop.web.controller.pages;

import com.es.phoneshop.core.cart.CartServiceBahtinova;
import com.es.phoneshop.core.model.phone.PhoneBahtinova;
import com.es.phoneshop.web.controller.forms.CartEntity;
import com.es.phoneshop.web.controller.forms.CartUpdateForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    @Resource
    private CartServiceBahtinova cartService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCart(ModelAndView model) {
        Set<Map.Entry<PhoneBahtinova, Long>> phoneEntrySet = cartService.getPhoneMap().entrySet();
        model.addObject("phoneEntries", phoneEntrySet);
        model.addObject("cartUpdateForm", getInitForm(phoneEntrySet));
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateCart(@RequestParam(name="delete", required = false) Long deleteId,
                             @ModelAttribute("cartUpdateForm") @Valid CartUpdateForm cartUpdateForm, BindingResult result, Model model) {
        if(deleteId != null) {
          return deleteFromCart(deleteId);
        } else {
            return updateCart(result, cartUpdateForm, model);
        }
    }

    private CartUpdateForm getInitForm(Set<Map.Entry<PhoneBahtinova, Long>> phoneEntrySet) {
        CartUpdateForm form = new CartUpdateForm();
        ArrayList<CartEntity> entityList = new ArrayList<>(phoneEntrySet.size());

        for(Map.Entry<PhoneBahtinova, Long> entry: phoneEntrySet) {
            CartEntity entity = new CartEntity();
            entity.setPhoneId(entry.getKey().getId());
            entity.setQuantity(entry.getValue());
            entityList.add(entity);
        }
        form.setEntities(entityList);
        return form;
    }

    private String deleteFromCart(Long deleteId) {
        if(cartService.getItemNumById(deleteId) != 0L) {
            cartService.remove(deleteId);
        }

        return "redirect:./";
    }

    private String updateCart(BindingResult result, CartUpdateForm cartUpdateForm, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("phoneEntries", cartService.getPhoneMap().entrySet());
            model.addAttribute("cartUpdateForm", cartUpdateForm);
            return "cart";
        } else {
            int outOfStockCount = 0;
            int i = 0;
            for (CartEntity entity : cartUpdateForm.getEntities()) {
                if (cartService.isNotEnoughStock(entity.getPhoneId(), entity.getQuantity() - cartService.getItemNumById(entity.getPhoneId()))) {
                    result.rejectValue("entities[" + i + "].quantity", "outOfStock", "Out of stock");
                    outOfStockCount++;
                }
                i++;
            }

            if (outOfStockCount > 0) {
                model.addAttribute("phoneEntries", cartService.getPhoneMap().entrySet());
                model.addAttribute("cartUpdateForm", cartUpdateForm);
                return "cart";
            } else {
                Map<Long, Long> updateMap = new HashMap<>();

                for (CartEntity entity : cartUpdateForm.getEntities()) {
                    updateMap.put(entity.getPhoneId(), entity.getQuantity());
                }
                cartService.update(updateMap);
                return "redirect:./";
            }
        }
    }
}
