<?php
    $response = array();
    $result = array();

    $con = mysqli_connect("localhost", "root", "", "averiguable");

    $nom = $_POST["Nombre"];
    $ape = $_POST["Apellido"];
    $cur = $_POST["Curso"];    
    
    $statement = mysqli_prepare($con, 
    "SELECT autorizados.*  FROM alumnos
    INNER JOIN autorizadosxalumnos ON alumnos.DNI = autorizadosxalumnos.AlumnoDNI
    INNER JOIN autorizados ON autorizadosxalumnos.AutorizadoDNI = autorizados.DNI
    WHERE alumnos.nombre = ? AND alumnos.Curso = ? AND alumnos.Apellido = ?");
    mysqli_stmt_bind_param($statement, "sss", $nom, $cur, $ape);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $DNI, $Nombre, $Apellido, $Telefono);

    $response["success"] = true;
    $tutores = array();
    $response["datos"] = array();

    $cont = 0;
    
while(mysqli_stmt_fetch($statement)){
        $tutores[$cont] = array("DNI"=>$DNI,"Nombre"=>$Nombre, "Apellido"=>$Apellido, "Telefono"=>$Telefono);
        
        $cont = $cont + 1;

    }

    $response["datos"] = $tutores;
       
    echo json_encode($response);
?>