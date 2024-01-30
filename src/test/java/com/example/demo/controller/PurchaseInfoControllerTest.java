package com.example.demo.controller;

import com.example.demo.data.dto.ItemDto;
import com.example.demo.data.dto.UserDto;
import com.example.demo.service.PurchaseInfoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseInfoControllerTest {
    @InjectMocks
    private PurchaseInfoController purchaseInfoController;
    @Mock
    private PurchaseInfoService purchaseInfoService;
    private final ItemDto item = new ItemDto();
    private final UserDto user = new UserDto();
    private final List<ItemDto> items = List.of(item);

    @Before
    public void setUp() {
        when(purchaseInfoService.purchaseFromLastWeek()).thenReturn(items);
        when(purchaseInfoService.mostPurchaseInAll()).thenReturn(item);
        when(purchaseInfoService.biggestBuyer()).thenReturn(user);
        when(purchaseInfoService.age18Interesting()).thenReturn(item);
    }

    @Test
    public void purchaseListLastWeek() {
        ExtendedModelMap model = new ExtendedModelMap();
        String page = purchaseInfoController.purchaseListLastWeek(model);

        assertNotNull(page);
        assertEquals("week", page);
        assertEquals(items, model.get("purchases"));
    }

    @Test
    public void mostPurchase() {
        ExtendedModelMap model = new ExtendedModelMap();
        String page = purchaseInfoController.mostPurchase(model);

        assertNotNull(page);
        assertEquals("top", page);
        assertEquals(item.getName(), model.get("item"));
    }

    @Test
    public void topPurchaseUser() {
        ExtendedModelMap model = new ExtendedModelMap();
        String page = purchaseInfoController.topPurchaseUser(model);

        assertNotNull(page);
        assertEquals("best", page);
        assertEquals(user.toString(), model.get("user"));
    }

    @Test
    public void olderItemInteresting() {
        ExtendedModelMap model = new ExtendedModelMap();
        String page = purchaseInfoController.olderItemInteresting(model);

        assertNotNull(page);
        assertEquals("interesting", page);
        assertEquals(item.getName(), model.get("item"));
    }
}
