CREATE TABLE "plan"(
	"id" SERIAL NOT NULL,
	"type" VARCHAR(255) NOT NULL,
	"price" FLOAT NOT NULL,
	"chanel" INT NOT NULL,
	CONSTRAINT pk_plan_id PRIMARY KEY("id")
);
	
CREATE TABLE "client"(
	"id" SERIAL NOT NULL,
	"full_name" VARCHAR(255)NOT NULL,
	"phone_number" VARCHAR(20) NOT NULL UNIQUE,
	"curp" VARCHAR(150) NOT NULL UNIQUE,
	CONSTRAINT pk_client_id PRIMARY KEY("id")
);

CREATE TABLE "contract"(
	"id" SERIAL NOT NULL,
	"plan_id" INT NOT NULL,
	"client_id" INT NOT NULL,
	"address" TEXT NOT NULL,
	"date" DATE NOT NULL,
	CONSTRAINT pk_contract_id PRIMARY KEY("id"),
	CONSTRAINT fk_plan_contract_id FOREIGN KEY ("plan_id") REFERENCES "plan"("id"),
	CONSTRAINT fk_client_contract_id FOREIGN KEY ("client_id") REFERENCES "client"("id") 
);

INSERT INTO "plan"("type", "price", "chanel") 
	VALUES('BASIC', 200.00,50);
INSERT INTO "client"("full_name", "phone_number", "curp") 
	VALUES('Edwin Garcia Quiterio', '741-107-3337','CURP12345');
INSERT INTO "contract"("plan_id", "client_id", "address", "date") 
	VALUES(1, 1, 'Los Chegües Guerrero México', '2023-10-15');

SELECT
    c.id AS contract_id,
    c.address,
    c.date,
    p.type AS plan_type,
    p.price AS plan_price,
    p.chanel AS plan_chanel,
    cl.full_name AS client_name,
    cl.phone_number AS client_phone,
    cl.curp AS client_curp
FROM contract AS c
INNER JOIN "plan" AS p ON c.plan_id = p.id
INNER JOIN client AS cl ON c.client_id = cl.id;