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
    email varchar2(30) unique,
    ssn integer unique not null, 
    bankClient_username varchar2(30) unique not null,
    bankClient_password varchar2(200) not null
);

create table bankAccount(
    bankAccount_id number primary key,
    bankAccount_name varchar2(40) not null,
    bankAccount_number number unique not null,
    interest_rate number,                      -- Attribute Exclusively for Savings Accounts
    minimum_balance number,                    -- Attribute Exclusively for Checking Accounts
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

create table transactionType(
    transactionType_id number primary key,
    transactionType_name varchar2(20) not null unique
);

create table bankAccountTransaction(
    transaction_id number primary key,
    amount number not null,
    transaction_datetime timestamp default CURRENT_TIMESTAMP,
    sourceBankAccount_id number not null,
    targetBankAccount_id number,
    transactionType_id number not null
);
    
-- Add foreign key constraints and composite primary keys
alter table bankClient add constraint fk_bank_id
    foreign key (bank_id) references bank(bank_id)
    on delete cascade;
    
alter table bankAccount add constraint fk_bankAccountType_id
    foreign key (bankAccountType_id) references bankAccountType(bankAccountType_id)
    on delete cascade;

alter table bankClientBankAccount add constraint fk_bankClient_id
    foreign key (bankClient_id) references bankClient(bankClient_id)
    on delete cascade;

alter table bankClientBankAccount add constraint fk_bankAccount_id
    foreign key (bankAccount_id) references bankAccount(bankAccount_id)
    on delete cascade;
    
alter table bankClientBankAccount add constraint pk_bankClientBankAccount
    primary key(bankClient_id, bankAccount_id);
    
alter table bankAccountTransaction add constraint fk_sourceBankAccount_id
    foreign key (sourceBankAccount_id) references bankAccount(bankAccount_id)
    on delete cascade;

alter table bankAccountTransaction add constraint fk_targetBankAccount_id
    foreign key (targetBankAccount_id) references bankAccount(bankAccount_id)
    on delete cascade;    

alter table bankAccountTransaction add constraint fk_transactionType_id
    foreign key (transactionType_id) references transactionType(transactionType_id)
    on delete cascade;    
    
    
-- Sequence
CREATE SEQUENCE SQ_BANK_PK START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE SQ_BANKCLIENT_PK START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE SQ_BANKACCOUNT_PK START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE SQ_BANKACCOUNTTYPE_PK START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE SQ_BANKACCOUNTTRANSACTION_PK START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE SQ_TRANSACTIONTYPE_PK START WITH 1 INCREMENT BY 1;

-- Trigger for auto-incrementing IDs (before insert, use sequence)
CREATE OR REPLACE TRIGGER TR_INSERT_BANK
BEFORE INSERT ON BANK
FOR EACH ROW
BEGIN
    SELECT SQ_BANK_PK.NEXTVAL INTO :NEW.BANK_ID FROM DUAL;
END;
/
    
CREATE OR REPLACE TRIGGER TR_INSERT_BANKCLIENT
BEFORE INSERT ON BANKCLIENT
FOR EACH ROW
BEGIN
    SELECT SQ_BANKCLIENT_PK.NEXTVAL INTO :NEW.BANKCLIENT_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_BANKACCOUNT
BEFORE INSERT ON BANKACCOUNT
FOR EACH ROW
BEGIN
    SELECT SQ_BANKACCOUNT_PK.NEXTVAL INTO :NEW.BANKACCOUNT_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_BANKACCOUNTTYPE
BEFORE INSERT ON BANKACCOUNTTYPE
FOR EACH ROW
BEGIN
    SELECT SQ_BANKACCOUNTTYPE_PK.NEXTVAL INTO :NEW.BANKACCOUNTTYPE_ID FROM DUAL;
END;
/
   
CREATE OR REPLACE TRIGGER TR_INSERT_ACCOUNTTRANSACTION
BEFORE INSERT ON BANKACCOUNTTRANSACTION
FOR EACH ROW
BEGIN
    SELECT SQ_BANKACCOUNTTRANSACTION_PK.NEXTVAL INTO :NEW.TRANSACTION_ID FROM DUAL;
END;
/

CREATE OR REPLACE TRIGGER TR_INSERT_TRANSACTIONTYPE
BEFORE INSERT ON TRANSACTIONTYPE
FOR EACH ROW
BEGIN
    SELECT SQ_TRANSACTIONTYPE_PK.NEXTVAL INTO :NEW.TRANSACTIONTYPE_ID FROM DUAL;
END;
/
-- Inserts
insert into bank(bank_name) values ('International Bank of Koalas');

insert into bankAccountType(bankAccountTypeName) values ('CHECKING');
insert into bankAccountType(bankAccountTypeName) values ('SAVINGS');

insert into transactionType(transactionType_name) values('DEPOSIT');
insert into transactionType(transactionType_name) values('WITHDRAW');
insert into transactionType(transactionType_name) values('TRANSFER');

-- Insert a bank client
insert into bankClient(bank_id, first_name, last_name, email, ssn, bankclient_username, bankclient_password) 
                values(1, 'Timothy', 'Touch', 'ttouch@hotmail.com', 123456789, 'timtouch', 'helloworld!');

-- Insert a bank transactions
insert into bankAccount(bankAccount_name, bankAccount_number,minimum_balance, bankAccountType_id)
                values('Tim''s first checking transactions', 987654321,  100, 1);
-- Link bank client to bank transactions
insert into bankClientBankAccount values(1,1);

insert into bankAccount(bankAccount_name, bankAccount_number, interest_rate, bankAccountType_id)
                values('Tim''s first savings transactions', 987654322, 0.03, 2);
insert into bankClientBankAccount values(1,3);                

--Bank transactions
-- Deposit
insert into bankAccountTransaction (amount, sourceBankAccount_id, transactionType_id)
                            values (200, 1, 1);

-- Withdraw
insert into bankAccountTransaction (amount, sourceBankAccount_id, transactionType_id)
                            values (100, 1, 2);                            

-- Transfer
insert into bankAccountTransaction (amount, sourceBankAccount_id, targetBankAccount_id, transactionType_id)
                            values (100, 1, 3, 3);     
-- Select Statements
select * from bank;

select * from bankClient;

select * from bankAccount;

select * from bankAccountType;

select * from transactionType;

select * from bankAccountTransaction;
--
select bankClient.first_name, bankClient.last_name, bankAccount.bankAccount_name, bankAccount.bankAccount_number from bankClient inner join bankClientBankAccount 
    on bankClient.bankClient_id = bankClientBankAccount.bankClient_id
    inner join bankAccount
    on bankClientBankAccount.bankAccount_id = bankAccount.bankAccount_id;

-- Maps all transactions to their bank account
select * from bankAccount inner join bankAccountTransaction
    on bankAccount.bankAccount_id = sourceBankAccount_id
    inner join transactionType
    on bankAccountTransaction.transactionType_id = transactionType.transactionType_id;

