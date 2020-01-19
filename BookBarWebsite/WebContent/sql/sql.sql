CREATE DATABASE BookBar;

CREATE TABLE User (
    UserID int NOT NULL PRIMARY KEY,
    UserType boolean NOT NULL,

    LastName varchar(255) NOT NULL,
    FirstName varchar(255) NOT NULL,

    Email varchar(255) NOT NULL,
    Password varchar(255) NOT NULL
);

CREATE TABLE Product (
    ProductID int NOT NULL PRIMARY KEY,

    Name varchar(255) NOT NULL,
    Description varchar(255) NOT NULL
);

CREATE TABLE Menu (
    MenuID int NOT NULL PRIMARY KEY,

    Name varchar(255) NOT NULL
);

CREATE TABLE Order (
    OrderID int NOT NULL,
    UserID int NOT NULL,

    Status int NOT NULL,
    Data timestamp NOT NULL,

    PRIMARY KEY (OrderID, UserID),

    CONSTRAINT fkOrder FOREIGN KEY (OrderID)
    REFERENCES Order (OrderID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

    CONSTRAINT fkUser FOREIGN KEY (UserID)
    REFERENCES User (UserID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE ProductOrder (
    ProductID int NOT NULL,
    OrderID int NOT NULL,

    Quantity int NOT NULL,

    PRIMARY KEY (OrderID, UserID),

    CONSTRAINT fkProduct FOREIGN KEY (ProductID)
    REFERENCES Product (ProductID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

    CONSTRAINT fkOrder FOREIGN KEY (OrderID)
    REFERENCES Order (OrderID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE Chart (
    UserID int NOT NULL,
    ProductID int NOT NULL,

    Quantity int NOT NULL,

    PRIMARY KEY (UserID, ProductID),

    CONSTRAINT fkUser FOREIGN KEY (UserID)
    REFERENCES User (UserID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

    CONSTRAINT fkProduct FOREIGN KEY (ProductID)
    REFERENCES Product (ProductID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE MenuProduct (
    MenuID int NOT NULL,
    ProductID int NOT NULL,

    Price int NOT NULL,

    PRIMARY KEY (UserID, ProductID),

    CONSTRAINT fkMenu FOREIGN KEY (MenuID)
    REFERENCES Menu (MenuID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

    CONSTRAINT fkProduct FOREIGN KEY (ProductID)
    REFERENCES Product (ProductID) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);