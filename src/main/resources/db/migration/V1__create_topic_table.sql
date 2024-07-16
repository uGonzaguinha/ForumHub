CREATE TABLE IF NOT EXISTS topic (
                                     id SERIAL PRIMARY KEY,
                                     titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    estado BOOLEAN NOT NULL,
    autor VARCHAR(255) NOT NULL,
    curso VARCHAR(255) NOT NULL
    );
