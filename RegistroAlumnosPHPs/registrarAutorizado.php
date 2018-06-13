<?php
    $con = mysqli_connect("localhost", "root", "", "averiguable");
    
    $dni = $_POST["DNI"];
    $nom = $_POST["Nombre"];
    $ape = $_POST["Apellido"];
    $tel = $_POST["Telefono"];
    
    $statement = mysqli_prepare($con, "INSERT INTO autorizados (DNI, Nombre, Apellido, Telefono) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "isss", $dni, $nom, $ape, $tel);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>