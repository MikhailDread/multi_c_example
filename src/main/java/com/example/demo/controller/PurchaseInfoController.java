package com.example.demo.controller;

import com.example.demo.data.dto.ItemDto;
import com.example.demo.data.dto.UserDto;
import com.example.demo.service.PurchaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Api("Контроллер, предоставляющий информацию по обороту товаров")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Запрос успешно обработан"),
        @ApiResponse(code = 401, message = "Пользователь не авторизован"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера"),
        @ApiResponse(code = 503, message = "Сервер не работает")
})
public class PurchaseInfoController {
    @Autowired
    private PurchaseInfoService purchaseInfoService;

    @GetMapping("/last-week")
    public String purchaseListLastWeek(Model model) {
        List<ItemDto> lastWeek = purchaseInfoService.purchaseFromLastWeek();
        model.addAttribute("purchases", lastWeek);
        return "week";
    }

    @GetMapping("/top-item")
    public String mostPurchase(Model model) {
        ItemDto itemDto = purchaseInfoService.mostPurchaseInAll();
        model.addAttribute("item", itemDto.getName());
        return "top";
    }

    @GetMapping("/top-purchaser")
    public String topPurchaseUser(Model model) {
        UserDto userDto = purchaseInfoService.biggestBuyer();
        model.addAttribute("user", userDto.toString());
        return "best";
    }

    @GetMapping("/interesting-item")
    public String olderItemInteresting(Model model) {
        ItemDto itemDto = purchaseInfoService.age18Interesting();
        model.addAttribute("item", itemDto.getName());
        return "interesting";
    }
}
