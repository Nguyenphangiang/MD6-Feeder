package com.lunch.appfeeder.service.dish;

import com.lunch.appfeeder.model.entity.Dish;
import com.lunch.appfeeder.service.IGeneralService;

public interface IDishService extends IGeneralService<Dish> {
    Iterable<Dish> findDishByMerchant(Long merchantId);
}
