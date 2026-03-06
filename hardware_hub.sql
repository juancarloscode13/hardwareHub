DROP DATABASE IF EXISTS hardware_hub;
CREATE DATABASE IF NOT EXISTS hardware_hub CHARACTER SET latin1 COLLATE latin_spanish_1;

-- ################################################################
-- TABLAS
-- ################################################################

CREATE TABLE IF NOT EXISTS cpu(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    cpu_socket VARCHAR(15) NOT NULL,
    cores JSON NOT NULL,
    3dvcache BOOLEAN,
    arquitectura varchar(25) NOT NULL,
    precio DECIMAL(7, 2),
    hilos TINYINT,
    hyperthreading BOOLEAN,
    frecuencia_max DECIMAL(5, 3),
    frecuencia_min DECIMAL(5, 3),
    cantidad_cache SMALLINT,
    tdp INTEGER,
    temperatura_max TINYINT,
    conectividad_pcie TINYINT,
    graficos_integrados VARCHAR(40) NULL
);

CREATE TABLE IF NOT EXISTS gpu(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    ensambladora VARCHAR(25) NOT NULL,
    nucleos JSON,
    frecuencia_max DECIMAL(5, 3),
    frecuencia_min DECIMAL(5, 3),
	temperatura_max TINYINT,
	cantidad_vram SMALLINT,
    tipo_vram VARCHAR(10),
    ancho_banda SMALLINT,
    arquitectura VARCHAR(25) NOT NULL,
    tdp SMALLINT,
    conectividad_pcie TINYINT,
    precio DECIMAL(7, 2),
    generacion VARCHAR(25) NOT NULL,
    alto_gpu SMALLINT
);

