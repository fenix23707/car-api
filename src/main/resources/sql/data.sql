insert into dealers (company_name, director_name, phone) values
    ('driveway', 'Beaumont Colin', '(639) 928-5362'),
    ('driveway', 'Michael L. Waters', '(252) 291-6877'),
    ('driveway', 'Kimberly R. McKay', '(619) 449-1970'),
    ('xpero', 'Berniece Caris', '(490) 768-4588'),
    ('sip', 'Vinny Cassandra', '(632) 651-0033');

insert into engines (type, power) values
    ('ELECTRIC', 500),
    ('GAS', 300),
    ('HYBRID', 700);

insert into vehicles (make, model, "year", price, engine_id) values
    ('Audi', 'A3', 2022, 26990.0, 1),
    ('Audi', 'A3', 2021, 30990.0, 2),
    ('Audi', 'A3', 2016, 15990.0, 1),
    ('Audi', 'A4', 2023, 35990, 3),
    ('Audi', 'A4', 2021, 29990, 2),
    ('Mercedes‑Benz', 'A‑Class', 2020, 20990, 2);

insert into colors (hex, vehicle_id) values
    ('FF5733', 1),
    ('33FFAF', 1),
    ('6433FF', 1),
    ('FF5733', 2),
    ('33FFAF', 2);

insert into dealers_vehicles (dealer_id, vehicle_id) values
    (1,1),
    (1,5),
    (2,1),
    (2,4),
    (3,1),
    (3,3);
