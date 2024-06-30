<?php 

include './config.php';

$STORAGE_PATH = "storage";

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    $query = 'SELECT * FROM files;';
    $result = $connection->execute_query($query);

    $data = [];

    if (!$result) {
        http_response_code(500);
        exit;
    }

    while($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) {
        if (file_exists($row['path'])) {
            $row["path"] = stripslashes($row["path"]);
            $data[] = $row;
        }
    }

    echo json_encode([
        'files' => $data
    ], JSON_UNESCAPED_SLASHES);
}
else if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nama_file = $_FILES['foto']['name'];
    $temp_file = $_FILES['foto']['tmp_name'];
    
    $path_destination = "{$STORAGE_PATH}/{$nama_file}";

    $query = 'SELECT id FROM files WHERE file_name=?;';
    $result = $connection->execute_query($query, [$nama_file]);
    
    
    if ($result === true || ($result instanceof mysqli_result && $result->num_rows > 0)) {
        http_response_code(409);
        exit();
    }

    if (!move_uploaded_file($temp_file, $path_destination)) {
        http_response_code(400);
        exit();
    }

    
    $query = 'INSERT INTO files (file_name, path) VALUES(?, ?)';
    $result = $connection->execute_query($query, [$nama_file, $path_destination]);

    if (!$result) {
        http_response_code(500);
        exit();
    }

    http_response_code(200);
}

?>