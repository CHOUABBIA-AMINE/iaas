CREATE TABLE `r_t000201_t000203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t000202_t000201` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t000202_t000203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t000203_t000204` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t010203_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t020207_t020208` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t020304_t020301` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t020404_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t020404_t010302` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t020404_t020208` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t020504_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t020504_t010302` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t020504_t020208` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t020604_t010203` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t020604_t010302` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t030303_t030307` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t030304_t030307` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t030305_t030204` (
  `f_01` bigint(20) NOT NULL,
  `f_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t030305_t030207` (
  `f_01` bigint(20) NOT NULL,
  `f_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `r_t030305_t030307` (
  `F_01` bigint(20) NOT NULL,
  `F_02` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_00_01_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_03` varchar(250) NOT NULL,
  `F_02` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_00_02_01` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_00_02_02` (
  `F_00` bigint(20) NOT NULL,
  `F_04` bit(1) NOT NULL,
  `F_05` bit(1) NOT NULL,
  `F_06` bit(1) NOT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_07` bit(1) NOT NULL,
  `F_03` varchar(120) NOT NULL,
  `F_01` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_00_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_00_02_04` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(100) NOT NULL,
  `F_03` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_00_02_05` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_03` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_00_03_01` (
  `f_00` bigint(20) NOT NULL,
  `f_03` varchar(20) NOT NULL,
  `f_18` varchar(50) DEFAULT NULL,
  `f_12` varchar(1000) DEFAULT NULL,
  `f_15` bigint(20) DEFAULT NULL,
  `f_02` bigint(20) NOT NULL,
  `f_01` varchar(100) NOT NULL,
  `f_14` text DEFAULT NULL,
  `f_06` varchar(45) DEFAULT NULL,
  `f_20` text DEFAULT NULL,
  `f_08` varchar(200) DEFAULT NULL,
  `f_17` varchar(50) DEFAULT NULL,
  `f_10` text DEFAULT NULL,
  `f_09` text DEFAULT NULL,
  `f_11` text DEFAULT NULL,
  `f_19` bigint(20) DEFAULT NULL,
  `f_16` varchar(100) DEFAULT NULL,
  `f_13` varchar(20) NOT NULL,
  `f_05` datetime(6) NOT NULL,
  `f_07` varchar(500) DEFAULT NULL,
  `f_04` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_00_04_01` (
  `F_00` bigint(20) NOT NULL,
  `F_02` datetime(6) NOT NULL,
  `F_01` varchar(255) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_01_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_01_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_01_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL,
  `F_06` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_01_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_01_05` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(20) NOT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) DEFAULT NULL,
  `F_05` varchar(200) NOT NULL,
  `F_01` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_01_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_01_07` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_04` int(11) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_02_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_02_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_04` datetime(6) DEFAULT NULL,
  `F_05` datetime(6) DEFAULT NULL,
  `F_02` varchar(50) DEFAULT NULL,
  `F_01` varchar(50) DEFAULT NULL,
  `F_03` varchar(500) DEFAULT NULL,
  `F_09` bigint(20) NOT NULL,
  `F_06` bigint(20) NOT NULL,
  `F_07` bigint(20) NOT NULL,
  `F_08` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_03_01` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(10) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_03_02` (
  `F_00` bigint(20) NOT NULL,
  `F_03` datetime(6) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(500) DEFAULT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_04_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(3) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_04_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_04_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_04_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_04_05` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(100) DEFAULT NULL,
  `F_05` varchar(100) DEFAULT NULL,
  `F_06` varchar(100) DEFAULT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_07` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_04_06` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(10) DEFAULT NULL,
  `F_05` varchar(10) DEFAULT NULL,
  `F_06` varchar(10) DEFAULT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_04_07` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_06` bigint(20) DEFAULT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_04_08` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_04_09` (
  `F_00` bigint(20) NOT NULL,
  `F_05` datetime(6) DEFAULT NULL,
  `F_06` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_01` varchar(100) NOT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_07` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_01_04_10` (
  `F_00` bigint(20) NOT NULL,
  `F_06` bit(1) DEFAULT NULL,
  `F_05` varchar(50) DEFAULT NULL,
  `F_02` bigint(20) DEFAULT NULL,
  `F_01` bigint(20) NOT NULL,
  `F_04` bigint(20) DEFAULT NULL,
  `F_03` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_01_01` (
  `F_00` bigint(20) NOT NULL,
  `F_05` varchar(20) NOT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_07` varchar(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(50) NOT NULL,
  `F_03` varchar(50) NOT NULL,
  `F_04` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_01_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_01_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(300) DEFAULT NULL,
  `F_02` varchar(300) DEFAULT NULL,
  `F_03` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_01_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_01_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_02_01` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_05` varchar(20) DEFAULT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_02_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(4) NOT NULL,
  `F_01` varchar(200) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_02_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_02_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_02_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_02_07` (
  `F_00` bigint(20) NOT NULL,
  `F_03` datetime(6) DEFAULT NULL,
  `F_02` varchar(500) DEFAULT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_02_08` (
  `F_00` bigint(20) NOT NULL,
  `F_04` double DEFAULT NULL,
  `F_01` varchar(200) NOT NULL,
  `F_03` double DEFAULT NULL,
  `F_02` double DEFAULT NULL,
  `F_07` bigint(20) NOT NULL,
  `F_06` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_02_09` (
  `F_00` bigint(20) NOT NULL,
  `F_01` float DEFAULT NULL,
  `F_02` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_03_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_03_02` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_05` varchar(20) DEFAULT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_03_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_03_04` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_03` varchar(20) DEFAULT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_11` varchar(200) DEFAULT NULL,
  `F_12` varchar(50) DEFAULT NULL,
  `F_06` double DEFAULT NULL,
  `F_08` datetime(6) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_15` varchar(200) DEFAULT NULL,
  `F_16` varchar(300) DEFAULT NULL,
  `F_14` varchar(200) DEFAULT NULL,
  `F_10` varchar(200) DEFAULT NULL,
  `F_13` varchar(50) DEFAULT NULL,
  `F_09` varchar(200) DEFAULT NULL,
  `F_17` varchar(200) DEFAULT NULL,
  `F_20` bigint(20) NOT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_18` bigint(20) DEFAULT NULL,
  `F_21` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_03_05` (
  `F_00` bigint(20) NOT NULL,
  `F_05` varchar(100) DEFAULT NULL,
  `F_03` varchar(200) DEFAULT NULL,
  `F_04` varchar(100) DEFAULT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_08` varchar(100) DEFAULT NULL,
  `F_06` varchar(50) DEFAULT NULL,
  `F_02` varchar(50) NOT NULL,
  `F_09` varchar(100) DEFAULT NULL,
  `F_07` varchar(100) DEFAULT NULL,
  `F_10` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_03_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL,
  `F_06` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_03_07` (
  `F_00` bigint(20) NOT NULL,
  `F_03` varchar(255) DEFAULT NULL,
  `F_02` datetime(6) DEFAULT NULL,
  `F_01` datetime(6) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL,
  `F_06` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_04_01` (
  `F_00` bigint(20) NOT NULL,
  `F_04` varchar(20) DEFAULT NULL,
  `F_05` varchar(20) DEFAULT NULL,
  `F_06` varchar(20) NOT NULL,
  `F_01` varchar(150) DEFAULT NULL,
  `F_02` varchar(150) DEFAULT NULL,
  `F_03` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_04_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_04_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_04_04` (
  `F_00` bigint(20) NOT NULL,
  `F_07` double DEFAULT NULL,
  `F_11` datetime(6) DEFAULT NULL,
  `F_10` varchar(255) DEFAULT NULL,
  `F_02` varchar(4) NOT NULL,
  `F_13` datetime(6) DEFAULT NULL,
  `F_04` varchar(300) DEFAULT NULL,
  `F_05` varchar(300) DEFAULT NULL,
  `F_06` varchar(300) NOT NULL,
  `F_08` double DEFAULT NULL,
  `F_01` varchar(3) NOT NULL,
  `F_14` varchar(500) DEFAULT NULL,
  `F_12` datetime(6) DEFAULT NULL,
  `F_03` varchar(20) DEFAULT NULL,
  `F_09` datetime(6) DEFAULT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_15` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL,
  `F_21` bigint(20) NOT NULL,
  `F_20` bigint(20) NOT NULL,
  `F_16` bigint(20) NOT NULL,
  `F_18` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_04_05` (
  `F_00` bigint(20) NOT NULL,
  `F_02` double DEFAULT NULL,
  `F_01` datetime(6) DEFAULT NULL,
  `F_05` bigint(20) DEFAULT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_07` bigint(20) DEFAULT NULL,
  `F_06` bigint(20) DEFAULT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_05_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_05_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_05_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_05_04` (
  `F_00` bigint(20) NOT NULL,
  `F_07` double DEFAULT NULL,
  `F_11` datetime(6) DEFAULT NULL,
  `F_10` varchar(255) DEFAULT NULL,
  `F_12` datetime(6) DEFAULT NULL,
  `F_14` int(11) DEFAULT NULL,
  `F_02` varchar(255) DEFAULT NULL,
  `F_04` varchar(300) DEFAULT NULL,
  `F_05` varchar(300) DEFAULT NULL,
  `F_06` varchar(300) NOT NULL,
  `F_01` varchar(255) NOT NULL,
  `F_13` datetime(6) DEFAULT NULL,
  `F_15` varchar(500) DEFAULT NULL,
  `F_03` varchar(255) DEFAULT NULL,
  `F_09` datetime(6) DEFAULT NULL,
  `F_08` double DEFAULT NULL,
  `F_20` bigint(20) DEFAULT NULL,
  `F_22` bigint(20) DEFAULT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_16` bigint(20) NOT NULL,
  `F_23` bigint(20) DEFAULT NULL,
  `F_21` bigint(20) NOT NULL,
  `F_18` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_05_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(255) NOT NULL,
  `F_05` varchar(255) DEFAULT NULL,
  `F_03` double DEFAULT NULL,
  `F_02` varchar(255) NOT NULL,
  `F_04` double DEFAULT NULL,
  `F_06` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_06_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_06_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_06_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(200) DEFAULT NULL,
  `F_02` varchar(200) DEFAULT NULL,
  `F_03` varchar(200) NOT NULL,
  `F_04` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_02_06_04` (
  `F_00` bigint(20) NOT NULL,
  `F_06` double DEFAULT NULL,
  `F_09` datetime(6) DEFAULT NULL,
  `F_03` varchar(300) DEFAULT NULL,
  `F_04` varchar(300) DEFAULT NULL,
  `F_05` varchar(300) DEFAULT NULL,
  `F_01` int(11) DEFAULT NULL,
  `F_10` datetime(6) DEFAULT NULL,
  `F_11` varchar(500) DEFAULT NULL,
  `F_02` varchar(255) DEFAULT NULL,
  `F_08` datetime(6) DEFAULT NULL,
  `F_07` double DEFAULT NULL,
  `F_15` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL,
  `F_16` bigint(20) DEFAULT NULL,
  `F_12` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL,
  `F_14` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_01_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_01_02` (
  `F_00` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_01_03` (
  `F_00` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_01_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_01_05` (
  `F_00` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_01_06` (
  `F_00` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_01_07` (
  `F_00` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_01_08` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_02_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_06` varchar(200) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_02_02` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_06` varchar(200) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_08` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_02_03` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(10) NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_07` double DEFAULT NULL,
  `F_05` double NOT NULL,
  `F_06` double NOT NULL,
  `F_08` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_02_04` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_05` double NOT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL,
  `F_07` double NOT NULL,
  `F_09` bit(1) NOT NULL,
  `F_08` double NOT NULL,
  `F_06` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_02_05` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_06` varchar(200) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_02_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_05` varchar(200) DEFAULT NULL,
  `F_06` varchar(200) DEFAULT NULL,
  `F_07` varchar(200) DEFAULT NULL,
  `F_02` varchar(100) DEFAULT NULL,
  `F_03` varchar(100) DEFAULT NULL,
  `F_04` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_02_07` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(20) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_02_08` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(100) DEFAULT NULL,
  `F_02` varchar(20) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_03_01` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(20) NOT NULL,
  `F_04` date DEFAULT NULL,
  `F_05` date DEFAULT NULL,
  `F_03` date DEFAULT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_06` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_03_02` (
  `F_10` double DEFAULT NULL,
  `F_08` double NOT NULL,
  `F_09` double NOT NULL,
  `F_07` varchar(100) NOT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_12` bigint(20) NOT NULL,
  `F_11` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_03_03` (
  `F_00` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_03_04` (
  `F_00` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_03_05` (
  `F_00` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_03_06` (
  `F_00` bigint(20) NOT NULL,
  `F_01` varchar(50) NOT NULL,
  `F_02` varchar(100) NOT NULL,
  `F_04` bigint(20) NOT NULL,
  `F_03` bigint(20) NOT NULL,
  `F_05` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_03_07` (
  `F_15` double NOT NULL,
  `F_11` double NOT NULL,
  `F_13` double NOT NULL,
  `F_08` double NOT NULL,
  `F_07` double NOT NULL,
  `F_10` double NOT NULL,
  `F_09` double NOT NULL,
  `F_16` double NOT NULL,
  `F_12` double NOT NULL,
  `F_14` double NOT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_23` bigint(20) NOT NULL,
  `F_22` bigint(20) NOT NULL,
  `F_17` bigint(20) NOT NULL,
  `F_18` bigint(20) NOT NULL,
  `F_19` bigint(20) NOT NULL,
  `F_21` bigint(20) NOT NULL,
  `F_20` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_03_08` (
  `F_07` double NOT NULL,
  `F_12` double NOT NULL,
  `F_08` double NOT NULL,
  `F_10` double NOT NULL,
  `F_11` double NOT NULL,
  `F_09` double NOT NULL,
  `F_00` bigint(20) NOT NULL,
  `F_13` bigint(20) DEFAULT NULL,
  `F_14` bigint(20) DEFAULT NULL,
  `F_15` bigint(20) DEFAULT NULL,
  `F_16` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `t_03_03_09` (
  `F_00` bigint(20) NOT NULL,
  `F_02` varchar(50) NOT NULL,
  `F_08` date DEFAULT NULL,
  `F_10` date DEFAULT NULL,
  `F_07` date DEFAULT NULL,
  `F_09` date DEFAULT NULL,
  `F_03` varchar(100) NOT NULL,
  `F_06` date DEFAULT NULL,
  `F_04` varchar(50) DEFAULT NULL,
  `F_01` varchar(100) NOT NULL,
  `F_05` varchar(100) DEFAULT NULL,
  `F_12` bigint(20) NOT NULL,
  `F_13` bigint(20) NOT NULL,
  `F_11` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE `r_t000201_t000203`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000201_T000203_FK_02` (`F_02`);

--
-- Indexes for table `r_t000202_t000201`
--
ALTER TABLE `r_t000202_t000201`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000202_T000201_FK_02` (`F_02`);

--
-- Indexes for table `r_t000202_t000203`
--
ALTER TABLE `r_t000202_t000203`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000202_T000203_FK_02` (`F_02`);

--
-- Indexes for table `r_t000203_t000204`
--
ALTER TABLE `r_t000203_t000204`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T000203_T000204_FK_02` (`F_02`);

--
-- Indexes for table `r_t010203_t010203`
--
ALTER TABLE `r_t010203_t010203`
  ADD UNIQUE KEY `R_T010203_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T010203_T010203_FK_02` (`F_02`);

--
-- Indexes for table `r_t020207_t020208`
--
ALTER TABLE `r_t020207_t020208`
  ADD UNIQUE KEY `R_T020207_T020208_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020207_T020208_FK_02` (`F_02`);

--
-- Indexes for table `r_t020304_t020301`
--
ALTER TABLE `r_t020304_t020301`
  ADD UNIQUE KEY `R_T020304_T020301_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020304_T020301_FK_02` (`F_02`);

--
-- Indexes for table `r_t020404_t010203`
--
ALTER TABLE `r_t020404_t010203`
  ADD UNIQUE KEY `R_T020404_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020404_T010203_FK_02` (`F_02`);

--
-- Indexes for table `r_t020404_t010302`
--
ALTER TABLE `r_t020404_t010302`
  ADD UNIQUE KEY `R_T020404_T010302_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020404_T010302_FK_02` (`F_02`);

--
-- Indexes for table `r_t020404_t020208`
--
ALTER TABLE `r_t020404_t020208`
  ADD UNIQUE KEY `R_T020404_T020208_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020404_T020208_FK_02` (`F_02`);

--
-- Indexes for table `r_t020504_t010203`
--
ALTER TABLE `r_t020504_t010203`
  ADD UNIQUE KEY `R_T020504_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020504_T010203_FK_02` (`F_02`);

--
-- Indexes for table `r_t020504_t010302`
--
ALTER TABLE `r_t020504_t010302`
  ADD UNIQUE KEY `R_T020504_T010302_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020504_T010302_FK_02` (`F_02`);

--
-- Indexes for table `r_t020504_t020208`
--
ALTER TABLE `r_t020504_t020208`
  ADD UNIQUE KEY `R_T020504_T020208_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020504_T020208_FK_02` (`F_02`);

--
-- Indexes for table `r_t020604_t010203`
--
ALTER TABLE `r_t020604_t010203`
  ADD UNIQUE KEY `R_T020604_T010203_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020604_T010203_FK_02` (`F_02`);

--
-- Indexes for table `r_t020604_t010302`
--
ALTER TABLE `r_t020604_t010302`
  ADD UNIQUE KEY `R_T020604_T010302_UK_01` (`F_01`,`F_02`),
  ADD KEY `R_T020604_T010302_FK_02` (`F_02`);

--
-- Indexes for table `r_t030303_t030307`
--
ALTER TABLE `r_t030303_t030307`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T030303_T030307_FK_02` (`F_02`);

--
-- Indexes for table `r_t030304_t030307`
--
ALTER TABLE `r_t030304_t030307`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD KEY `R_T030304_T030307_FK_02` (`F_02`);

--
-- Indexes for table `r_t030305_t030204`
--
ALTER TABLE `r_t030305_t030204`
  ADD PRIMARY KEY (`f_01`,`f_02`),
  ADD KEY `R_T030305_T030204_FK_02` (`f_02`),
  ADD KEY `R_T030305_T030204_FK_01` (`f_01`) USING BTREE;

--
-- Indexes for table `r_t030305_t030207`
--
ALTER TABLE `r_t030305_t030207`
  ADD PRIMARY KEY (`f_01`,`f_02`),
  ADD KEY `R_T030305_T030207_FK_02` (`f_02`),
  ADD KEY `R_T030305_T030207_FK_01` (`f_01`);

--
-- Indexes for table `r_t030305_t030307`
--
ALTER TABLE `r_t030305_t030307`
  ADD PRIMARY KEY (`F_01`,`F_02`),
  ADD UNIQUE KEY `R_T030305_T030307_FK_01` (`F_01`),
  ADD KEY `R_T030305_T030307_FK_02` (`F_02`);

--
-- Indexes for table `t_00_01_01`
--
ALTER TABLE `t_00_01_01`
  ADD PRIMARY KEY (`F_00`);

--
-- Indexes for table `t_00_02_01`
--
ALTER TABLE `t_00_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_01_UK_01` (`F_01`);

--
-- Indexes for table `t_00_02_02`
--
ALTER TABLE `t_00_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_00_02_02_UK_02` (`F_02`);

--
-- Indexes for table `t_00_02_03`
--
ALTER TABLE `t_00_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_03_UK_01` (`F_01`);

--
-- Indexes for table `t_00_02_04`
--
ALTER TABLE `t_00_02_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_04_UK_01` (`F_01`);

--
-- Indexes for table `t_00_02_05`
--
ALTER TABLE `t_00_02_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_02_05_UK_01` (`F_01`);

--
-- Indexes for table `t_00_03_01`
--
ALTER TABLE `t_00_03_01`
  ADD PRIMARY KEY (`f_00`);

--
-- Indexes for table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_00_04_01_UK_01` (`F_03`);

--
-- Indexes for table `t_01_01_01`
--
ALTER TABLE `t_01_01_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_01_UK_01` (`F_03`);

--
-- Indexes for table `t_01_01_02`
--
ALTER TABLE `t_01_01_02`
  ADD PRIMARY KEY (`F_00`);

--
-- Indexes for table `t_01_01_03`
--
ALTER TABLE `t_01_01_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_03_UK_01` (`F_05`,`F_04`),
  ADD KEY `T_01_01_03_FK_02` (`F_06`);

--
-- Indexes for table `t_01_01_04`
--
ALTER TABLE `t_01_01_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_04_UK_01` (`F_01`),
  ADD KEY `T_01_01_04_FK_01` (`F_05`);

--
-- Indexes for table `t_01_01_05`
--
ALTER TABLE `t_01_01_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_05_UK_02` (`F_02`),
  ADD UNIQUE KEY `T_01_01_05_UK_03` (`F_05`),
  ADD UNIQUE KEY `T_01_01_05_UK_01` (`F_01`);

--
-- Indexes for table `t_01_01_06`
--
ALTER TABLE `t_01_01_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_06_UK_01` (`F_01`),
  ADD KEY `T_01_01_06_FK_01` (`F_03`),
  ADD KEY `T_01_01_06_FK_02` (`F_04`);

--
-- Indexes for table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_01_07_UK_01` (`F_01`),
  ADD KEY `T_01_01_07_FK_01` (`F_03`);

--
-- Indexes for table `t_01_02_01`
--
ALTER TABLE `t_01_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_02_01_UK_01` (`F_03`);

--
-- Indexes for table `t_01_02_02`
--
ALTER TABLE `t_01_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_02_02_UK_01` (`F_03`);

--
-- Indexes for table `t_01_02_03`
--
ALTER TABLE `t_01_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_02_03_UK_01` (`F_01`),
  ADD KEY `T_01_02_03_FK_04` (`F_09`),
  ADD KEY `T_01_02_03_FK_01` (`F_06`),
  ADD KEY `T_01_02_03_FK_02` (`F_07`),
  ADD KEY `T_01_02_03_FK_03` (`F_08`);

--
-- Indexes for table `t_01_03_01`
--
ALTER TABLE `t_01_03_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_03_01_UK_01` (`F_03`);

--
-- Indexes for table `t_01_03_02`
--
ALTER TABLE `t_01_03_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_03_02_UK_01` (`F_01`),
  ADD KEY `T_01_03_02_FK_01` (`F_04`),
  ADD KEY `T_01_03_02_FK_02` (`F_05`);

--
-- Indexes for table `t_01_04_01`
--
ALTER TABLE `t_01_04_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_01_UK_01` (`F_03`);

--
-- Indexes for table `t_01_04_02`
--
ALTER TABLE `t_01_04_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_01_04_02_UK_02` (`F_04`);

--
-- Indexes for table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_03_UK_01` (`F_01`),
  ADD KEY `T_01_04_03_FK_01` (`F_05`);

--
-- Indexes for table `t_01_04_04`
--
ALTER TABLE `t_01_04_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_04_UK_01` (`F_03`);

--
-- Indexes for table `t_01_04_05`
--
ALTER TABLE `t_01_04_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_05_UK_01` (`F_03`),
  ADD KEY `T_01_04_05_FK_01` (`F_07`);

--
-- Indexes for table `t_01_04_06`
--
ALTER TABLE `t_01_04_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_06_UK_01` (`F_03`);

--
-- Indexes for table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_07_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_01_04_07_UK_02` (`F_04`),
  ADD KEY `T_01_04_07_FK_02` (`F_06`),
  ADD KEY `T_01_04_07_FK_01` (`F_05`);

--
-- Indexes for table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_01_04_08_UK_01` (`F_01`),
  ADD KEY `T_01_04_08_FK_01` (`F_05`);

--
-- Indexes for table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `FK5d5f2io70jjhnxm49c4nsmtjm` (`F_07`);

--
-- Indexes for table `t_01_04_10`
--
ALTER TABLE `t_01_04_10`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `FKfploqhev5tkv2uul79eyvwlsp` (`F_02`),
  ADD KEY `FKjucg9o5kabtxoi0j8qxf4bc0n` (`F_01`),
  ADD KEY `FKl4ebqb4p2x7bb2n7lg3k9pik9` (`F_04`),
  ADD KEY `FKre9gryisoebvijo6siwxm4e3s` (`F_03`);

--
-- Indexes for table `t_02_01_01`
--
ALTER TABLE `t_02_01_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_01_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_02_01_01_UK_02` (`F_02`),
  ADD UNIQUE KEY `T_02_01_01_UK_03` (`F_03`),
  ADD UNIQUE KEY `T_02_01_01_UK_04` (`F_04`),
  ADD UNIQUE KEY `T_02_01_01_UK_05` (`F_05`,`F_06`,`F_07`);

--
-- Indexes for table `t_02_01_02`
--
ALTER TABLE `t_02_01_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_02_UK_01` (`F_03`);

--
-- Indexes for table `t_02_01_03`
--
ALTER TABLE `t_02_01_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_03_UK_01` (`F_03`);

--
-- Indexes for table `t_02_01_04`
--
ALTER TABLE `t_02_01_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_04_UK_01` (`F_03`);

--
-- Indexes for table `t_02_01_05`
--
ALTER TABLE `t_02_01_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_01_05_UK_01` (`F_03`);

--
-- Indexes for table `t_02_02_01`
--
ALTER TABLE `t_02_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_01_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_02_02_01_UK_02` (`F_06`);

--
-- Indexes for table `t_02_02_02`
--
ALTER TABLE `t_02_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_02_UK_01` (`F_03`);

--
-- Indexes for table `t_02_02_03`
--
ALTER TABLE `t_02_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_03_UK_01` (`F_01`),
  ADD KEY `T_02_02_03_FK_01` (`F_03`);

--
-- Indexes for table `t_02_02_04`
--
ALTER TABLE `t_02_02_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_04_UK_01` (`F_03`);

--
-- Indexes for table `t_02_02_05`
--
ALTER TABLE `t_02_02_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_05_UK_01` (`F_03`),
  ADD KEY `T_02_02_05_FK_01` (`F_04`);

--
-- Indexes for table `t_02_02_06`
--
ALTER TABLE `t_02_02_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_06_UK_01` (`F_02`),
  ADD KEY `T_02_02_06_FK_01` (`F_04`);

--
-- Indexes for table `t_02_02_07`
--
ALTER TABLE `t_02_02_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_02_07_UK_01` (`F_03`,`F_04`),
  ADD KEY `T_02_02_07_FK_01` (`F_04`),
  ADD KEY `T_02_02_07_FK_02` (`F_05`);

--
-- Indexes for table `t_02_02_08`
--
ALTER TABLE `t_02_02_08`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_02_08_FK_03` (`F_07`),
  ADD KEY `T_02_02_08_FK_02` (`F_06`),
  ADD KEY `T_02_02_08_FK_01` (`F_05`);

--
-- Indexes for table `t_02_02_09`
--
ALTER TABLE `t_02_02_09`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_02_09_FK_01` (`F_02`),
  ADD KEY `T_02_02_09_FK_02` (`F_03`);

--
-- Indexes for table `t_02_03_01`
--
ALTER TABLE `t_02_03_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_01_UK_01` (`F_04`);

--
-- Indexes for table `t_02_03_02`
--
ALTER TABLE `t_02_03_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_02_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_02_03_02_UK_02` (`F_06`);

--
-- Indexes for table `t_02_03_03`
--
ALTER TABLE `t_02_03_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_03_UK_01` (`F_03`);

--
-- Indexes for table `t_02_03_04`
--
ALTER TABLE `t_02_03_04`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_03_04_FK_03` (`F_20`),
  ADD KEY `T_02_03_04_FK_02` (`F_19`),
  ADD KEY `T_02_03_04_FK_01` (`F_18`),
  ADD KEY `T_02_03_04_FK_04` (`F_21`);

--
-- Indexes for table `t_02_03_05`
--
ALTER TABLE `t_02_03_05`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_02_03_05_FK_01` (`F_10`);

--
-- Indexes for table `t_02_03_06`
--
ALTER TABLE `t_02_03_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_06_UK_01` (`F_03`),
  ADD KEY `T_02_03_06_FK_01` (`F_04`),
  ADD KEY `T_02_03_06_FK_02` (`F_05`),
  ADD KEY `T_02_03_06_FK_03` (`F_06`);

--
-- Indexes for table `t_02_03_07`
--
ALTER TABLE `t_02_03_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_03_07_UK_01` (`F_01`,`F_05`),
  ADD KEY `T_02_03_07_FK_01` (`F_04`),
  ADD KEY `T_02_03_07_FK_02` (`F_05`),
  ADD KEY `T_02_03_07_FK_03` (`F_06`);

--
-- Indexes for table `t_02_04_01`
--
ALTER TABLE `t_02_04_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_01_UK_01` (`F_03`),
  ADD UNIQUE KEY `T_02_04_01_UK_02` (`F_06`);

--
-- Indexes for table `t_02_04_02`
--
ALTER TABLE `t_02_04_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_02_UK_01` (`F_03`);

--
-- Indexes for table `t_02_04_03`
--
ALTER TABLE `t_02_04_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_03_UK_01` (`F_03`),
  ADD KEY `T_02_04_03_FK_01` (`F_04`);

--
-- Indexes for table `t_02_04_04`
--
ALTER TABLE `t_02_04_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_04_UK_01` (`F_01`,`F_02`),
  ADD KEY `T_02_04_04_FK_05` (`F_19`),
  ADD KEY `T_02_04_04_FK_01` (`F_15`),
  ADD KEY `T_02_04_04_FK_03` (`F_17`),
  ADD KEY `T_02_04_04_FK_07` (`F_21`),
  ADD KEY `T_02_04_04_FK_06` (`F_20`),
  ADD KEY `T_02_04_04_FK_02` (`F_16`),
  ADD KEY `T_02_04_04_FK_04` (`F_18`);

--
-- Indexes for table `t_02_04_05`
--
ALTER TABLE `t_02_04_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_04_05_UK_01` (`F_03`,`F_04`),
  ADD KEY `T_02_04_05_FK_03` (`F_05`),
  ADD KEY `T_02_04_05_FK_05` (`F_07`),
  ADD KEY `T_02_04_05_FK_04` (`F_06`),
  ADD KEY `T_02_04_05_FK_02` (`F_04`);

--
-- Indexes for table `t_02_05_01`
--
ALTER TABLE `t_02_05_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_01_UK_01` (`F_03`);

--
-- Indexes for table `t_02_05_02`
--
ALTER TABLE `t_02_05_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_02_UK_01` (`F_03`);

--
-- Indexes for table `t_02_05_03`
--
ALTER TABLE `t_02_05_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_03_UK_01` (`F_03`),
  ADD KEY `T_02_05_03_FK_01` (`F_04`);

--
-- Indexes for table `t_02_05_04`
--
ALTER TABLE `t_02_05_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_04_UK_01` (`F_01`),
  ADD KEY `T_02_05_04_FK_05` (`F_20`),
  ADD KEY `T_02_05_04_FK_07` (`F_22`),
  ADD KEY `T_02_05_04_FK_04` (`F_19`),
  ADD KEY `T_02_05_04_FK_01` (`F_16`),
  ADD KEY `T_02_05_04_FK_08` (`F_23`),
  ADD KEY `T_02_05_04_FK_06` (`F_21`),
  ADD KEY `T_02_05_04_FK_03` (`F_18`),
  ADD KEY `T_02_05_04_FK_02` (`F_17`);

--
-- Indexes for table `t_02_05_05`
--
ALTER TABLE `t_02_05_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_05_05_UK_01` (`F_02`,`F_06`),
  ADD KEY `T_02_05_05_FK_01` (`F_06`);

--
-- Indexes for table `t_02_06_01`
--
ALTER TABLE `t_02_06_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_01_UK_01` (`F_03`);

--
-- Indexes for table `t_02_06_02`
--
ALTER TABLE `t_02_06_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_02_UK_01` (`F_03`);

--
-- Indexes for table `t_02_06_03`
--
ALTER TABLE `t_02_06_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_03_UK_01` (`F_03`),
  ADD KEY `T_02_06_03_FK_01` (`F_04`);

--
-- Indexes for table `t_02_06_04`
--
ALTER TABLE `t_02_06_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_02_06_04_UK_01` (`F_02`),
  ADD KEY `T_02_06_04_FK_04` (`F_15`),
  ADD KEY `T_02_06_04_FK_02` (`F_13`),
  ADD KEY `T_02_06_04_FK_05` (`F_16`),
  ADD KEY `T_02_06_04_FK_01` (`F_12`),
  ADD KEY `T_02_06_04_FK_06` (`F_17`),
  ADD KEY `T_02_06_04_FK_03` (`F_14`);

--
-- Indexes for table `t_03_01_01`
--
ALTER TABLE `t_03_01_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_01_01_UK_01` (`F_03`);

--
-- Indexes for table `t_03_01_02`
--
ALTER TABLE `t_03_01_02`
  ADD PRIMARY KEY (`F_00`);

--
-- Indexes for table `t_03_01_03`
--
ALTER TABLE `t_03_01_03`
  ADD PRIMARY KEY (`F_00`);

--
-- Indexes for table `t_03_01_04`
--
ALTER TABLE `t_03_01_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_01_04_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_01_04_UK_02` (`F_04`);

--
-- Indexes for table `t_03_01_05`
--
ALTER TABLE `t_03_01_05`
  ADD PRIMARY KEY (`F_00`);

--
-- Indexes for table `t_03_01_06`
--
ALTER TABLE `t_03_01_06`
  ADD PRIMARY KEY (`F_00`);

--
-- Indexes for table `t_03_01_07`
--
ALTER TABLE `t_03_01_07`
  ADD PRIMARY KEY (`F_00`);

--
-- Indexes for table `t_03_01_08`
--
ALTER TABLE `t_03_01_08`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_01_08_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_01_08_UK_02` (`F_04`);

--
-- Indexes for table `t_03_02_01`
--
ALTER TABLE `t_03_02_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_01_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_01_02_UK_02` (`F_04`);

--
-- Indexes for table `t_03_02_02`
--
ALTER TABLE `t_03_02_02`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_02_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_02_02_UK_02` (`F_04`),
  ADD KEY `T_03_02_02_FK_01` (`F_08`);

--
-- Indexes for table `t_03_02_03`
--
ALTER TABLE `t_03_02_03`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_03_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_02_03_UK_02` (`F_01`),
  ADD KEY `T_03_02_03_FK_01` (`F_08`);

--
-- Indexes for table `t_03_02_04`
--
ALTER TABLE `t_03_02_04`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_04_UK_01` (`F_01`,`F_04`);

--
-- Indexes for table `t_03_02_05`
--
ALTER TABLE `t_03_02_05`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_05_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_02_05_UK_02` (`F_04`);

--
-- Indexes for table `t_03_02_06`
--
ALTER TABLE `t_03_02_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_06_UK_01` (`F_01`),
  ADD UNIQUE KEY `T_03_02_06_UK_02` (`F_04`);

--
-- Indexes for table `t_03_02_07`
--
ALTER TABLE `t_03_02_07`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_07_UK_01` (`F_02`),
  ADD KEY `T_03_02_07_FK_02` (`F_04`),
  ADD KEY `T_03_02_07_FK_01` (`F_03`);

--
-- Indexes for table `t_03_02_08`
--
ALTER TABLE `t_03_02_08`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_02_08_UK_01` (`F_02`),
  ADD KEY `T_03_02_08_FK_02` (`F_04`),
  ADD KEY `T_03_02_08_FK_01` (`F_03`);

--
-- Indexes for table `t_03_03_01`
--
ALTER TABLE `t_03_03_01`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_03_01_UK_01` (`F_01`),
  ADD KEY `T_03_03_01_FK_01` (`F_06`);

--
-- Indexes for table `t_03_03_02`
--
ALTER TABLE `t_03_03_02`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_03_02_FK_02` (`F_12`),
  ADD KEY `T_03_03_02_FK_01` (`F_11`);

--
-- Indexes for table `t_03_03_03`
--
ALTER TABLE `t_03_03_03`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_03_03_FK_01` (`F_13`);

--
-- Indexes for table `t_03_03_04`
--
ALTER TABLE `t_03_03_04`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_03_04_FK_01` (`F_13`);

--
-- Indexes for table `t_03_03_05`
--
ALTER TABLE `t_03_03_05`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_03_05_FK_01` (`F_13`);

--
-- Indexes for table `t_03_03_06`
--
ALTER TABLE `t_03_03_06`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_03_06_UK_01` (`F_01`),
  ADD KEY `T_03_03_06_FK_02` (`F_04`),
  ADD KEY `T_03_03_06_FK_01` (`F_03`),
  ADD KEY `T_03_03_06_FK_03` (`F_05`);

--
-- Indexes for table `t_03_03_07`
--
ALTER TABLE `t_03_03_07`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_03_07_FK_07` (`F_23`),
  ADD KEY `T_03_03_07_FK_06` (`F_22`),
  ADD KEY `T_03_03_07_FK_01` (`F_17`),
  ADD KEY `T_03_03_07_FK_02` (`F_18`),
  ADD KEY `T_03_03_07_FK_03` (`F_19`),
  ADD KEY `T_03_03_07_FK_05` (`F_21`),
  ADD KEY `T_03_03_07_FK_04` (`F_20`);

--
-- Indexes for table `t_03_03_08`
--
ALTER TABLE `t_03_03_08`
  ADD PRIMARY KEY (`F_00`),
  ADD KEY `T_03_02_09_FK_01` (`F_13`),
  ADD KEY `T_03_02_09_FK_02` (`F_14`),
  ADD KEY `T_03_02_09_FK_03` (`F_15`),
  ADD KEY `T_03_02_10_FK_01` (`F_16`);

--
-- Indexes for table `t_03_03_09`
--
ALTER TABLE `t_03_03_09`
  ADD PRIMARY KEY (`F_00`),
  ADD UNIQUE KEY `T_03_03_09_UK_01` (`F_02`),
  ADD KEY `T_03_03_09_FK_02` (`F_12`),
  ADD KEY `T_03_03_09_FK_03` (`F_13`),
  ADD KEY `T_03_03_09_FK_01` (`F_11`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_00_01_01`
--
ALTER TABLE `t_00_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_00_02_01`
--
ALTER TABLE `t_00_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `t_00_02_02`
--
ALTER TABLE `t_00_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `t_00_02_03`
--
ALTER TABLE `t_00_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_00_02_04`
--
ALTER TABLE `t_00_02_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=181;

--
-- AUTO_INCREMENT for table `t_00_02_05`
--
ALTER TABLE `t_00_02_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `t_00_03_01`
--
ALTER TABLE `t_00_03_01`
  MODIFY `f_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_01_01`
--
ALTER TABLE `t_01_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `t_01_01_02`
--
ALTER TABLE `t_01_01_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `t_01_01_03`
--
ALTER TABLE `t_01_01_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_01_01_04`
--
ALTER TABLE `t_01_01_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `t_01_01_05`
--
ALTER TABLE `t_01_01_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_01_01_06`
--
ALTER TABLE `t_01_01_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_02_01`
--
ALTER TABLE `t_01_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_01_02_02`
--
ALTER TABLE `t_01_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `t_01_02_03`
--
ALTER TABLE `t_01_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_03_01`
--
ALTER TABLE `t_01_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `t_01_03_02`
--
ALTER TABLE `t_01_03_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_04_01`
--
ALTER TABLE `t_01_04_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=250;

--
-- AUTO_INCREMENT for table `t_01_04_02`
--
ALTER TABLE `t_01_04_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT for table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1542;

--
-- AUTO_INCREMENT for table `t_01_04_04`
--
ALTER TABLE `t_01_04_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `t_01_04_05`
--
ALTER TABLE `t_01_04_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `t_01_04_06`
--
ALTER TABLE `t_01_04_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_01_04_10`
--
ALTER TABLE `t_01_04_10`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_01_01`
--
ALTER TABLE `t_02_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=142;

--
-- AUTO_INCREMENT for table `t_02_01_02`
--
ALTER TABLE `t_02_01_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_02_01_03`
--
ALTER TABLE `t_02_01_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_02_01_04`
--
ALTER TABLE `t_02_01_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `t_02_01_05`
--
ALTER TABLE `t_02_01_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `t_02_02_01`
--
ALTER TABLE `t_02_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_02_02_02`
--
ALTER TABLE `t_02_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `t_02_02_03`
--
ALTER TABLE `t_02_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_04`
--
ALTER TABLE `t_02_02_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_05`
--
ALTER TABLE `t_02_02_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_06`
--
ALTER TABLE `t_02_02_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_07`
--
ALTER TABLE `t_02_02_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_08`
--
ALTER TABLE `t_02_02_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_02_09`
--
ALTER TABLE `t_02_02_09`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_03_01`
--
ALTER TABLE `t_02_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1175;

--
-- AUTO_INCREMENT for table `t_02_03_02`
--
ALTER TABLE `t_02_03_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `t_02_03_03`
--
ALTER TABLE `t_02_03_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_02_03_04`
--
ALTER TABLE `t_02_03_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_03_05`
--
ALTER TABLE `t_02_03_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_03_06`
--
ALTER TABLE `t_02_03_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_03_07`
--
ALTER TABLE `t_02_03_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_04_01`
--
ALTER TABLE `t_02_04_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `t_02_04_02`
--
ALTER TABLE `t_02_04_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `t_02_04_03`
--
ALTER TABLE `t_02_04_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `t_02_04_04`
--
ALTER TABLE `t_02_04_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_04_05`
--
ALTER TABLE `t_02_04_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_05_01`
--
ALTER TABLE `t_02_05_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_02_05_02`
--
ALTER TABLE `t_02_05_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_02_05_03`
--
ALTER TABLE `t_02_05_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `t_02_05_04`
--
ALTER TABLE `t_02_05_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_05_05`
--
ALTER TABLE `t_02_05_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_02_06_01`
--
ALTER TABLE `t_02_06_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_02_06_02`
--
ALTER TABLE `t_02_06_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_02_06_03`
--
ALTER TABLE `t_02_06_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `t_02_06_04`
--
ALTER TABLE `t_02_06_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_03_01_01`
--
ALTER TABLE `t_03_01_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `t_03_01_04`
--
ALTER TABLE `t_03_01_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `t_03_01_08`
--
ALTER TABLE `t_03_01_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `t_03_02_01`
--
ALTER TABLE `t_03_02_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_03_02_02`
--
ALTER TABLE `t_03_02_02`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `t_03_02_03`
--
ALTER TABLE `t_03_02_03`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_03_02_04`
--
ALTER TABLE `t_03_02_04`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `t_03_02_05`
--
ALTER TABLE `t_03_02_05`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `t_03_02_06`
--
ALTER TABLE `t_03_02_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `t_03_02_07`
--
ALTER TABLE `t_03_02_07`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `t_03_02_08`
--
ALTER TABLE `t_03_02_08`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `t_03_03_01`
--
ALTER TABLE `t_03_03_01`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;

--
-- AUTO_INCREMENT for table `t_03_03_06`
--
ALTER TABLE `t_03_03_06`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `t_03_03_09`
--
ALTER TABLE `t_03_03_09`
  MODIFY `F_00` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `r_t000201_t000203`
--
ALTER TABLE `r_t000201_t000203`
  ADD CONSTRAINT `R_T000201_T000203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_01` (`F_00`),
  ADD CONSTRAINT `R_T000201_T000203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_03` (`F_00`);

--
-- Constraints for table `r_t000202_t000201`
--
ALTER TABLE `r_t000202_t000201`
  ADD CONSTRAINT `R_T000202_T000201_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_02` (`F_00`),
  ADD CONSTRAINT `R_T000202_T000201_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_01` (`F_00`);

--
-- Constraints for table `r_t000202_t000203`
--
ALTER TABLE `r_t000202_t000203`
  ADD CONSTRAINT `R_T000202_T000203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_02` (`F_00`),
  ADD CONSTRAINT `R_T000202_T000203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_03` (`F_00`);

--
-- Constraints for table `r_t000203_t000204`
--
ALTER TABLE `r_t000203_t000204`
  ADD CONSTRAINT `R_T000203_T000204_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_00_02_03` (`F_00`),
  ADD CONSTRAINT `R_T000203_T000204_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_00_02_04` (`F_00`);

--
-- Constraints for table `r_t010203_t010203`
--
ALTER TABLE `r_t010203_t010203`
  ADD CONSTRAINT `R_T010203_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_01_02_03` (`F_00`),
  ADD CONSTRAINT `R_T010203_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `r_t020207_t020208`
--
ALTER TABLE `r_t020207_t020208`
  ADD CONSTRAINT `R_T020207_T020208_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_02_07` (`F_00`),
  ADD CONSTRAINT `R_T020207_T020208_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`);

--
-- Constraints for table `r_t020304_t020301`
--
ALTER TABLE `r_t020304_t020301`
  ADD CONSTRAINT `R_T020304_T020301_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `R_T020304_T020301_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_03_01` (`F_00`);

--
-- Constraints for table `r_t020404_t010203`
--
ALTER TABLE `r_t020404_t010203`
  ADD CONSTRAINT `R_T020404_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `R_T020404_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `r_t020404_t010302`
--
ALTER TABLE `r_t020404_t010302`
  ADD CONSTRAINT `R_T020404_T010302_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `R_T020404_T010302_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Constraints for table `r_t020404_t020208`
--
ALTER TABLE `r_t020404_t020208`
  ADD CONSTRAINT `R_T020404_T020208_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `R_T020404_T020208_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`);

--
-- Constraints for table `r_t020504_t010203`
--
ALTER TABLE `r_t020504_t010203`
  ADD CONSTRAINT `R_T020504_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `R_T020504_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `r_t020504_t010302`
--
ALTER TABLE `r_t020504_t010302`
  ADD CONSTRAINT `R_T020504_T010302_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `R_T020504_T010302_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Constraints for table `r_t020504_t020208`
--
ALTER TABLE `r_t020504_t020208`
  ADD CONSTRAINT `R_T020504_T020208_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `R_T020504_T020208_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`);

--
-- Constraints for table `r_t020604_t010203`
--
ALTER TABLE `r_t020604_t010203`
  ADD CONSTRAINT `R_T020604_T010203_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_06_04` (`F_00`),
  ADD CONSTRAINT `R_T020604_T010203_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `r_t020604_t010302`
--
ALTER TABLE `r_t020604_t010302`
  ADD CONSTRAINT `R_T020604_T010302_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_02_06_04` (`F_00`),
  ADD CONSTRAINT `R_T020604_T010302_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Constraints for table `r_t030303_t030307`
--
ALTER TABLE `r_t030303_t030307`
  ADD CONSTRAINT `R_T030303_T030307_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_03_03_03` (`F_00`),
  ADD CONSTRAINT `R_T030303_T030307_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_03_03_07` (`F_00`);

--
-- Constraints for table `r_t030304_t030307`
--
ALTER TABLE `r_t030304_t030307`
  ADD CONSTRAINT `R_T030304_T030307_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_03_03_04` (`F_00`),
  ADD CONSTRAINT `R_T030304_T030307_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_03_03_07` (`F_00`);

--
-- Constraints for table `r_t030305_t030204`
--
ALTER TABLE `r_t030305_t030204`
  ADD CONSTRAINT `R_T030305_T030204_FK_01` FOREIGN KEY (`f_01`) REFERENCES `t_03_03_05` (`F_00`),
  ADD CONSTRAINT `R_T030305_T030204_FK_02` FOREIGN KEY (`f_02`) REFERENCES `t_03_02_04` (`F_00`);

--
-- Constraints for table `r_t030305_t030207`
--
ALTER TABLE `r_t030305_t030207`
  ADD CONSTRAINT `R_T030305_T030207_FK_01` FOREIGN KEY (`f_01`) REFERENCES `t_03_03_05` (`F_00`),
  ADD CONSTRAINT `R_T030305_T030207_FK_02` FOREIGN KEY (`f_02`) REFERENCES `t_03_02_07` (`F_00`);

--
-- Constraints for table `r_t030305_t030307`
--
ALTER TABLE `r_t030305_t030307`
  ADD CONSTRAINT `R_T030305_T030307_FK_01` FOREIGN KEY (`F_01`) REFERENCES `t_03_03_05` (`F_00`),
  ADD CONSTRAINT `R_T030305_T030307_FK_02` FOREIGN KEY (`F_02`) REFERENCES `t_03_03_07` (`F_00`);

--
-- Constraints for table `t_00_04_01`
--
ALTER TABLE `t_00_04_01`
  ADD CONSTRAINT `T_00_04_01_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_00_02_02` (`F_00`);

--
-- Constraints for table `t_01_01_03`
--
ALTER TABLE `t_01_01_03`
  ADD CONSTRAINT `T_01_01_03_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_01_01` (`F_00`),
  ADD CONSTRAINT `T_01_01_03_FK_02` FOREIGN KEY (`F_06`) REFERENCES `t_01_01_02` (`F_00`);

--
-- Constraints for table `t_01_01_04`
--
ALTER TABLE `t_01_01_04`
  ADD CONSTRAINT `T_01_01_04_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_01_03` (`F_00`);

--
-- Constraints for table `t_01_01_06`
--
ALTER TABLE `t_01_01_06`
  ADD CONSTRAINT `T_01_01_06_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_01_01_04` (`F_00`),
  ADD CONSTRAINT `T_01_01_06_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_01_01_05` (`F_00`);

--
-- Constraints for table `t_01_01_07`
--
ALTER TABLE `t_01_01_07`
  ADD CONSTRAINT `T_01_01_07_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_01_01_06` (`F_00`);

--
-- Constraints for table `t_01_02_03`
--
ALTER TABLE `t_01_02_03`
  ADD CONSTRAINT `T_01_02_03_FK_01` FOREIGN KEY (`F_06`) REFERENCES `t_01_02_01` (`F_00`),
  ADD CONSTRAINT `T_01_02_03_FK_02` FOREIGN KEY (`F_07`) REFERENCES `t_01_02_02` (`F_00`),
  ADD CONSTRAINT `T_01_02_03_FK_03` FOREIGN KEY (`F_08`) REFERENCES `t_01_04_07` (`F_00`),
  ADD CONSTRAINT `T_01_02_03_FK_04` FOREIGN KEY (`F_09`) REFERENCES `t_00_01_01` (`F_00`);

--
-- Constraints for table `t_01_03_02`
--
ALTER TABLE `t_01_03_02`
  ADD CONSTRAINT `T_01_03_02_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_01_03_01` (`F_00`),
  ADD CONSTRAINT `T_01_03_02_FK_02` FOREIGN KEY (`F_05`) REFERENCES `t_00_01_01` (`F_00`);

--
-- Constraints for table `t_01_04_03`
--
ALTER TABLE `t_01_04_03`
  ADD CONSTRAINT `T_01_04_03_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_04_02` (`F_00`);

--
-- Constraints for table `t_01_04_05`
--
ALTER TABLE `t_01_04_05`
  ADD CONSTRAINT `T_01_04_05_FK_01` FOREIGN KEY (`F_07`) REFERENCES `t_01_04_04` (`F_00`);

--
-- Constraints for table `t_01_04_07`
--
ALTER TABLE `t_01_04_07`
  ADD CONSTRAINT `T_01_04_07_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_04_06` (`F_00`),
  ADD CONSTRAINT `T_01_04_07_FK_02` FOREIGN KEY (`F_06`) REFERENCES `t_01_04_07` (`F_00`);

--
-- Constraints for table `t_01_04_08`
--
ALTER TABLE `t_01_04_08`
  ADD CONSTRAINT `T_01_04_08_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_01_04_07` (`F_00`);

--
-- Constraints for table `t_01_04_09`
--
ALTER TABLE `t_01_04_09`
  ADD CONSTRAINT `FK5d5f2io70jjhnxm49c4nsmtjm` FOREIGN KEY (`F_07`) REFERENCES `t_01_04_01` (`F_00`);

--
-- Constraints for table `t_01_04_10`
--
ALTER TABLE `t_01_04_10`
  ADD CONSTRAINT `FKfploqhev5tkv2uul79eyvwlsp` FOREIGN KEY (`F_02`) REFERENCES `t_01_04_08` (`F_00`),
  ADD CONSTRAINT `FKjucg9o5kabtxoi0j8qxf4bc0n` FOREIGN KEY (`F_01`) REFERENCES `t_01_04_09` (`F_00`),
  ADD CONSTRAINT `FKl4ebqb4p2x7bb2n7lg3k9pik9` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_05` (`F_00`),
  ADD CONSTRAINT `FKre9gryisoebvijo6siwxm4e3s` FOREIGN KEY (`F_03`) REFERENCES `t_01_04_07` (`F_00`);

--
-- Constraints for table `t_02_02_03`
--
ALTER TABLE `t_02_02_03`
  ADD CONSTRAINT `T_02_02_03_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_02_02_01` (`F_00`);

--
-- Constraints for table `t_02_02_05`
--
ALTER TABLE `t_02_02_05`
  ADD CONSTRAINT `T_02_02_05_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_02_04` (`F_00`);

--
-- Constraints for table `t_02_02_06`
--
ALTER TABLE `t_02_02_06`
  ADD CONSTRAINT `T_02_02_06_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_02_05` (`F_00`);

--
-- Constraints for table `t_02_02_07`
--
ALTER TABLE `t_02_02_07`
  ADD CONSTRAINT `T_02_02_07_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_01_03_02` (`F_00`),
  ADD CONSTRAINT `T_02_02_07_FK_02` FOREIGN KEY (`F_05`) REFERENCES `t_01_03_02` (`F_00`);

--
-- Constraints for table `t_02_02_08`
--
ALTER TABLE `t_02_02_08`
  ADD CONSTRAINT `T_02_02_08_FK_01` FOREIGN KEY (`F_05`) REFERENCES `t_02_02_02` (`F_00`),
  ADD CONSTRAINT `T_02_02_08_FK_02` FOREIGN KEY (`F_06`) REFERENCES `t_02_02_06` (`F_00`),
  ADD CONSTRAINT `T_02_02_08_FK_03` FOREIGN KEY (`F_07`) REFERENCES `t_02_02_03` (`F_00`);

--
-- Constraints for table `t_02_02_09`
--
ALTER TABLE `t_02_02_09`
  ADD CONSTRAINT `T_02_02_09_FK_01` FOREIGN KEY (`F_02`) REFERENCES `t_02_02_08` (`F_00`),
  ADD CONSTRAINT `T_02_02_09_FK_02` FOREIGN KEY (`F_03`) REFERENCES `t_01_04_07` (`F_00`);

--
-- Constraints for table `t_02_03_04`
--
ALTER TABLE `t_02_03_04`
  ADD CONSTRAINT `T_02_03_04_FK_01` FOREIGN KEY (`F_18`) REFERENCES `t_00_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_03_04_FK_02` FOREIGN KEY (`F_19`) REFERENCES `t_02_03_02` (`F_00`),
  ADD CONSTRAINT `T_02_03_04_FK_03` FOREIGN KEY (`F_20`) REFERENCES `t_01_04_01` (`F_00`),
  ADD CONSTRAINT `T_02_03_04_FK_04` FOREIGN KEY (`F_21`) REFERENCES `t_01_04_02` (`F_00`);

--
-- Constraints for table `t_02_03_05`
--
ALTER TABLE `t_02_03_05`
  ADD CONSTRAINT `T_02_03_05_FK_01` FOREIGN KEY (`F_10`) REFERENCES `t_02_03_04` (`F_00`);

--
-- Constraints for table `t_02_03_06`
--
ALTER TABLE `t_02_03_06`
  ADD CONSTRAINT `T_02_03_06_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_03_06_FK_02` FOREIGN KEY (`F_05`) REFERENCES `t_02_03_05` (`F_00`),
  ADD CONSTRAINT `T_02_03_06_FK_03` FOREIGN KEY (`F_06`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `t_02_03_07`
--
ALTER TABLE `t_02_03_07`
  ADD CONSTRAINT `T_02_03_07_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_03_03` (`F_00`),
  ADD CONSTRAINT `T_02_03_07_FK_02` FOREIGN KEY (`F_05`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_03_07_FK_03` FOREIGN KEY (`F_06`) REFERENCES `t_01_02_03` (`F_00`);

--
-- Constraints for table `t_02_04_03`
--
ALTER TABLE `t_02_04_03`
  ADD CONSTRAINT `T_02_04_03_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_04_02` (`F_00`);

--
-- Constraints for table `t_02_04_04`
--
ALTER TABLE `t_02_04_04`
  ADD CONSTRAINT `T_02_04_04_FK_01` FOREIGN KEY (`F_15`) REFERENCES `t_02_04_01` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_02` FOREIGN KEY (`F_16`) REFERENCES `t_02_01_04` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_03` FOREIGN KEY (`F_17`) REFERENCES `t_02_02_01` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_04` FOREIGN KEY (`F_18`) REFERENCES `t_02_01_05` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_05` FOREIGN KEY (`F_19`) REFERENCES `t_02_01_02` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_06` FOREIGN KEY (`F_20`) REFERENCES `t_02_01_03` (`F_00`),
  ADD CONSTRAINT `T_02_04_04_FK_07` FOREIGN KEY (`F_21`) REFERENCES `t_02_04_03` (`F_00`);

--
-- Constraints for table `t_02_04_05`
--
ALTER TABLE `t_02_04_05`
  ADD CONSTRAINT `T_02_04_05_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_03` FOREIGN KEY (`F_05`) REFERENCES `t_00_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_04` FOREIGN KEY (`F_06`) REFERENCES `t_00_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_04_05_FK_05` FOREIGN KEY (`F_07`) REFERENCES `t_00_01_01` (`F_00`);

--
-- Constraints for table `t_02_05_03`
--
ALTER TABLE `t_02_05_03`
  ADD CONSTRAINT `T_02_05_03_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_05_02` (`F_00`);

--
-- Constraints for table `t_02_05_04`
--
ALTER TABLE `t_02_05_04`
  ADD CONSTRAINT `T_02_05_04_FK_01` FOREIGN KEY (`F_16`) REFERENCES `t_02_05_01` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_02` FOREIGN KEY (`F_17`) REFERENCES `t_02_03_04` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_03` FOREIGN KEY (`F_18`) REFERENCES `t_02_01_05` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_04` FOREIGN KEY (`F_19`) REFERENCES `t_02_05_03` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_05` FOREIGN KEY (`F_20`) REFERENCES `t_02_01_02` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_06` FOREIGN KEY (`F_21`) REFERENCES `t_02_01_01` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_07` FOREIGN KEY (`F_22`) REFERENCES `t_02_04_04` (`F_00`),
  ADD CONSTRAINT `T_02_05_04_FK_08` FOREIGN KEY (`F_23`) REFERENCES `t_02_05_04` (`F_00`);

--
-- Constraints for table `t_02_05_05`
--
ALTER TABLE `t_02_05_05`
  ADD CONSTRAINT `T_02_05_05_FK_01` FOREIGN KEY (`F_06`) REFERENCES `t_02_05_04` (`F_00`);

--
-- Constraints for table `t_02_06_03`
--
ALTER TABLE `t_02_06_03`
  ADD CONSTRAINT `T_02_06_03_FK_01` FOREIGN KEY (`F_04`) REFERENCES `t_02_06_02` (`F_00`);

--
-- Constraints for table `t_02_06_04`
--
ALTER TABLE `t_02_06_04`
  ADD CONSTRAINT `T_02_06_04_FK_01` FOREIGN KEY (`F_12`) REFERENCES `t_02_05_04` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_02` FOREIGN KEY (`F_13`) REFERENCES `t_02_06_01` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_03` FOREIGN KEY (`F_14`) REFERENCES `t_02_01_05` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_04` FOREIGN KEY (`F_15`) REFERENCES `t_02_06_02` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_05` FOREIGN KEY (`F_16`) REFERENCES `t_02_01_02` (`F_00`),
  ADD CONSTRAINT `T_02_06_04_FK_06` FOREIGN KEY (`F_17`) REFERENCES `t_02_01_01` (`F_00`);

--
-- Constraints for table `t_03_01_02`
--
ALTER TABLE `t_03_01_02`
  ADD CONSTRAINT `FKstsyiqy29nd3jit5aqxo901bn` FOREIGN KEY (`F_00`) REFERENCES `t_03_01_01` (`F_00`);

--
-- Constraints for table `t_03_01_03`
--
ALTER TABLE `t_03_01_03`
  ADD CONSTRAINT `FK8pwa5h3sfhh159lyodsjr7v7t` FOREIGN KEY (`F_00`) REFERENCES `t_03_01_01` (`F_00`);

--
-- Constraints for table `t_03_01_05`
--
ALTER TABLE `t_03_01_05`
  ADD CONSTRAINT `FKm6d6tm3wmkopl5utx7anh5suw` FOREIGN KEY (`F_00`) REFERENCES `t_03_01_04` (`F_00`);

--
-- Constraints for table `t_03_01_06`
--
ALTER TABLE `t_03_01_06`
  ADD CONSTRAINT `FKfwd88fag562777n9e2yojop39` FOREIGN KEY (`F_00`) REFERENCES `t_03_01_04` (`F_00`);

--
-- Constraints for table `t_03_01_07`
--
ALTER TABLE `t_03_01_07`
  ADD CONSTRAINT `FKn5h40fqfwftknoss2tkku4kdy` FOREIGN KEY (`F_00`) REFERENCES `t_03_01_04` (`F_00`);

--
-- Constraints for table `t_03_02_02`
--
ALTER TABLE `t_03_02_02`
  ADD CONSTRAINT `T_03_02_02_FK_01` FOREIGN KEY (`F_08`) REFERENCES `t_03_02_01` (`F_00`);

--
-- Constraints for table `t_03_02_03`
--
ALTER TABLE `t_03_02_03`
  ADD CONSTRAINT `T_03_02_03_FK_01` FOREIGN KEY (`F_08`) REFERENCES `t_01_04_03` (`F_00`);

--
-- Constraints for table `t_03_02_07`
--
ALTER TABLE `t_03_02_07`
  ADD CONSTRAINT `T_03_02_07_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_03_01_02` (`F_00`),
  ADD CONSTRAINT `T_03_02_07_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_01` (`F_00`);

--
-- Constraints for table `t_03_02_08`
--
ALTER TABLE `t_03_02_08`
  ADD CONSTRAINT `T_03_02_08_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_03_01_03` (`F_00`),
  ADD CONSTRAINT `T_03_02_08_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_01_04_01` (`F_00`);

--
-- Constraints for table `t_03_03_01`
--
ALTER TABLE `t_03_03_01`
  ADD CONSTRAINT `T_03_03_01_FK_01` FOREIGN KEY (`F_06`) REFERENCES `t_03_02_05` (`F_00`);

--
-- Constraints for table `t_03_03_02`
--
ALTER TABLE `t_03_03_02`
  ADD CONSTRAINT `FKm0u49vcytxoa5vkdyw3c2j7ct` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_01` (`F_00`),
  ADD CONSTRAINT `T_03_03_02_FK_01` FOREIGN KEY (`F_11`) REFERENCES `t_03_02_08` (`F_00`),
  ADD CONSTRAINT `T_03_03_02_FK_02` FOREIGN KEY (`F_12`) REFERENCES `t_01_04_03` (`F_00`);

--
-- Constraints for table `t_03_03_03`
--
ALTER TABLE `t_03_03_03`
  ADD CONSTRAINT `FKdlyi8pcitn594pkyqvw7aia4l` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_02` (`F_00`),
  ADD CONSTRAINT `T_03_03_03_FK_01` FOREIGN KEY (`F_13`) REFERENCES `t_03_01_05` (`F_00`);

--
-- Constraints for table `t_03_03_04`
--
ALTER TABLE `t_03_03_04`
  ADD CONSTRAINT `FKfbkrrnm1ngghdwl80nbkaob6d` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_02` (`F_00`),
  ADD CONSTRAINT `T_03_03_04_FK_01` FOREIGN KEY (`F_13`) REFERENCES `t_03_01_06` (`F_00`);

--
-- Constraints for table `t_03_03_05`
--
ALTER TABLE `t_03_03_05`
  ADD CONSTRAINT `FKaf2mrh81h9kterqpqa9fmb7nq` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_02` (`F_00`),
  ADD CONSTRAINT `T_03_03_05_FK_01` FOREIGN KEY (`F_13`) REFERENCES `t_03_01_07` (`F_00`);

--
-- Constraints for table `t_03_03_06`
--
ALTER TABLE `t_03_03_06`
  ADD CONSTRAINT `T_03_03_06_FK_01` FOREIGN KEY (`F_03`) REFERENCES `t_03_02_04` (`F_00`),
  ADD CONSTRAINT `T_03_03_06_FK_02` FOREIGN KEY (`F_04`) REFERENCES `t_03_02_05` (`F_00`),
  ADD CONSTRAINT `T_03_03_06_FK_03` FOREIGN KEY (`F_05`) REFERENCES `t_03_02_02` (`F_00`);

--
-- Constraints for table `t_03_03_07`
--
ALTER TABLE `t_03_03_07`
  ADD CONSTRAINT `FKdvhk5c3134g3x8jcbepos8psp` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_01` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_01` FOREIGN KEY (`F_17`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_02` FOREIGN KEY (`F_18`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_03` FOREIGN KEY (`F_19`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_04` FOREIGN KEY (`F_20`) REFERENCES `t_03_02_08` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_05` FOREIGN KEY (`F_21`) REFERENCES `t_03_03_06` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_06` FOREIGN KEY (`F_22`) REFERENCES `t_03_03_02` (`F_00`),
  ADD CONSTRAINT `T_03_03_07_FK_07` FOREIGN KEY (`F_23`) REFERENCES `t_03_03_02` (`F_00`);

--
-- Constraints for table `t_03_03_08`
--
ALTER TABLE `t_03_03_08`
  ADD CONSTRAINT `FKahj34utm6aoq9ftq4960frf9v` FOREIGN KEY (`F_00`) REFERENCES `t_03_03_01` (`F_00`),
  ADD CONSTRAINT `T_03_02_09_FK_01` FOREIGN KEY (`F_13`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_02_09_FK_02` FOREIGN KEY (`F_14`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_02_09_FK_03` FOREIGN KEY (`F_15`) REFERENCES `t_03_02_06` (`F_00`),
  ADD CONSTRAINT `T_03_02_10_FK_01` FOREIGN KEY (`F_16`) REFERENCES `t_03_03_07` (`F_00`);

--
-- Constraints for table `t_03_03_09`
--
ALTER TABLE `t_03_03_09`
  ADD CONSTRAINT `T_03_03_09_FK_01` FOREIGN KEY (`F_11`) REFERENCES `t_03_02_05` (`F_00`),
  ADD CONSTRAINT `T_03_03_09_FK_02` FOREIGN KEY (`F_12`) REFERENCES `t_03_01_08` (`F_00`),
  ADD CONSTRAINT `T_03_03_09_FK_03` FOREIGN KEY (`F_13`) REFERENCES `t_03_03_02` (`F_00`);
COMMIT;
