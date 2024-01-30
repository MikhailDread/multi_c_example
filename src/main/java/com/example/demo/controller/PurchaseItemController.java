package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.utils.MarshallerUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;

@RestController
@Api("Контроллер, принимающий и обрабатывающий входящие XML")
@ApiResponses(value = {
        @ApiResponse(code = 201, message = "Запрос успешно обработан и данные занесены в БД"),
        @ApiResponse(code = 400, message = "Некорректный запрос"),
        @ApiResponse(code = 500, message = "Внутренняя ошибка сервера"),
        @ApiResponse(code = 503, message = "Сервер не работает")
})
public class PurchaseItemController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseItemController.class);
    @Autowired
    private Validator validator;
    @Autowired
    private UserService userService;

    @PostMapping("/purchase")
    @ApiOperation("Обработать входящий xml-документ операций по товарам")
    public ResponseEntity purchaseItem(@RequestBody String xmlPurchaseInfo) throws IOException, JAXBException {
        Source source = new StreamSource(new StringReader(xmlPurchaseInfo));
        try {
            validator.validate(source);
        } catch (SAXException e) {
            LOGGER.error("В процессе валидации xml-документа на соответствие xsd-схемы произошла ошибка");
            return ResponseEntity
                    .badRequest()
                    .body("Некорректный xml-документ, просьба прикрепить действующий!");
        }

        userService.saveUserPurchase(MarshallerUtil.unmarshalToUser(xmlPurchaseInfo));

        LOGGER.info("Документ успешно обработан и данные занесены а базу");
        return ResponseEntity
                .ok()
                .build();
    }
}
