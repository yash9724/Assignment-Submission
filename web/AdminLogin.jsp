<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login | Site Admin</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link href="css/adminLoginCustom.css" rel="stylesheet">
</head>
<body class="admin-login" onload="document.adminloginform.reset()">
    
    <div class="modal-dialog text-center">
        <div class="col-xs-1 col-sm-3"></div>
        <div class="col-xs-10 col-sm-6 main-section">
            <div class="modal-content">
                <div class="col-12 user-img">
                    <img src="img/face.png" alt="">
                </div>
                <form  class="col-12" name="adminloginform" accept-charset="utf-8">
                    <div class="form-group">
                        <input type="text" class="form-control" id="username" placeholder="Enter Username" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="pwd" placeholder="Enter Password" required>
                    </div>
                    <span id="loginresult"></span>
                </form>
                    <button  class="btn btn-primary login-btn" data-id="adminlogin" id="loginbtn" onclick="validate()">Login</button>

                <div class="class-12 forgot">
                    <a href="">Forgot Password?</a>
                </div>
            <!-- </div> END of Modal-content -->
        </div>
        </div>
        <div class="col-xs-1 col-sm-6"></div>

        <script src="scripts/login.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>