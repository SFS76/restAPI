--Возраст студента не может быть меньше 16 лет.
ALTER TABLE student ADD CONSTRAINT age_constraint CHECK (age >= 16);

--Имена студентов должны быть уникальными и не равны нулю.
ALTER TABLE student add CONSTRAINT unuq_name UNIQUE (name);
ALTER TABLE student alter column name set not null;

--Пара “значение названия” - “цвет факультета” должна быть уникальной.
ALTER TABLE faculty add CONSTRAINT unuq UNIQUE (name, color);

--При создании студента без возраста ему автоматически должно присваиваться 20 лет.
ALTER TABLE student alter column age set default 20;