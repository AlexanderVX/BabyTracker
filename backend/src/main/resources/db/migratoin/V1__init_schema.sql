-- V1__init_schema.sql
-- Первая миграция: создаём все таблицы проекта

-- Пользователи: кто входит в приложение
CREATE TABLE users (
                       id            BIGSERIAL PRIMARY KEY,              -- автоинкрементный номер
                       email         VARCHAR(255) UNIQUE,                -- уникальная почта (NULL у гостя)
                       password_hash VARCHAR(255),                       -- хэш пароля, НЕ сам пароль!
                       is_guest      BOOLEAN NOT NULL DEFAULT FALSE,     -- гостевой аккаунт?
                       vk_id         VARCHAR(64) UNIQUE,                 -- идентификатор из VK ID
                       created_at    TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Дети: профили, которые ведёт пользователь
CREATE TABLE children (
                          id           BIGSERIAL PRIMARY KEY,
                          user_id      BIGINT NOT NULL REFERENCES users(id), -- чей это ребёнок
                          name         VARCHAR(100) NOT NULL,
                          birth_date   DATE NOT NULL,                        -- дата рождения (без времени)
                          gender       VARCHAR(10) NOT NULL CHECK (gender IN ('MALE','FEMALE')),
                          created_at   TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Прививки: календарь и факты
CREATE TABLE vaccinations (
                              id            BIGSERIAL PRIMARY KEY,
                              child_id      BIGINT NOT NULL REFERENCES children(id),
                              vaccine_name  VARCHAR(200) NOT NULL,               -- «БЦЖ», «АКДС-2» и т.п.
                              scheduled_date DATE,                               -- когда запланирована
                              done_date     DATE,                                -- когда сделана (NULL = не сделана)
                              note          VARCHAR(500),                        -- свободная заметка
                              created_at    TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Замеры роста и веса: по ним строим график
CREATE TABLE growth_records (
                                id         BIGSERIAL PRIMARY KEY,
                                child_id   BIGINT NOT NULL REFERENCES children(id),
                                weight_kg  NUMERIC(5,2),                           -- вес, 2 знака после запятой
                                height_cm  NUMERIC(5,1),                           -- рост
                                measured_at DATE NOT NULL,                         -- когда измерили
                                created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Сон: интервалы от засыпания до пробуждения
CREATE TABLE sleep_records (
                               id         BIGSERIAL PRIMARY KEY,
                               child_id   BIGINT NOT NULL REFERENCES children(id),
                               start_time TIMESTAMPTZ NOT NULL,
                               end_time   TIMESTAMPTZ,                            -- NULL = спит прямо сейчас
                               note       VARCHAR(500),
                               created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Кормления: три типа в одной таблице
CREATE TABLE feedings (
                          id          BIGSERIAL PRIMARY KEY,
                          child_id    BIGINT NOT NULL REFERENCES children(id),
                          feed_type   VARCHAR(20) NOT NULL
                              CHECK (feed_type IN ('BREAST','BOTTLE','SOLID')), -- грудь/бутылочка/прикорм
                          amount_ml   INT,                                   -- для бутылочки
                          duration_min INT,                                  -- для груди
                          food_name   VARCHAR(200),                          -- для прикорма: «кабачок»
                          fed_at      TIMESTAMPTZ NOT NULL,
                          created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Советы: статический справочник, заливается один раз
CREATE TABLE tips (
                      id         BIGSERIAL PRIMARY KEY,
                      age_from   INT NOT NULL,                           -- от скольких месяцев
                      age_to     INT NOT NULL,                           -- до скольких месяцев
                      title      VARCHAR(200) NOT NULL,
                      body       TEXT NOT NULL,
                      created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Напоминания: пользователь сам задаёт время
CREATE TABLE reminders (
                           id          BIGSERIAL PRIMARY KEY,
                           child_id    BIGINT NOT NULL REFERENCES children(id),
                           kind        VARCHAR(20) NOT NULL
                               CHECK (kind IN ('VACCINE','FEEDING','CUSTOM')),
                           title       VARCHAR(200) NOT NULL,
                           remind_at   TIMESTAMPTZ NOT NULL,                  -- когда напомнить
                           is_done     BOOLEAN NOT NULL DEFAULT FALSE,
                           created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Индексы: чтобы поиск по ребёнку не листал всю таблицу
CREATE INDEX idx_children_user   ON children(user_id);
CREATE INDEX idx_vaccinations_child ON vaccinations(child_id);
CREATE INDEX idx_growth_child    ON growth_records(child_id);
CREATE INDEX idx_sleep_child     ON sleep_records(child_id);
CREATE INDEX idx_feedings_child  ON feedings(child_id);
CREATE INDEX idx_reminders_child ON reminders(child_id);
