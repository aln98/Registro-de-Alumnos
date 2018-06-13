<?php
    $con = mysqli_connect("localhost", "root", "", "averiguable");
    
    $dniAl = $_POST["DNI"];
    
    $statement = mysqli_prepare($con, "SELECT DNI FROM autorizados WHERE DNI = ?");
    mysqli_stmt_bind_param($statement, "i", $dniAl);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $dniAu);
    
    $response = array();

    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["DNI"] = $dniAu;
    }
    
    echo json_encode($response);
?>