CREATE TABLE identities (
  id IDENTITY NOT NULL PRIMARY KEY,
  identity_id UUID NOT NULL,
  pesel VARCHAR(11) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);
CREATE TABLE accounts (
  account_id UUID NOT NULL PRIMARY KEY,
  identity_id UUID NOT NULL,
  trader_number VARCHAR(15) NOT NULL
);
CREATE TABLE transactions (
  transaction_number UUID NOT NULL PRIMARY KEY,
  account_id UUID NOT NULL,
  transaction_type VARCHAR(255) NOT NULL,
  fund_value DECIMAL(19, 2) NOT NULL,
  fund_currency VARCHAR(3) NOT NULL,
  transaction_date TIMESTAMP NOT NULL,
  CONSTRAINT fk_transaction_account_id FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);
CREATE TABLE wallets (
  wallet_id UUID NOT NULL PRIMARY KEY,
  account_id UUID NOT NULL,
  fund_value DECIMAL(19, 2) NOT NULL,
  fund_currency VARCHAR(3) NOT NULL,
  CONSTRAINT fk_wallet_account_id FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);