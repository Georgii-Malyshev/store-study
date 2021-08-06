--
-- PostgreSQL database dump
--

-- Dumped from database version 13.3 (Ubuntu 13.3-1.pgdg20.04+1)
-- Dumped by pg_dump version 13.3 (Ubuntu 13.3-1.pgdg20.04+1)

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: storedev
--

CREATE TABLE public.admin (
    id integer NOT NULL
);


ALTER TABLE public.admin OWNER TO storedev;

--
-- Name: appuser; Type: TABLE; Schema: public; Owner: storedev
--

CREATE TABLE public.appuser (
    id integer NOT NULL,
    email character varying(255),
    password character varying(255)
);


ALTER TABLE public.appuser OWNER TO storedev;

--
-- Name: appuser_id_seq; Type: SEQUENCE; Schema: public; Owner: storedev
--

CREATE SEQUENCE public.appuser_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.appuser_id_seq OWNER TO storedev;

--
-- Name: cart; Type: TABLE; Schema: public; Owner: storedev
--

CREATE TABLE public.cart (
    customer_id integer NOT NULL
);


ALTER TABLE public.cart OWNER TO storedev;

--
-- Name: cart_item; Type: TABLE; Schema: public; Owner: storedev
--

CREATE TABLE public.cart_item (
    id integer NOT NULL,
    quantity integer NOT NULL,
    cart_id integer,
    product_id integer
);


ALTER TABLE public.cart_item OWNER TO storedev;

--
-- Name: cart_item_id_seq; Type: SEQUENCE; Schema: public; Owner: storedev
--

CREATE SEQUENCE public.cart_item_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cart_item_id_seq OWNER TO storedev;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: storedev
--

CREATE TABLE public.customer (
    firstname character varying(255),
    lastname character varying(255),
    mobilephonenumber character varying(255),
    shippingaddress character varying(255),
    id integer NOT NULL
);


ALTER TABLE public.customer OWNER TO storedev;

--
-- Name: product; Type: TABLE; Schema: public; Owner: storedev
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying(255),
    price integer NOT NULL,
    product_category_id integer
);


ALTER TABLE public.product OWNER TO storedev;

--
-- Name: product_catalog; Type: TABLE; Schema: public; Owner: storedev
--

CREATE TABLE public.product_catalog (
    id integer NOT NULL
);


ALTER TABLE public.product_catalog OWNER TO storedev;

--
-- Name: product_catalog_id_seq; Type: SEQUENCE; Schema: public; Owner: storedev
--

CREATE SEQUENCE public.product_catalog_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_catalog_id_seq OWNER TO storedev;

--
-- Name: product_category; Type: TABLE; Schema: public; Owner: storedev
--

CREATE TABLE public.product_category (
    id integer NOT NULL,
    name character varying(255),
    product_catalog_id integer
);


ALTER TABLE public.product_category OWNER TO storedev;

--
-- Name: product_category_id_seq; Type: SEQUENCE; Schema: public; Owner: storedev
--

CREATE SEQUENCE public.product_category_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_category_id_seq OWNER TO storedev;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: storedev
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO storedev;

--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: storedev
--

COPY public.admin (id) FROM stdin;
\.


--
-- Data for Name: appuser; Type: TABLE DATA; Schema: public; Owner: storedev
--

COPY public.appuser (id, email, password) FROM stdin;
902	customer1	1
952	customer2	2
1002	customer3	3
1052	customer4	4
1102	customer21	21
1152	customer22	22
1202	customer23	23
1502	customer30	30
1552	customer31	31
1602	customer32	32
1652	customer33	33
1702	customer35	35
1752	customer36	36
1802	customer37	37
1852	customer38	38
1902	customer39	39
1952	customer40	40
2002	customer41	41
\.


--
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: storedev
--

COPY public.cart (customer_id) FROM stdin;
\.


--
-- Data for Name: cart_item; Type: TABLE DATA; Schema: public; Owner: storedev
--

COPY public.cart_item (id, quantity, cart_id, product_id) FROM stdin;
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: storedev
--

