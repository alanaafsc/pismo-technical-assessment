-- Creation of the Accounts table
CREATE TABLE Accounts (
    Account_ID BIGSERIAL PRIMARY KEY,
    Document_Number VARCHAR(20) UNIQUE NOT NULL
);

-- Creation of the Operation Types table
CREATE TABLE Operation_Types (
    OperationType_ID SERIAL PRIMARY KEY,
    Description VARCHAR(50) NOT NULL
);

-- Creation of the Transactions table
CREATE TABLE Transactions (
    Transaction_ID BIGSERIAL PRIMARY KEY,
    Account_ID BIGINT NOT NULL REFERENCES Accounts(Account_ID),
    OperationType_ID BIGINT NOT NULL REFERENCES Operation_Types(OperationType_ID),
    Amount DECIMAL(10, 2) NOT NULL,
    EventDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Initial load of Operation Types
INSERT INTO Operation_Types (OperationType_ID, Description) VALUES (1, 'PURCHASE');
INSERT INTO Operation_Types (OperationType_ID, Description) VALUES (2, 'INSTALLMENT PURCHASE');
INSERT INTO Operation_Types (OperationType_ID, Description) VALUES (3, 'WITHDRAWAL');
INSERT INTO Operation_Types (OperationType_ID, Description) VALUES (4, 'PAYMENT');