package com.example.demo.controller;

import com.example.demo.data.xml.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.MarshallerUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.validation.Validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore({"javax.management.*", "org.apache.http.conn.ssl.*", "com.amazonaws.*", "javax.net.ssl.*","com.sun.*"})
@PrepareForTest(MarshallerUtil.class)
public class PurchaseItemControllerTest {
    @InjectMocks
    private PurchaseItemController purchaseItemController;
    @Mock
    private Validator validator;
    @Mock
    private UserService userService;

    @Before
    public void setUp() throws JAXBException {
        mockStatic(MarshallerUtil.class);
        PowerMockito.when(MarshallerUtil.unmarshalToUser(any())).thenReturn(new User());
    }

    @Test
    public void purchaseItem() throws Exception {
        doNothing().when(validator).validate(any());
        ResponseEntity responseEntity = purchaseItemController.purchaseItem("");

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(userService, times(1)).saveUserPurchase(any());
    }

    @Test
    public void purchaseItemFail() throws Exception {
        doThrow(SAXException.class).when(validator).validate(any());
        ResponseEntity responseEntity = purchaseItemController.purchaseItem("");

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Некорректный xml-документ, просьба прикрепить действующий!", responseEntity.getBody());
        verify(userService, never()).saveUserPurchase(any());
    }
}
