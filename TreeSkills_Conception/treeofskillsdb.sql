/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  1/6/2021 3:59:31 PM                      */
/*==============================================================*/


drop trigger tib_promotion;

drop table if exists Apprenant;

drop table if exists Competence;

drop table if exists Niveau;

drop table if exists Promotion;

drop table if exists Refereniel;

drop table if exists Staff;

drop table if exists lier;

/*==============================================================*/
/* Table : Apprenant                                            */
/*==============================================================*/
create table Apprenant
(
   idApp                int not null,
   nomApp               varchar(254),
   prenomApp            varchar(254),
   emailApp             varchar(254),
   telApp               varchar(254),
   villeApp             varchar(254),
   primary key (idApp)
);

/*==============================================================*/
/* Table : Competence                                           */
/*==============================================================*/
create table Competence
(
   idRef                int not null,
   idComp               int not null,
   titreComp            varchar(254),
   primary key (idRef, idComp)
);

/*==============================================================*/
/* Table : Niveau                                               */
/*==============================================================*/
create table Niveau
(
   idNiveau             int not null,
   titreNiveau          varchar(254),
   primary key (idNiveau)
);

/*==============================================================*/
/* Table : Promotion                                            */
/*==============================================================*/
create table Promotion
(
   idPromo              int not null,
   idStaff              int not null,
   idApp                int not null,
   titrePromo           varchar(254),
   anneePromo           int,
   primary key (idPromo)
);

/*==============================================================*/
/* Table : Refereniel                                           */
/*==============================================================*/
create table Refereniel
(
   idRef                int not null,
   idPromo              int not null,
   titreRef             varchar(254),
   primary key (idRef)
);

/*==============================================================*/
/* Table : Staff                                                */
/*==============================================================*/
create table Staff
(
   idStaff              int not null,
   nomStaff             varchar(254),
   prenomStaff          varchar(254),
   specialiteStaff      varchar(254),
   nbrAppStaff          int,
   primary key (idStaff)
);

/*==============================================================*/
/* Table : lier                                                 */
/*==============================================================*/
create table lier
(
   idRef                int not null,
   idComp               int not null,
   idNiveau             int not null,
   primary key (idRef, idComp, idNiveau)
);

alter table Competence add constraint FK_possede foreign key (idRef)
      references Refereniel (idRef) on delete restrict on update restrict;

alter table Promotion add constraint FK_Contient2 foreign key (idApp)
      references Apprenant (idApp) on delete restrict on update restrict;

alter table Promotion add constraint FK_contient1 foreign key (idStaff)
      references Staff (idStaff) on delete restrict on update restrict;

alter table Refereniel add constraint FK_contient3 foreign key (idPromo)
      references Promotion (idPromo) on delete restrict on update restrict;

alter table lier add constraint FK_lier foreign key (idRef, idComp)
      references Competence (idRef, idComp) on delete restrict on update restrict;

alter table lier add constraint FK_lier foreign key (idNiveau)
      references Niveau (idNiveau) on delete restrict on update restrict;


create trigger tib_promotion before insert
on Promotion for each row
begin
end;

