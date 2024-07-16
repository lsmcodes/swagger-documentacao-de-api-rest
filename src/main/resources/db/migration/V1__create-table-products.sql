CREATE TABLE products(
        id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        price DOUBLE NOT NULL,
        quantity INT NOT NULL
);