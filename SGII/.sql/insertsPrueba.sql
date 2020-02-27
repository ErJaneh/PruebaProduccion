SET DEFINE OFF;

-- Inserts Promociones
--insert into DR_SGII_PROMOCION (CODIGO_PROMOCION,NUMERO_PROMOCION,DESCRIPCION_PROMOCION,CODIGO_GRUPO_DE_INTERES,CODIGO_CARRERA,CODIGO_PERIODO,CODIGO_UNIDAD_ACADEMICA,CUPOS,FECHA_INICIO_PROMOCION,FECHA_FIN_PROMOCION,ESTADO_PROMOCION)
--values('CINA-202000-BA-INFORM',1,'Promocion Test','SAB','BA-INFORM', '999996','RC', 10, sysdate, sysdate, 'Disponible para inscripcion'); 

--insert into DR_SGII_PROMOCION (CODIGO_PROMOCION,NUMERO_PROMOCION,DESCRIPCION_PROMOCION,CODIGO_GRUPO_DE_INTERES,CODIGO_CARRERA,CODIGO_PERIODO,CODIGO_UNIDAD_ACADEMICA,CUPOS,FECHA_INICIO_PROMOCION,FECHA_FIN_PROMOCION,ESTADO_PROMOCION)
--values('CINA-202000-DI-INFORM',1,'Promocion Test','SAB','DI-INFORM', '999996','RC', 10, sysdate, sysdate, 'Disponible para inscripcion');

-- insert into DR_SGII_PROMOCION (CODIGO_PROMOCION,NUMERO_PROMOCION,DESCRIPCION_PROMOCION,CODIGO_GRUPO_DE_INTERES,CODIGO_CARRERA,CODIGO_PERIODO,CODIGO_UNIDAD_ACADEMICA,CUPOS,FECHA_INICIO_PROMOCION,FECHA_FIN_PROMOCION,ESTADO_PROMOCION)
-- values('CINA-202000-MA-TCINED',1,'Promocion Test','SAB','MA-TCINED', '999996','RC', 10, sysdate, sysdate, 'Disponible para inscripcion');

-- insert into DR_SGII_PROMOCION (CODIGO_PROMOCION,NUMERO_PROMOCION,DESCRIPCION_PROMOCION,CODIGO_GRUPO_DE_INTERES,CODIGO_CARRERA,CODIGO_PERIODO,CODIGO_UNIDAD_ACADEMICA,CUPOS,FECHA_INICIO_PROMOCION,FECHA_FIN_PROMOCION,ESTADO_PROMOCION)
-- values('CINA-202000-MA-ADTEIN',1,'Promocion Test','SAB','MA-ADTEIN', '999996','RC', 10, sysdate, sysdate, 'Disponible para inscripcion');

-- insert into DR_SGII_PROMOCION (CODIGO_PROMOCION,NUMERO_PROMOCION,DESCRIPCION_PROMOCION,CODIGO_GRUPO_DE_INTERES,CODIGO_CARRERA,CODIGO_PERIODO,CODIGO_UNIDAD_ACADEMICA,CUPOS,FECHA_INICIO_PROMOCION,FECHA_FIN_PROMOCION,ESTADO_PROMOCION)
-- values('SAB-202900-TG-ARTCOM',2,'Promocion Test Arte','SAB','TG-ARTCOM', '999996','CV', 10, TO_DATE('01/01/19','DD/MM/RR'), TO_DATE('31/12/19','DD/MM/RR'), 'Disponible para inscripcion');


-- Inserts Responsables Promocion
-- INSERT INTO DR_SGII_RESPONSABLES_PROMOCION (INDEX_RESPONSABLES_PROMOCION,NUMERO_PROMOCION,CORREO,DESCRIPCION) 
-- VALUES (index_responsables_promocion.NEXTVAL,1,'test@info.com','test info');

-- INSERT INTO DR_SGII_RESPONSABLES_PROMOCION (INDEX_RESPONSABLES_PROMOCION,NUMERO_PROMOCION,CORREO,DESCRIPCION) 
-- VALUES (index_responsables_promocion.NEXTVAL,1,'test2@info.com','test 2 info');

