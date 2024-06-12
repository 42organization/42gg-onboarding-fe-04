--- DROP DATABASE IF EXISTS `school`;

--- CREATE DATABASE IF NOT EXISTS `school` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

--- USE `school`;

CREATE TABLE IF NOT EXISTS `student` (
    `id` INTEGER AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `birth_date` DATE NOT NULL,
    `status` ENUM('attend', 'drop', 'graduate') NOT NULL,
    `total_credit` INTEGER NOT NULL,
    `enrolled_credit` INTEGER NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL,
    CONSTRAINT `unique_student_name_birth_date` UNIQUE (`name`, `birth_date`)
);

CREATE INDEX `index_student_name_birth_date` ON `student` (`name`, `birth_date`);

CREATE TABLE IF NOT EXISTS `course` (
    `id` INTEGER AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `professor_name` VARCHAR(255) NOT NULL,
    `credit` INTEGER NOT NULL,
    `is_true` BOOLEAN NOT NULL,
    `max_student_count` INTEGER NOT NULL,
    `current_student_count` INTEGER NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL,
    CONSTRAINT `unique_course_name_professor_name_credit` UNIQUE (`name`, `professor_name`, `credit`)
);

CREATE TABLE IF NOT EXISTS `enrollment` (
    `id` INTEGER AUTO_INCREMENT PRIMARY KEY,
    `course_id` INTEGER NOT NULL,
    `student_id` INTEGER NOT NULL,
    `status` ENUM('enroll', 'cancel', 'success') NOT NULL,
    `created_at` DATETIME NOT NULL,
    `updated_at` DATETIME NOT NULL,
    CONSTRAINT `fk_enrollment_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
    CONSTRAINT `fk_enrollment_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
);