CREATE TABLE person (
    person_id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    full_name varchar(100) NOT NULL,
    date_of_birth int4 NOT NULL,
    CONSTRAINT person_age_check CHECK ((date_of_birth > 0)),
    CONSTRAINT person_pkey PRIMARY KEY (person_id),
    CONSTRAINT unique_constrain UNIQUE (full_name)
);

CREATE TABLE book (
    book_id int4 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "name" varchar(150) NOT NULL,
    autor varchar(100) NOT NULL,
    print_date int4 NOT NULL,
    person_id int4 NULL,
    CONSTRAINT book_age_check CHECK ((print_date > 0)),
    CONSTRAINT book_pkey PRIMARY KEY (book_id),
    CONSTRAINT constraint_first FOREIGN KEY (person_id) REFERENCES public.person(person_id) ON DELETE SET NULL
);