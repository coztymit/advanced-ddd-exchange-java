INSERT INTO identities (identity_id, pesel, first_name, surname, email) VALUES
  ('123e4567-e89b-12d3-a456-426655440000', '85070465418', 'Jan', 'Kowalski', 'jan.kowalski@gmail.com'),
  ('123e4567-e89b-12d3-a456-426655440001', '74062373642', 'Piotr', 'Nowak', 'piotr.nowak@gmail.com'),
  ('123e4567-e89b-12d3-a456-426655440002', '84032785221', 'Adam', 'Wiśniewski', 'adam.wisniewski@gmail.com');

INSERT INTO accounts (account_number, identity_id, trader_number)
VALUES
('9c3627e7-d5be-4f7e-8e52-4b7fca97d0a0', '123e4567-e89b-12d3-a456-426655440000', 'ABC-01-2023-123');

INSERT INTO transactions (transaction_number, account_number, transaction_type, fund_value, fund_currency, transaction_date)
VALUES
('f49c5c94-3b0e-467c-94c1-2b8356db8cfa', '9c3627e7-d5be-4f7e-8e52-4b7fca97d0a0', 'CARD', 100.00, 'PLN', CURRENT_TIMESTAMP);

INSERT INTO wallets (wallet_id, account_number, fund_value, fund_currency)
VALUES
('ea1f5ded-9a48-4083-a7b7-3b526dbf1768', '9c3627e7-d5be-4f7e-8e52-4b7fca97d0a0', 100.00, 'PLN');


INSERT INTO accounts (account_number, identity_id, trader_number)
VALUES
('3f0f3b8e-6d75-4f61-9c8a-4a64f9e6b267', '123e4567-e89b-12d3-a456-426655440001', 'XYZ-01-2023-123');

INSERT INTO transactions (transaction_number, account_number, transaction_type, fund_value, fund_currency, transaction_date)
VALUES
('7f7ccae0-8ad9-4754-a361-b37a87c2d0c8', '3f0f3b8e-6d75-4f61-9c8a-4a64f9e6b267', 'CARD', 500.00, 'PLN', CURRENT_TIMESTAMP);

INSERT INTO wallets (wallet_id, account_number, fund_value, fund_currency)
VALUES
('4cd50a81-4bb4-4e2a-bf62-58c8ed51307b', '3f0f3b8e-6d75-4f61-9c8a-4a64f9e6b267', 500.00, 'PLN');
