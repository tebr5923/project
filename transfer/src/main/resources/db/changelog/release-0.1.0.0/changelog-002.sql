    --liquibase formatted sql

    --changeset Dmitry:1
    insert into
        transfer.account_transfer(account_number, amount, purpose, account_details_id)
        values (111, 11.11, 'aaa', 11111);

    --changeset Dmitry:2
    insert into
        transfer.account_transfer(account_number, amount, purpose, account_details_id)
    values (222, 22.22, 'bbb', 22222);

    --changeset Dmitry:3
    insert into
        transfer.card_transfer(card_number, amount, purpose, account_details_id)
    values (111, 11.11, 'aaa', 11111);

    --changeset Dmitry:4
    insert into
        transfer.card_transfer(card_number, amount, purpose, account_details_id)
    values (222, 22.22, 'bbb', 22222);

    --changeset Dmitry:5
    insert into
        transfer.phone_transfer(phone_number, amount, purpose, account_details_id)
    values (111, 11.11, 'aaa', 11111);

    --changeset Dmitry:6
    insert into
        transfer.phone_transfer(phone_number, amount, purpose, account_details_id)
    values (222, 22.22, 'bbb', 22222);