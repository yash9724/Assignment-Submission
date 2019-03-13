<%-- 
    Document   : FileUpload
    Created on : 10 Mar, 2019, 12:14:34 PM
    Author     : yash
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FileUpload</title>
    </head>
    <body>
        <form action="FileUploadServlet" method="post" enctype="multipart/form-data">  
            <pre>
    Select File:  <input type="file" name="filename"/><br/>  
    <input type="submit" value="Upload"/>  
        </form>
    </body>
</html>
