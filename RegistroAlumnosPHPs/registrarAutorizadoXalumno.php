<?php
    $con = mysqli_connect("localhost", "root", "", "averiguable");
    
    $dniAl = $_POST["AlumnoDNI"];
    $dniAu = $_POST["AutorizadoDNI"];
    
    $statement = mysqli_prepare($con, "INSERT INTO autorizadosxalumnos (AlumnoDNI, AutorizadoDNI) VALUES (?, ?)");
    mysqli_stmt_bind_param($statement, "ii", $dniAl, $dniAu);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>