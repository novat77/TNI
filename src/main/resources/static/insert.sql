INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');


INSERT INTO users (username, password, enabled) VALUES ('admin', '$2a$10$bN7OWEvi6rTqJEYbZfDOg.FHmG.xPTDxJR1k9LzsR4O6Nt8zuIKwq', 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);

commit;