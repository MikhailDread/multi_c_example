CREATE TABLE IF NOT EXISTS item (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    last_name VARCHAR(255),
    age INT,
    count INT,
    amount DECIMAL(10, 2),
    purchase_date DATE,
    CONSTRAINT fk_user_item FOREIGN KEY (id) REFERENCES item(id)
);

CREATE TABLE IF NOT EXISTS users_items (
    user_id INT,
    item_id INT,
    PRIMARY KEY (user_id, item_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (item_id) REFERENCES item(id)
);