COPY public.customer (firstname, lastname, mobilephonenumber, shippingaddress, id) FROM stdin;
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: storedev
--

COPY public.product (id, name, price, product_category_id) FROM stdin;
1	productname1category1	10	1
2	productname2category1	20	1
3	productname3category2	30	2
4	productname4category3	40	3
\.


--
-- Data for Name: product_catalog; Type: TABLE DATA; Schema: public; Owner: storedev
--

COPY public.product_catalog (id) FROM stdin;
1
\.


--
-- Data for Name: product_category; Type: TABLE DATA; Schema: public; Owner: storedev
--

COPY public.product_category (id, name, product_catalog_id) FROM stdin;
1	productcategory1	1
2	productcategory2	1
3	productcategory3	1
\.


--
-- Name: appuser_id_seq; Type: SEQUENCE SET; Schema: public; Owner: storedev
--

SELECT pg_catalog.setval('public.appuser_id_seq', 2051, true);


--
-- Name: cart_item_id_seq; Type: SEQUENCE SET; Schema: public; Owner: storedev
--

SELECT pg_catalog.setval('public.cart_item_id_seq', 851, true);


--
-- Name: product_catalog_id_seq; Type: SEQUENCE SET; Schema: public; Owner: storedev
--

SELECT pg_catalog.setval('public.product_catalog_id_seq', 1, false);


--
-- Name: product_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: storedev
--

SELECT pg_catalog.setval('public.product_category_id_seq', 1, false);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: storedev
--

SELECT pg_catalog.setval('public.product_id_seq', 1, false);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (id);


--
-- Name: appuser appuser_pkey; Type: CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.appuser
    ADD CONSTRAINT appuser_pkey PRIMARY KEY (id);


--
-- Name: cart_item cart_item_pkey; Type: CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT cart_item_pkey PRIMARY KEY (id);


--
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (customer_id);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: product_catalog product_catalog_pkey; Type: CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.product_catalog
    ADD CONSTRAINT product_catalog_pkey PRIMARY KEY (id);


--
-- Name: product_category product_category_pkey; Type: CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT product_category_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: cart_item fk1uobyhgl1wvgt1jpccia8xxs3; Type: FK CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT fk1uobyhgl1wvgt1jpccia8xxs3 FOREIGN KEY (cart_id) REFERENCES public.cart(customer_id) ON DELETE CASCADE;


--
-- Name: product fkcwclrqu392y86y0pmyrsi649r; Type: FK CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT fkcwclrqu392y86y0pmyrsi649r FOREIGN KEY (product_category_id) REFERENCES public.product_category(id);


--
-- Name: cart fkdebwvad6pp1ekiqy5jtixqbaj; Type: FK CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fkdebwvad6pp1ekiqy5jtixqbaj FOREIGN KEY (customer_id) REFERENCES public.customer(id) ON DELETE CASCADE;


--
-- Name: product_category fkeh51g5y2fptvmy8iumcoqb44s; Type: FK CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.product_category
    ADD CONSTRAINT fkeh51g5y2fptvmy8iumcoqb44s FOREIGN KEY (product_catalog_id) REFERENCES public.product_catalog(id);


--
-- Name: admin fkguo1boyanoetyg7yf77wgoms6; Type: FK CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT fkguo1boyanoetyg7yf77wgoms6 FOREIGN KEY (id) REFERENCES public.appuser(id);


--
-- Name: customer fkj85cgwox9mtjdur0ijved6kvn; Type: FK CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT fkj85cgwox9mtjdur0ijved6kvn FOREIGN KEY (id) REFERENCES public.appuser(id) ON DELETE CASCADE;


--
-- Name: cart_item fkjcyd5wv4igqnw413rgxbfu4nv; Type: FK CONSTRAINT; Schema: public; Owner: storedev
--

ALTER TABLE ONLY public.cart_item
    ADD CONSTRAINT fkjcyd5wv4igqnw413rgxbfu4nv FOREIGN KEY (product_id) REFERENCES public.product(id);


--
-- PostgreSQL database dump complete
--

