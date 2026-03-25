DROP DATABASE IF EXISTS hardware_hub;
CREATE DATABASE IF NOT EXISTS hardware_hub CHARACTER SET latin1;
USE hardware_hub;

-- ################################################################
-- TABLAS
-- ################################################################

CREATE TABLE IF NOT EXISTS cpu(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    cpu_socket VARCHAR(15) NOT NULL,
    cores JSON NOT NULL, -- si fabricante = intel, power cores y efficient cores; si fabricante = amd, cores y ccd
    cache_apilada BOOLEAN, -- 3d-v cache
    arquitectura varchar(25) NOT NULL,
    precio DECIMAL(7, 2),
    hilos TINYINT,
    hyperthreading BOOLEAN,
    frecuencia_max DECIMAL(5, 3),
    frecuencia_min DECIMAL(5, 3),
    cantidad_cache SMALLINT,
    tdp SMALLINT NULL,
    temperatura_max TINYINT,
    conectividad_pcie TINYINT,
    graficos_integrados VARCHAR(40) NULL,
    puntuacion_passmark INTEGER UNSIGNED
);

CREATE TABLE IF NOT EXISTS gpu(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    ensambladora VARCHAR(25) NOT NULL,
    nucleos JSON, -- si fabricante = nvidia, cuda y tensor; si fabricante = amd, unidades de computo (CU); si fabricante = intel, Xe Cores.
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
    alto_gpu SMALLINT,
    puntuacion_passmark INTEGER UNSIGNED
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
    atributos JSON -- si tipo = liquida, tamaño_radiador; si tipo = aire, alto y numero_ventiladores
);

CREATE TABLE IF NOT EXISTS caja(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    modelo VARCHAR(75) NOT NULL,
    id_fabricante INTEGER UNSIGNED NOT NULL,
    precio DECIMAL(7, 2),
    formato VARCHAR(15) NOT NULL,
    placas_base_compatibles VARCHAR(15) NOT NULL,
    color VARCHAR(25),
    dimensiones JSON NOT NULL, -- alto, ancho, largo
    psu_compatible VARCHAR(10),
    longitud_max_gpu SMALLINT,
    bahias_25 TINYINT,
    bahias_35 TINYINT,
    espacio_ventiladores JSON, -- superiores, laterales, frontal, trasero, inferior
    ventiladores_incluidos BOOLEAN,
    soportes_radiador JSON, -- superior, inferior, frontal, lateral
    conectividad_frontal JSON, -- tipo de puerto y numero
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
    conectividad_interna JSON, -- tipo de puerto y numero
    wifi_soportado VARCHAR(10)
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
    icono_perfil MEDIUMBLOB,
    nombre VARCHAR(40) NOT NULL,
    email VARCHAR(100) NOT NULL,
    contraseña VARCHAR(100),
    rol VARCHAR(40)
);

