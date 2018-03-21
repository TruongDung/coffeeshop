INSERT INTO ADDRESS (ID, CITY, STATE, COUNTRY, ZIP_CODE)
    VALUES (-1, 'Fairfield', 'Iowa', 'US', '52556');
    INSERT INTO ADDRESS (ID, CITY, STATE, COUNTRY, ZIP_CODE)
    VALUES (-2, 'Fairfield', 'Iowa', 'US', '52556');
INSERT INTO PERSON (ID, FIRST_NAME, LAST_NAME, EMAIL, ADDRESS_ID, PHONE, ENABLED)
    VALUES (-1, 'Admin', 'Admin', 'admin@mum.edu', -1, '+15179189395', 1);

INSERT INTO PERSON (ID, FIRST_NAME, LAST_NAME, EMAIL, ADDRESS_ID, PHONE, ENABLED)
    VALUES (-2, 'Customer', 'Customer', 'customer@mum.edu', -2, '+15179189395', 1);

INSERT INTO ROLE(ID, ROLE) VALUES(-1, 'ROLE_ADMIN');
INSERT INTO ROLE(ID, ROLE) VALUES(-2, 'ROLE_CUSTOMER');
-- pass = 123456
INSERT INTO USER(ID, EMAIL, PASSWORD, ENABLED) VALUES(-1, 'admin@mum.edu', '$2a$04$pQeYdWnoGFRuxc2GZWMiVuA.lQ345CrC8FDc2cTY4FuRnI4C8rGf.', 1);
INSERT INTO USER_ROLE(ID, USER_ID, ROLE_ID) VALUES(-1, -1, -1);

INSERT INTO USER(ID, EMAIL, PASSWORD, ENABLED) VALUES(-2, 'customer@mum.edu', '$2a$04$pQeYdWnoGFRuxc2GZWMiVuA.lQ345CrC8FDc2cTY4FuRnI4C8rGf.', 1);
INSERT INTO USER_ROLE(ID, USER_ID, ROLE_ID) VALUES(-2, -2, -2);

INSERT INTO PRODUCT(ID, NAME, DESCRIPTION, PRICE, PRODUCT_TYPE)
  VALUES (-1, 'Capuchino', 'The variety of discernable tastes, such as chocolate, berry, citrus, caramel, sweet, used in describing coffee. Some become more evident as the coffee cools.', '100.00', 'BREAKFAST');

INSERT INTO PRODUCT(ID, NAME, DESCRIPTION, PRICE, PRODUCT_TYPE)
  VALUES (-2, 'Espresso', 'Deep but not dark, the Espresso roast is to Full City what a fine vintage port is to still wine. Plush body in the foreground with just a trace of supporting acidity and varietal nuances still subtly present.', '112.00', 'BREAKFAST');

INSERT INTO PRODUCT(ID, NAME, DESCRIPTION, PRICE, PRODUCT_TYPE)
  VALUES (-3, 'French Roast', 'At the French Roast stage, varietal flavors have been burned off and are replaced with the smokey power of the roasting process. Intense, spicy, yet light in body.', '52.00', 'BREAKFAST');

INSERT INTO PRODUCT(ID, NAME, DESCRIPTION, PRICE, PRODUCT_TYPE)
  VALUES (-4, 'Full City', 'Most Full City roasts are a deep chestnut brown with little or no oil on the bean surface. This roast results in maximum varietal flavor and aroma.', '57.00', 'BREAKFAST');