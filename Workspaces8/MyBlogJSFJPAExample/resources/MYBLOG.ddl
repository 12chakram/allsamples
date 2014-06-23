
    create table "MYBLOG"."POST"(
        "ID" INTEGER not null generated always as identity,
       "TITLE" VARCHAR(128) not null,
       "CONTENT" CLOB,
       "POSTTIME" TIMESTAMP,
        constraint "SQL070512055014130" primary key ("ID")
    );

    create unique index "SQL0705120