-- INSERT INTO DR_SGII_RESPONSABLES_PROMOCION (INDEX_RESPONSABLES_PROMOCION,NUMERO_PROMOCION,CORREO,DESCRIPCION) 
-- VALUES (index_responsables_promocion.NEXTVAL,2,'test@arte.com','test arte'); 

-- Inserts Grupo indigena
insert into REGISTRO.DR_SGII_GRUPO_INDIGENA(CODIGO,DESCRIPCION) values('G01','MALEKU');
insert into REGISTRO.DR_SGII_GRUPO_INDIGENA(CODIGO,DESCRIPCION) values('G02','BRIBRI');
insert into REGISTRO.DR_SGII_GRUPO_INDIGENA(CODIGO,DESCRIPCION) values('G03','CAB�CAR');
insert into REGISTRO.DR_SGII_GRUPO_INDIGENA(CODIGO,DESCRIPCION) values('G04','BRUNCA/BORUCA');
insert into REGISTRO.DR_SGII_GRUPO_INDIGENA(CODIGO,DESCRIPCION) values('G05','T�RRABA/TERIBE');
insert into REGISTRO.DR_SGII_GRUPO_INDIGENA(CODIGO,DESCRIPCION) values('G06','CHOROTEGA');
insert into REGISTRO.DR_SGII_GRUPO_INDIGENA(CODIGO,DESCRIPCION) values('G07','HUETAR');
insert into REGISTRO.DR_SGII_GRUPO_INDIGENA(CODIGO,DESCRIPCION) values('G08','NG�BE');

-- Inserts Territorio indigena
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T01','MATAMB�');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T02','GUATUSO');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T03','HUETAR DE QUITIRRIS�');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T04','HUETAR DE ZAPAT�N');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T05','CAB�CAR DE NAIRI-AWARI');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T06','CAB�CAR DE CHIRRIP�(Duchii)');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T07','CAB�CAR DE BAJO CHIRRIP�');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T08','CAB�CAR DE TAYNI');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T09','BRIBRI DE KEK�LDI(Cocles)');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T10','CAB�CAR DE TELIRE');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T11','CAB�CAR DE TALAMANCA');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T12','BRIBRI DE TALAMANCA');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T13','BRBRI DE CABAGRA');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T14','BRIBRI DE SALITRE');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T15','CAB�CAR DE UJARR�S');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T16','CHINA KICHA');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T17','BRUNKA DE BORUCA');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T18','BRUNKA DE CURR�(Rey Curr�');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T19','GUAYM� DE COTO BRUS');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T20','GUAYM� DE ABROJOS-MONTEZUMA');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T21','GUAYM� DE ALTOS DE SAN ANTONIO');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T22','GUAYM� DE CONTEBURICA');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T23','GUAYM� DE OSA');
insert into REGISTRO.DR_SGII_TERRITORIO_INDIGENA(CODIGO,DESCRIPCION) values('T24','FUERA DE TERRITORIO');


--Inserts personas
-- insert into DR_SGII_PERSONA (IDENTIFICACION,CODIGO_TIPO_IDENTIFICACION,NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,CONOCIDO_COMO,FECHA_NACIMIENTO,CODIGO_COLEGIO,ANIO_GRADUACION_COLEGIO,NOTA_COLEGIO,INDIGENA,CODIGO_TERRITORIO,CODIGO_GRUPO_INDIGENA, TELEFONO, CORREO_ELECTRONICO, ESTADO_EN_SISTEMA) 
-- values('117130144', '1', 'Gabriel', 'Araya', 'Ruiz', 'N/A', TO_DATE('01/07/98','DD/MM/RR'), '1080301', 2016, 87, 'N', NULL, NULL, 60531785, 'araya.gabriel98@gmail.com', 'A' );

