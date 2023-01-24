CREATE TABLE customer
(
    id_customer     serial       NOT NULL,
    last_name       varchar(30)  NOT NULL,
    first_name      varchar(30)  NOT NULL,
    middle_name     varchar(30)  NOT NULL,
    phone_number    varchar(30)  NOT NULL unique,
    email           varchar(30)  NOT NULL unique,
    CONSTRAINT user_pk PRIMARY KEY (id_customer)
);

CREATE TABLE request
(
    id_request      serial       NOT NULL,
    date            timestamp DEFAULT CURRENT_TIMESTAMP,
    id_car          int          NOT NULL,
    id_customer     int          NOT NULL,
    CONSTRAINT request_pk PRIMARY KEY (id_request)
);

ALTER TABLE request
    ADD CONSTRAINT request_fk0 FOREIGN KEY (id_customer) REFERENCES customer (id_customer);

CREATE TABLE car
(
    id_car          serial       NOT NULL,
    brand           varchar(30)  NOT NULL,
    model           varchar(30)  NOT NULL,
    features        varchar(150)  NOT NULL,
    year            int          NOT NULL,
    price           int          NOT NULL,
    quantity        int          NOT NULL,
    id_producer     int          NOT NULL,
    CONSTRAINT car_pk PRIMARY KEY (id_car)
);

ALTER TABLE request
    ADD CONSTRAINT request_fk1 FOREIGN KEY (id_car) REFERENCES car (id_car);

CREATE TABLE producer
(
    id_producer  serial       NOT NULL,
    name         varchar(50)  NOT NULL,
    address      varchar(100)  NOT NULL,
    phone_number varchar(30)  NOT NULL,
    CONSTRAINT producer_pk PRIMARY KEY (id_producer)
);

ALTER TABLE car
    ADD CONSTRAINT car_fk0 FOREIGN KEY (id_producer) REFERENCES producer (id_producer);

CREATE TABLE employee
(
    id_employee  serial       NOT NULL,
    last_name       varchar(30)  NOT NULL,
    first_name      varchar(30)  NOT NULL,
    middle_name     varchar(30)  NOT NULL,
    phone_number    varchar(30)  NOT NULL unique,
    CONSTRAINT employee_pk PRIMARY KEY (id_employee)
);

CREATE TABLE delivery
(
    id_delivery     serial       NOT NULL,
    delivery_method  varchar(40) NOT NULL,
    date_of_delivery timestamp   NOT NULL,
    id_request      int          NOT NULL,
    CONSTRAINT delivery_pk PRIMARY KEY (id_delivery)
);

ALTER TABLE delivery
    ADD CONSTRAINT delivery_fk0 FOREIGN KEY (id_request) REFERENCES request (id_request);


INSERT INTO customer (last_name, first_name, middle_name, phone_number, email)
VALUES ('Комов', 'Максим', 'Андреевич', '89204510151', 'komovmax@mail.ru');

INSERT INTO customer (last_name, first_name, middle_name, phone_number, email)
VALUES ('Котляров', 'Сергей', 'Александрович', '89009518560', 'kott7@yandex.ru');

INSERT INTO customer (last_name, first_name, middle_name, phone_number, email)
VALUES ('Бабкин', 'Александр', 'Владиславович', '89204515111', 'babkinsolik@mail.ru');

INSERT INTO customer (last_name, first_name, middle_name, phone_number, email)
VALUES ('Иванов', 'Иван', 'Иванович', '89105648649', 'ivanov@gmail.com');

INSERT INTO customer (last_name, first_name, middle_name, phone_number, email)
VALUES ('Попов', 'Евгений', 'Николаевич', '89205643460', 'popov.evg@yandex.ru');


INSERT INTO producer (name, address, phone_number)
VALUES ('MazdaClubRU', 'Москва, ул. Орджоникидзе, д. 16а',  '+79104567333');

INSERT INTO producer (name, address, phone_number)
VALUES ('Авто-альянс', 'Владивосток, ул. Шишкова, офис 1',  '+79004537766');

INSERT INTO producer (name, address, phone_number)
VALUES ('FordMotorsRU', 'Екатеринбург, ул. Пушкина, д. 1',  '+79104567811');

INSERT INTO producer (name, address, phone_number)
VALUES ('ООО "Автомир"', 'Москва, Ленинградское ш., вл. 18',  '89495956871');


INSERT INTO car (brand, model, features, year, price, quantity, id_producer)
VALUES ('Mazda', '3', 'Объем двигателя - 1.6, 105 л.с., АКПП, передний привод, цвет: коричневый', 2012, 500000, 3, 1);

INSERT INTO car (brand, model, features, year, price, quantity, id_producer)
VALUES ('Mazda', 'CX-7', 'Объем двигателя - 2.5, 163 л.с., АКПП, передний привод, цвет: серый', 2011, 1000000, 6, 1);

INSERT INTO car (brand, model, features, year, price, quantity, id_producer)
VALUES ('Ford', 'Focus 2', 'Объем двигателя - 2.0, 124 л.с., МКПП, передний привод, цвет: голубой', 2010, 600000, 4, 3);

INSERT INTO car (brand, model, features, year, price, quantity, id_producer)
VALUES ('Hyundai', 'Solaris', 'Объем двигателя - 1.4, 107 л.с., МКПП, передний привод, цвет: черный', 2011, 600000, 2, 2);


INSERT INTO employee (last_name, first_name, middle_name, phone_number)
VALUES ('Петров', 'Петр', 'Александрович', '89204204141');

INSERT INTO employee (last_name, first_name, middle_name, phone_number)
VALUES ('Кудрин', 'Николай', 'Андреевич', '89304514221');

INSERT INTO employee (last_name, first_name, middle_name, phone_number)
VALUES ('Кочергин', 'Денис', 'Вячеславович', '89006115691');
