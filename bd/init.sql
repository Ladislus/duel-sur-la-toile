DROP DATABASE IF EXISTS serveurDeJeux;
CREATE DATABASE serveurDeJeux DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE serveurDeJeux;
CREATE TABLE ROLE (
  nomRole VARCHAR(10) NOT NULL,
  PRIMARY KEY (nomRole)
);

CREATE TABLE UTILISATEUR (
  idUt SMALLINT NOT NULL AUTO_INCREMENT,
  pseudoUt VARCHAR(30) UNIQUE NOT NULL,
  emailUt VARCHAR(100) NOT NULL,
  activeUt BOOLEAN NOT NULL,
  nomRole VARCHAR(10) NOT NULL,
  hash VARCHAR(100) NOT NULL,
  salt VARCHAR(100) NOT NULL,
  image BLOB,
  PRIMARY KEY (idUt)
);


CREATE TABLE ETREAMI (
  idUt SMALLINT NOT NULL,
  idUt1 SMALLINT NOT NULL,
  PRIMARY KEY (idUt, idUt1)
);

CREATE TABLE MESSAGE (
  idMsg SMALLINT NOT NULL AUTO_INCREMENT,
  dateMsg DATETIME NOT NULL,
  contenuMsg TEXT,
  luMsg CHAR(1) NOT NULL,
  idUt SMALLINT NOT NULL,
  idUt1 SMALLINT NOT NULL,
  PRIMARY KEY (idMsg)
);


CREATE TABLE INVITATION (
  idInv SMALLINT NOT NULL AUTO_INCREMENT,
  dateInv DATETIME NOT NULL,
  etatInv CHAR(1) NOT NULL,
  idUt SMALLINT NOT NULL,
  idUt1 SMALLINT NOT NULL,
  PRIMARY KEY (idInv)
);

CREATE TABLE PARTIE (
  idPa SMALLINT NOT NULL AUTO_INCREMENT,
  debutPa DATETIME NOT NULL,
  numEtape CHAR(1) NOT NULL,
  etatPartie TEXT NOT NULL,
  idJeu SMALLINT NOT NULL,
  idUt1 SMALLINT NOT NULL,
  score1 VARCHAR(42) NOT NULL,
  idUt2 SMALLINT NOT NULL,
  score2 VARCHAR(42) NOT NULL,
  PRIMARY KEY (idPa)
);

CREATE TABLE JEU (
  idJeu SMALLINT NOT NULL AUTO_INCREMENT,
  nomJeu VARCHAR(20) NOT NULL,
  regleJeu TEXT,
  jarJeu BLOB NOT NULL,
  activeJeu CHAR(1) NOT NULL,
  idTy SMALLINT NOT NULL,
  image BLOB,
  PRIMARY KEY (idJeu)
);

CREATE TABLE TYPEJEU (
  idTy SMALLINT NOT NULL AUTO_INCREMENT,
  nomTy VARCHAR(20) NOT NULL,
  PRIMARY KEY (idTy)
);

ALTER TABLE UTILISATEUR ADD FOREIGN KEY (nomRole) REFERENCES ROLE (nomRole);
ALTER TABLE ETREAMI ADD FOREIGN KEY (idUt1) REFERENCES UTILISATEUR (idUt);
ALTER TABLE ETREAMI ADD FOREIGN KEY (idUt) REFERENCES UTILISATEUR (idUt);
ALTER TABLE MESSAGE ADD FOREIGN KEY (idUt1) REFERENCES UTILISATEUR (idUt);
ALTER TABLE MESSAGE ADD FOREIGN KEY (idUt) REFERENCES UTILISATEUR (idUt);
ALTER TABLE PARTIE ADD FOREIGN KEY (idUt1) REFERENCES UTILISATEUR (idUt);
ALTER TABLE PARTIE ADD FOREIGN KEY (idUt2) REFERENCES UTILISATEUR (idUt);
ALTER TABLE PARTIE ADD FOREIGN KEY (idJeu) REFERENCES JEU (idJeu);
ALTER TABLE INVITATION ADD FOREIGN KEY (idUt1) REFERENCES UTILISATEUR (idUt);
ALTER TABLE INVITATION ADD FOREIGN KEY (idUt) REFERENCES UTILISATEUR (idUt);
ALTER TABLE JEU ADD FOREIGN KEY (idTy) REFERENCES TYPEJEU (idTy);

