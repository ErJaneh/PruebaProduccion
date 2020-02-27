echo create user registro identified by registro123; > temp.sql
echo grant dba to registro; >> temp.sql
echo exit >> temp.sql

sqlplus system/root @temp.sql 2
del temp.sql

sqlplus registro/registro123 @CreateTableU 
sqlplus registro/registro123 @SGII 

cd InsertsTablasU

sqlplus registro/registro123 @GTVZIPC_DIRECCIONES 
sqlplus registro/registro123 @REG_ADMIS_TIPO_IDENTIFICACION 
sqlplus registro/registro123 @REG_GENERAL_CARRERA
sqlplus registro/registro123 @REG_GENERAL_COLEGIO 
sqlplus registro/registro123 @REG_GENERAL_ESTADO_CIVIL  
sqlplus registro/registro123 @STVATTS_CONVENIOS 
sqlplus registro/registro123 @STVCOLL_ESCUELAS 
sqlplus registro/registro123 @STVNATN_PAISES 
sqlplus registro/registro123 @STVTERM_PERIODO 

cd ..

sqlplus registro/registro123 @InsertsSGII


