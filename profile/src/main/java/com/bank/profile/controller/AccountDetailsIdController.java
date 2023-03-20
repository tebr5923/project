package com.bank.profile.controller;

import com.bank.profile.entity.AccountDetailsId;
import com.bank.profile.service.serviceInterface.AccountDetailsIdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account_details_id")
@Tag(name = "AccountDetailsId", description = "REST контролер для сущности AccountDetailsId (маппинг профиля клиента и его счетов).")
public class AccountDetailsIdController {
    private final AccountDetailsIdService accountDetailsIdService;

    @Autowired
    public AccountDetailsIdController(AccountDetailsIdService accountDetailsIdService) {
        this.accountDetailsIdService = accountDetailsIdService;
    }

    @GetMapping("/")
    @Operation(
            summary = "Получение всех объектов AccountDetailsId.",
            description = "Получение всех объектов AccountDetailsId."
    )
    public List<AccountDetailsId> getAllAccountDetailsId() {
        return accountDetailsIdService.getAllAccountDetailsId();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение конкретного объекта AccountDetailsId.",
            description = "Получение конкретного объекта AccountDetailsId через accountDetailsId.id."
    )
    public AccountDetailsId getAccountDetailsId(@PathVariable Long id) {
        return accountDetailsIdService.findAccountDetailsIdById(id);
    }

    @PostMapping("/")
    @Operation(
            summary = "Сохранение в бд нового объекта AccountDetailsId.",
            description = "Сохранение в бд нового объекта AccountDetailsId."
    )
    public AccountDetailsId createAccountDetailsId(@RequestBody AccountDetailsId accountDetailsId) {
        accountDetailsIdService.saveAccountDetailsId(accountDetailsId);
        return accountDetailsId;
    }

    @PutMapping("/")
    @Operation(
            summary = "Обновление существующего объекта AccountDetailsId.",
            description = "Обновление существующего объекта AccountDetailsId."
    )
    public AccountDetailsId editAccountDetailsId(@RequestBody AccountDetailsId accountDetailsId) {
        accountDetailsIdService.editAccountDetailsId(accountDetailsId);
        return accountDetailsId;
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление существующего объекта AccountDetailsId.",
            description = "Удаление существующего объекта AccountDetailsId."
    )
    public ResponseEntity<HttpStatus> deleteAccountDetailsId(@PathVariable Long id) {
        accountDetailsIdService.deleteAccountDetailsId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
