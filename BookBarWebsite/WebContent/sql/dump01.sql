--
-- PostgreSQL database dump
--

-- Dumped from database version 11.6
-- Dumped by pg_dump version 11.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: trigger_set_timestamp(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.trigger_set_timestamp() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$;


ALTER FUNCTION public.trigger_set_timestamp() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: Chart; Type: TABLE; Schema: public; Owner: bb
--

CREATE TABLE public."Chart" (
    "UserID" integer NOT NULL,
    "ProductID" integer NOT NULL,
    "Quantity" integer NOT NULL
);


ALTER TABLE public."Chart" OWNER TO bb;

--
-- Name: Menu; Type: TABLE; Schema: public; Owner: bb
--

CREATE TABLE public."Menu" (
    "MenuID" integer NOT NULL,
    "Name" character varying(255) NOT NULL,
    "Prezzo" double precision
);


ALTER TABLE public."Menu" OWNER TO bb;

--
-- Name: MenuProduct; Type: TABLE; Schema: public; Owner: bb
--

CREATE TABLE public."MenuProduct" (
    "MenuID" integer NOT NULL,
    "ProductID" integer NOT NULL,
    "Price" integer NOT NULL
);


ALTER TABLE public."MenuProduct" OWNER TO bb;

--
-- Name: Menu_MenuID_seq; Type: SEQUENCE; Schema: public; Owner: bb
--

CREATE SEQUENCE public."Menu_MenuID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Menu_MenuID_seq" OWNER TO bb;

--
-- Name: Menu_MenuID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bb
--

ALTER SEQUENCE public."Menu_MenuID_seq" OWNED BY public."Menu"."MenuID";


--
-- Name: Order; Type: TABLE; Schema: public; Owner: bb
--

CREATE TABLE public."Order" (
    "UserID" integer NOT NULL,
    "MenuID" integer NOT NULL,
    "Status" character varying(255) NOT NULL,
    "Data" timestamp without time zone DEFAULT now() NOT NULL,
    "OrderID" integer NOT NULL
);


ALTER TABLE public."Order" OWNER TO bb;

--
-- Name: Order_OrderID_seq; Type: SEQUENCE; Schema: public; Owner: bb
--

CREATE SEQUENCE public."Order_OrderID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Order_OrderID_seq" OWNER TO bb;

--
-- Name: Order_OrderID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bb
--

ALTER SEQUENCE public."Order_OrderID_seq" OWNED BY public."Order"."OrderID";


--
-- Name: Product; Type: TABLE; Schema: public; Owner: bb
--

CREATE TABLE public."Product" (
    "ProductID" integer NOT NULL,
    "Name" character varying(255) NOT NULL,
    "Description" character varying(255) NOT NULL
);


ALTER TABLE public."Product" OWNER TO bb;

--
-- Name: Product_ProductID_seq; Type: SEQUENCE; Schema: public; Owner: bb
--

CREATE SEQUENCE public."Product_ProductID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Product_ProductID_seq" OWNER TO bb;

--
-- Name: Product_ProductID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bb
--

ALTER SEQUENCE public."Product_ProductID_seq" OWNED BY public."Product"."ProductID";


--
-- Name: User; Type: TABLE; Schema: public; Owner: bb
--

CREATE TABLE public."User" (
    "UserID" integer NOT NULL,
    "UserType" character varying(255) NOT NULL,
    "FirstName" character varying(255) NOT NULL,
    "Email" character varying(255) NOT NULL,
    "Password" character varying(255) NOT NULL,
    "LastName" character varying(255) NOT NULL
);


ALTER TABLE public."User" OWNER TO bb;

--
-- Name: User_UserID_seq; Type: SEQUENCE; Schema: public; Owner: bb
--

CREATE SEQUENCE public."User_UserID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."User_UserID_seq" OWNER TO bb;

--
-- Name: User_UserID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bb
--

ALTER SEQUENCE public."User_UserID_seq" OWNED BY public."User"."UserID";


--
-- Name: Menu MenuID; Type: DEFAULT; Schema: public; Owner: bb
--

ALTER TABLE ONLY public."Menu" ALTER COLUMN "MenuID" SET DEFAULT nextval('public."Menu_MenuID_seq"'::regclass);


--
-- Name: Order OrderID; Type: DEFAULT; Schema: public; Owner: bb
--

ALTER TABLE ONLY public."Order" ALTER COLUMN "OrderID" SET DEFAULT nextval('public."Order_OrderID_seq"'::regclass);


--
-- Name: Product ProductID; Type: DEFAULT; Schema: public; Owner: bb
--

ALTER TABLE ONLY public."Product" ALTER COLUMN "ProductID" SET DEFAULT nextval('public."Product_ProductID_seq"'::regclass);


--
-- Name: User UserID; Type: DEFAULT; Schema: public; Owner: bb
--

ALTER TABLE ONLY public."User" ALTER COLUMN "UserID" SET DEFAULT nextval('public."User_UserID_seq"'::regclass);


--
-- Data for Name: Chart; Type: TABLE DATA; Schema: public; Owner: bb
--

COPY public."Chart" ("UserID", "ProductID", "Quantity") FROM stdin;
6	1	1
6	2	3
7	1	2
\.


--
-- Data for Name: Menu; Type: TABLE DATA; Schema: public; Owner: bb
--

COPY public."Menu" ("MenuID", "Name", "Prezzo") FROM stdin;
1	Latte/Cornetto	1.98999999999999999
2	Caffe/Cornetto	1.48999999999999999
18	Spremuta/Cornetto/Caffe	3
20	Fame!!!	7.99000000000000021
\.


--
-- Data for Name: MenuProduct; Type: TABLE DATA; Schema: public; Owner: bb
--

COPY public."MenuProduct" ("MenuID", "ProductID", "Price") FROM stdin;
2	1	0
2	3	0
1	2	0
1	3	0
18	1	0
18	3	0
18	11	0
20	1	0
20	2	0
20	3	0
20	11	0
20	12	0
\.


--
-- Data for Name: Order; Type: TABLE DATA; Schema: public; Owner: bb
--

COPY public."Order" ("UserID", "MenuID", "Status", "Data", "OrderID") FROM stdin;
11	3	Consegnato	2020-01-21 21:16:56.207218	6
7	2	Lavorazione	2020-01-21 21:16:42.332491	5
6	3	Lavorazione	2020-01-21 20:54:15.755274	4
11	2	Lavorazione	2020-01-20 20:51:02.358912	2
11	1	Nuovo	2020-01-23 19:13:51.001422	7
11	1	Nuovo	2020-01-23 23:11:36.978767	8
11	1	Nuovo	2020-01-23 23:40:13.193468	9
11	2	Nuovo	2020-01-23 23:40:13.240178	10
11	2	Lavorazione	2020-01-23 23:44:49.376131	12
11	1	Lavorazione	2020-01-23 23:42:09.740106	11
11	1	Nuovo	2020-01-24 07:53:19.930615	13
11	17	Nuovo	2020-01-24 08:15:44.537949	14
11	1	Nuovo	2020-01-24 08:15:44.56012	15
11	2	Consegnato	2020-01-24 08:15:44.596234	16
6	1	Lavorazione	2020-01-18 16:33:21.550515	1
11	18	Lavorazione	2020-01-24 09:27:31.035594	17
11	1	Nuovo	2020-01-24 09:58:29.740373	18
11	18	Nuovo	2020-01-24 09:58:29.767191	19
11	2	Nuovo	2020-01-24 09:58:29.796017	20
\.


--
-- Data for Name: Product; Type: TABLE DATA; Schema: public; Owner: bb
--

COPY public."Product" ("ProductID", "Name", "Description") FROM stdin;
2	Latte	Bicchiere di latte
3	Cornetto	Cornetto vari gusti
1	Caffe	Espresso senza zucchero
11	Spremuta	Succo di arancia spremuto al momeno
12	Cioccolata	Cioccolata calda in tazza
\.


--
-- Data for Name: User; Type: TABLE DATA; Schema: public; Owner: bb
--

COPY public."User" ("UserID", "UserType", "FirstName", "Email", "Password", "LastName") FROM stdin;
11	user	Paola-Utente	p@p.it	p	asd
12	admin	Test1	p@t.it	p	Test1
10	admin	Paola-A	p@g.it	p	Guarasci
\.


--
-- Name: Menu_MenuID_seq; Type: SEQUENCE SET; Schema: public; Owner: bb
--

SELECT pg_catalog.setval('public."Menu_MenuID_seq"', 20, true);


--
-- Name: Order_OrderID_seq; Type: SEQUENCE SET; Schema: public; Owner: bb
--

SELECT pg_catalog.setval('public."Order_OrderID_seq"', 20, true);


--
-- Name: Product_ProductID_seq; Type: SEQUENCE SET; Schema: public; Owner: bb
--

SELECT pg_catalog.setval('public."Product_ProductID_seq"', 12, true);


--
-- Name: User_UserID_seq; Type: SEQUENCE SET; Schema: public; Owner: bb
--

SELECT pg_catalog.setval('public."User_UserID_seq"', 12, true);


--
-- Name: Chart Chart_pkey; Type: CONSTRAINT; Schema: public; Owner: bb
--

ALTER TABLE ONLY public."Chart"
    ADD CONSTRAINT "Chart_pkey" PRIMARY KEY ("UserID", "ProductID");


--
-- Name: MenuProduct MenuProduct_pkey; Type: CONSTRAINT; Schema: public; Owner: bb
--

ALTER TABLE ONLY public."MenuProduct"
    ADD CONSTRAINT "MenuProduct_pkey" PRIMARY KEY ("MenuID", "ProductID");


--
-- Name: Menu Menu_pkey; Type: CONSTRAINT; Schema: public; Owner: bb
--

ALTER TABLE ONLY public."Menu"
    ADD CONSTRAINT "Menu_pkey" PRIMARY KEY ("MenuID");


--
-- Name: Order Order_pkey; Type: CONSTRAINT; Schema: public; Owner: bb
--

ALTER TABLE ONLY public."Order"
    ADD CONSTRAINT "Order_pkey" PRIMARY KEY ("OrderID");


--
-- Name: Product Product_pkey; Type: CONSTRAINT; Schema: public; Owner: bb
--

ALTER TABLE ONLY public."Product"
    ADD CONSTRAINT "Product_pkey" PRIMARY KEY ("ProductID");


--
-- Name: User User_pkey; Type: CONSTRAINT; Schema: public; Owner: bb
--

ALTER TABLE ONLY public."User"
    ADD CONSTRAINT "User_pkey" PRIMARY KEY ("UserID");


--
-- PostgreSQL database dump complete
--

