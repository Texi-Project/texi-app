INSERT INTO texi.user (first_name,last_name,password,photo_url,username,birthday,status)
VALUES ('Super', 'Admin Jane', '$2a$10$5rd4PENFLH66OMeTLczdpur/jJDu4ldGoas1i072SvJ3RpvjmE5fS','https://fcw.com/-/media/GIG/FCWNow/Topics/Concepts/smiley.png','super@texi.com', NOW() - INTERVAL 80 DAY, 'ACTIVE');

INSERT INTO texi.role (role_id, role, user_id) VALUES (1, 'ROLE_USER', 1);
INSERT INTO texi.role (role_id, role, user_id) VALUES (2, 'ROLE_ADMIN', 1);
# INSERT INTO texi.user_role (user_id, role_id) VALUES (1, 1);
# INSERT INTO texi.user_role (user_id, role_id) VALUES (1, 2);

INSERT INTO texi.role (role_id, role, user_id) VALUES (1, 'SUPER', 9);
INSERT INTO texi.role (role_id, role, user_id) VALUES (2, 'ADMIN', 9);
INSERT INTO texi.role (role_id, role, user_id) VALUES (3, 'USER', 9);
INSERT INTO texi.role (role_id, role, user_id) VALUES (1, 'ROLE_USER', 9);
INSERT INTO texi.role (role_id, role, user_id) VALUES (2, 'ROLE_ADMIN', 9);

INSERT INTO texi.user (first_name,last_name,password,photo_url,username,birthday,status)
VALUES ('Admin', 'Admin Mark', '$2a$10$5rd4PENFLH66OMeTLczdpur/jJDu4ldGoas1i072SvJ3RpvjmE5fS','https://fcw.com/-/media/GIG/FCWNow/Topics/Concepts/smiley.png','admin@texi.com', NOW() - INTERVAL 80 DAY, 'ACTIVE');

INSERT INTO texi.role (role_id, role, user_id) VALUES (3, 'ROLE_USER',2);
INSERT INTO texi.role (role_id, role, user_id) VALUES (4, 'ROLE_ADMIN',2);
# INSERT INTO texi.user_role (user_id, role_id) VALUES (2, 3);
# INSERT INTO texi.user_role (user_id, role_id) VALUES (2, 4);
INSERT INTO texi.role (role_id, role) VALUES (4, 'ROLE_USER');
INSERT INTO texi.role (role_id, role) VALUES (5, 'ROLE_ADMIN');
INSERT INTO texi.user_role (user_id, role_id) VALUES (2, 3);
INSERT INTO texi.user_role (user_id, role_id) VALUES (2, 4);

# delete from texi.user where username = 'admin';
# delete from texi.role where role_id = 1;