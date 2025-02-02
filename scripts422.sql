//Описание структуры: у каждого человека есть машина. Причем несколько человек могут пользоваться одной машиной.
//У каждого человека есть имя, возраст и признак того, что у него есть права (или их нет).
//У каждой машины есть марка, модель и стоимость. Также не забудьте добавить таблицам первичные ключи и связать их.

CREATE TABLE cars (
    id INTEGER PRIMARY KEY,
    mark VARCHAR (255) NOT NULL,
    model VARCHAR (255),
    price INTEGER CHECK (price > 0));
CREATE TABLE users (
    id INTEGER PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    age INTEGER CHECK (age > 0),
    isDriver BOOLEAN,
    car_id INTEGER REFERENCES cars (id));
