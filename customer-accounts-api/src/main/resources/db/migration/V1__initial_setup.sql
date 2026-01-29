-- Criação da tabela de Contas
CREATE TABLE Accounts (
    Account_ID SERIAL PRIMARY KEY,
    Document_Number VARCHAR(20) UNIQUE NOT NULL
);

-- Criação da tabela de Tipos de Operação
CREATE TABLE Operation_Types (
    OperationType_ID SERIAL PRIMARY KEY,
    Description VARCHAR(50) NOT NULL
);

-- Criação da tabela de Transações
CREATE TABLE Transactions (
    Transaction_ID SERIAL PRIMARY KEY,
    Account_ID INTEGER NOT NULL REFERENCES Accounts(Account_ID),
    OperationType_ID INTEGER NOT NULL REFERENCES Operation_Types(OperationType_ID),
    Amount DECIMAL(10, 2) NOT NULL,
    EventDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Carga inicial de Tipos de Operação
INSERT INTO Operation_Types (OperationType_ID, Description) VALUES (1, 'PURCHASE');
INSERT INTO Operation_Types (OperationType_ID, Description) VALUES (2, 'INSTALLMENT PURCHASE');
INSERT INTO Operation_Types (OperationType_ID, Description) VALUES (3, 'WITHDRAWAL');
INSERT INTO Operation_Types (OperationType_ID, Description) VALUES (4, 'PAYMENT');