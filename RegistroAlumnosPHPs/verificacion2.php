<?php
$con = mysqli_connect("localhost", "root", "", "averiguable");

$nomAl = $_POST["Nombre"];
$apeAl = $_POST["Apellido"];
$curAl = $_POST["Curso"];

$statement = mysqli_prepare($con, "SELECT DNI FROM alumnos WHERE Nombre = ? AND Apellido = ? AND Curso= ?");
mysqli_stmt_bind_param($statement, "sss", $nomAl, $apeAl, $curAl);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $dniAl);

$response = array();

$response["success"] = false;  

while(mysqli_stmt_fetch($statement)){
    $response["success"] = true;
    $response["DNI"] = $dniAl;
}

echo json_encode($response);
?>