-- Tabla refresh_token para almacenar tokens de usuarios.
CREATE TABLE IF NOT EXISTS refresh_token (
    id          INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    token       VARCHAR(255) NOT NULL UNIQUE,
    id_usuario  INTEGER UNSIGNED NOT NULL,
    expiracion  DATETIME NOT NULL,
    revocado    BOOLEAN NOT NULL DEFAULT FALSE,
    creado_en   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_refresh_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS publicacion(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    contenido_texto VARCHAR(300),
    multimedia MEDIUMBLOB,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_montaje INTEGER UNSIGNED NULL,
    id_usuario INTEGER UNSIGNED NOT NULL
);

CREATE TABLE IF NOT EXISTS comentario(
	id INTEGER UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    texto_contenido VARCHAR(200) NOT NULL,
    likes INTEGER UNSIGNED NOT NULL DEFAULT 0,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    id_usuario INTEGER UNSIGNED NOT NULL,
    id_comentario INTEGER UNSIGNED NULL,
    id_publicacion INTEGER UNSIGNED NULL
);
-- Tabla intermedia para implementar la lógica de seguidores entre usuarios. Un usuario puede seguir a muchos otros usuarios, y un usuario puede ser seguido por muchos otros usuarios.
-- Se establece una restricción para evitar que un usuario se siga a sí mismo.
CREATE TABLE IF NOT EXISTS usuario_seguidor (
    id_seguidor INTEGER UNSIGNED NOT NULL,
    id_seguido  INTEGER UNSIGNED NOT NULL,
    PRIMARY KEY (id_seguidor, id_seguido),
    CONSTRAINT fk_us_seguidor FOREIGN KEY (id_seguidor) REFERENCES usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_us_seguido  FOREIGN KEY (id_seguido)  REFERENCES usuario(id) ON DELETE CASCADE,
    CONSTRAINT ck_no_self_follow CHECK (id_seguidor <> id_seguido)
);

-- Tabla de reacciones: cada usuario puede tener exactamente una reacción por publicación.
-- La clave primaria compuesta (id_usuario, id_publicacion) garantiza la unicidad.
CREATE TABLE IF NOT EXISTS reaccion (
    id_usuario INTEGER UNSIGNED NOT NULL,
    id_publicacion INTEGER UNSIGNED NOT NULL,
    tipo ENUM('LIKE','DISLIKE','LOVE','FUNNY','INTERESTING') NOT NULL,
    PRIMARY KEY (id_usuario, id_publicacion),
    CONSTRAINT fk_reaccion_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_reaccion_publicacion
        FOREIGN KEY (id_publicacion) REFERENCES publicacion(id) ON DELETE CASCADE ON UPDATE CASCADE
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
    ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_publicacion_montaje
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
    ON DELETE CASCADE ON UPDATE CASCADE;

-- Relaciones con fabricante
ALTER TABLE cpu
ADD CONSTRAINT fk_cpu_fabricante
    FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
    ON UPDATE CASCADE;

ALTER TABLE gpu
ADD CONSTRAINT fk_gpu_fabricante
    FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
    ON UPDATE CASCADE;

ALTER TABLE psu
ADD CONSTRAINT fk_psu_fabricante
    FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
    ON UPDATE CASCADE;

ALTER TABLE ram
ADD CONSTRAINT fk_ram_fabricante
    FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
    ON UPDATE CASCADE;

ALTER TABLE almacenamiento
ADD CONSTRAINT fk_almacenamiento_fabricante
    FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
    ON UPDATE CASCADE;

ALTER TABLE refrigeracion
ADD CONSTRAINT fk_refrigeracion_fabricante
    FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
    ON UPDATE CASCADE;

ALTER TABLE caja
ADD CONSTRAINT fk_caja_fabricante
    FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
    ON UPDATE CASCADE;

ALTER TABLE placa_base
ADD CONSTRAINT fk_placa_base_fabricante
    FOREIGN KEY (id_fabricante) REFERENCES fabricante(id)
    ON UPDATE CASCADE;

ALTER TABLE fabricante
ADD CONSTRAINT uq_fabricante_nombre
    UNIQUE (nombre);

-- Constraints unique
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

-- ################################################################
-- INSERTS DE PRUEBA
-- ################################################################

-- CPU
-- Fabricantes: AMD=5, Intel=27
-- cores JSON: Intel -> {"power_cores": N, "efficient_cores": N} | AMD -> {"cores": N, "ccd": N}
INSERT INTO cpu (modelo, id_fabricante, cpu_socket, cores, cache_apilada, arquitectura, precio, hilos, hyperthreading, frecuencia_max, frecuencia_min, cantidad_cache, tdp, temperatura_max, conectividad_pcie, graficos_integrados, puntuacion_passmark) VALUES
('Intel Core i9-13900K',  27, 'LGA1700', '{"power_cores": 8, "efficient_cores": 16}', FALSE, 'Raptor Lake', 599.99, 32, TRUE,  5.800, 3.000, 36,  125, 100, 20, 'Intel UHD Graphics 770', 59000),
('Intel Core i7-13700K',  27, 'LGA1700', '{"power_cores": 8, "efficient_cores": 8}',  FALSE, 'Raptor Lake', 409.99, 24, TRUE,  5.400, 3.400, 30,  125, 100, 20, 'Intel UHD Graphics 770', 47000),
('Intel Core i5-13600KF', 27, 'LGA1700', '{"power_cores": 6, "efficient_cores": 8}',  FALSE, 'Raptor Lake', 299.99, 20, TRUE,  5.100, 3.500, 24,  125, 100, 20, NULL,                    29000),
('AMD Ryzen 9 7950X',     5,  'AM5',     '{"cores": 16, "ccd": 2}',                    FALSE, 'Zen 4',       699.99, 32, TRUE,  5.700, 4.500, 64,  170, 95,  24, NULL,                    73000),
('AMD Ryzen 9 7900X3D',   5,  'AM5',     '{"cores": 12, "ccd": 2}',                    TRUE,  'Zen 4',       449.99, 24, TRUE,  5.600, 4.400, 128, 120, 89,  24, NULL,                    60000);

-- GPU
-- Fabricantes chip: Nvidia=38, AMD=5
-- nucleos JSON: Nvidia -> {"cuda": N, "tensor": N} | AMD -> {"unidades_computo": N}
INSERT INTO gpu (modelo, id_fabricante, ensambladora, nucleos, frecuencia_max, frecuencia_min, temperatura_max, cantidad_vram, tipo_vram, ancho_banda, arquitectura, tdp, conectividad_pcie, precio, generacion, alto_gpu, puntuacion_passmark) VALUES
('MSI GeForce RTX 4090 Gaming X Trio',         38, 'MSI',      '{"cuda": 16384, "tensor": 512}', 2.610, 2.235, 84,  24, 'GDDR6X', 1008, 'Ada Lovelace', 450, 4, 1899.99, 'RTX 40',   336, 38000),
('Asus TUF Gaming GeForce RTX 4070 Ti',        38, 'Asus',     '{"cuda": 7680, "tensor": 240}',  2.610, 2.310, 84,  12, 'GDDR6X', 504,  'Ada Lovelace', 285, 4,  849.99, 'RTX 40',   300, 27000),
('Gigabyte GeForce RTX 4060 Gaming OC',        38, 'Gigabyte', '{"cuda": 3072, "tensor": 96}',   2.505, 1.830, 87,  8,  'GDDR6',  272,  'Ada Lovelace', 115, 4,  329.99, 'RTX 40',   250, 14000),
('Sapphire NITRO+ Radeon RX 7900 XTX',         5,  'Sapphire', '{"unidades_computo": 96}',       2.615, 1.855, 110, 24, 'GDDR6',  960,  'RDNA 3',       355, 4,  999.99, 'RX 7000',  320, 25000),
('XFX Speedster MERC 310 Radeon RX 7800 XT',  5,  'XFX',      '{"unidades_computo": 60}',       2.565, 1.295, 110, 16, 'GDDR6',  624,  'RDNA 3',       263, 4,  499.99, 'RX 7000',  275, 18000);

-- PSU
INSERT INTO psu (modelo, id_fabricante, precio, modular, potencia, certificacion, factor_forma) VALUES
('Corsair RM1000x Shift',        14, 159.99, TRUE,  1000, '80 PLUS Gold',     'ATX'),
('Seasonic Focus GX-850',        49, 134.99, TRUE,  850,  '80 PLUS Gold',     'ATX'),
('Be quiet Pure Power 12 M 750W',11,  94.99, TRUE,  750,  '80 PLUS Gold',     'ATX'),
('Corsair RM750x',               14, 109.99, TRUE,  750,  '80 PLUS Gold',     'ATX');

-- RAM
INSERT INTO ram (modelo, id_fabricante, precio, velocidad, cantidad, modulos, capacidad_por_modulo, tipo, latencia) VALUES
('G.Skill Trident Z5 RGB DDR5-6000 32GB', 23, 149.99, 6000, 32, 2, 16, 'DDR5', 30),
('Corsair Vengeance DDR5-5600 32GB',      14, 129.99, 5600, 32, 2, 16, 'DDR5', 36),
('Kingston Fury Beast DDR4-3200 16GB',    28,  44.99, 3200, 16, 2,  8, 'DDR4', 16),
('Crucial Ballistix DDR4-3600 32GB',      16,  79.99, 3600, 32, 2, 16, 'DDR4', 18);

-- ALMACENAMIENTO
-- capacidad en TB: DECIMAL(4,3)
INSERT INTO almacenamiento (modelo, id_fabricante, precio, capacidad, tipo, formato, velocidad_lectura, velocidad_escritura, conectividad) VALUES
('Samsung 990 Pro 1TB',      45, 109.99, 1.000, 'NVMe SSD',  'M.2 2280', 7450, 6900, 'PCIe 4.0 x4'),
('WD Black SN850X 2TB',      56, 189.99, 2.000, 'NVMe SSD',  'M.2 2280', 7300, 6600, 'PCIe 4.0 x4'),
('Seagate Barracuda 2TB',    48,  54.99, 2.000, 'HDD',       '3.5',      220,  220, 'SATA III'),
('Samsung 870 EVO 1TB',      45,  79.99, 1.000, 'SATA SSD',  '2.5',      560,  530, 'SATA III');

-- REFRIGERACION
-- atributos JSON: aire -> {"alto": N, "numero_ventiladores": N} | liquida -> {"tamanio_radiador": N}
INSERT INTO refrigeracion (modelo, id_fabricante, precio, socket_compatible, tipo, atributos) VALUES
('DeepCool AK620',                  17,  69.99, 'LGA1700/AM5', 'aire',    '{"alto": 160, "numero_ventiladores": 2}'),
('Be quiet Dark Rock Pro 4',        11,  89.99, 'LGA1700/AM5', 'aire',    '{"alto": 163, "numero_ventiladores": 2}'),
('Corsair iCUE H150i Elite Capellix',14, 199.99, 'LGA1700/AM5', 'liquida', '{"tamanio_radiador": 360}'),
('Arctic Liquid Freezer II 360',     8, 109.99, 'LGA1700/AM5', 'liquida', '{"tamanio_radiador": 360}');

-- CAJA
INSERT INTO caja (modelo, id_fabricante, precio, formato, placas_base_compatibles, color, dimensiones, psu_compatible, longitud_max_gpu, bahias_25, bahias_35, espacio_ventiladores, ventiladores_incluidos, soportes_radiador, conectividad_frontal, rgb, altura_max_enfriador_cpu) VALUES
('NZXT H510 Flow',
 37, 89.99, 'Mid Tower', 'ATX', 'Negro',
 '{"alto": 460, "ancho": 210, "largo": 428}', 'ATX', 381, 2, 0,
 '{"superiores": 0, "laterales": 0, "frontal": 2, "trasero": 1, "inferior": 0}', TRUE,
 '{"superior": 280, "inferior": 0, "frontal": 280, "lateral": 0}',
 '{"USB_A": 1, "USB_C": 1, "audio": 1}', FALSE, 165),

('Fractal Design Meshify 2',
 20, 129.99, 'Mid Tower', 'ATX', 'Negro',
 '{"alto": 474, "ancho": 232, "largo": 474}', 'ATX', 460, 4, 3,
 '{"superiores": 3, "laterales": 0, "frontal": 3, "trasero": 1, "inferior": 0}', TRUE,
 '{"superior": 360, "inferior": 0, "frontal": 360, "lateral": 0}',
 '{"USB_A": 2, "USB_C": 1, "audio": 1}', FALSE, 185),

('Phanteks Eclipse P500A',
 41, 119.99, 'Mid Tower', 'ATX', 'Negro',
 '{"alto": 510, "ancho": 230, "largo": 480}', 'ATX', 435, 4, 2,
 '{"superiores": 3, "laterales": 0, "frontal": 3, "trasero": 1, "inferior": 0}', TRUE,
 '{"superior": 360, "inferior": 0, "frontal": 360, "lateral": 0}',
 '{"USB_A": 2, "USB_C": 1, "audio": 1}', FALSE, 185),

('Lian-Li Lancool III',
 29, 149.99, 'Mid Tower', 'ATX', 'Negro',
 '{"alto": 494, "ancho": 259, "largo": 501}', 'ATX', 435, 2, 2,
 '{"superiores": 3, "laterales": 3, "frontal": 3, "trasero": 1, "inferior": 0}', TRUE,
 '{"superior": 360, "inferior": 0, "frontal": 360, "lateral": 360}',
 '{"USB_A": 2, "USB_C": 1, "audio": 1}', FALSE, 190);

-- PLACA BASE
INSERT INTO placa_base (modelo, id_fabricante, precio, socket_compatible, chipset, memoria_maxima, espacios_ram, tipo_ram_soportada, formato, ranuras_expansion, ranuras_almacenamiento, puertos_usb, conectividad_interna, wifi_soportado) VALUES
('Asus ROG Strix Z790-E Gaming WiFi', 10, 499.99, 'LGA1700', 'Z790',  192, 4, 'DDR5', 'ATX', 5, 5, 10, '{"SATA": 6, "M2": 5, "USB_headers": 4}', 'WiFi 6E'),
('MSI MAG X670E Tomahawk WiFi',       34, 349.99, 'AM5',     'X670E', 128, 4, 'DDR5', 'ATX', 4, 4,  8, '{"SATA": 6, "M2": 4, "USB_headers": 3}', 'WiFi 6E'),
('Gigabyte Z790 Aorus Master',        24, 449.99, 'LGA1700', 'Z790',  192, 4, 'DDR5', 'ATX', 5, 5, 10, '{"SATA": 6, "M2": 5, "USB_headers": 4}', 'WiFi 6E'),
('Asrock B650E Taichi',                9, 299.99, 'AM5',     'B650E', 128, 4, 'DDR5', 'ATX', 4, 3,  8, '{"SATA": 4, "M2": 3, "USB_headers": 3}', 'WiFi 6E');

-- USUARIO
-- Contraseñas: admin -> "admin123" | resto -> "user1234"  (BCrypt strength=10)
INSERT INTO usuario (nombre, email, contraseña, rol) VALUES
('admin',      'admin@hardwarehub.com',  '$2a$10$tR1wy.Tbq5MmuwNxUPqA8.PCc4/S.qKj1pVzQygucHF3LcJrEtc1.', 'ROL_ADMIN'),
('juan_carlos','juancarlos@email.com',   '$2a$10$o2G8f49QS13IpIx1sHTnsumAhYwQAj.g09SPhgIRQZckjgK8RCQQ.', 'ROL_USUARIO'),
('maria_g',    'mariag@email.com',       '$2a$10$o2G8f49QS13IpIx1sHTnsumAhYwQAj.g09SPhgIRQZckjgK8RCQQ.', 'ROL_USUARIO'),
('tech_pete',  'techpete@email.com',     '$2a$10$o2G8f49QS13IpIx1sHTnsumAhYwQAj.g09SPhgIRQZckjgK8RCQQ.', 'ROL_USUARIO');

-- MONTAJE
-- Montaje 1: Intel top gaming  -> cpu LGA1700 + placa LGA1700/DDR5 + ram DDR5
-- Montaje 2: AMD workstation   -> cpu AM5    + placa AM5/DDR5    + ram DDR5
-- Montaje 3: Budget gaming     -> cpu LGA1700 + placa LGA1700/DDR5 + ram DDR5
INSERT INTO montaje (id_cpu, id_gpu, id_ram, id_placa_base, id_psu, id_refrigeracion, id_almacenamiento, id_caja, id_usuario) VALUES
(1, 1, 1, 1, 1, 3, 1, 1, 2),  -- Build gaming tope: i9-13900K + RTX 4090 (juan_carlos)
(4, 4, 2, 2, 2, 4, 2, 2, 3),  -- Workstation AMD: Ryzen 9 7950X + RX 7900 XTX (maria_g)
(3, 3, 1, 3, 3, 1, 4, 3, 4);  -- Budget gaming: i5-13600KF + RTX 4060 (tech_pete)

-- PUBLICACION
INSERT INTO publicacion (contenido_texto, multimedia, fecha, id_montaje, id_usuario) VALUES
('Mi nuevo build gaming tope de gama! i9-13900K + RTX 4090, va a todo sin problema.',          NULL, '2026-01-15 10:30:00', 1, 2),
('Workstation AMD para render 3D y compilacion. El Ryzen 9 7950X es una autentica bestia.',    NULL, '2026-01-20 14:00:00', 2, 3),
('Nuevo setup de streaming listo. Proximamente subo el video con el proceso de montaje.',      NULL, '2026-02-01 18:45:00', NULL, 2),
('Build de presupuesto para gaming 1080p. i5-13600KF + RTX 4060, muy contento con el resultado.', NULL, '2026-02-10 09:15:00', 3, 4);

-- COMENTARIO
-- Comentarios raiz: id_comentario = NULL, id_publicacion = N
-- Respuestas:       id_comentario = N (padre), id_publicacion = NULL
INSERT INTO comentario (texto_contenido, likes, fecha, id_usuario, id_comentario, id_publicacion) VALUES
('Que bestia de build! Cuanto has gastado en total?',               5, '2026-01-15 11:00:00', 3,    NULL, 1),
('Como va el rendimiento en juegos? Hay cuello de botella?',        3, '2026-01-15 12:30:00', 4,    NULL, 1),
('Ninguno! Va fluido al 100% en todos los titulos a 4K.',           2, '2026-01-15 13:15:00', 2,    2,    NULL),
('Increible setup para trabajar, envidia te tengo.',                7, '2026-01-21 10:00:00', 2,    NULL, 2),
('El i5-13600KF con la RTX 4060 para 1080p es una ganga total.',   4, '2026-02-11 08:00:00', 3,    NULL, 4);

-- USUARIO_SEGUIDOR
INSERT INTO usuario_seguidor (id_seguidor, id_seguido) VALUES
(2, 3),  -- juan_carlos sigue a maria_g
(2, 4),  -- juan_carlos sigue a tech_pete
(3, 2),  -- maria_g sigue a juan_carlos
(4, 2),  -- tech_pete sigue a juan_carlos
(4, 3);  -- tech_pete sigue a maria_g

-- REACCION
INSERT INTO reaccion (id_usuario, id_publicacion, tipo) VALUES
(3, 1, 'LOVE'),        -- maria_g ama el build gaming de juan_carlos
(4, 1, 'LIKE'),        -- tech_pete le da like al build de juan_carlos
(1, 1, 'INTERESTING'), -- admin lo encuentra interesante
(2, 2, 'LOVE'),        -- juan_carlos ama el workstation de maria_g
(4, 2, 'INTERESTING'), -- tech_pete lo encuentra interesante
(3, 4, 'LIKE'),        -- maria_g le da like al budget build
(2, 4, 'FUNNY');       -- juan_carlos lo encuentra gracioso

