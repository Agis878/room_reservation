-- Insert 1
INSERT INTO users (username, password, first_name, last_name, role, active)
VALUES ('john_doe', '{noop}Password123', 'John', 'Doe', 'ROLE_USER', true);

-- Insert 2
INSERT INTO users (username, password, first_name, last_name, role, active)
VALUES ('jane_smith', '{noop}SecurePass789', 'Jane', 'Smith', 'ROLE_USER', true);

-- Insert 3
INSERT INTO users (username, password, first_name, last_name, role, active)
VALUES ('admin', '{noop}AdminPass123', 'Admin', 'User', 'ROLE_ADMIN', true);

-- Insert 4
INSERT INTO users (username, password, first_name, last_name, role, active)
VALUES ('alice_wonder', '{noop}Wonderland123', 'Alice', 'Wonder', 'ROLE_USER', true);

-- Insert 5
INSERT INTO users (username, password, first_name, last_name, role, active)
VALUES ('bob_marley', '{noop}Reggae123', 'Bob', 'Marley', 'ROLE_USER', true);

-- Insert 6
INSERT INTO users (username, password, first_name, last_name, role, active)
VALUES ('tech_guru', '{noop}TechPass789', 'Tech', 'Guru', 'ROLE_USER', true);

-- Insert 7
INSERT INTO users (username, password, first_name, last_name, role, active)
VALUES ('developer_x', '{noop}DevPass123', 'Developer', 'X', 'ROLE_USER', true);

-- Insert 8
INSERT INTO users (username, password, first_name, last_name, role, active)
VALUES ('power_user', '{noop}PowerPass456', 'Power', 'User', 'ROLE_USER', true);

-- Insert 9
INSERT INTO users (username, password, first_name, last_name, role, active)
VALUES ('security_ninja', '{noop}SecureNinja789', 'Security', 'Ninja', 'ROLE_USER', true);

-- Insert 10
INSERT INTO users (username, password, first_name, last_name, role, active)
VALUES ('java_programmer', '{noop}JavaPass123', 'Java', 'Programmer', 'ROLE_USER', true);


-- Insert 1
INSERT INTO rooms (name, seats_qty, available_status)
VALUES ('Conference Room A', 20, 'AVAILABLE');

-- Insert 2
INSERT INTO rooms (name, seats_qty, available_status)
VALUES ('Meeting Room 1', 10, 'AVAILABLE');

-- Insert 3
INSERT INTO rooms (name, seats_qty, available_status)
VALUES ('Boardroom', 15, 'AVAILABLE');

-- Insert 4
INSERT INTO rooms (name, seats_qty, available_status)
VALUES ('Training Room B', 30, 'AVAILABLE');

-- Insert 5
INSERT INTO rooms (name, seats_qty, available_status)
VALUES ('Huddle Room', 6, 'AVAILABLE');

-- Insert 6
INSERT INTO rooms (name, seats_qty, available_status)
VALUES ('Presentation Room', 25, 'AVAILABLE');

-- Insert 7
INSERT INTO rooms (name, seats_qty, available_status)
VALUES ('Study Room 2', 8, 'AVAILABLE');

-- Insert 8
INSERT INTO rooms (name, seats_qty, available_status)
VALUES ('Creative Space', 12, 'AVAILABLE');

-- Insert 9
INSERT INTO rooms (name, seats_qty, available_status)
VALUES ('Quiet Room', 4, 'AVAILABLE');

-- Insert 10
INSERT INTO rooms (name, seats_qty, available_status)
VALUES ('Collaboration Zone', 18, 'AVAILABLE');

-- Insert 1
INSERT INTO reservations (reservation_start, reservation_end, reservation_status, reservation_date, room_id, user_id)
VALUES ('2023-11-01', '2023-11-03', 'active', '2023-10-15', 1, 1);

-- Insert 2
INSERT INTO reservations (reservation_start, reservation_end, reservation_status, reservation_date, room_id, user_id)
VALUES ('2023-11-05', '2023-11-08', 'active', '2023-10-16', 2, 2);

-- Insert 3
INSERT INTO reservations (reservation_start, reservation_end, reservation_status, reservation_date, room_id, user_id)
VALUES ('2023-11-10', '2023-11-15', 'active', '2023-10-17', 3, 3);

-- Insert 4
INSERT INTO reservations (reservation_start, reservation_end, reservation_status, reservation_date, room_id, user_id)
VALUES ('2023-11-20', '2023-11-22', 'active', '2023-10-18', 4, 4);

-- Insert 5
INSERT INTO reservations (reservation_start, reservation_end, reservation_status, reservation_date, room_id, user_id)
VALUES ('2023-11-25', '2023-11-27', 'active', '2023-10-19', 5, 5);
