INSERT INTO `t_role` (`symbol`, `name`) VALUES ('SYSTEM_ADMIN', '系统管理员');
INSERT INTO `t_role` (`symbol`, `name`) VALUES ('STUDENT', '学生');

INSERT INTO `t_user` (`id`, `name`, `password`, `created_at`, `updated_at`, `created_by`, `updated_by`) VALUES
  ('40b2dd57ca074dc0bd757c3e36fb2ffd', 'admin',
   '$2a$10$W3rO9JXdqvH01bYK/QRYCO7VYNsPDI2tK.4zzmAarYYlE1fLoXxfe');

INSERT INTO `t_user` (`id`, `name`, `password`) VALUES
  ('40b2d3r7ca074dc0bd757c3e36fb2ffd', 'student',
   '$2a$10$W3rO9JXdqvH01bYK/QRYCO7VYNsPDI2tK.4zzmAarYYlE1fLoXxfe');


INSERT INTO `t_privilege` (`symbol`, `name`) VALUES ('CREATE_USER', '创建用户');
INSERT INTO `t_privilege` (`symbol`, `name`) VALUES ('RETRIVE_USER', '检索用户');
INSERT INTO `t_privilege` (`symbol`, `name`) VALUES ('RETRIVE_CAMP_LIST', '检索训练营列表');


INSERT INTO `t_role_privilege` (`role_symbol`, `privilege_symbol`) VALUES ('SYSTEM_ADMIN', 'CREATE_USER');
INSERT INTO `t_role_privilege` (`role_symbol`, `privilege_symbol`) VALUES ('SYSTEM_ADMIN', 'RETRIVE_USER');
INSERT INTO `t_role_privilege` (`role_symbol`, `privilege_symbol`) VALUES ('STUDENT', 'RETRIVE_CAMP_LIST');