------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `student` (
  `id` INTEGER AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `birth_date` DATETIME NOT NULL,
  `status` ENUM('attend', 'drop', 'graduate') NOT NULL,
  `total_credit` INTEGER NOT NULL,
  `enrolled_credit` INTEGER NOT NULL,
  `created_by` DATETIME NOT NULL,
  `updated_by` DATETIME NOT NULL,
  CONSTRAINT `unique_student_name_birth_date` UNIQUE (`name`, `birth_date`)
);
CREATE INDEX IF NOT EXISTS `student_name_birth_date_index` ON `student` (`name`, `birth_date`);
------------------------------------------------------------
------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `course` (
  `id` INTEGER AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `professor_name` VARCHAR(255) NOT NULL,
  `credit` INTEGER NOT NULL,
  `is_true` BOOLEAN NOT NULL,
  `max_student_count` INTEGER NOT NULL,
  `current_student_count` INTEGER NOT NULL,
  `created_by` DATETIME NOT NULL,
  `updated_by` DATETIME NOT NULL,
  CONSTRAINT `unique_course_name_professor_name_credit` UNIQUE (`name`, `professor_name`, `credit`)
);
------------------------------------------------------------
------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `student_course` (
  `id` INTEGER AUTO_INCREMENT PRIMARY KEY,
  `course_id` INTEGER NOT NULL,
  `student_id` INTEGER NOT NULL,
  `status` ENUM('enroll', 'cancel') NOT NULL,
  `created_by` DATETIME NOT NULL,
  `updated_by` DATETIME NOT NULL,
  CONSTRAINT `fk_student_course_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `fk_student_course_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
);
------------------------------------------------------------