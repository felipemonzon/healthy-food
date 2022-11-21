CREATE TABLE menu (
    idmenu VARCHAR(20) NOT NULL,
    title VARCHAR(50) NOT NULL,
    path VARCHAR(50) NOT NULL,
    icon VARCHAR(100) NOT NULL,
    active bool NULL DEFAULT true,
    created_by varchar(250) NOT NULL,
    created_date timestamp NOT NULL,
    updated_by varchar(255) NOT NULL,
    updated_date timestamp NOT NULL,
    CONSTRAINT PK_MENU PRIMARY KEY (idmenu,title)
);

INSERT INTO menu(idmenu, title, path, icon, active, created_by, created_date, updated_by, updated_date) VALUES
('catalogs','Proveedores','suppliers','/assets/icons/supplies.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('catalogs','Usuarios','users','/assets/icons/user.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('catalogs','Sucursales','offices','/assets/icons/sucursal.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('catalogs','Direcciones','offices','/assets/icons/addressbook.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('alimentos','Ingredientes','ingredient','/assets/icons/vegetales.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('alimentos','Platillos','foods','/assets/icons/comiendo.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('alimentos','Agendar Platillo','scheduleFood','/assets/icons/calendario.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('configurations','Parametros','parameters','/assets/icons/engineering.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('configurations','Colonias','suburbs','/assets/icons/colonies.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('configurations','Unidad de Medida','units','/assets/icons/balanceFood.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('reports','Reporte de Costos','foodCostReport','/assets/icons/foodReport.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('reports','Reporte de Entregas Diarias','deliveryReport','/assets/icons/foodDelivery.png', 1, 'ADMIN', NOW(), 'ADMIN', NOW());
