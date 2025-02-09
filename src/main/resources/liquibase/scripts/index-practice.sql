--Индекс для поиска по имени студента.
-- liquibase formatted sql
-- changeset sfs:1
CREATE INDEX name_index ON student (name);
--Индекс для поиска по названию и цвету факультета.
CREATE INDEX name_color_index ON faculty (name, color);
