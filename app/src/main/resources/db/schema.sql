insert into ships(height, width, date_created, last_updated, imonumber, name)
values (50, 200, now(), now(),'9241082', '[Anchored] Going Merry');

insert into ships(height, width, date_created, last_updated, imonumber, name)
values (55, 250, now(), now(),'9241061', '[Anchored] Sunny');

insert into ships(height, width, date_created, last_updated, imonumber, name)
values (60, 300, now(), now(),'123456', '[Anchored] Titanic');

insert into ships(height, width, date_created, last_updated, imonumber, name)
values (70, 250, now(), now(),'523456', '[Moving] Ship1');

insert into ships(height, width, date_created, last_updated, imonumber, name)
values (80, 320, now(), now(),'763456', '[Moving] ship2');

insert into ships(height, width, date_created, last_updated, imonumber, name)
values (40, 330, now(), now(),'3233456', '[Moving] ship3');

insert into ships(height, width, date_created, last_updated, imonumber, name)
values (40, 330, now(), now(),'323123456', '[Obstacle] Obstacle1');

insert into ships(height, width, date_created, last_updated, imonumber, name)
values (40, 330, now(), now(),'323323456', '[Obstacle] Obstacle2');

insert into ships(height, width, date_created, last_updated, imonumber, name)
values (40, 330, now(), now(),'323443456', '[Obstacle] Obstacle3');

insert into anchored_ships(x, y, date_created, last_updated, ship_seq_id_id)
values (35.10356228317691, 129.08856689929965, now(),now(), 1);

insert into anchored_ships(x, y, date_created, last_updated, ship_seq_id_id)
values (35.10356228317691, 129.08531069755557, now(),now(), 2);

insert into anchored_ships(x, y, date_created, last_updated, ship_seq_id_id)
values (35.10347889773341, 129.0818774700165, now(),now(), 3);

insert into moving_ships(lat, lng, date_created, last_updated, ship_seq_id_id)
values (35.08989031981692, 129.084849357605, now(),now(), 4);

insert into moving_ships(lat, lng, date_created, last_updated, ship_seq_id_id)
values (35.095543724231476, 129.07480716705325, now(),now(), 5);

insert into moving_ships(lat, lng, date_created, last_updated, ship_seq_id_id)
values (35.09161096265107, 129.0953636169434, now(),now(), 6);

insert into moving_ships(lat, lng, date_created, last_updated, ship_seq_id_id)
values (35.093048, 129.08613400000002, now(),now(), 7);

insert into moving_ships(lat, lng, date_created, last_updated, ship_seq_id_id)
values (35.095471, 129.10309500000002, now(),now(), 8);

insert into moving_ships(lat, lng, date_created, last_updated, ship_seq_id_id)
values (35.100317, 129.08613400000002, now(),now(), 9);

insert into containers(inspection_result, date_created, check_digit, disembarkation_ship_name, owner_code, serial_code)
values (1,now(), '3', 'SKY GLORY[KR]', 'NSSU', '018443');

insert into containers(inspection_result, date_created, check_digit, disembarkation_ship_name, owner_code, serial_code)
values (1, now(), '5', 'ATLANTIC EAST[SG]', 'ASSU', '218447');

insert into containers(inspection_result, date_created, check_digit, disembarkation_ship_name, owner_code, serial_code)
values (1, now(), '7', 'SKY GLORY[KR]', 'NTSU', '218447');

insert into containers(inspection_result, date_created, check_digit, disembarkation_ship_name, owner_code, serial_code)
values (1, now(), '7', 'ATLANTIC EAST[SG]', 'MASU', '312447');

insert into containers(inspection_result, date_created, check_digit, disembarkation_ship_name, owner_code, serial_code)
values (1, now(), '6', 'ATLANTIC EAST[SG]', 'APSQ', '518447');

insert into containers(inspection_result, date_created, check_digit, disembarkation_ship_name, owner_code, serial_code)
values (1, now(), '5', 'SUNNY CLOVER[PA]', 'ASDF', '512321');

insert into containers(inspection_result, date_created, check_digit, disembarkation_ship_name, owner_code, serial_code)
values (1, now(), '5', 'SUNNY CLOVER[PA]', 'QWER', '712447');

insert into containers(inspection_result, date_created, check_digit, disembarkation_ship_name, owner_code, serial_code)
values (1, now(), '3', 'SUNNY CLOVER[PA]', 'NSSU', '213217');