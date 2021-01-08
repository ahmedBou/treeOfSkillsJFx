/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  1/8/2021 2:55:19 PM                      */
/*==============================================================*/


drop table if exists Apprenant;

drop table if exists Competence;

drop table if exists Niveau;

drop table if exists PromoApprenant;

drop table if exists Promotion;

drop table if exists Refereniel;

drop table if exists Staff;

/*==============================================================*/
/* Table : Apprenant                                            */
/*==============================================================*/
create table Apprenant
(
   idApprenant          int not null,
   nomApprenant         varchar(254),
   prenomApprenant      varchar(254),
   emailApprenant       varchar(254),
   telApprenant         varchar(254),
   villeApprenant       varchar(254),
   primary key (idApprenant)
);

/*==============================================================*/
/* Table : Competence                                           */
/*==============================================================*/
create table Competence
(
   idRef                int,
   idComp               int not null,
   idNiveau             int,
   titreComp            varchar(254),
   status               bool,
   primary key (idComp)
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
/* Table : PromoApprenant                                       */
/*==============================================================*/
create table PromoApprenant
(
   id_Promo             int,
   idApprenant          int
);

/*==============================================================*/
/* Table : Promotion                                            */
/*==============================================================*/
create table Promotion
(
   id_Promo             int not null,
   titre_Promo          varchar(254),
   annee_Promo          int,
   primary key (id_Promo)
);

/*==============================================================*/
/* Table : Refereniel                                           */
/*==============================================================*/
create table Refereniel
(
   id_Promo             int,
   idRef                int not null,
   titreRef             varchar(254),
   primary key (idRef)
);

/*==============================================================*/
/* Table : Staff                                                */
/*==============================================================*/
create table Staff
(
   id_Promo             int,
   idStaff              int not null,
   nomStaff             varchar(254),
   prenomStaff          varchar(254),
   specialiteStaff      varchar(254),
   nbrAppStaff          int,
   primary key (idStaff)
);

alter table Competence add constraint FK_Association_4 foreign key (idRef)
      references Refereniel (idRef) on delete restrict on update restrict;

alter table Competence add constraint FK_Association_5 foreign key (idNiveau)
      references Niveau (idNiveau) on delete restrict on update restrict;

alter table PromoApprenant add constraint FK_Association_1 foreign key (idApprenant)
      references Apprenant (idApprenant) on delete restrict on update restrict;

alter table PromoApprenant add constraint FK_PromoApprenant foreign key (id_Promo)
      references Promotion (id_Promo) on delete restrict on update restrict;

alter table Refereniel add constraint FK_Association_3 foreign key (id_Promo)
      references Promotion (id_Promo) on delete restrict on update restrict;

alter table Staff add constraint FK_Association_2 foreign key (id_Promo)
      references Promotion (id_Promo) on delete restrict on update restrict;

