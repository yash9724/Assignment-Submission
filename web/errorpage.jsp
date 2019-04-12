<%@page isErrorPage="true" contentType="text/html"%>
<!<html>
    <head>
        <title>Error</title>
    </head>
    <body>
        <% System.out.println(exception.getMessage());
           exception.printStackTrace();
        %>
            <center>
                <h2>Error occurred at server end.</h2>
                <h3>We are trying to fix this soon.</h3>
                <a href="AdminHome.jsp">Click Here</a> to Go Back to Admin Home.<br>
                To Go to Site Home <a href="index.html">Click Here</a>
            </center>
    </body>
</html>
