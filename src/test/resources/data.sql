INSERT INTO customer (id, name) VALUES (123456789,'Cristiano Ronaldo');
INSERT INTO account (id, customer_id, balance) VALUES (100,123456789,50.00);
INSERT INTO transaction (id, account_id, amount, date_time) VALUES (999,100,50.00,CURRENT_TIMESTAMP);