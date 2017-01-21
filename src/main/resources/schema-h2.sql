CREATE TABLE SIGNUP
(
    ID NUMBER(100) auto_increment,
    ACCOUNT_ID NUMBER(100),
    address VARCHAR2(50),
    name VARCHAR2(50)
);
CREATE TABLE ACCOUNT
(
    ID NUMBER(100) auto_increment,
    username VARCHAR2(50),
    password VARCHAR2(200)
);