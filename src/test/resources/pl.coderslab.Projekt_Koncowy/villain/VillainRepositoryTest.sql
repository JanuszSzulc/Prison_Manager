insert into prisons (id, name, date_opened, number_of_cells)
values (100, 'Wronia', '30.05.1940', 200);
insert into prisons (id, name, date_opened, number_of_cells)
values (200, 'ZK Barczewo', '30.05.1812', 781);

insert into offenses (id, level, description)
values (110, 'MEDIUM', 'robbery');
insert into offenses (id, level, description)
values (120, 'LOW', 'biting the dog');
insert into offenses (id, level, description)
values (130, 'LOW', 'exceeding the speed limit');

insert into villains (id, first_name, last_name, origin_country, date_of_conviction, deposit, alive, offense_id,
                      prison_id, transfer_id)
values (100, 'Maciej', 'Rataj', 'Poland', '08.02.1993', '100.01', true, 110, 100, null);
insert into villains (id, first_name, last_name, origin_country, date_of_conviction, deposit, alive, offense_id,
                      prison_id, transfer_id)
values (200, 'Janusz', 'Kusociński', 'Poland', '31.12.1960', 200.02, true, 110, 100, null);
insert into villains (id, first_name, last_name, origin_country, date_of_conviction, deposit, alive, offense_id,
                      prison_id, transfer_id)
values (300, 'Maksymilian', 'Kolbe', 'Poland', '17.02.1941', 5000.12, true, 120, 200, null);

insert into transfers (id, villain_id, destinaton_prison, execution_status, reason, transfer_time)
values (100, 100, 'Alcatraz', true, 'bo tak', 5);
insert into transfers (id, villain_id, destinaton_prison, execution_status, reason, transfer_time)
values (200, 200, 'Alcatraz', true, 'why not', 10);
insert into transfers (id, villain_id, destinaton_prison, execution_status, reason, transfer_time)
values (300, 300, 'Alcatraz', true, 'well yes', 15);