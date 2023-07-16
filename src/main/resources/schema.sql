CREATE TABLE identities (
  id IDENTITY NOT NULL PRIMARY KEY,
  identity_id UUID NOT NULL,
  pesel VARCHAR(11) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  surname VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);

CREATE TABLE accounts (
  account_number UUID NOT NULL PRIMARY KEY,
  identity_id UUID NOT NULL,
  trader_number VARCHAR(15) NOT NULL
);

CREATE TABLE transactions (
  transaction_number UUID NOT NULL PRIMARY KEY,
  account_number UUID NOT NULL,
  transaction_type VARCHAR(255) NOT NULL,
  fund_value DECIMAL(19, 2) NOT NULL,
  fund_currency VARCHAR(3) NOT NULL,
  transaction_date TIMESTAMP NOT NULL,
  CONSTRAINT fk_transaction_account_number FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);

CREATE TABLE wallets (
  wallet_id UUID NOT NULL PRIMARY KEY,
  account_number UUID NOT NULL,
  fund_value DECIMAL(19, 2) NOT NULL,
  fund_currency VARCHAR(3) NOT NULL,
  CONSTRAINT fk_wallet_account_number FOREIGN KEY (account_number) REFERENCES accounts(account_number)
);

CREATE TABLE quotes (
    quote_id UUID NOT NULL PRIMARY KEY,
    requester_identity_id UUID NOT NULL,
    expiration_date TIMESTAMP NOT NULL,
    best_exchange_rate_currency_to_sell VARCHAR(3) NOT NULL,
    best_exchange_rate_currency_to_buy VARCHAR(3) NOT NULL,
    best_exchange_rate DECIMAL(15, 2) NOT NULL,
    to_exchange_value DECIMAL(15, 2) NOT NULL,
    to_exchange_currency VARCHAR(3) NOT NULL,
    exchanged_value DECIMAL(15, 2) NOT NULL,
    exchanged_currency VARCHAR(3) NOT NULL,
    status VARCHAR(255) NOT NULL
);

CREATE TABLE currency_pairs (
    currency_pair_id UUID NOT NULL PRIMARY KEY,
    base_currency VARCHAR(3) NOT NULL,
    target_currency VARCHAR(3) NOT NULL,
    base_rate DECIMAL(15, 2) NOT NULL,
    adjusted_rate DECIMAL(15, 2),
    status VARCHAR(50) NOT NULL
);
CREATE TABLE negotiations (
    negotiation_id UUID NOT NULL PRIMARY KEY,
    negotiator_identity_id UUID NOT NULL,
    operator_id UUID,
    expiration_date TIMESTAMP,
    target_currency VARCHAR(3) NOT NULL,
    base_currency VARCHAR(3) NOT NULL,
    proposed_exchange_amount DECIMAL(15, 2) NOT NULL,
    proposed_exchange_currency VARCHAR(3) NOT NULL,
    propose_exchange_rate DECIMAL(15, 2) NOT NULL,
    base_exchange_rate DECIMAL(15, 2) NOT NULL,
    difference_in_percentage DECIMAL(15, 2) NOT NULL,
    status varchar(255)
);

CREATE TABLE risk_assessments
(
    risk_assessment_number UUID NOT NULL PRIMARY KEY,
    negotiator_identity_id UUID NOT NULL,
    risk_level VARCHAR(10) NOT NULL
);

CREATE TABLE risk_lines
(
    risk_line_id UUID NOT NULL PRIMARY KEY,
    negotiation_id UUID NOT NULL,
    risk_negotiation_value_amount DECIMAL(15, 2) NOT NULL,
    risk_negotiation_value_currency CHAR(3) NOT NULL,
    risk_assessment_number UUID NOT NULL,
    CONSTRAINT fk_risk_assessment_number FOREIGN KEY (risk_assessment_number) REFERENCES risk_assessments(risk_assessment_number)
);