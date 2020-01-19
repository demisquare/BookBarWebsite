CREATE DATABASE BookBar;

CREATE TABLE public."User"
(
    "UserID" serial NOT NULL,
    "UserType" character varying(255) NOT NULL,
    "FirstName" character varying(255) NOT NULL,
    "LastName" character varying(255) NOT NULL,
    "Email" character varying(255) NOT NULL,
    "Password" character varying(255) NOT NULL,
    PRIMARY KEY ("UserID")
)
WITH (
    OIDS = FALSE
);

CREATE TABLE public."Product"
(
    "ProductID" serial NOT NULL,
    "Name" character varying(255) NOT NULL,
    "Description" character varying(255) NOT NULL,
    PRIMARY KEY ("ProductID")
) WITH (
    OIDS = FALSE
);

CREATE TABLE public."Menu" (
    "MenuID" serial,
    "Name" character varying(255) NOT NULL,
    PRIMARY KEY ("MenuID")
)
WITH (
    OIDS = FALSE
);


CREATE TABLE public."Order" (
    "UserID" integer NOT NULL,
    "MenuID" integer NOT NULL,
    
    "Status" char varying(255) NOT NULL,
    "Data" timestamp TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    PRIMARY KEY ("MenuID", "UserID"),

    CONSTRAINT "fkMenu" FOREIGN KEY ("MenuID")
    REFERENCES public."Menu" ("MenuID") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID,

    CONSTRAINT "fkUser" FOREIGN KEY ("UserID")
    REFERENCES public."User" ("UserID") MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID
)
WITH (
    OIDS = FALSE
);

CREATE TABLE public."ProductOrder" (
    "ProductID" integer NOT NULL,
    "OrderID" integer NOT NULL,
    "Quantity" integer NOT NULL,

    PRIMARY KEY ("ProductID", "OrderID"),

    CONSTRAINT "fkProduct" FOREIGN KEY ("ProductID")
    REFERENCES public."Product" ("ProductID") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

    CONSTRAINT "fkOrder" FOREIGN KEY ("OrderID")
    REFERENCES public."Order" ("OrderID") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
);

CREATE TABLE public."Chart" (
    "UserID" int NOT NULL,
    "ProductID" int NOT NULL,
    "Quantity" int NOT NULL,

    PRIMARY KEY ("UserID", "ProductID")

    CONSTRAINT "fkUser" FOREIGN KEY ("UserID")
    REFERENCES public."User" ("UserID") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

    CONSTRAINT "fkProduct" FOREIGN KEY ("ProductID")
    REFERENCES public."Product" ("ProductID") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)WITH (
    OIDS = FALSE
);

CREATE TABLE public."MenuProduct" (
    "MenuID" int NOT NULL,
    "ProductID" int NOT NULL,
    "Price" int NOT NULL,

    PRIMARY KEY ("MenuID", "ProductID")

    CONSTRAINT "fkMenu" FOREIGN KEY ("MenuID")
    REFERENCES public."Menu" ("MenuID") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,

    CONSTRAINT "fkProduct" FOREIGN KEY ("ProductID")
    REFERENCES public."Product" ("ProductID") MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH (
    OIDS = FALSE
);


ALTER TABLE public."MenuProduct" OWNER to bb;
ALTER TABLE public."Chart" OWNER to bb;
ALTER TABLE public."ProductOrder" OWNER to bb;
ALTER TABLE public."Order" OWNER to bb;
ALTER TABLE public."Menu" OWNER to bb;
ALTER TABLE public."Product" OWNER to bb;
ALTER TABLE public."User" OWNER to bb;




CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;