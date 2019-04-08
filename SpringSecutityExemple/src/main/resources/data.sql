-- Pour résoudre le problème de data zone
SET GLOBAL time_zone = '+2:00';


-- Insertion des roles dans la table tb_role
INSERT INTO `tb_role` (`i_r_id`, `v_r_name`) VALUES ('0', 'ROLE_USER');
INSERT INTO `tb_role` (`i_r_id`, `v_r_name`) VALUES ('1', 'ROLE_PM');
INSERT INTO `tb_role` (`i_r_id`, `v_r_name`) VALUES ('2', 'ROLE_ADMIN');


-- Mise à jour des index de la table des sequences
UPDATE `tb_sequence` SET `next_val` = (SELECT COALESCE((MAX(`tb_role`.`i_r_id`)+1), 0) FROM  `tb_role`) WHERE (`sequence_name` = 'tb_role');
UPDATE `tb_sequence` SET `next_val` = (SELECT COALESCE((MAX(`tb_user`.`i_u_id`)+1), 0) FROM  `tb_user`) WHERE (`sequence_name` = 'tb_user');

