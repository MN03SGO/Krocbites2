---- CRUD_GALLETA --- 
* Readme nuevo el anterior le di en la nuca por ende no logro recuperlo y que hueva reescribir todo el readme : ) 

PD: $ git reflog
87d14d9 (HEAD -> master, origin/master, origin/HEAD) HEAD@{0}: clone: from https://github.com/MN03SGO/Krocbites2.git

anoni@DESKTOP-G5VR175 MINGW64 ~/Krocbites2 (master)
$

: (
* DATO INTERESANTE
  --Si queres insertar un user desde la SMSS en el caso que lo ocupes tenes que hacer lo siguiente
  -- INSERT INTO usuarios (
    nombre, apellido, documento, direccion, telefono,
    correo, tipo_usuario, usuario, contra
)
VALUES (
    'cipote', 'tenchis', '698945', 'SS', '555-5555',
    'peperecha@gmail.com', 'empleado', 'Empleado',
    CONVERT(VARCHAR(64), HASHBYTES('SHA2_256', '123'), 2)
);




* Arreglo del login y de crendeciales
  ---Implementacion del cifrado con SHA-256
  ---Intento de replicar el readme xD
     
   