-- insert into DR_SGII_PERSONA (IDENTIFICACION,CODIGO_TIPO_IDENTIFICACION, NOMBRE, PRIMER_APELLIDO,SEGUNDO_APELLIDO,CONOCIDO_COMO,FECHA_NACIMIENTO,CODIGO_COLEGIO,ANIO_GRADUACION_COLEGIO,NOTA_COLEGIO,INDIGENA,CODIGO_TERRITORIO,CODIGO_GRUPO_INDIGENA, TELEFONO, CORREO_ELECTRONICO, ESTADO_EN_SISTEMA)
-- values('117140986','1','Janel','Garces','Castillo','JGC',TO_DATE('20/06/98','DD/MM/RR'),'2010802', 2015, 80, 'N', NULL, NULL, 60150040, 'janel@gmail.com', 'A');

-- insert into DR_SGII_PERSONA (IDENTIFICACION,CODIGO_TIPO_IDENTIFICACION, NOMBRE, PRIMER_APELLIDO,SEGUNDO_APELLIDO,CONOCIDO_COMO,FECHA_NACIMIENTO,CODIGO_COLEGIO,ANIO_GRADUACION_COLEGIO,NOTA_COLEGIO,INDIGENA,CODIGO_TERRITORIO,CODIGO_GRUPO_INDIGENA, TELEFONO, CORREO_ELECTRONICO, ESTADO_EN_SISTEMA)
-- values('546872210','1','Arelis','Porras','Torres','N/A',TO_DATE('02/05/98','DD/MM/RR'),'2010802', 2014, 84, 'S', 'T01', 'G01', 80569584, 'arelis.pig@gmail.com', 'A');

--Inserts solicitudes
-- insert into DR_SGII_SOLICITUD(IDENTIFICACION_SOLICITUD,IDENTIFICACION_PERSONA,GENERO,ESTADO_SOLICITUD_1,ESTADO_SOLICITUD_2,CODIGO_UBICACION,DIRECCION_EXACTA,CODIGO_ESTADO_CIVIL,ADECUACION,CODIGO_NACIONALIDAD,OBSERVACIONES,CODIGO_PROMOCION_1,CODIGO_PROMOCION_2,NUMERO_PROMOCION, )
-- values ('CINA-202000-BA-INFORM-1-0','546872210', 'Mujer', 1, 1, '30503', 'Direcci�n test', 'S', 'S', '840', null, 'CINA-202000-BA-INFORM', 'CINA-202000-DI-INFORM', 1 );

-- insert into DR_SGII_SOLICITUD(IDENTIFICACION_SOLICITUD,IDENTIFICACION_PERSONA,GENERO,ESTADO_SOLICITUD_1,ESTADO_SOLICITUD_2,CODIGO_UBICACION,DIRECCION_EXACTA,CODIGO_ESTADO_CIVIL,ADECUACION,CODIGO_NACIONALIDAD,OBSERVACIONES,CODIGO_PROMOCION_1,CODIGO_PROMOCION_2,NUMERO_PROMOCION)
-- values ('CINA-202000-BA-INFORM-1-1','117140986', 'Hombre', 1, 1, '30503', 'Direcci�n test 2', 'S', 'S', '840', null, 'CINA-202000-BA-INFORM', 'CINA-202000-DI-INFORM', 1 );

-- insert into DR_SGII_SOLICITUD(IDENTIFICACION_SOLICITUD,IDENTIFICACION_PERSONA,GENERO,ESTADO_SOLICITUD_1,ESTADO_SOLICITUD_2,CODIGO_UBICACION,DIRECCION_EXACTA,CODIGO_ESTADO_CIVIL,ADECUACION,CODIGO_NACIONALIDAD,OBSERVACIONES,CODIGO_PROMOCION_1,CODIGO_PROMOCION_2,NUMERO_PROMOCION)
-- values ('SAB-202900-TG-ARTCOM-1-0','117130144', 'Hombre', 1, null, '30503', 'Direcci�n test 3', 'S', 'S', '840', null, 'SAB-202900-TG-ARTCOM', NULL, 2 );

exit

