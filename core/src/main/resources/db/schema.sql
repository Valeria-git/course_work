drop table if exists phone2color cascade;
drop table if exists colors cascade;
drop table if exists stocks cascade;
drop table if exists phones cascade;
drop table if exists statuses cascade;
drop table if exists orders cascade;
drop table if exists phone2order cascade;

create table colors (
  id BIGSERIAL PRIMARY KEY,
  code VARCHAR(50),
  UNIQUE (code)
);

create table phones (
  id BIGSERIAL PRIMARY KEY,
  brand VARCHAR(50) NOT NULL,
  model VARCHAR(254) NOT NULL,
  price FLOAT,
  displaySizeInches FLOAT,
  weightGr SMALLINT,
  lengthMm FLOAT,
  widthMm FLOAT,
  heightMm FLOAT,
  announced timestamp without time zone,
  deviceType VARCHAR(50),
  os VARCHAR(100),
  displayResolution VARCHAR(50),
  pixelDensity SMALLINT,
  displayTechnology VARCHAR(50),
  backCameraMegapixels FLOAT,
  frontCameraMegapixels FLOAT,
  ramGb FLOAT,
  internalStorageGb FLOAT,
  batteryCapacityMah SMALLINT,
  talkTimeHours FLOAT,
  standByTimeHours FLOAT,
  bluetooth VARCHAR(50),
  positioning VARCHAR(100),
  imageUrl VARCHAR(254),
  description VARCHAR(4096),
  CONSTRAINT UC_phone UNIQUE (brand, model)
);

create table phone2color (
  phoneId BIGINT,
  colorId BIGINT,
  CONSTRAINT FK_phone2color_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT FK_phone2color_colorId FOREIGN KEY (colorId) REFERENCES colors (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table stocks (
  phoneId BIGINT NOT NULL,
  stock SMALLINT NOT NULL,
  reserved SMALLINT NOT NULL,
  UNIQUE (phoneId),
  CONSTRAINT FK_stocks_phoneId FOREIGN KEY (phoneId) REFERENCES phones (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table statuses(
  id SMALLSERIAL PRIMARY KEY,
  code VARCHAR(15) NOT NULL
);

create table orders (
  id BIGSERIAL PRIMARY KEY,
  subtotal FLOAT NOT NULL,
  deliveryPrice FLOAT NOT NULL,
  totalPrice FLOAT NOT NULL,
  firstName VARCHAR(20) NOT NULL,
  lastName VARCHAR(25) NOT NULL,
  deliveryAddress VARCHAR(50) NOT NULL,
  contactPhoneNo VARCHAR(15) NOT NULL,
  statusId SMALLINT,
  orderDate TIMESTAMP,
  CONSTRAINT FK_orders_statusId FOREIGN KEY (statusId) REFERENCES statuses (id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table phone2order (
  orderId BIGINT,
  phoneId BIGINT,
  quantity SMALLINT NOT NULL,
  CONSTRAINT FK_phone2order_orderId FOREIGN KEY (orderId) REFERENCES orders(id) ON UPDATE CASCADE,
  CONSTRAINT FK_phone2order_phoneId FOREIGN KEY (phoneId) REFERENCES phones(id) ON UPDATE CASCADE
);

