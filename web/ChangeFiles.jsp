<%@page import="com.portal.dao.FileDAO"%>
<%@page import="com.portal.dto.FileDTO"%>
<%@page import="java.util.ArrayList"%>

<%@page errorPage="errorpage.jsp" contentType="text/html"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Site Administration | Dashboard</title>
    <!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link href="css/custom.css" rel="stylesheet">
</head>
<body onload="">
    <%
        ArrayList<FileDTO> fileList = FileDAO.getAllFiles();
        String username = (String)session.getAttribute("username");
        if(username == null){
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
    %>
    <nav class="navbar navbar-default hidden-xs">
        <div class="navbar-header">
            <a class="navbar-brand" href="#" id="brand">Site Administration</a>
        </div>
        <div class="container-fluid" id="lg-header">
            <div class="nav navbar-right">
                <span class="text-uppercase">Welcome, <strong id="admin"><a href="#" class="link" data-target="#adminProfile" data-toggle="modal"><%= username %></a><strong>.</span>
                <a href="index.html" class="text-uppercase link">View Site</a> /
                <a href="Email.jsp" class="text-uppercase link">Send Mail</a> /  
                <a class="text-uppercase link"  data-target="#cngPassModal" data-toggle="modal">Change Password</a> /
                <a href="LoginControllerServlet?logout=logout" class="text-uppercase link">Logout</a>
            </div>
        </div>
    </nav>
    <nav class="navbar navbar-default navbar-fixed visible-xs">
        <div class="navbar-header">
            <a class="navbar-brand " href="#" id="brand">Site Administration</a>
            <button type="button" class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="index.html" class="link">View Site</a></li>
                <li><a href="Email.jsp" class="link">Send Mail</a></li>
                <li><a href="#" class="link" data-target="#cngPassModal" data-toggle="modal">Change Password</a></li>
                <li><a href="LoginControllerServlet?logout=logout" class="link">Logout</a></li>
            </ul>
        </div>
    </nav>
    
    <header id="header">
            <div class="row">
                <div class="col-md-10">
                    <h3 id="header-text"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> &nbsp;Dashboard  <small>Manage Your Site</small></h3>
                </div>
            </div>
    </header>

    <section id="breadcrumb">
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="AdminHome.jsp">Dashboard</a></li>
                <li class="active">Files</li>
            </ol>
        </div>
    </section>
        
                
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="container-fluid">
                    <div class="jumbotron addUserJumbo">
                        <% if(fileList.isEmpty()){
                            out.println("<h2>No Data Found</h2>");
                        }
                        else{
                            int count=0;
                            out.println("<div class=\"page-header\"><h2>File List&nbsp;&nbsp;<small>Click on FileID to Edit Details</small></h2></div>");
                            out.println("<div class=\"table-responsive\"><table class=\"table table-striped\">");
                            out.println("<thead><th>FileID</th><th>Filename</th><th>Description</th><th>File Type</th><th>Subject</th><th>Semester</th><th>Action</th></thead><tbody>");
                            for(FileDTO file : fileList){
                              String fileID = "fileID"+count; 
                              System.out.println(fileID);
                              out.println("<tr><td><a href=\"#\" class=link data-id=\""+count + "\"id=\""+fileID+ "\" onclick=\"fillFileDetailsModal(event)\">"+file.getFileId()+"</a></td><td>"+file.getFileName()+"</td><td>"+file.getDescription()+"</td><td>"+file.getFileType()+"</td><td>"+file.getSubject()+"</td><td>"+file.getSemester()+"</td><td><a href=\"#\" onclick=\"deleteFileDetails(event)\">Delete</a></td></tr>");
                              System.out.println(count);
                              count++;
                            }
                            out.println("</tbody></table></div>");
                        }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </div>    

    <!-- Edit faculty Modal -->
    <div class="modal" id="editFilesModal" tabindex="-1">
        <div class="modal-dialog modal-sm" >
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal" onclick="document.fileForm.reset()">&times;</button>
                    <h4 class="modal-title">Edit Faculty</h4>
                </div>
                <div class="modal-body">
                    <form action="" name="fileForm" id="fileForm">
                        <div class="form-group">
                            <label for="fileid">FileID</label>
                            <input class="form-control" type="text" id="fileid" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="filename">Filename</label>
                            <input class="form-control" type="text" id="filename" required/>
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>
                            <input class="form-control" type="text" id="description" required/>
                        </div>
                        <div class="form-group">
                            <label for="filetype">File Type</label>
                            <input class="form-control" type="text" id="filetype" required/>
                        </div>
                        <div class="form-group">
                            <label for="subject">Subject</label>
                            <input class="form-control" type="text" id="subject" required/>
                        </div>
                        <div class="form-group">
                            <label for="semester">Semester</label>
                            <input class="form-control" type="text" id="semester" required/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <span id="filesaveresult"></span>
                    <button class="btn btn-primary btn-sm" data-id="change" onclick="saveFile(event)">Save</button>
                    <button class="btn btn-primary btn-sm" data-dismiss="modal" onclick="document.fileForm.reset()">Close</button>
                </div>
            </div>
        </div>
    </div>
    <!-- End of edit faculty modal-->
    
    <!-- Change Password Modal -->
    <div class="modal" id="cngPassModal" tabindex="-1">
        <div class="modal-dialog modal-sm" >
            <div class="modal-content">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal" onclick="document.changePassForm.reset()">&times;</button>
                    <h4 class="modal-title">Change Password</h4>
                </div>
                <div class="modal-body">
                    <form action="" name="changePassForm">
                        <div class="form-group">
                            <label for="oldPass">Old Password</label>
                            <input class="form-control" type="password" id="oldPass" required/>
                        </div>
                        <div class="form-group">
                            <label for="newPass">New Password</label>
                            <input class="form-control" type="password" id="oldPass" required/>
                        </div>
                        <div class="form-group">
                            <label for="confPass">Confirm New Password</label>
                            <input class="form-control" type="password" id="confPass" required/>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary btn-sm" onclick="changeAdminPassword()">Change</button>
                    <button class="btn btn-primary btn-sm" data-dismiss="modal" onclick="document.changePassForm.reset()">Close</button>
                </div>
            </div>
        </div>
    </div>
    <!-- End of change password modal-->     

    
    
    <!-- Admin profile modal-->
    <div class="modal" id="adminProfile" tabindex="-1">
        <div class="modal-dialog modal-sm" >
            <div class="modal-content">
                <div class="modal-body ">
                    <div class="card">
                        <div class="row">
                            <div class="col-xs-12">
                                 <img class="card-img-top img-fluid" src="img/admin.png"  alt="Card image cap">
                            </div>
                        </div>
                        <div class="card-body">
                            <h4 class="card-title">Admin Profile</h4>
                            <form action="">
                                <div class="form-group card-text">
                                    <label for="name">Name</label>
                                    <input class="form-control" type="text" id="name" value="<%out.println(session.getAttribute("adminName"));%>" required/>
                                </div>
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input class="form-control" type="email" id="email" value="<%out.println(session.getAttribute("email"));%>" required/>
                                </div>
                                <div class="form-group">
                                    <label for="contact">Contact No</label>
                                    <input class="form-control" type="text" id="contact" value="<%out.println(session.getAttribute("contact"));%>" required/>
                                </div>
                                <div class="form-group">
                                    <label for="add">Address</label>
                                    <input class="form-control" type="text" id="add" value="<%out.println(session.getAttribute("address"));%>" required/>
                                </div>
                            </form> 
                      </div>
                  </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary btn-sm" onclick="saveAdminDetails()">Save</button>
                    <button class="btn btn-primary btn-sm" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
<!-- End of admin profile modal-->
    <script src="scripts/AdminHome.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</body>
</html>

