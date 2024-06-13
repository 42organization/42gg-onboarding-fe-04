CREATE TABLE student (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    birth_date DATETIME NOT NULL,
    status VARCHAR(10) NOT NULL,
    total_credit INT NOT NULL,
    enrolled_credit INT NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL
);

TABLE student_course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    status VARCHAR(10) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (course_id) REFERENCES course(id)
);

TABLE course (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    professor_name VARCHAR(20) NOT NULL,
    credit INT NOT NULL,
    is_true BOOLEAN NOT NULL,
    max_student_count INT NOT NULL,
    current_student_count INT NOT NULL
);
