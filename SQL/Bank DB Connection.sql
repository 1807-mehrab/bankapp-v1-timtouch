
-- DDL Stuff
create table bank(
    bank_id number primary key,
    bank_name varchar2(30) unique not null
);

create table bankClient(
    bankClient_id number primary key,
    bank_id number not null,
    first_name varchar2(30) not null,
    last_name varchar2(30) not null,
    email varchar2(30),
    ssn integer unique not null, 
    bankClient_username varchar2(30) unique not null,
    bankClient_password varchar2(100) not null
);

create table bankAccount(
    bankAccount_id number primary key,
    bankAccount_name varchar2(40) not null,
    bankAccount_number number unique not null,
    balance number not null,
    interest_rate number,
    minimum_balance number,
    bankAccountType_id number not null
);

create table bankAccountType(
    bankAccountType_id number primary key,
    bankAccountTypeName varchar2(40) unique not null
);

-- Join table for many-to-many between bankClient and bankAccount
create table bankClientBankAccount(
    bankClient_id number not null,
    bankAccount_id number not null
);

alter table bankClient add constraint fk_bank_id
    foreign key (bank_id) references bank(bank_id);
    
alter table bankAccount add constraint fk_bankAccountType_id
    foreign key (bankAccountType_id) references bankAccountType(bankAccountType_id);

alter table bankClientBankAccount add constraint fk_bankClient_id
    foreign key (bankClient_id) references bankClient(bankClient_id);

alter table bankClientBankAccount add constraint fk_bankAccount_id
    foreign key (bankAccount_id) references bankAccount(bankAccount_id);
    
alter table bankClientBankAccount add constraint pk_bankClientBankAccount
    primary key(bankClient_id, bankAccount_id);
    
-- Sequence
CREATE SEQUENCE SQ_BANK_PK START WITH 1 INCREMENT BY 1;

-- Trigger (before insert, use sequence)
CREATE OR REPLACE TRIGGER TR_INSERT_BANK
BEFORE INSERT ON BANK
FOR EACH ROW
BEGIN
    SELECT SQ_BANK_PK.NEXTVAL INTO :NEW.BANK_ID FROM DUAL;
END;
/
    
    
-- Inserts


