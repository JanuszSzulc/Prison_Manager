insert into prisons (id, name, date_opened, number_of_cells)
values (1, 'Wronia', '30.05.1940', 200);
insert into prisons (id, name, date_opened, number_of_cells)
values (2, 'ZK Barczewo', '30.05.1812', 781);


insert into villains (id, first_name, last_name, origin_country, date_of_conviction, deposit, alive, offense_id,
                      prison_id, transfer_id)
values (1, 'Maciej', 'Rataj', 'Poland', '08.02.1993', '100.01', true, 11, 1, 1);
insert into villains (id, first_name, last_name, origin_country, date_of_conviction, deposit, alive, offense_id,
                      prison_id, transfer_id)
values (2, 'Janusz', 'Kusociński', 'Poland', '31.12.1960', 200.02, true, 11, 1, 1);
insert into villains (id, first_name, last_name, origin_country, date_of_conviction, deposit, alive, offense_id,
                      prison_id, transfer_id)
values (3, 'Maksymilian', 'Kolbe', 'Poland', '17.02.1941', 5000.12, true, 12, 2, 1);

insert into offenses (id, level, description)
values (11, 'MEDIUM', 'robbery');
insert into offenses (id, level, description)
values (12, 'LOW', 'biting the dog');
insert into offenses (id, level, description)
values (13, 'LOW', 'exceeding the speed limit');


insert into transfers (id, destinaton_prison, execution_status, reason, transfer_date)
values (1, 'Alcatraz', false, 'bo tak', 'unknown');
insert into transfers (id, destinaton_prison, execution_status, reason, transfer_date)
values (1, 'Alcatraz', false, 'why not', 'unknown');
insert into transfers (id, destinaton_prison, execution_status, reason, transfer_date)
values (1, 'Alcatraz', false, 'well yes', 'unknown');