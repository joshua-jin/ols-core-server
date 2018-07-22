CREATE TABLE `t_user` (
  `id`                VARCHAR(255) NOT NULL PRIMARY KEY,
  `name`              VARCHAR(255) NOT NULL UNIQUE,
  `password`          VARCHAR(255) NOT NULL
--  `updated_at`        TIMESTAMP,
--  `created_at`        TIMESTAMP,
--  `created_by`        TIMESTAMP,
--  `updated_by`        TIMESTAMP
--  `real_name`         VARCHAR(255)          DEFAULT NULL,
--  `role`              VARCHAR(255) NOT NULL,
--  FOREIGN KEY (`role`) REFERENCES `t_role` (`symbol`)
);