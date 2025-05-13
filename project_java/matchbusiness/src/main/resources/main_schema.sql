-- Tabela de Usuários
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Tabela de Conexões (Matches)
CREATE TABLE matches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario1_id INT NOT NULL,
    usuario2_id INT NOT NULL,
    CONSTRAINT fk_usuario1 FOREIGN KEY (usuario1_id) REFERENCES usuarios(id),
    CONSTRAINT fk_usuario2 FOREIGN KEY (usuario2_id) REFERENCES usuarios(id)
);

-- Tabela de Encontros
CREATE TABLE encontros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario1_id INT NOT NULL,
    usuario2_id INT NOT NULL,
    dataHora TIMESTAMP NOT NULL,
    local VARCHAR(255),
    CONSTRAINT fk_encontro_usuario1 FOREIGN KEY (usuario1_id) REFERENCES usuarios(id),
    CONSTRAINT fk_encontro_usuario2 FOREIGN KEY (usuario2_id) REFERENCES usuarios(id)
);

-- Tabela de Feedback
CREATE TABLE feedback (
    id INT AUTO_INCREMENT PRIMARY KEY,
    encontro_id INT NOT NULL,
    avaliador_id INT NOT NULL,
    nota INT NOT NULL CHECK (nota BETWEEN 1 AND 5),
    comentario VARCHAR(500),
    CONSTRAINT fk_feedback_encontro FOREIGN KEY (encontro_id) REFERENCES encontros(id),
    CONSTRAINT fk_feedback_avaliador FOREIGN KEY (avaliador_id) REFERENCES usuarios(id)
);