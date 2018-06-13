<?php
    $con = mysqli_connect("localhost", "root", "", "averiguable");
    
    $dni = $_POST["DNI"];
    $nom = $_POST["Nombre"];
    $ape = $_POST["Apellido"];
    $fec = $_POST["FechaNacimiento"];
    $dir = $_POST["Direccion"];
    $cur = $_POST["Curso"];
    $tel = $_POST["TelefonoContacto"];
    
    $statement = mysqli_prepare($con, "INSERT INTO alumnos (DNI, Nombre, Apellido, FechaNacimiento, Direccion, Curso, TelefonoContacto) VALUES (?, ?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "issssss", $dni, $nom, $ape, $fec, $dir, $cur, $tel);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>