CREATE TABLE IF NOT EXISTS psu(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    precio DECIMAL(7,2),
    modular BOOLEAN,
    potencia SMALLINT,
    certificacion VARCHAR(35) NOT NULL,
    factor_forma VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS ram(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    precio DECIMAL(7, 2),
    velocidad SMALLINT,
    cantidad TINYINT,
    modulos TINYINT,
    capacidad_por_modulo TINYINT,
    tipo VARCHAR(5) NOT NULL,
    latencia TINYINT
);

CREATE TABLE IF NOT EXISTS almacenamiento(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    precio DECIMAL(7, 2),
    capacidad DECIMAL(4, 3),
    tipo VARCHAR(25) NOT NULL,
    formato VARCHAR(10) NULL,
    velocidad_lectura SMALLINT NOT NULL,
    velocidad_escritura SMALLINT NOT NULL,
    conectividad VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS refrigeracion(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    precio DECIMAL(7, 2),
    socket_compatible VARCHAR(15) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    atributos JSON
);

CREATE TABLE IF NOT EXISTS caja(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    precio DECIMAL(7, 2),
    formato VARCHAR(15) NOT NULL,
    placas_base_compatibles VARCHAR(15) NOT NULL,
    color VARCHAR(25),
    dimensiones JSON NOT NULL,
    psu_compatible VARCHAR(10),
    espacio_max_gpu JSON,
    bahias_25 TINYINT,
    bahias_35 TINYINT,
    espacio_ventiladores JSON,
    ventiladores_incluidos BOOLEAN,
    soportes_radiador JSON,
    conectividad_frontal JSON,
    rgb BOOLEAN,
    altura_max_enfriador_cpu SMALLINT
);

CREATE TABLE IF NOT EXISTS placa_base(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    precio DECIMAL(7, 2),
    socket_compatible VARCHAR(15) NOT NULL,
    chipset VARCHAR(10) NOT NULL,
    memoria_maxima SMALLINT,
    espacios_ram TINYINT,
    tipo_ram_soportada VARCHAR(10),
    formato VARCHAR(15) NOT NULL,
    ranuras_expansion TINYINT,
    ranuras_almacenamiento TINYINT,
    puertos_usb TINYINT,
    conectividad_interna JSON,
    wifi_soportado ENUM('WI-FI 1', 'WI-FI 2', 'WI-FI 3', 'WI-FI 4', 'WI-FI 5', 'WI-FI 6', 'WI-FI 6E', 'WI-FI 7')
);

CREATE TABLE IF NOT EXISTS fabricante(
    id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS montaje(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ram INTEGER UNSIGNED NOT NULL,
    id_cpu INTEGER UNSIGNED NOT NULL,
    id_gpu INTEGER UNSIGNED NULL,
    id_refrigeracion INTEGER UNSIGNED NOT NULL,
    id_caja INTEGER UNSIGNED NOT NULL,
    id_placa_base INTEGER UNSIGNED NOT NULL,
    id_psu INTEGER UNSIGNED NOT NULL,
    id_almacenamiento INTEGER UNSIGNED NOT NULL,
    id_usuario INTEGER UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(40) NOT NULL,
    email VARCHAR(100) NOT NULL,
    contraseña VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS publicacion(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    contenido_texto VARCHAR(300),
    multimedia MEDIUMBLOB,
    id_usuario INTEGER UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS publicacion_montaje(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    contenido_texto VARCHAR(300),
    multimedia MEDIUMBLOB,
    id_montaje INTEGER UNSIGNED NOT NULL,
    id_usuario INTEGER UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS comentario(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    texto_contenido VARCHAR(200) NOT NULL,
    id_usuario INTEGER UNSIGNED NOT NULL,
    id_comentario INTEGER UNSIGNED NULL,
    id_publicacion INTEGER UNSIGNED NULL,
    id_publicacion_montaje INTEGER UNSIGNED NULL
);

-- ################################################################
-- Relaciones y constraints
-- ################################################################

-- Tabla montaje
ALTER TABLE montaje
ADD CONSTRAINT fk_montaje_cpu
    FOREIGN KEY (id_cpu) REFERENCES cpu(id)
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_montaje_gpu
    FOREIGN KEY (id_gpu) REFERENCES gpu(id)
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_montaje_ram
    FOREIGN KEY (id_ram) REFERENCES ram(id)
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_montaje_refrigeracion
    FOREIGN KEY (id_refrigeracion) REFERENCES refrigeracion(id)
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_montaje_caja
    FOREIGN KEY (id_caja) REFERENCES caja(id)
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_montaje_placa_base
    FOREIGN KEY (id_placa_base) REFERENCES placa_base(id)
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_montaje_psu
    FOREIGN KEY (id_psu) REFERENCES psu(id)
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_montaje_almacenamiento
    FOREIGN KEY (id_almacenamiento) REFERENCES almacenamiento(id)
    ON UPDATE CASCADE,
ADD CONSTRAINT fk_montaje_usuario
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
    ON UPDATE CASCADE;

-- Tabla publicacion
ALTER TABLE publicacion
ADD CONSTRAINT fk_publicacion_usuario
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- Tabla publicacion_montaje
ALTER TABLE publicacion_montaje
ADD CONSTRAINT fk_publicacion_montaje_usuario
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_publicacion_montaje_montaje
    FOREIGN KEY (id_montaje) REFERENCES montaje(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- Tabla comentario
ALTER TABLE comentario
ADD CONSTRAINT fk_comentario_usuario
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_comentario_respuesta
    FOREIGN KEY (id_comentario) REFERENCES comentario(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_comentario_publicacion
	FOREIGN KEY (id_publicacion) REFERENCES publicacion(id)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_comentario_publicacion_montaje
	FOREIGN KEY (id_publicacion_montaje) REFERENCES publicacion_montaje(id)
    ON DELETE CASCADE ON UPDATE CASCADE;

-- Tabla fabricante
ALTER TABLE fabricante
ADD CONSTRAINT fk_fabricante_cpu
    FOREIGN KEY (id) REFERENCES cpu(id_fabricante)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_fabricante_gpu
    FOREIGN KEY (id) REFERENCES gpu(id_fabricante)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_fabricante_psu
    FOREIGN KEY (id) REFERENCES psu(id_fabricante)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_fabricante_ram
    FOREIGN KEY (id) REFERENCES ram(id_fabricante)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_fabricante_almacenamiento
    FOREIGN KEY (id) REFERENCES almacenamiento(id_fabricante)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_fabricante_refrigeracion
    FOREIGN KEY (id) REFERENCES refrigeracion(id_fabricante)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_fabricante_caja
    FOREIGN KEY (id) REFERENCES caja(id_fabricante)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_fabricante_placa_base
    FOREIGN KEY (id) REFERENCES placa_base(id_fabricante)
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT uq_fabricante_nombre
    UNIQUE (nombre);

-- Agregar constraints UNIQUE para modelo en tablas de componentes
ALTER TABLE cpu
ADD CONSTRAINT uq_cpu_modelo
    UNIQUE (modelo);

ALTER TABLE gpu
ADD CONSTRAINT uq_gpu_modelo
    UNIQUE (modelo);

ALTER TABLE psu
ADD CONSTRAINT uq_psu_modelo
    UNIQUE (modelo);

ALTER TABLE ram
ADD CONSTRAINT uq_ram_modelo
    UNIQUE (modelo);

ALTER TABLE almacenamiento
ADD CONSTRAINT uq_almacenamiento_modelo
    UNIQUE (modelo);

ALTER TABLE refrigeracion
ADD CONSTRAINT uq_refrigeracion_modelo
    UNIQUE (modelo);

ALTER TABLE caja
ADD CONSTRAINT uq_caja_modelo
    UNIQUE (modelo);

ALTER TABLE placa_base
ADD CONSTRAINT uq_placa_base_modelo
    UNIQUE (modelo);

-- Agregar constraint UNIQUE para email en tabla usuario
ALTER TABLE usuario
ADD CONSTRAINT uq_usuario_email
    UNIQUE (email);

-- ################################################################
-- INSERTS
-- ################################################################

INSERT INTO fabricante (nombre) VALUES
('Abysm'), ('Acer'), ('Adata'), ('Aerocool'),
('AMD'), ('Antec'), ('AORUS'), ('Artic'),
('Asrock'), ('Asus'), ('Be quiet'), ('Biostar'),
('Cooler Master'), ('Corsair'), ('Cougar'), ('Crucial'),
('DeepCool'), ('Dell'), ('ENDORFY'), ('Fractal'),
('Forgeon'), ('Gainward'), ('G.Skill'), ('Gigabyte'),
('Hyte'), ('Inno3D'), ('Intel'), ('Kingston'),
('Lian-Li'), ('Lexar'), ('Lenovo'), ('Mars Gaming'),
('Maxsun'), ('MSI'), ('Nfortec'), ('NOX'),
('NZXT'), ('Nvidia'), ('Palit'), ('Patriot'),
('Phanteks'), ('PNY'), ('PowerColor'), ('QNAP'),
('Samsung'), ('Sandisk'), ('Sapphire'), ('Seagate'),
('Seasonic'), ('Sparkle'), ('Synology'), ('Tempest'),
('Thermaltake'), ('TUF Gaming'), ('Valkyrie'), ('Western Digital'),
('XFX'), ('Zotac');