package com.namruslan.mobilephonepayment.controller;

import com.namruslan.mobilephonepayment.dto.BalanceDto;
import com.namruslan.mobilephonepayment.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MainController {

    private BalanceService balanceService;

    @Autowired
    public void setBalanceService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id")int id) {
        Optional<BalanceDto> dtoOptional = balanceService.get(id);

        return dtoOptional.isPresent()
                ? ResponseEntity.ok(dtoOptional.get())
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<?> findByName(@PathVariable("name") String name) {
        List<BalanceDto> dtoList = balanceService.findAllByCustomerName(name);

        return !dtoList.isEmpty()
                ? ResponseEntity.ok(dtoList)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        List<BalanceDto> dtoList = balanceService.getAll();

        return dtoList.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(dtoList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> add(@RequestBody BalanceDto dto) {
        balanceService.create(dto);
        return ResponseEntity.ok().body(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return balanceService.delete(id)
                ? ResponseEntity.ok().body(HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestParam("id") int id,
                                  @RequestParam("number") int number) {
        Optional<BalanceDto> balanceDtoOptional = balanceService.get(id);

        if (balanceDtoOptional.isPresent()) {
            balanceService.create(balanceDtoOptional.get().setPhoneNumber(number));
            return ResponseEntity.ok().body(HttpStatus.OK);

        } else return ResponseEntity.notFound().build();

    }

    @PutMapping("/add-money")
    public ResponseEntity<?> addMoney(@RequestParam("number") int number,
                                      @RequestParam("sum") int sum) {
        return balanceService.addMoneyToBalance(number, sum)
                ? ResponseEntity.ok().body(HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/add-money-body")
    public ResponseEntity<?> addMoneyRequestBody(@RequestBody BalanceDto dto) {
        return balanceService.addMoneyToBalance(dto.getPhoneNumber(), dto.getBalance())
                ? ResponseEntity.ok().body(HttpStatus.OK)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/set-cookie")
    public ResponseEntity<?> setCookie(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("data", "Here_comes_the_cookie");
        cookie.setMaxAge(8640);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setContentType("text/plain");
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/get-cookie")
    public ResponseEntity<?> getCookie(@CookieValue(name = "data") String data) {
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/get-headers")
    public ResponseEntity<?> getHeaders(@RequestHeader Map<String, String> headers) {
        return ResponseEntity.ok(headers);
    }

    @GetMapping("/set-header")
    public ResponseEntity<?> setHeader() {
        return ResponseEntity.ok().header("header-name", "header-value").body(HttpStatus.OK);
    }

}
