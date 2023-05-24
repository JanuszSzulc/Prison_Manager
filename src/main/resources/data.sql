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

insert into transfers (id, destinaton_prison, execution_status, reason, transfer_date)
values (100, 'Alcatraz', false, 'bo tak', 'unknown');
insert into transfers (id, destinaton_prison, execution_status, reason, transfer_date)
values (200, 'Alcatraz', false, 'why not', 'unknown');
insert into transfers (id, destinaton_prison, execution_status, reason, transfer_date)
values (300, 'Alcatraz', false, 'well yes', 'unknown');