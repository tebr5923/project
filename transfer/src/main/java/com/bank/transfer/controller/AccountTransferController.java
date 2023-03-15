package com.bank.transfer.controller;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.entity.AccountTransfer;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.service.AccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account_transfers")
public class AccountTransferController {

    private final AccountTransferService accountTransferService;

    @Autowired
    public AccountTransferController(AccountTransferService accountTransferService) {
        this.accountTransferService = accountTransferService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountTransferDTO> getById(@PathVariable("id") Long id) {
        AccountTransfer accountTransfer = accountTransferService.getById(id).orElseThrow(RuntimeException::new);
        AccountTransferDTO dto = AccountTransferMapper.MAPPER.ToDTO(accountTransfer);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED) //201
    public ResponseEntity<AccountTransfer> create(@RequestBody AccountTransferDTO dto) {
        AccountTransfer accountTransfer = AccountTransferMapper.MAPPER.ToEntity(dto);
        accountTransferService.save(accountTransfer);
        return new ResponseEntity<>(accountTransfer, HttpStatus.CREATED);//201
    }
}
