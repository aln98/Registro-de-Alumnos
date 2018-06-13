<?php
    $response = array();
    $result = array();

    $con = mysqli_connect("localhost", "root", "", "averiguable");

    $ape = $_POST["Apellido"];
    $dni = $_POST["DNI"];    
    
    $statement = mysqli_prepare($con, 
    "SELECT alumnos.DNI, alumnos.Nombre, alumnos.Apellido, alumnos.Curso  FROM autorizados
    INNER JOIN autorizadosxalumnos ON autorizados.DNI = autorizadosxalumnos.autorizadoDNI
    INNER JOIN alumnos ON autorizadosxalumnos.AlumnoDNI = alumnos.DNI
    WHERE autorizados.DNI = ? AND autorizados.Apellido = ?");
    mysqli_stmt_bind_param($statement, "is", $dni, $ape);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $DNI, $Nombre, $Apellido, $Curso);

    $response["success"] = true;
    $alumnos = array();
    $response["datos"] = array();

    $cont = 0;
    
while(mysqli_stmt_fetch($statement)){
        $alumnos[$cont] = array("DNI"=>$DNI,"Nombre"=>$Nombre, "Apellido"=>$Apellido, "Curso"=>$Curso);
        
        $cont = $cont + 1;

    }

    $response["datos"] = $alumnos;
   

    echo json_encode($response);
?>